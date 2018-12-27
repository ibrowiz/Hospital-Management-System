package org.calminfotech.patient.controllers;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.admin.boInterface.AilmentBo;
import org.calminfotech.admin.boInterface.DrugBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.RelatedPatientBo;
import org.calminfotech.patient.boInterface.RelatedPatientViewBo;
import org.calminfotech.patient.forms.PatientAllergyForm;
import org.calminfotech.patient.forms.PatientForm;
import org.calminfotech.patient.forms.PatientImageForm;
import org.calminfotech.patient.forms.PatientSearchForm;
import org.calminfotech.patient.forms.RelatedPatientForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.patient.models.PatientAllergyView;
import org.calminfotech.patient.models.RelatedPatient;
import org.calminfotech.patient.models.RelatedPatientView;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.user.boInterface.LanguageBo;
import org.calminfotech.user.boInterface.TitleBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.GenderDao;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.MaritalStatusList;
import org.calminfotech.utils.StatesList;
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
@RequestMapping(value = "/relpatient")
public class RelatedPatientController {
	
	@Autowired
	private Auditor auditor;
	
	@Autowired
	private RelatedPatientViewBo relPatViewBo;
	
	
	
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

	
	/*@RequestMapping(value = "/list/{patientId}")
	@Layout("layouts/datatable")
	public String indexAction(@PathVariable("patientId") Integer patientId,Model model,RedirectAttributes redirectAttributes) {

		model.addAttribute("relpatient",
				this.relatedPatientBo.getRelatedPatientByPatientId(patientId));
		 return "customers/patients/index"; 
		return "customers/patients/index";
	}
*/
	
	
	
	
	@RequestMapping(value = "/add/{patientId}")
	@Layout(value = "layouts/form_wizard_layout")
	public String addAction(@PathVariable("patientId") Integer patientId, Model model,
			RedirectAttributes redirectAttributes) {
		//Patient patient = this.patientBo.getPatientById(patientId);
		RelatedPatientForm relatedPatientForm = new RelatedPatientForm();
		relatedPatientForm.setPatientId(patientId);
		model.addAttribute("relatedPatientForm", relatedPatientForm);
		
		return "customers/relatedpatient/add";
	}

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/add/{patientId}", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("relatedPatientForm") RelatedPatientForm relatedPatientForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws Exception {

		/*if (result.hasErrors()) {
			model.addAttribute("titles", this.titleBo.fetchAllByOrganisation());
			model.addAttribute("languages",
					this.languageBo.fetchAllByOrganisation());
			model.addAttribute("genders", this.genderDao.fetchAll());

			model.addAttribute("lgas", this.lgasList.fetchAll());
			model.addAttribute("states", this.stateList.fetchAll());

			return "customers/relatedpatients/add";
		}*/
		RelatedPatient relatedPatient = this.relatedPatientBo.save(relatedPatientForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! New Patient Succesfully Added! Patient id:  "
						+ relatedPatient.getRelPatientId());

