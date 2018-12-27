package org.calminfotech.consultation.controller;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.consultation.bo.ExaminationCategoryBo;
import org.calminfotech.consultation.bo.ExaminationResultSetupBo;
import org.calminfotech.consultation.bo.ExaminationCategoryViewBo;
import org.calminfotech.consultation.forms.ExaminationCategoryForm;
import org.calminfotech.consultation.forms.ExaminationResultSetupForm;
import org.calminfotech.consultation.forms.VisitDoctorForm;
import org.calminfotech.consultation.models.ExaminationCategory;
import org.calminfotech.consultation.models.ExaminationResultSetup;
import org.calminfotech.consultation.models.ExaminationCategoryView;
import org.calminfotech.disease.forms.DiseaseCategoryForm;
import org.calminfotech.disease.models.DiseaseCategory;
import org.calminfotech.disease.models.DiseaseCategoryView;
import org.calminfotech.lab.forms.LabTestForm;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.lab.models.LabTestCategory;
import org.calminfotech.setup.forms.OrgForm;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.annotations.Layout;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/examinationcategory")
public class ExaminationCategoryController {
	
	@Autowired
	private ExaminationCategoryBo examCategoryBo;
	
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
	
	/*@RequestMapping(value = "/listDoctorsNote/{visitId}")
	@Layout("layouts/blank")
	public String listNote(@PathVariable("visitId") Integer visitId,
			Model model,RedirectAttributes redirectAttributes) {
		
		VisitDoctorForm visitDoctorForm = new VisitDoctorForm();
		visitDoctorForm.setVisitId(visitId);
		model.addAttribute("visitDoctorForm" ,visitDoctorForm);
		model.addAttribute("noteList", this.doctorBo.fetchAllByUIdAndOrg(userIdentity.getUserId(),userIdentity.getOrganisation().getId() ));
		return "consultations/visits/doctor/doctorsnotelist";
	}*/
	
	@RequestMapping(value = "/examlist")
	@Layout("layouts/datatable")
	public String indexAction(Model model) {
		List<ExaminationCategoryView> examCategoryView = examCategoryViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		model.addAttribute("examCategoryView", examCategoryView);
		OrgForm orgForm = new OrgForm();
		orgForm.setOrgId(userIdentity.getOrganisation().getId());
		ExaminationCategoryForm examForm = new ExaminationCategoryForm();
		model.addAttribute("examForm" ,examForm);
		model.addAttribute("orgform", orgForm);
		//model.addAttribute("allergies", aViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		return "consultations/visits/examinationcategory/index";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String addExam(Model model){
		//model.addAttribute("globalItemType", globalItemTypeBo.fetchAllByOrganisation());
		
		List<ExaminationCategory> examCategory = this.examCategoryBo.fetchAllByOrgainsation(userIdentity.getOrganisation().getId());
	    model.addAttribute("examCategory",examCategory);
		model.addAttribute("examCategoryForm", new ExaminationCategoryForm());
		return "consultations/visits/examinationcategory/add";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("examForm") ExaminationCategoryForm examinationForm, 
					   BindingResult result, Model model,
					   RedirectAttributes redirectAttributes){
		
		
		examCategoryBo.save(examinationForm);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS, examinationForm.getName()+" added Successfully!");
		return "redirect:/examinationcategory/examlist";
		

	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editExamAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		List<ExaminationCategoryView> examViewList = this.examCategoryViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		ExaminationCategoryView examView = examCategoryViewBo.getExamViewById(id);
		ExaminationCategory exam = this.examCategoryBo.getExaminationById(id);
		if (null == exam) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
		}
		ExaminationCategoryForm examinationForm = new ExaminationCategoryForm();
		examinationForm.setExamCategoryId(id);
		//System.out.println("the parent");
		//System.out.println("the parent "+ac.getParentId());
		examinationForm.setParentId(exam.getParentId());
		examinationForm.setName(exam.getName());
		/*examinationForm.setMinimumValue(exam.getMinimumValue());
		examinationForm.setMaximumValue(exam.getMaximumValue());*/
		//allergyForm.setCategory(allergy.getCategory());

		model.addAttribute("exam", exam);
		model.addAttribute("examinationForm", examinationForm);
		model.addAttribute("examViewList", examViewList);

		auditor.before(request, "Examination Form", examinationForm);

		return "consultations/visits/examinationcategory/edit";
	}

	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateExamAction(
			@Valid @ModelAttribute("examinationForm") ExaminationCategoryForm examinationForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		/*if (result.hasErrors()) {
			model.addAttribute("categories", this.diseaseCatBo.fetchAllByOrganisation(userIdentity.getUserId()));
			return "admin/medical/components/disease/edit";
		}*/
		
		examCategoryBo.update(examinationForm);
		auditor.after(request, "Examination Form", examinationForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Examination updated");

		return "redirect:/examinationcategory/examlist/";
				
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteExamAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		ExaminationCategory examination = this.examCategoryBo.getExaminationById(id);
	/*	if (null == examination) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/disease/categories";
		}*/

		ExaminationCategoryForm examinationForm = new ExaminationCategoryForm();
		examinationForm.setExamCategoryId(examination.getExamCategoryId());
		examinationForm.setName(examination.getName());

		model.addAttribute("examinationForm", examinationForm);
		model.addAttribute("examination", examination);

		return "consultations/visits/examinationcategory/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String removeExamAction(
			@ModelAttribute("examinationForm") ExaminationCategoryForm examinationForm,
			RedirectAttributes redirectAttributes) {

		ExaminationCategory examination = this.examCategoryBo.getExaminationById(examinationForm.getExamCategoryId());
		System.out.println("the del " + examination.getName() + " " + examination.getExamCategoryId()+ " " + examinationForm.getExamCategoryId());
				
		/*if (null == diseaseCategory) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/disease/categories";
		}
*/
		this.examCategoryBo.delete(examination);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Examination Deleted!");

		return "redirect:/examinationcategory/examlist";
	}
	
	@RequestMapping(value = "/refreshexamination/{organisationId}", method = RequestMethod.GET,consumes="application/json")
	@ResponseBody
	public void refreshallexam(@PathVariable("organisationId") Integer organisationId,Model model)throws HibernateException, SQLException  {
		
		System.out.println("inrefresh");
		
		Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session
		    .connection()
		    .prepareCall("{?= call exam_outerrecursive(?)}");

		  cs.registerOutParameter(1, Types.INTEGER);
		  cs.setInt(2, organisationId);
		  
		  
		  cs.execute();
		  System.out.println(cs.getInt(1));
		  
		  System.out.println("Done with the query");
		  
		;
		
		
		
	}
	
	

}
