package org.calminfotech.system.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.calminfotech.setup.boInterface.UnitListBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.forms.UserForm;
import org.calminfotech.system.forms.VisitWorkflowPointUserForm;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.boInterface.RoleBo;
import org.calminfotech.user.boInterface.TitleBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.boInterface.UserLoginSessionBo;
import org.calminfotech.user.forms.TitleForm;
import org.calminfotech.user.models.Title;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserActivation;
import org.calminfotech.user.models.UserProfile;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.AppConfig;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.Encryptor;
import org.calminfotech.utils.NotificationCentre;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/umgt/users")
public class UsersController {
	
	@Autowired
	private UnitListBo unitListBo;

	@Autowired
	private UserBo userBo;

	@Autowired
	private UserLoginSessionBo userLoginSession;
	
	@Autowired
	private RoleBo roleBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Encryptor encryptor;

	@Autowired
	private TitleBo titleBo;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private VisitWorkflowPointBo visitWorkflowPointBo;

	@Autowired
	private NotificationCentre notificationCentre;

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	public String indexAction(Model model) {

		// Index the users to the front
		model.addAttribute("users", userBo.fetchAllByOrganisation());

		return "system/umgt/users/index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addAction(Model model) {
		model.addAttribute("unit", unitListBo.fetchAllByOrganisationId(userIdentity.getOrganisation().getId()));
		model.addAttribute("userForm", new UserForm());
		model.addAttribute("roles", roleBo.fetchAllRoleByOrganisation());
		model.addAttribute("titles", titleBo.fetchAllByOrganisation());
		return "system/umgt/users/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String entryAction(
			@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest httpServletRequest) {

		if (result.hasErrors()) {
			model.addAttribute("roles", roleBo.fetchAllRoleByOrganisation());
			model.addAttribute("titles", titleBo.fetchAllByOrganisation());
			return "system/umgt/users/add/";
		}

		userBo.createUser(userForm, httpServletRequest);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success!, User created!");

		return "redirect:/system/umgt/users";
	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String viewAction(@PathVariable("id") int userId, Model model) {
		User user = userBo.getUserById(userId);
		model.addAttribute("user", user);
		model.addAttribute("loginSessionList",
				userLoginSession.fetchAllByUsername(user.getUsername()));

		// Attach the workflow point form here
		// That way u can easily attach a user to a workflow point
		System.out.println(user.getWorkflowPoints().size());

		VisitWorkflowPointUserForm wfPointForm = new VisitWorkflowPointUserForm();
		wfPointForm.setUserId(user.getUserId());

		Integer[] userPoints = new Integer[user.getWorkflowPoints().size()];
		int i = 0;
		for (VisitWorkflowPoint point : user.getWorkflowPoints()) {
			userPoints[i] = point.getId();
			i++;
		}

		wfPointForm.setPointsId(userPoints);

		model.addAttribute("pointsForm", wfPointForm);
		model.addAttribute("points",
				this.visitWorkflowPointBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));

		return "system/umgt/users/view";
	}

	@RequestMapping(value = "/lock/{id}", method = RequestMethod.GET)
	public String lockAction(@PathVariable("id") int userId,
			RedirectAttributes redirectAttributes, Model model) {
		UserForm userForm = new UserForm();
		userForm.setUserId(userId);
		User user = userBo.getUserById(userId);
		model.addAttribute("userForm", userForm);
		model.addAttribute("user", user);
		return "system/umgt/users/lock";
	}

