package org.calminfotech.user.controllers;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.boInterface.UserLoginSessionBo;
import org.calminfotech.user.boInterface.UserSecurityQuestionsBo;
import org.calminfotech.user.forms.ForgotPasswordForm;
import org.calminfotech.user.forms.UserForm;
import org.calminfotech.user.forms.UserQuestionForm;
import org.calminfotech.user.models.Permission;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserActivation;
import org.calminfotech.user.models.UserLoginSession;
import org.calminfotech.user.models.UserSecurityQuestions;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.AppConfig;
import org.calminfotech.utils.Encryptor;
import org.calminfotech.utils.NotificationCentre;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.dao.InternetConnection;
import org.calminfotech.utils.email.EmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Layout(value = "layouts/login")
@RequestMapping(value = "/user/session")
public class SessionController {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Alert alert;

	@Autowired
	private UserBo userBo;
	
	/*@Autowired
	private RoleBo roleBo;
*/
	@Autowired
	private UserSecurityQuestionsBo userSecurityQuestionsBo;

	@Autowired
	private UserLoginSessionBo userLoginSessionBo;

	@Autowired
	private Encryptor encryptor;

	@Autowired
	private NotificationCentre notificationCentre;

	// Display Form
	@RequestMapping(value = { "/login", "/" }, method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("userForm", new UserForm());
		model.addAttribute("forgotPassword", new ForgotPasswordForm());
		return "/user/session/login";
	}

	// Process the login data
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String authenticate(
			@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result, final RedirectAttributes redirectAttributes,
			HttpServletRequest httpRequest) {

		// If Form contains
		if (result.hasErrors()) {
			return "/user/session/login";
		}

		// Check if user exist.
		List<User> list = userBo.checkUserCredentialsByEmailAndPassword(
				userForm.getEmail(), userForm.getPassword());
		System.out.println("List ===== "+list.size());
		for(User p : list){
			System.out.println("====View ===="+p.getUserId()+" | "+p.getUsername());
		}
		// If the list is greater or less than 1. Should throw error.
		if (list.size() != 1) {
			// report incorrect user name
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid username or password. Try again or register!");
			return "redirect:/user/session/login";
		}

		User user = list.get(0);
		if (user.getStatus() && !user.getLock()) {

			HttpSession session = httpRequest.getSession();
			// Set user Id in session for question
			session.setAttribute("who", list.get(0).getUserId());

			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! So We know you. But not completely. So...");

			return "redirect:/user/session/question";
		} else {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Your account is locked. Please contact the admin");
			return "redirect:/user/session/login";
		}
	}

	// Display security question
	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public String question(Model model, HttpServletRequest httpRequest,
			final RedirectAttributes redirectAttributes) {

		// Get the userId from the session
		HttpSession session = httpRequest.getSession();
		
		if (session.getAttribute("who") == null || session.getAttribute("who") == "" ) {
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"Warning, Are you trying to hack us? Stop that...");
			return "redirect:/user/session/login";
		}

		int userSessionId = (Integer) session.getAttribute("who");
		Random rand = new Random();
		UserSecurityQuestions userSecurityQuestions = userSecurityQuestionsBo
				.getUserSecurityQuestionsByUserId(userSessionId);

