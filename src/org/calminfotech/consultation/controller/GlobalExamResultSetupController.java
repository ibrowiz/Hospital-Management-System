package org.calminfotech.consultation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.consultation.bo.GlobalExamResultSetupBo;
import org.calminfotech.consultation.forms.GlobalExamResultSetupForm;
import org.calminfotech.consultation.models.GlobalExaminationResultSetup;
import org.calminfotech.disease.forms.DiseaseForm;
import org.calminfotech.disease.models.Diseases;

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
@RequestMapping(value = "/globalexamresultsetup")
public class GlobalExamResultSetupController {
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private Auditor auditor;
	
	@Autowired
	private GlobalExamResultSetupBo globalExamResultSetupBo;
	
	@RequestMapping(value = "/list")
	@Layout(value = "layouts/datatable")
	public String indexAction(Model model) {
		List<GlobalExaminationResultSetup> gExamResultSetup = globalExamResultSetupBo.fetchAllByOrganisationId(userIdentity.getOrganisation().getId());
		model.addAttribute("gExamResultSetup", gExamResultSetup);
		return "consultations/visits/globalexamresultsetup/index";
	}
	
	@RequestMapping(value = "/add")
	public String addAction(Model model) {
		model.addAttribute("globalExamResultSetupForm", new GlobalExamResultSetupForm());
		//model.addAttribute("DiseasecatogoryList", this.diseaseCategoryListBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));

		return "consultations/visits/globalexamresultsetup/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveDiseaseAction(
			@Valid @ModelAttribute("globalExamResultSetupForm") GlobalExamResultSetupForm globalExamResultSetupForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		
		GlobalExaminationResultSetup globalExamResultSetup = globalExamResultSetupBo.save(globalExamResultSetupForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + globalExamResultSetup.getExamResultName() + " added!");
		return "redirect:/globalexamresultsetup/list";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		GlobalExaminationResultSetup globalExamResultSetup = globalExamResultSetupBo.getExamResultSetupById(id);
		if (null == globalExamResultSetup) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
		}
		GlobalExamResultSetupForm globalExamResultSetupForm = new GlobalExamResultSetupForm();
		globalExamResultSetupForm.setExamResultSetupId(globalExamResultSetup.getExamResultSetupId());
		globalExamResultSetupForm.setExamResultName(globalExamResultSetup.getExamResultName());
		globalExamResultSetupForm.setDescription(globalExamResultSetup.getDescription());

		model.addAttribute("globalExamResultSetup", globalExamResultSetup);
		model.addAttribute("globalExamResultSetupForm", globalExamResultSetupForm);
		auditor.before(request, "globalExamResultSetupForm", globalExamResultSetupForm);

		return "consultations/visits/globalexamresultsetup/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateDiseaseAction(
			@Valid @ModelAttribute("globalExamResultSetupForm") GlobalExamResultSetupForm globalExamResultSetupForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		/*if (result.hasErrors()) {
			model.addAttribute("categories", this.allergyCategoryBo.fetchAll());
			return "admin/medical/components/allergies/edit";
		}
*/
		globalExamResultSetupBo.update(globalExamResultSetupForm);

		auditor.after(request, "globalExamResultSetupForm", globalExamResultSetupForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Result updated");

		return "redirect:/globalexamresultsetup/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		GlobalExaminationResultSetup globalExamResultSetup = globalExamResultSetupBo.getExamResultSetupById(id);
		/*if (null == globalExamResultSetup) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/allergies";
		}*/
		GlobalExamResultSetupForm globalExamResultSetupForm = new GlobalExamResultSetupForm();
		globalExamResultSetupForm.setExamResultSetupId(globalExamResultSetup.getExamResultSetupId());
		model.addAttribute("globalExamResultSetupForm", globalExamResultSetupForm);
		model.addAttribute("globalExamResultSetup", globalExamResultSetup);

		return "consultations/visits/globalexamresultsetup/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("globalExamResultSetupForm") GlobalExamResultSetupForm globalExamResultSetupForm, Model model,
			RedirectAttributes redirectAttributes) {

		GlobalExaminationResultSetup globalExamResultSetup = globalExamResultSetupBo.getExamResultSetupById(globalExamResultSetupForm.getExamResultSetupId());
		/*if (null == allergy) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/allergies";
		}*/

		globalExamResultSetupBo.delete(globalExamResultSetup);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Exam result Deleted");

		return "redirect:/globalexamresultsetup/list";
	}

	
}
