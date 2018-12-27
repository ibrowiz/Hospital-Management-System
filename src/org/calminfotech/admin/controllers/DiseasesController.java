package org.calminfotech.admin.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.admin.boInterface.DiseaseBo;
import org.calminfotech.admin.forms.DiseaseForm;
import org.calminfotech.system.models.Disease;
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
@RequestMapping(value = "/admin/medical/components/diseases")
public class DiseasesController {

	@Autowired
	private DiseaseBo diseaseBo;

	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Auditor auditor;
	

	@RequestMapping(value = { "", "/" })
	@Layout(value = "layouts/datatable")
	public String indexAction(Model model) {

		model.addAttribute("diseases", diseaseBo.fetchAll());
		return "admin/medical/components/diseases/index";
	}

	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Disease disease = diseaseBo.getDiseaseById(id);
		if (null == disease) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! invalid resource");
			return "reidrect:/admin/medical/components/diseases";
		}

		model.addAttribute("disease", disease);
		return "admin/medical/components/diseases/view";
	}

	@RequestMapping(value = "/add")
	public String addAction(Model model) {

		model.addAttribute("dForm", new DiseaseForm());
		return "admin/medical/components/diseases/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("dForm") DiseaseForm diseaseForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "admin/medical/components/diseases/add";
		}

		Disease disease = diseaseBo.save(diseaseForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Disease added!");

		return "redirect:/admin/medical/components/diseases/view/"
				+ disease.getId();
	}

	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		Disease disease = diseaseBo.getDiseaseById(id);
		if (null == disease) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource.");
			return "redirect:/admin/medical/components/diseases";
		}

		DiseaseForm diseaseForm = new DiseaseForm();
		diseaseForm.setId(disease.getId());
		diseaseForm.setName(disease.getName());
		diseaseForm.setDescription(disease.getDescription());

		model.addAttribute("dForm", diseaseForm);
		model.addAttribute("disease", disease);

		auditor.before(request, "Disease Form", diseaseForm);
		return "admin/medical/components/diseases/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("dForm") DiseaseForm diseaseForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, @PathVariable("id") Integer id,
			HttpServletRequest request) {

		if (result.hasErrors()) {
			Disease disease = diseaseBo.getDiseaseById(id);
			model.addAttribute("disease", disease);
			return "admin/medical/components/diseases/edit";
		}

		diseaseBo.update(diseaseForm);
		auditor.after(request, "Disease Form", diseaseForm,
				userIdentity.getUsername());
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Disease updated");

		return "redirect:/admin/medical/components/diseases/view/"
				+ diseaseForm.getId();
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Disease disease = diseaseBo.getDiseaseById(id);
		if (null == disease) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/diseases";
		}

		DiseaseForm diseaseForm = new DiseaseForm();
		diseaseForm.setId(id);

		model.addAttribute("disease", disease);
		model.addAttribute("dForm", diseaseForm);
		return "admin/medical/components/diseases/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("dForm") DiseaseForm diseaseForm, Model model,
			RedirectAttributes redirectAttributes) {

		Disease disease = diseaseBo.getDiseaseById(diseaseForm.getId());
		if (null == disease) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/diseases";
		}

		diseaseBo.delete(disease);
		alert.setAlert(redirectAttributes, Alert.ERROR,
				"Success! Disease Deleted");

		return "redirect:/admin/medical/components/diseases";
	}
}