	@RequestMapping(value = "/lock/toggle", method = RequestMethod.POST)
	public String lockToggle(
			@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest httpServletRequest) {
		User user = userBo.getUserById(userForm.getUserId());

		if (null == user) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "system/umgt/users";
		}
		if (user.getLock()) {
			user.setLock(false);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! User unlocked");
		} else {
			user.setLock(true);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! User locked");
		}
		user.setModifiedDate(new Date(System.currentTimeMillis()));
		userBo.update(user);
		return "redirect:/system/umgt/users";
	}

	@RequestMapping(value = "/reactivate/{id}", method = RequestMethod.GET)
	public String reactivateAction(@PathVariable("id") int userId,
			RedirectAttributes redirectAttributes, Model model) {
		User user = userBo.getUserById(userId);

		if (null == user) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "system/users";
		}

		UserForm userForm = new UserForm();
		userForm.setUserId(user.getUserId());
		model.addAttribute("userForm", userForm);
		model.addAttribute("user", user);
		return "system/umgt/users/reactivate";
	}

	@RequestMapping(value = "/reactivated", method = RequestMethod.POST)
	public String reactivatedAction(
			@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest httpServletRequest) {

		User user = userBo.getUserById(userForm.getUserId());

		if (null == user) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "system/umgt/users";
		}

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
		String code = encryptor.createActivationCode(userForm.getEmail());

		UserActivation userActivation = new UserActivation();
		userActivation.setUser(user);
		userActivation.setActivationCode(code);
		userActivation.setUsed(false);
		userActivations.add(userActivation); // Add new code here

		user.setUserActivation(userActivations);
		user.setLock(true);
		user.setModifiedDate(new Date(System.currentTimeMillis()));
		userBo.update(user);

		String url = AppConfig.DEVELOPMENTAL_URL + "/user/setup/password/"
				+ code;

		String mailContent = "<h1>Rectivate your account</h1><div>Dear "
				+ user.getUserProfile().getLastName() + "</div><div>"
				+ "<a href='" + url
				+ "'>Click here to reactivate your account</a>"
				+ "</div><div>HMS Team</div>";

		// Send email
		this.notificationCentre.sendEmail(user.getUsername(),
				"Reactivation for HMS Account", mailContent);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Reactivation code sent...");
		return "redirect:/system/umgt/users";
	}

	@ResponseBody
	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	public String viewImage(HttpServletResponse response,
			@PathVariable("id") Integer id) {
		User user = userBo.getUserById(id);
		UserProfile userProfile = user.getUserProfile();

		if (null != userProfile.getProfileImage()) {
			try {
				response.setContentType(userProfile.getImageContentType());
				response.setHeader("Content-Disposition", "inline;filename=\""
						+ userProfile.getLastName() + "\"");
				OutputStream outputStream = response.getOutputStream();
				IOUtils.copy(userProfile.getProfileImage().getBinaryStream(),
						outputStream);
				outputStream.flush();
				outputStream.close();

			} catch (IOException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/workflowpoint/update", method = RequestMethod.POST)
	public String updateWorkflowPoints(
			@Valid @ModelAttribute("pointsForm") VisitWorkflowPointUserForm form,
			BindingResult result) {
		String response = "";
		boolean status = true;
		String payload = null;

		if (result.hasErrors()) {
			status = false;
			response = "{ \"status\":" + status + ", \"payload\": " + payload
					+ "}";
			return response;
		}

		User user = this.userBo.getUserById(form.getUserId());
		user.getWorkflowPoints().clear();

		// for all the selected checkbox, loop through the all points and attach
		// selected ones only
		for (int i = 0; i < form.getPointsId().length; i++) {
			for (VisitWorkflowPoint point : this.visitWorkflowPointBo
					.fetchAllByOrganisation(userIdentity.getOrganisation().getId())) {
				if (point.getId() == form.getPointsId()[i]) {
					user.getWorkflowPoints().add(point);
				}
			}
		}

		this.userBo.update(user);

		if (user.getWorkflowPoints().size() > 0) {
			payload = "";
			for (VisitWorkflowPoint point : user.getWorkflowPoints()) {
				payload += "<tr><td>" + point.getName() + "</td></tr>";
			}
		} else {
			payload = "<tr><td>User not associated with any workflow</td></tr>";
		}

		response = "{ \"status\":" + status + ", \"payload\": \"" + payload
				+ "\"}";
		return response;
	}

	/**
	 * 
	 * Titles
	 * 
	 */
	@Layout("layouts/datatable")
	@RequestMapping(value = { "/titles" }, method = RequestMethod.GET)
	public String titlesAction(Model model) {

		model.addAttribute("titles", titleBo.fetchAllByOrganisation());
		model.addAttribute("titleForm", new TitleForm());
		return "system/umgt/users/titles/index";
	}

	@RequestMapping(value = { "/titles" }, method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("titleForm") TitleForm titleForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("titles", titleBo.fetchAll());
			return "system/umgt/users/titles/index";
		}

		titleBo.save(titleForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Title Created!");

		return "redirect:/system/umgt/users/titles";
	}

	@RequestMapping(value = "/titles/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		Title title = titleBo.getTitleById(id);
		if (null == title) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource.");
			return "redirect:/system/umgt/users/titles";
		}
		TitleForm titleForm = new TitleForm();
		titleForm.setId(title.getId());
		titleForm.setTitle(title.getName());
		titleForm.setAcronym(title.getAcronym());

		model.addAttribute("titleForm", titleForm);

		auditor.before(request, "Title Form", titleForm);
		return "system/umgt/users/titles/edit";
	}

	@RequestMapping(value = "/titles/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("titleForm") TitleForm titleForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "system/users/titles/edit";
		}

		titleBo.update(titleForm);
		auditor.after(request, "Title Form", titleForm,
				userIdentity.getUsername());
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Title updated");
		return "redirect:/system/umgt/users/titles";
	}

	@RequestMapping(value = "/titles/delete/{id}")
	public String deleteTitle(@PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes) {
		Title title = this.titleBo.getTitleById(id);
		if (null == title) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "reidrect:/system/umgt/users/titles";
		}

		this.titleBo.delete(title);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Title deleted.");
		return "redirect:/system/umgt/users/titles";
	}
}
