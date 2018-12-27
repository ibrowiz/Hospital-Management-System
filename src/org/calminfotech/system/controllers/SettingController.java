package org.calminfotech.system.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.SettingBo;
import org.calminfotech.system.forms.OrganisationSettingForm;
import org.calminfotech.system.models.Setting;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.SystemSetting;
import org.calminfotech.utils.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/setting")
public class SettingController {

	@Autowired
	private SettingBo settingBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String indexAction(Model model, HttpServletRequest request) {

		OrganisationSettingForm organisationSettingForm = new OrganisationSettingForm();
		Organisation organisation = this.userIdentity.getOrganisation();

		organisationSettingForm.setName(organisation.getName());
		organisationSettingForm.setAddress(organisation.getAddress());
		organisationSettingForm.setSystemEmail(organisation.getSystemEmail());
		organisationSettingForm.setConsultationCode(organisation
				.getConsultationCode());

		auditor.before(request, "Organisation Settings Form",
				organisationSettingForm);

		model.addAttribute("settingForm", organisationSettingForm);
		return "system/setting/index";
	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public String update(
			@Valid @ModelAttribute("settingForm") OrganisationSettingForm organisationSettingForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "system/setting/index";
		}

		// Update settings
		settingBo.update(organisationSettingForm);

		auditor.after(request, "Organisation Settings Form",
				organisationSettingForm, userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Organisation details Updated!");
		return "redirect:/system/setting";
	}

}
