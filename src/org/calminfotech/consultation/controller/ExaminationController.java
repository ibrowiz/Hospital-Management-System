package org.calminfotech.consultation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.consultation.bo.ExaminationBo;
import org.calminfotech.consultation.bo.ExaminationCategoryBo;
import org.calminfotech.consultation.bo.ExaminationListBo;
import org.calminfotech.consultation.bo.ExaminationResultSetupBo;
import org.calminfotech.consultation.bo.ExaminationCategoryViewBo;
import org.calminfotech.consultation.bo.ExaminationViewBo;
import org.calminfotech.consultation.bo.GlobalExamResultSetupBo;
import org.calminfotech.consultation.forms.ExaminationCategoryForm;
import org.calminfotech.consultation.forms.ExaminationForm;
import org.calminfotech.consultation.forms.ExaminationResultSetupForm;
import org.calminfotech.consultation.models.Examination;
import org.calminfotech.consultation.models.ExaminationCategory;
import org.calminfotech.consultation.models.ExaminationCategoryView;
import org.calminfotech.consultation.models.ExaminationResultSetup;
import org.calminfotech.consultation.models.ExaminationView;
import org.calminfotech.consultation.models.GlobalExaminationResultSetup;
import org.calminfotech.setup.forms.OrgForm;
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
@RequestMapping(value = "/examination")
public class ExaminationController {
	
	@Autowired
	private GlobalExamResultSetupBo globalExamResultSetupBo;
	
	@Autowired
	private ExaminationCategoryBo examCategoryBo;
	
	@Autowired
	private ExaminationBo examBo;
	
	@Autowired
	private ExaminationViewBo examViewBo;
	
	@Autowired
	private ExaminationListBo  examListBo;
	
	@Autowired
	private ExaminationCategoryViewBo  examCategoryViewBo;
	
	@Autowired
	private ExaminationResultSetupBo examResultSetupBo;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private Auditor auditor;
	
	@RequestMapping(value = "/examlist")
	@Layout("layouts/datatable")
	public String indexAction(Model model) {
		List<ExaminationView> examView = examViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		model.addAttribute("examView", examView);
		OrgForm orgForm = new OrgForm();
		orgForm.setOrgId(userIdentity.getOrganisation().getId());
		ExaminationForm examForm = new ExaminationForm();
		model.addAttribute("examForm" ,examForm);
		return "consultations/visits/examination/index";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String addExamCategory(Model model){
		model.addAttribute("examList", this.examListBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		//List<Examination> exam = this.examBo.fetchAllByOrgainsation(userIdentity.getOrganisation().getId());
	    //model.addAttribute("examList",examList);
		model.addAttribute("examForm", new ExaminationForm());
		return "consultations/visits/examination/add";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("examForm") ExaminationForm examinationForm, 
					   BindingResult result, Model model,
					   RedirectAttributes redirectAttributes){
		
		
		examBo.save(examinationForm);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS, examinationForm.getName()+" added Successfully!");
		return "redirect:/examination/examlist";
		

	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		//List<AllergyCatView> aCatView = aCatViewBo.fetchAll();
		Examination exam = examBo.getExaminationById(id);
		if (null == exam) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
		}
		ExaminationForm examForm = new ExaminationForm();
		examForm.setExamId(exam.getExamId());
		examForm.setName(exam.getName());
		examForm.setMinimumValue(exam.getMinimumValue());
		examForm.setMaximumValue(exam.getMaximumValue());
		examForm.setDescription(exam.getDescription());
		examForm.setExamCategoryId(exam.getExamCategory().getExamCategoryId());

		model.addAttribute("exam", exam);
		model.addAttribute("examForm", examForm);
		model.addAttribute("examList", this.examListBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));

		auditor.before(request, "examForm", examForm);

		return "consultations/visits/examination/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("examinationForm") ExaminationForm examinationForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		/*if (result.hasErrors()) {
			model.addAttribute("categories", this.allergyCategoryBo.fetchAll());
			return "admin/medical/components/allergies/edit";
		}*/

		examBo.update(examinationForm);

		auditor.after(request, "examinationForm", examinationForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Allergy updated");

		return "redirect:/examination/examlist";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		Examination examination = this.examBo.getExaminationById(id);
	/*	if (null == examination) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/disease/categories";
		}*/

		ExaminationForm examinationForm = new ExaminationForm();
		examinationForm.setExamId(examination.getExamId());
		examinationForm.setName(examination.getName());

		model.addAttribute("examinationForm", examinationForm);
		model.addAttribute("examination", examination);

		return "consultations/visits/examination/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String remove(
			@ModelAttribute("examinationForm") ExaminationForm examinationForm,
			RedirectAttributes redirectAttributes) {

		Examination examination = this.examBo.getExaminationById(examinationForm.getExamId());				
		/*if (null == diseaseCategory) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/disease/categories";
		}
*/
		this.examBo.delete(examination);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Examination Deleted!");

		return "redirect:/examination/examlist";
	}
	
	@RequestMapping(value = "/detail/{id}")
	@Layout("layouts/datatable")
	public String viewDetail(@PathVariable("id") Integer id,Model model,
			RedirectAttributes redirectAttributes) {
		Examination exam = this.examBo.getExaminationById(id);
		System.out.println("examms " + exam.getName());
		ExaminationView examView = this.examViewBo.getExamViewById(id);
		List<GlobalExaminationResultSetup> globalExamResultSetup = this.globalExamResultSetupBo.fetchAllByOrganisationId(userIdentity.getOrganisation().getId());
		List<ExaminationResultSetup> examResultSetup = this.examResultSetupBo.getExamResultSetupByExamId(id);
		model.addAttribute("list",  this.examCategoryBo.fetchAllByOrgainsation(userIdentity.getOrganisation().getId()));
		ExaminationResultSetupForm examResultSetupForm = new ExaminationResultSetupForm();
		examResultSetupForm.setExamId(exam.getExamId());
		model.addAttribute("globalExamResultSetup", globalExamResultSetup);
		model.addAttribute("exam", exam);
		model.addAttribute("examView", examView);
		model.addAttribute("examResultSetup", examResultSetup);
		model.addAttribute("examResultSetupForm", examResultSetupForm);
		model.addAttribute("List",  examResultSetupBo.getExamResultSetupById(id));
		return "consultations/visits/examinationresultsetup/details";
	}	
	
}
