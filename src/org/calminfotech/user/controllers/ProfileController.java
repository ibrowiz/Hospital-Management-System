package org.calminfotech.user.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.calminfotech.system.models.Gender;
import org.calminfotech.user.boInterface.SecurityQuestionBo;
import org.calminfotech.user.boInterface.TitleBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.boInterface.UserProfileBo;
import org.calminfotech.user.boInterface.UserSecurityQuestionsBo;
import org.calminfotech.user.forms.PasswordForm;
import org.calminfotech.user.forms.ProfileForm;
import org.calminfotech.user.forms.UserImageForm;
import org.calminfotech.user.forms.UserQuestionsForm;
import org.calminfotech.user.models.Title;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserProfile;
import org.calminfotech.user.models.UserSecurityQuestions;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.GenderDao;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/user/profile")
public class ProfileController {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private UserBo userBo;

	@Autowired
	private Alert alert;

	@Autowired
	private UserProfileBo userProfileBo;

	@Autowired
	private GenderDao genderList;

	@Autowired
	private TitleBo titleBo;

	@Autowired
	private SecurityQuestionBo securityQuestionBo;

	@Autowired
	private UserSecurityQuestionsBo userSecurityQuestionsBo;

	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		User user = userBo.getUserById(userIdentity.getUserId());
		model.addAttribute("user", user);
		model.addAttribute("imageForm", new UserImageForm());
		
		return "user/profile/index";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model) {
		User user = userBo.getUserById(userIdentity.getUserId());

		ProfileForm pForm = new ProfileForm();
		pForm.setUserId(user.getUserId());
		pForm.setLastName(user.getUserProfile().getLastName());
		pForm.setOtherNames(user.getUserProfile().getOtherNames());
		pForm.setTitleId(user.getUserProfile().getTitle().getId());

		if (user.getUserProfile().getGender() != null)
			pForm.setGenderId(user.getUserProfile().getGender().getId());

		pForm.setAddress(user.getUserProfile().getAddress());

		model.addAttribute("user", user);
		model.addAttribute("profileForm", pForm);
		model.addAttribute("genders", genderList.fetchAll());
		model.addAttribute("titles", titleBo.fetchAllByOrganisation());

		return "user/profile/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(
			@Valid @ModelAttribute("profileForm") ProfileForm profileForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		// Get the user's id
		User user = userBo.getUserById(userIdentity.getUserId());

		if (result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("genders", genderList.fetchAll());
			model.addAttribute("titles", titleBo.fetchAllByOrganisation());
			return "user/profile/edit";
		}

		UserProfile userProfile = user.getUserProfile();
		userProfile.setLastName(profileForm.getLastName());
		userProfile.setOtherNames(profileForm.getOtherNames());
		userProfile.setAddress(profileForm.getAddress());

		Title title = this.titleBo.getTitleById(profileForm.getTitleId());
		userProfile.setTitle(title);

		Gender gender = genderList.getGenderById(profileForm.getGenderId());
		userProfile.setGender(gender);
		userProfile.setModifiedDate(new Date(System.currentTimeMillis()));

		user.setUserProfile(userProfile);
		userBo.update(user);

		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Profile Updated!");

		return "redirect:/user/profile";
	}

	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String setPassword(Model model) {

		User user = userBo.getUserById(userIdentity.getUserId());

		PasswordForm pForm = new PasswordForm();
		model.addAttribute("passwordForm", pForm);
		return "user/profile/password";
	}

	@RequestMapping(value = "/processpassword", method = RequestMethod.POST)
	public String processPassword(
			@Valid @ModelAttribute("passwordForm") PasswordForm passwordForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		if (result.hasErrors()) {
			return "user/profile/password";
		}

		if (!passwordForm.getPassword().equals(
				passwordForm.getConfirmPassword())) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Please ensure the new password and confirmation password are the same.");
			return "redirect:/user/profile/password";
		}

		User user = userBo.getUserById(userIdentity.getUserId());

		if (!user.getPassword().equals(passwordForm.getFormerPassword())) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid password. Please put the correct password");
			return "redirect:/user/profile/password";
		}

		user.setPassword(passwordForm.getPassword());
		user.setModifiedDate(new Date(System.currentTimeMillis()));
		userBo.update(user);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Password changed successfully");

		return "redirect:/user/profile";
	}

	@RequestMapping(value = "/questions", method = RequestMethod.GET)
	public String setSecurityQuestion(Model model) {

		User user = userBo.getUserById(userIdentity.getUserId());
		UserSecurityQuestions userSecurityQuestions = userSecurityQuestionsBo
				.getUserSecurityQuestionsByUserId(user.getUserId());

		UserQuestionsForm questionsForm = new UserQuestionsForm();
		questionsForm.setQuestionOne(userSecurityQuestions.getQuestionOne()
				.getId());
		questionsForm.setQuestionTwo(userSecurityQuestions.getQuestionTwo()
				.getId());
		questionsForm.setQuestionThree(userSecurityQuestions.getQuestionThree()
				.getId());
		questionsForm.setQuestionFour(userSecurityQuestions.getQuestionFour()
				.getId());

		questionsForm.setAnswerOne(userSecurityQuestions.getAnswerOne());
		questionsForm.setAnswerTwo(userSecurityQuestions.getAnswerTwo());
		questionsForm.setAnswerThree(userSecurityQuestions.getAnswerThree());
		questionsForm.setAnswerFour(userSecurityQuestions.getAnswerFour());

		model.addAttribute("questionsForm", questionsForm);
		model.addAttribute("questions", securityQuestionBo.fetchAll());
		return null;
	}

	@RequestMapping(value = "/processquestions", method = RequestMethod.POST)
	public String processSecurityQuestions(
			@Valid @ModelAttribute("questionsForm") UserQuestionsForm userQuestionsForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("questions", securityQuestionBo.fetchAll());
			return "user/profile/questions";
		}

		User user = userBo.getUserById(userIdentity.getUserId());
		UserSecurityQuestions userSecurityQuestions = userSecurityQuestionsBo
				.getUserSecurityQuestionsByUserId(user.getUserId());

		return "redirect:/user/profile";
	}

	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public String processImage(
			@ModelAttribute("imageForm") UserImageForm imageForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		UserProfile userProfile = userProfileBo
				.getUserProfileByUserId(userIdentity.getUserId());

		if (null == userProfile) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not upload image");
			return "redirect:/user/profile";
		}

		try {
			@SuppressWarnings("deprecation")
			Blob blob = Hibernate.createBlob(imageForm.getImageFile().getInputStream());
			userProfile.setProfileImage(blob);

			String contentType = imageForm.getImageFile().getContentType();
			userProfile.setImageContentType(contentType);
			userProfile.setModifiedDate(new Date(System.currentTimeMillis()));

			userProfileBo.update(userProfile);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Image Uploaded successfully");

		} catch (IOException e) {
			e.printStackTrace();
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Image Upload failed");
		}

		return "redirect:/user/profile";
	}

	@ResponseBody
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public String viewImage(HttpServletResponse response) {
		UserProfile userProfile = userProfileBo
				.getUserProfileByUserId(userIdentity.getUserId());
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
}
