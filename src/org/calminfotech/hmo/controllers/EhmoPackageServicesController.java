package org.calminfotech.hmo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.hmo.boInterface.EhmoPackageServicesBo;
import org.calminfotech.hmo.forms.EhmoPackageServicesForm;
import org.calminfotech.hmo.models.EhmoPackageServices;
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
@RequestMapping(value = "/admin/insurances/hmos/ehmopackageservices")
public class EhmoPackageServicesController {
	
	@Autowired 
	private UserIdentity userIdentity;
	
	@Autowired 
	private GlobalHmoServicesBo globalHmoServicesBo;
	
	@Autowired 
	private EhmoPackageServicesBo ehmoPackageServicesBo;
	
	@Autowired 
	private Auditor auditor;
	
	@Autowired 
	private Alert alert;
	
	@RequestMapping(value = "/add")
	public String addAction(Model model) {

		model.addAttribute("ehmoPackageServicesForm", new EhmoPackageServicesForm());
		return "admin/insurances/hmos/ehmopackageservice/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("ehmoPackageServicesForm") EhmoPackageServicesForm ehmoPackageServicesForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		
		EhmoPackageServices ehmoPackageServices = this.ehmoPackageServicesBo.save(ehmoPackageServicesForm);
		//Diseases disease = diseasesBo.save(diseaseForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! HMO package Service added!");
		return "redirect:/admin/insurances/hmos/hmoglobalservice";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		//List<AllergyCatView> aCatView = aCatViewBo.fetchAll();
		EhmoPackageServices ehmoPackageServices = this.ehmoPackageServicesBo.getEhmoPackageServicesById(id);
		/*if (null == globalHmoServices) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
		}*/
		EhmoPackageServicesForm ehmoPackageServicesForm = new EhmoPackageServicesForm();
		ehmoPackageServicesForm.setId(id);
		ehmoPackageServicesForm.setHmoPackageId(ehmoPackageServices.getGlobalServiceId());
		ehmoPackageServicesForm.setGlobalServiceId(ehmoPackageServices.getGlobalServiceId());
		ehmoPackageServicesForm.setName(ehmoPackageServices.getName());
		ehmoPackageServicesForm.setPrice(ehmoPackageServices.getPrice());
		
		
		//model.addAttribute("allergy", allergy);
		model.addAttribute("ehmoPackageServicesForm", ehmoPackageServicesForm);
		//model.addAttribute("categories", this.aCatViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));

		auditor.before(request, "ehmoPackageServicesForm", ehmoPackageServicesForm);

		return "admin/insurances/hmos/ehmopackageservices/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("ehmoPackageServicesForm") EhmoPackageServicesForm ehmoPackageServicesForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		/*if (result.hasErrors()) {
			model.addAttribute("categories", this.allergyCategoryBo.fetchAll());
			return "admin/medical/components/allergies/edit";
		}*/

		this.ehmoPackageServicesBo.update(ehmoPackageServicesForm);

		auditor.after(request, "ehmoPackageServicesForm", ehmoPackageServicesForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! HMO Package Service updated");

		return "redirect:/admin/insurances/hmos/ehmopackageservices/";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		EhmoPackageServices ehmoPackageServices = this.ehmoPackageServicesBo.getEhmoPackageServicesById(id);
		EhmoPackageServicesForm ehmoPackageServicesForm = new EhmoPackageServicesForm();
		ehmoPackageServicesForm.setId(id);
		model.addAttribute("ehmoPackageServicesForm", ehmoPackageServicesForm);
		model.addAttribute("ehmoPackageServices", ehmoPackageServices);

		return "admin/insurances/hmos/ehmopackageservices/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("ehmoPackageServicesForm") EhmoPackageServicesForm ehmoPackageServicesForm, Model model,
			RedirectAttributes redirectAttributes) {

		EhmoPackageServices ehmoPackageServices = this.ehmoPackageServicesBo.getEhmoPackageServicesById(ehmoPackageServicesForm.getId());
		ehmoPackageServicesBo.delete(ehmoPackageServices);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! HMO package Service Deleted");

		return "redirect:/admin/insurances/hmos/ehmopackageservices";
	}
}
