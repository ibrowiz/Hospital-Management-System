package org.calminfotech.system.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.setup.forms.AllergyForm;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.system.boInterface.GlobalHmoServicesBo;
import org.calminfotech.system.forms.GlobalHmoServicesForm;
import org.calminfotech.system.models.GlobalHmoServices;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/admin/insurances/hmos/hmoglobalservice")
public class GlobalHmoServicesController {
	
	@Autowired 
	private UserIdentity userIdentity;
	
	@Autowired 
	private GlobalHmoServicesBo globalHmoServicesBo;
	
	@Autowired 
	private Auditor auditor;
	
	@Autowired 
	private Alert alert;
	
	@RequestMapping(value = { "", "/" })
	@Layout(value = "layouts/datatable")
	public String indexAction(Model model) {
		List<GlobalHmoServices> globalHmoServices = globalHmoServicesBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		model.addAttribute("globalHmoServices", globalHmoServices);
		//model.addAttribute("allergies", aViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		return "admin/insurances/hmos/hmoglobalservice/index";
	}
	
	@RequestMapping(value = "/add")
	public String addAction(Model model) {

		model.addAttribute("globalHmoServicesForm", new GlobalHmoServicesForm());
		return "admin/insurances/hmos/hmoglobalservice/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveDiseaseAction(
			@Valid @ModelAttribute("globalHmoServicesForm") GlobalHmoServicesForm globalHmoServicesForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		
		GlobalHmoServices globalHmoServices = this.globalHmoServicesBo.save(globalHmoServicesForm);
		//Diseases disease = diseasesBo.save(diseaseForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Service added!");
		return "redirect:/admin/insurances/hmos/hmoglobalservice";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		//List<AllergyCatView> aCatView = aCatViewBo.fetchAll();
		GlobalHmoServices globalHmoServices = this.globalHmoServicesBo.getGlobalHmoServiesById(id);
		if (null == globalHmoServices) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
		}
		GlobalHmoServicesForm globalHmoServicesForm = new GlobalHmoServicesForm();
		globalHmoServicesForm.setHmoServiceId(globalHmoServices.getHmoServiceId());
		globalHmoServicesForm.setServiceName(globalHmoServices.getServiceName());
		globalHmoServicesForm.setDescription(globalHmoServices.getDescription());
		
		//model.addAttribute("allergy", allergy);
		model.addAttribute("globalHmoServicesForm", globalHmoServicesForm);
		//model.addAttribute("categories", this.aCatViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));

		auditor.before(request, "globalHmoServicesForm", globalHmoServicesForm);

		return "admin/insurances/hmos/hmoglobalservice/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("globalHmoServicesForm") GlobalHmoServicesForm globalHmoServicesForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		/*if (result.hasErrors()) {
			model.addAttribute("categories", this.allergyCategoryBo.fetchAll());
			return "admin/medical/components/allergies/edit";
		}*/

		this.globalHmoServicesBo.update(globalHmoServicesForm);

		auditor.after(request, "globalHmoServicesForm", globalHmoServicesForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Global Service updated");

		return "redirect:/admin/insurances/hmos/hmoglobalservice";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		GlobalHmoServices globalHmoServices = this.globalHmoServicesBo.getGlobalHmoServiesById(id);
		/*if (null == globalHmoServices) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/allergies";
		}*/
		GlobalHmoServicesForm globalHmoServicesForm = new GlobalHmoServicesForm();
		globalHmoServicesForm.setHmoServiceId(globalHmoServices.getHmoServiceId());
		model.addAttribute("globalHmoServicesForm", globalHmoServicesForm);
		model.addAttribute("globalHmoServices", globalHmoServices);

		return "admin/insurances/hmos/hmoglobalservice/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("globalHmoServicesForm") GlobalHmoServicesForm globalHmoServicesForm, Model model,
			RedirectAttributes redirectAttributes) {

		GlobalHmoServices GlobalHmoServices = this.globalHmoServicesBo.getGlobalHmoServiesById(globalHmoServicesForm.getHmoServiceId());
		/*if (null == GlobalHmoServices) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/allergies";
		}*/

		globalHmoServicesBo.delete(GlobalHmoServices);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Service Deleted");

		return "redirect:/admin/insurances/hmos/hmoglobalservice";
	}

}
