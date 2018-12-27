package org.calminfotech.disease.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.admin.boInterface.DiseaseBo;
import org.calminfotech.disease.boInterface.DiseaseCategoryListBo;
import org.calminfotech.disease.boInterface.DiseaseViewBo;
import org.calminfotech.disease.boInterface.DiseasesBo;
import org.calminfotech.disease.forms.DiseaseForm;
import org.calminfotech.disease.models.DiseaseView;
import org.calminfotech.disease.models.Diseases;
import org.calminfotech.setup.forms.AllergyForm;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.setup.models.AllergyView;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.annotations.Layout;
import org.hibernate.SessionFactory;
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
@RequestMapping(value = "/diseases")
public class DiseaseController {
	
	@Autowired
	private DiseaseViewBo diseaseViewBo;
	
	@Autowired
	private DiseasesBo diseasesBo;
	
	@Autowired
	private DiseaseCategoryListBo diseaseCategoryListBo;
	
	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;
	
	@RequestMapping(value = { "", "/" })
	@Layout(value = "layouts/datatable")
	public String indexDiseaseAction(Model model) {
		List<DiseaseView> diseaseView = diseaseViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		System.out.println("the size is " + diseaseView.size());
		model.addAttribute("diseaseView", diseaseView);
		//model.addAttribute("allergies", aViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		return "admin/medical/components/disease/index";
	}
	
	@RequestMapping(value = "/add")
	public String addDiseaseAction(Model model) {
		 System.out.println("Before query");
		  System.out.println("Done with the query");
		model.addAttribute("DForm", new DiseaseForm());
		model.addAttribute("DiseasecatogoryList", this.diseaseCategoryListBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));

		return "admin/medical/components/disease/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveDiseaseAction(
			@Valid @ModelAttribute("DForm") DiseaseForm diseaseForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		
       
		Diseases disease = diseasesBo.save(diseaseForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Disease added!");
		return "redirect:/diseases";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editDiseaseAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		//List<AllergyCatView> aCatView = aCatViewBo.fetchAll();
		Diseases diseases = diseasesBo.getDiseaseById(id);
		if (null == diseases) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
		}
		DiseaseForm diseaseForm = new DiseaseForm();
		diseaseForm.setDiseaseId(diseases.getDiseaseId());
		diseaseForm.setName(diseases.getName());
		diseaseForm.setCategoryId(diseases.getCategory().getDiseaseCategoryId());

		model.addAttribute("diseases", diseases);
		model.addAttribute("DForm", diseaseForm);
		model.addAttribute("DiseasecatogoryList", this.diseaseCategoryListBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		auditor.before(request, "Disease Form", diseaseForm);

		return "admin/medical/components/disease/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateDiseaseAction(
			@Valid @ModelAttribute("DForm") DiseaseForm diseaseForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		/*if (result.hasErrors()) {
			model.addAttribute("categories", this.allergyCategoryBo.fetchAll());
			return "admin/medical/components/allergies/edit";
		}*/

		diseasesBo.update(diseaseForm);

		auditor.after(request, "Disease Form", diseaseForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Disease updated");

		return "redirect:/diseases";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Diseases disease = this.diseasesBo.getDiseaseById(id);
		/*if (null == allergy) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/allergies";
		}*/
		DiseaseForm diseaseForm = new DiseaseForm();
		diseaseForm.setDiseaseId(disease.getDiseaseId());
		model.addAttribute("DForm", diseaseForm);
		model.addAttribute("disease", disease);

		return "admin/medical/components/disease/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("DForm") DiseaseForm diseaseForm, Model model,
			RedirectAttributes redirectAttributes) {

		Diseases disease = this.diseasesBo.getDiseaseById(diseaseForm.getDiseaseId());
		/*if (null == allergy) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/allergies";
		}*/

		diseasesBo.delete(disease);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Disease Deleted");

		return "redirect:/diseases";
	}

}
