package org.calminfotech.patient.controllers;

import java.util.List;

import org.calminfotech.admin.boInterface.AilmentBo;
import org.calminfotech.admin.boInterface.DrugBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientSearchBo;
import org.calminfotech.patient.boInterface.RelatedPatientBo;
import org.calminfotech.patient.forms.PatientSearchForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.user.boInterface.LanguageBo;
import org.calminfotech.user.boInterface.TitleBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.GenderDao;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.MaritalStatusList;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchRelPatientController {
	

	
	@Autowired
	private RelatedPatientBo relatedPatientBo;
	
	@Autowired
	UserIdentity userIdentity;
	
	@Autowired
	private PatientBo patientBo;
	
	@Autowired
	private LocalGovernmentAreaList lgasList;

	@Autowired
	private MaritalStatusList MSList;

	@Autowired
	private StatesList stateList;
	@Autowired
	private TitleBo titleBo;

	@Autowired
	private LanguageBo languageBo;

	@Autowired
	private AilmentBo ailmentBo;

	@Autowired
	private DrugBo drugBo;

	@Autowired
	private GenderDao genderDao;
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private PatientSearchBo searchBo;
	
	@RequestMapping(value = "/getRelatedPatient", method = RequestMethod.GET)
	@Layout(value = "layouts/blank")
	public String patientSearch( Model model) {
		model.addAttribute("patientstable",
				this.patientBo.fetchAllByOrganisation());
		model.addAttribute("patientSearch", new PatientSearchForm());
		return "customers/relatedpatient/searchrelation";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@Layout(value = "layouts/blank")
	public String searchPatient(
			@ModelAttribute("patientSearch") PatientSearchForm searchForm,
			Model model) {

		List patientList = searchBo.searchPatient(searchForm);
		model.addAttribute("patient", patientList);
		return "customers/relatedpatient/searchrelresult";
	}

}
