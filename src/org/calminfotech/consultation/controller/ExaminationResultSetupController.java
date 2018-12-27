package org.calminfotech.consultation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.consultation.bo.ExaminationResultSetupBo;
import org.calminfotech.consultation.bo.GlobalExamResultSetupBo;
import org.calminfotech.consultation.forms.ExaminationForm;
import org.calminfotech.consultation.forms.ExaminationResultSetupForm;
import org.calminfotech.consultation.models.Examination;
import org.calminfotech.consultation.models.ExaminationResultSetup;
import org.calminfotech.consultation.models.GlobalExaminationResultSetup;
import org.calminfotech.lab.forms.LabTestForm;
import org.calminfotech.lab.models.LabTest;
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
@RequestMapping(value = "/examresultsetup")
public class ExaminationResultSetupController {
	@Autowired
	private GlobalExamResultSetupBo globalExamResultSetupBo;
	@Autowired
	private UserIdentity userIdentity;
	@Autowired
	private Alert alert;
	@Autowired
	private ExaminationResultSetupBo examResultSetupBo;
	@Autowired
	private Auditor auditor;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("examinationResultSetupForm") ExaminationResultSetupForm examinationResultSetupForm, 
					   BindingResult result, Model model,
					   RedirectAttributes redirectAttributes){
		
		
		examResultSetupBo.save(examinationResultSetupForm);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS, "setup added Successfully!");
		return "redirect:/examination/detail/"+examinationResultSetupForm.getExamId();
		

	}
	
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/editExamResultSetup/{id}")
	public String editExam(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		ExaminationResultSetup examinationResultSetup = this.examResultSetupBo.getExamResultSetupByRowId(id);
		System.out.println("exami id " + examinationResultSetup.getExam().getExamId());
		/*if (null == labTest) {

			return "redirect:/index";
		}*/

		ExaminationResultSetupForm examinationResultSetupForm = new ExaminationResultSetupForm();

		examinationResultSetupForm.setGlobalExamResultSetupId(examinationResultSetup.getExamResultSetupId());
		examinationResultSetupForm.setExamId(examinationResultSetup.getExam().getExamId());
		examinationResultSetupForm.setExamResultName(examinationResultSetup.getExamResultName());
		
		List<GlobalExaminationResultSetup> globalExamResultSetup = this.globalExamResultSetupBo.fetchAllByOrganisationId(userIdentity.getOrganisation().getId());
		model.addAttribute("globalExamResultSetup", globalExamResultSetup);
		model.addAttribute("examinationResultSetupForm", examinationResultSetupForm);
		//model.addAttribute("list", this.labTestCatBo.fetchAllCatByOrganisation(userIdentity.getOrganisation().getId()));
		auditor.before(request, "examinationResultSetupForm", examinationResultSetupForm);
		return "consultations/visits/examinationresultsetup/edit";

	}
	
	@RequestMapping(value = "/editExamResultSetup/{id}", method = RequestMethod.POST)
	public String updateExam(
			@Valid @ModelAttribute("examinationResultSetupForm") ExaminationResultSetupForm examinationResultSetupForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		System.out.println("ex id is " + examinationResultSetupForm.getExamId());
		this.examResultSetupBo.update(examinationResultSetupForm);
		auditor.after(request, "examinationResultSetupForm", examinationResultSetupForm,
				userIdentity.getUsername());
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! updated");
		 return "redirect:/examination/detail/"+examinationResultSetupForm.getExamId();
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		ExaminationResultSetup examinationResultSetup = this.examResultSetupBo.getExamResultSetupByRowId(id);
	/*	if (null == examination) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/disease/categories";
		}*/

		ExaminationResultSetupForm examinationResultSetupForm = new ExaminationResultSetupForm();
		examinationResultSetupForm.setGlobalExamResultSetupId(examinationResultSetup.getExamResultSetupId());
		examinationResultSetupForm.setExamId(examinationResultSetup.getExam().getExamId());
		examinationResultSetupForm.setExamResultName(examinationResultSetup.getExamResultName());

		model.addAttribute("examinationResultSetupForm", examinationResultSetupForm);
		model.addAttribute("examinationResultSetup", examinationResultSetup);

		return "consultations/visits/examinationresultsetup/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String remove(
			@ModelAttribute("examinationResultSetupForm") ExaminationResultSetupForm examinationResultSetupForm,
			RedirectAttributes redirectAttributes) {

		ExaminationResultSetup examinationResultSetup = this.examResultSetupBo.getExamResultSetupById(examinationResultSetupForm.getGlobalExamResultSetupId());				
		/*if (null == diseaseCategory) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/disease/categories";
		}
*/
		this.examResultSetupBo.delete(examinationResultSetup);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Examination Deleted!");

		return "redirect:/examination/detail/" + examinationResultSetupForm.getExamId();
	}

}