		return "redirect:/patients/view/" + relatedPatientForm.getPatientId();
	}

	

	@Layout("layouts/datatable")
	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		
		
		System.out.println("Allergy Patient Id: " +id);
		//Patient patient = this.patientBo.getPatientById(id);
		//List<RelatedPatient> rPatient1 = this.relatedPatientBo.fetchAllByOrganisation();
		Patient pat = this.patientBo.getPatientById(id);
		RelatedPatientView rPatient = this.relPatViewBo.getRelPatViewById(id);
		/*if (null == rPatient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/relpatient";
		}*/
		
		//System.out.println("RelPatient size: " + rPatient.size());
		

		//PatientAllergyForm patientAllergyForm = new PatientAllergyForm();
		//patientAllergyForm.setPatientId(patient.getPatientId());

		//model.addAttribute("aForm", patientAllergyForm);
		//model.addAttribute("categories", this.allergyCategoryBo.fetchAll());
		
		//PatientImageForm piForm = new PatientImageForm();
		//piForm.setId(patient.getPatientId());
		//model.addAttribute("aForm", new AllergyForm());
		
		//model.addAttribute("categories", this.allergyCategoryBo.fetchAll());
		//model.addAttribute("imageForm", piForm);
		model.addAttribute("patient", rPatient);
		model.addAttribute("pat", pat);
		model.addAttribute("id", id);
		
		// System.out.println("size: " + patient.getPatientallergies().size());
		return "customers/relatedpatient/view";
	}

	@RequestMapping(value = "/edit/{patientId}/{relPatientId}")
	public String allergyeditAction(
			@PathVariable("patientId") Integer patientId,
			@PathVariable("relPatientId") Integer relPatientId, Model model,
			RedirectAttributes redirectAttributes,HttpServletRequest request) {

		RelatedPatient rPatient = this.relatedPatientBo.getRelPatientByPatientAndRel(patientId, relPatientId);
		RelatedPatientForm rpatientForm = new RelatedPatientForm();
		rpatientForm.setPatientId(rPatient.getPatient().getPatientId());
		rpatientForm.setRelPatientId(rPatient.getRelPatientId());
		rpatientForm.setRelationship(rPatient.getRelationship());
		
		model.addAttribute("rpatientForm", rpatientForm);
		
		auditor.before(request, "Patient Allergy Form", rpatientForm);
	
		return "customers/relatedpatient/edit";
	}

	
	@RequestMapping(value = "/edit/{patientId}/{relPatientId}", method = RequestMethod.POST)
	public String update(
			@ModelAttribute("rpatientForm") RelatedPatientForm rpatientForm,
			BindingResult result,Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		System.out.println("Information "+rpatientForm.getPatientId()+" "+rpatientForm.getRelationship()+" "+rpatientForm.getRelPatientId()+" "+userIdentity.getUserId()+" "+new GregorianCalendar().getTime());

		relatedPatientBo.update(rpatientForm);
		auditor.after(request, "Patient Allergy Form", rpatientForm,
				userIdentity.getUsername());
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Related Patient updated");

		return "redirect:/patients/view/" + rpatientForm.getPatientId();
	}
	
	@RequestMapping(value = "/delete/{id}/{patientId}")
	public String deleteAllergyAction(@PathVariable("id") Integer id,
			@PathVariable("paientId") Integer patientId,
			
			Model model,
			RedirectAttributes redirectAttributes) {
		RelatedPatient rPatient = this.relatedPatientBo.getRelPatientById(id);
		if (null == rPatient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/patients/view/"+patientId;
		}
		RelatedPatientForm rpatientForm = new RelatedPatientForm();
		
		rpatientForm.setPatientId(rPatient.getPatient().getPatientId());
		rpatientForm.setRelPatientId(rPatient.getRelPatientId());
		model.addAttribute("rpatientForm", rpatientForm);
		model.addAttribute("rPatient", rPatient);
		

		return "customers/relatedpatient/delete";
	}

	@RequestMapping(value = "/delete/{patientId}/{relPatientId}", method = RequestMethod.POST)
	public String confirmAllergyDeleteAction(
			@ModelAttribute("rpatientForm") RelatedPatientForm rpatientForm, Model model,
			RedirectAttributes redirectAttributes) {

		RelatedPatient rPatient = this.relatedPatientBo.getRelPatientByPatientAndRel(rpatientForm.getPatientId(), rpatientForm.getRelPatientId());
		if (null == rPatient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/patients/view/"+rpatientForm.getPatientId();
		}

		relatedPatientBo.delete(rPatient);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! relation Deleted");

		return "redirect:/patients/view/"+rpatientForm.getPatientId();
	}
	/*@Layout("layouts/datatable")
	@RequestMapping(value = "/view/{id}", method = RequestMethod.POST)
	public String saveAllergy(
			@Valid @ModelAttribute("aForm") PatientAllergyForm form,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		Patient patient = this.patientBo.getPatientById(form.getPatientId());

		if (result.hasErrors()) {
			model.addAttribute("patient", patient);
			return "customers/patients/allergy";
		}

		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not save allergy. Invalid resource");
			return "redirect:/patients";
		}

		Allergy allergy = this.allergyBo.getAllergyById(form.getAllergyId());
		if (null == allergy) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! The allergy selected does not exist in the sysem");
			return "redirect:/patients";
		}

		// test first if allergy is already attached to patient
		PatientAllergy pAllergy = this.patientAllergyBo
				.getPatientAllergyByPatientAndAllergy(patient, allergy);
		if (null != pAllergy) {
			// That means it exist and cannot save
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Patient already has allergy saved on profile");
			return "redirect:/patients/view/" + patient.getPatientId();
		}

		this.patientAllergyBo.save(form);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! saved successfully");

		return "redirect:/patients/view/" + patient.getPatientId();
	}*/
	
}