		// If Security Question are missing due to incomplete registration
		// Think of a way to send user an email link to set security questions
		if (userSecurityQuestions == null) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Application Error. Can't find your question sets. Please contact Admin...");
			return "redirect:/user/session/login";
		}

		int randomNumber = rand.nextInt(3) + 1;
		UserQuestionForm userQuestionForm = new UserQuestionForm();
		userQuestionForm.setQuestionNum(randomNumber);

		switch (randomNumber) {
		case 1:
			userQuestionForm.setQuestion(userSecurityQuestions.getQuestionOne()
					.getQuestionText());
			break;
		case 2:
			userQuestionForm.setQuestion(userSecurityQuestions.getQuestionTwo()
					.getQuestionText());
			break;
		case 3:
			userQuestionForm.setQuestion(userSecurityQuestions
					.getQuestionThree().getQuestionText());
			break;
		case 4:
			userQuestionForm.setQuestion(userSecurityQuestions
					.getQuestionFour().getQuestionText());
			break;
		}

		model.addAttribute("userQuestionForm", userQuestionForm);
		return "/user/session/question";
	}

	// Process security answer
	@RequestMapping(value = "/answer", method = RequestMethod.POST)
	public String answer(
			@Valid @ModelAttribute("userQuestionForm") UserQuestionForm userQuestionForm,
			BindingResult result, final RedirectAttributes redirectAttributes,
			HttpServletRequest httpRequest) {

		if (result.hasErrors()) {
			return "/user/session/question";
		}

		// Get the userId from the session
		HttpSession session = httpRequest.getSession();
		if (session.getAttribute("who") == null) {
			alert.setAlert(redirectAttributes, Alert.WARNING,
					"Warning, Are you trying to hack us? Stop that...");
			return "redirect:/user/session/login";
		}

		int userSessionId = (Integer) session.getAttribute("who");
		UserSecurityQuestions userSecurityQuestions = userSecurityQuestionsBo
				.getUserSecurityQuestionsByUserId(userSessionId);

		// If Security Question are missing at this point. Hacker is detected
		if (userSecurityQuestions == null) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Nice Hack!... But sorry...");
			return "redirect:/user/session/login";
		}

		int randomNumber = userQuestionForm.getQuestionNum();
		boolean correct = this.compare(userSecurityQuestions, userQuestionForm,
				randomNumber, userSessionId);
		if (correct) {
			// alert.setAlert(redirectAttributes, Alert.SUCCESS,
			// "Success. You are now logged in.");
			String callbackUrl = (String) session.getAttribute("callbackUrl");
			session.setAttribute("login", true);
			// Get and store user items here
			User user = userBo.getUserById(userSessionId);
			userIdentity.setUserId(user.getUserId());
			userIdentity.setUsername(user.getUsername());
			userIdentity.setUserProfile(user.getUserProfile());
			userIdentity.setIdentity(true);
			userIdentity.setRole(user.getRole());
			userIdentity.setOrganisation(user.getOrganisation());
			//set permission to list
			userIdentity.setuList(user.getUserPermission());
			userIdentity.setpList(userIdentity.getRole().getPermission());
			//print out list value
			System.out.println("Organisation :"+userIdentity.getOrganisation().getDescription());
			System.out.println("Username :"+userIdentity.getUsername());
			System.out.println("User Id :"+userIdentity.getUserId());
			Permission upList = userIdentity.getUserPermission();
			Set<Permission> pList = userIdentity.getRole().getPermission();
			for(Permission p : pList ){
				System.out.println("Kunle check Permission for "+p.getPermissionId()+" is = "+userIdentity.renderLink(p.getPermissionId()));	
			}
			//System.out.println("User level Permission :"+upList.getPermissionId()+" | "+upList.getDescription());
			//System.out.println("Kunle check User Permission for "+upList.getPermissionId()+" is = "+userIdentity.renderLink(upList.getPermissionId()));
			// Log user login session
			System.out.println("Kunle 1");
			UserLoginSession userLoginSession = new UserLoginSession();
			System.out.println("Kunle 2");
			userLoginSession.setUsername(user.getUsername());
			System.out.println("Kunle 3");
			userLoginSession.setActionType(UserLoginSession.pActionType.Login
					.toString());
			// Actual save
			System.out.println("Kunle 4");
			System.out.print(userIdentity.getUserId());
			userLoginSessionBo.save(userLoginSession);
			// Check if profile has setup
			/*if (user.getUserProfile().getGender() == null) {
				String msg = "Your profile has not been setup. Click on the profile icon at the top right corner of the screen to view and edit your profile details.";
				alert.setAlert(redirectAttributes, Alert.INFO, msg);
			}*/

			if (null == callbackUrl) {
				return "redirect:/";
			} else {
				// Take the user to the url he was on before the security
				// mechanism kicked in
				System.out.println("Kunle 5");
				System.out.print(userIdentity.getUserId());
				return "redirect:" + callbackUrl;
			}
		}

		alert.setAlert(redirectAttributes, Alert.ERROR,
				"Sorry. You are not sure of yourself. Try again");
		return "redirect:/user/session/login";
	}

	// Log user out
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session,
			RedirectAttributes redirectAttributes) {

		String username = userIdentity.getUsername();

		userIdentity.setIdentity(false);
		userIdentity.setUsername(null);
		userIdentity.setUserProfile(null);
		userIdentity.setUserId(0);

		// Log user login session
		UserLoginSession userLoginSession = new UserLoginSession();
		userLoginSession.setUsername(username);
		userLoginSession.setActionType(UserLoginSession.pActionType.Logout
				.toString());
		// Actual save
		userLoginSessionBo.save(userLoginSession);

		session.removeAttribute("login"); // Remove the login session
		session.invalidate(); // Destroy session Object
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"You have been logged out!");
		return "redirect:/";
	}

	public boolean compare(UserSecurityQuestions userSecurityQuestions,
			UserQuestionForm userQuestionForm, int randomNumber,
			int userSessionId) {

		System.out.println("Form Answer: " + userQuestionForm.getAnswer());
		System.out.println("Random Number: " + randomNumber);
		switch (randomNumber) {
		case 1:
			if (userQuestionForm.getAnswer().equalsIgnoreCase(
					userSecurityQuestions.getAnswerOne())) {
				System.out.println("Answer One: "
						+ userSecurityQuestions.getAnswerOne());
				return true;
			}
			break;
		case 2:
			if (userQuestionForm.getAnswer().equalsIgnoreCase(
					userSecurityQuestions.getAnswerTwo())) {
				System.out.println("Answer Two: "
						+ userSecurityQuestions.getAnswerTwo());
				return true;
			}
			break;
		case 3:
			if (userQuestionForm.getAnswer().equalsIgnoreCase(
					userSecurityQuestions.getAnswerThree())) {
				System.out.println("Answer Three: "
						+ userSecurityQuestions.getAnswerThree());
				return true;
			}
			break;
		case 4:
			if (userQuestionForm.getAnswer().equalsIgnoreCase(
					userSecurityQuestions.getAnswerFour())) {
				System.out.println("Answer Four: "
						+ userSecurityQuestions.getAnswerFour());
				return true;
			}
			break;
		default:
			return false;
		}
		return false;
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String forgotPassword(
			@Valid @ModelAttribute("forgotPassword") ForgotPasswordForm forgotPasswordForm,
			BindingResult result, final RedirectAttributes redirectAttributes,
			HttpServletRequest httpRequest) throws UnknownHostException, IOException {

		if (forgotPasswordForm.getEmail().isEmpty()) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Please enter your email in the password recovery form!");
		} else {
			User user = userBo.getUserByEmail(forgotPasswordForm.getEmail());
			if (null != user) {

				// Reactivation code....
				Set<UserActivation> userActivations = user.getUserActivation();
				for (UserActivation userActivation : userActivations) {
					if (!userActivation.isExpired()) {
						userActivation.setExpired(true);
						userActivation.setModifiedDate(new Date(System
								.currentTimeMillis()));
					}
				}
				// Add new activation code here

				// Create the encrypted code
				String code = encryptor.createActivationCode(forgotPasswordForm
						.getEmail());

				UserActivation userActivation = new UserActivation();
				userActivation.setUser(user);
				userActivation.setActivationCode(code);
				userActivation.setUsed(false);
				userActivations.add(userActivation); // Add new code here

				user.setUserActivation(userActivations);
				user.setLock(true);
				user.setModifiedDate(new Date(System.currentTimeMillis()));
				userBo.update(user);

				String url = AppConfig.DEVELOPMENTAL_URL
						+ "/user/setup/password/" + code;

				String mailContent = "<h1>Rectivate your account</h1><div>Dear "
						+ user.getUserProfile().getLastName()
						+ "</div><div>"
						+ "<a href='"
						+ url
						+ "'>Click here to reactivate your account</a>"
						+ "</div><div>HMS Team</div>";
				//check if internet is available
				InternetConnection internetConnection = new InternetConnection();
				if(!internetConnection.checkConnection()){
					String msg = "Mail can not be send now, network connection error";
					alert.setAlert(redirectAttributes, Alert.INFO, msg);
					return "redirect:/user/session/login";
				}
				// Send email
				EmailSend emailSender = new EmailSend();
				emailSender.processMail(AppConfig.NO_REPLY, null, user.getUsername(), AppConfig.ACCT_REACTIVATION, mailContent);

				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						"Success! User reactivated");
			} else {
				alert.setAlert(redirectAttributes, Alert.DANGER,
						"Sorry! Email does not exist our records");
			}
		}
		return "redirect:/user/session/login";
	}
}
