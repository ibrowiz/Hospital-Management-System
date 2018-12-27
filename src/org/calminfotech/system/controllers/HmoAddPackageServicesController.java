package org.calminfotech.system.controllers;

import javax.validation.Valid;

import org.calminfotech.system.boInterface.HmoAddPackageServicesBo;
import org.calminfotech.system.boInterface.HmoPckServiceBo;
import org.calminfotech.system.boInterface.HmoPckSubServiceBo;
import org.calminfotech.system.boInterface.EHmoPackagesBo;
import org.calminfotech.system.forms.HmoAddPackageServicesForm;
import org.calminfotech.system.models.HmoAddPackageServices;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.utils.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HmoAddPackageServicesController {

	@Autowired
	private HmoAddPackageServicesBo hmoAddPackageServicesBo;

	@Autowired
	private EHmoPackagesBo organisationHmoPackagesBo;

	@Autowired
	private HmoPckServiceBo hmoPckServiceBo;

	@Autowired
	private HmoPckSubServiceBo hmoPckSubServiceBo;

	@Autowired
	Alert alert;

	@RequestMapping(value = "/hmoaddpackageservice/index", method = RequestMethod.GET)
	public String indexAction(Model model) {

		EhmoPackages organisationHmoPackage = new EhmoPackages();
		organisationHmoPackage = organisationHmoPackagesBo
				.getPackageById(organisationHmoPackage.getId());

		model.addAttribute("hmoAddPackageService",
				hmoAddPackageServicesBo.fetchAll());
		 return "null";
		//return "redirect:system/hmos/view/" + organisationHmoPackage.getId();
	}

	@RequestMapping(value = "/hmoaddpackageservice/add", method = RequestMethod.GET)
	public String addAction(Model model) {

		HmoAddPackageServicesForm hmoAddPackageServicesForm = new HmoAddPackageServicesForm();
		model.addAttribute("hmoAddPackageServicesForm",
				hmoAddPackageServicesForm);
		model.addAttribute("hmoPckService", hmoPckServiceBo.fetchAll());
		model.addAttribute("hmoPckSubService", hmoPckSubServiceBo.fetchAll());

		return null;

	}

	@RequestMapping(value = "/hmoaddpackageservice/add", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("hmoAddPackageServicesForm") HmoAddPackageServicesForm hmoAddPackageServicesForm,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "";
		}
		HmoAddPackageServices hmoAddPakageService = hmoAddPackageServicesBo
				.save(hmoAddPackageServicesForm);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Hmo Service Added to Package Created Successfully!");
		// return "redirect:/hmoaddpackageservice/index";

		return "redirect:/system/hmos/view/"
				+ hmoAddPakageService.getOrganisationHmoPackage().getId();
	}

}
