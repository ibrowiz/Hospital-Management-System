package org.calminfotech.consultation.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.calminfotech.admin.boInterface.DrugBo;
import org.calminfotech.consultation.bo.DoctorBo;
import org.calminfotech.consultation.bo.VisitBo;
import org.calminfotech.consultation.forms.SearchQueueForm;
import org.calminfotech.consultation.forms.VisitDoctorForm;
import org.calminfotech.consultation.forms.VisitWorkflowUserConfigurationForm;
import org.calminfotech.consultation.models.Doctor;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.lab.forms.LabTestForm;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.patient.boInterface.PatientAllergyBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientDocumentBo;
import org.calminfotech.patient.boInterface.PatientFamilyHistoryBo;
import org.calminfotech.patient.boInterface.PatientMedicalHistoryBo;
import org.calminfotech.patient.boInterface.PatientSocialHistoryBo;
import org.calminfotech.patient.boInterface.PatientSurgicalHistoryBo;
import org.calminfotech.patient.forms.PatientDocumentForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.patient.models.PatientDocument;
import org.calminfotech.patient.models.PatientFamilyHistory;
import org.calminfotech.patient.models.PatientMedicalHistory;
import org.calminfotech.patient.models.PatientSocialHistory;
import org.calminfotech.patient.models.PatientSurgicalHistory;
import org.calminfotech.setup.boInterface.AllergyBo;
import org.calminfotech.system.boInterface.VisitStatusBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.annotations.Layout;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
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
@RequestMapping(value = "/consultations/visits/doctor")
public class DoctorHomePageController {
	
	@Autowired
	private Auditor auditor;
	
	@Autowired
	private VisitBo visitBo;
	
	@Autowired
	private DoctorBo doctorBo;
	
	@Autowired
	private PatientDocumentBo patientDocumentBo;
	
	@Autowired
	PatientSocialHistoryBo patientSocialHistoryBo;
	
	@Autowired
	PatientMedicalHistoryBo patientMedicalHistoryBo;
	
	@Autowired
	private PatientFamilyHistoryBo patientFamilyHistoryBo;
	@Autowired
	private PatientBo patientBo;

	@Autowired
	private DrugBo drugBo;
	

	
	@Autowired
	private PatientAllergyBo patientAllergyBo;

	@Autowired
	private VisitWorkflowPointBo wfPointBo;

	@Autowired
	private UserBo userBo;
	
	@Autowired
	private AllergyBo allergyBo;

	@Autowired
	private VisitStatusBo visitStatusBo;
	
	@Autowired
	PatientSurgicalHistoryBo patientSurgicalHistoryBo;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Alert alert;
	
	
	@Layout("layouts/datatable")
	@RequestMapping(value = { "", "/index" })
	public String indexAction(Model model) {
		// Use the side to display the list of current visits based on the role
		// of person logged in
	/*	model.addAttribute("visits", this.visitBo.fetchAllByWorkflowPoint(
				VisitWorkflowPoint.PHARMACY,
				this.visitStatusBo.getNotEndPointStatus()));*/
		
		
		return "consultations/visits/doctor/index";
	}
	
	@RequestMapping(value = "/searchdocqueue")
	@Layout(value = "layouts/form_wizard_layout")
	public String searchAction(
			@Valid @ModelAttribute("searchQueueForm") SearchQueueForm searchQueueForm,
			BindingResult result,Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		// Use the side to display the list of current visits based on the role
		// of person logged in
		Date date = new Date();
		//date.toString()
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(date);
		System.out.println(date);
		
		searchQueueForm.setFrom(formattedDate);
		searchQueueForm.setTo(formattedDate);
		searchQueueForm.setMstatus(2);
		model.addAttribute("statusList",  visitStatusBo.fetchAll());
		model.addAttribute("searchQueueForm",searchQueueForm);
		System.out.println(userIdentity.getUserId());
		return "consultations/visits/doctor/searchqueue";
		
	}
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/searchdocqueueresult1")
	public String Labqueue(Model model,@Valid @ModelAttribute("searchQueueForm") SearchQueueForm searchQueueForm,
			BindingResult result,
			RedirectAttributes redirectAttributes) throws java.text.ParseException {
		
		
	//int uid =userIdentity.getUserId();
		/*if (searchQueueForm.getChkothers()!= null)
				{
			
			//uid = (Integer) null;
			uid = 50;
				}*/
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateFrom = searchQueueForm.getFrom();
		String dateTo = searchQueueForm.getTo();
		try {
			

			Date date1 = formatter.parse(dateFrom);
			Date date2 = formatter.parse(dateTo);
			model.addAttribute("queue", this.visitBo.fetchAllByThese(userIdentity.getUserId(), date1,date2, searchQueueForm.getMstatus(), searchQueueForm.getChkothers()));
			System.out.println("iam here");
			
			System.out.println(userIdentity.getUserId());
			System.out.println(searchQueueForm.getFrom());
			System.out.println(searchQueueForm.getTo());
			System.out.println("value of open her");
			System.out.println(searchQueueForm.getMstatus());
			System.out.println(searchQueueForm.getChkothers());
			System.out.println("value of open stop");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "consultations/visits/doctor/searchqueueresult";
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/view/consult/{id}")
	@Layout(value = "layouts/default")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Visit visit = this.visitBo.getVisitationById(id);
	Patient patient = this.patientBo.getPatientById(visit.getPatient().getPatientId());
	System.out.println("patid" + patient.getPatientId());
	List<PatientAllergy> patientAllergy = this.patientAllergyBo.fetchAllpAllergiesByPatient(patient.getPatientId());
	System.out.println("pallergysize" + patientAllergy.size());
	// List<Allergy> allergy = this.allergyBo.fetchAllergyById(3);
	 //System.out.println("allergysize" + allergy.size());
	
		List<Visit> visit1 = this.visitBo.fetchAll(patient);
		VisitDoctorForm visitDoctorForm = new VisitDoctorForm(); 
		visitDoctorForm.setVisitId(id);	//Visit visit1 = this.visitBo.getVisitByPatient(patient);
		visitDoctorForm.setPatientId(patient.getPatientId());
		List<PatientFamilyHistory> patientFamilyHistory = this.patientFamilyHistoryBo.fetchAllByPatient(patient.getPatientId());
		List<PatientSocialHistory> patientSocialHistory = this.patientSocialHistoryBo.fetchAllByPatient(patient.getPatientId());
		List<PatientSurgicalHistory> patientSurgicalHistory = this.patientSurgicalHistoryBo.fetchAllByPatient(patient.getPatientId());
		List<PatientMedicalHistory>  patientMedHist  = this.patientMedicalHistoryBo.fetchAllByPatient(patient.getPatientId());
		System.out.println("Visit is here :" + visit.getId() + " Discription :"
				+ visit.getCode());
		if (null == visit) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/consultations";
		}
		VisitWorkflowUserConfigurationForm vForm = new VisitWorkflowUserConfigurationForm();
		//VisitDoctorForm visitDoctorForm = new VisitDoctorForm();
		model.addAttribute("vForm", vForm);
		model.addAttribute("patientMedHist", patientMedHist);
		model.addAttribute("visit", visit);
		model.addAttribute("visit1", visit1);
		model.addAttribute("visitDoctorForm", visitDoctorForm);
		//model.addAttribute("allergy", allergy);
		model.addAttribute("id", id);
		model.addAttribute("patient", patient);
		model.addAttribute("patientAllergy", patientAllergy);
		model.addAttribute("patientFamilyHistory", patientFamilyHistory);
		model.addAttribute("patientSocialHistory", patientSocialHistory);
		model.addAttribute("patientSurgicalHistory", patientSurgicalHistory);
		return "consultations/visits/doctor/index";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("visitDoctorForm") VisitDoctorForm visitDoctorForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		/*if (result.hasErrors()) {
			model.addAttribute("categories", this.allergyCategoryBo.fetchAll());
			return "admin/medical/components/allergies/add";
		}*/
		Doctor doctor = new Doctor();
		doctor.setVisitId(visitDoctorForm.getVisitId());
		doctor.setComment(visitDoctorForm.getComment());
		doctor.setOrganisationId(userIdentity.getOrganisation().getId());
		doctor.setCreateDate(new Date(System.currentTimeMillis()));
		doctor.setCreatedBy(userIdentity.getUserId());
         
		this.doctorBo.save(doctor);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Comment added!");
		return "redirect:/consultations/visits/doctor/view/consult/"+visitDoctorForm.getVisitId();
	}
	
	@Layout("layouts/blank")
	@RequestMapping(value = "/clerkindocuments/{id}", method = RequestMethod.GET)
	public String documentAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Patient patient = this.patientBo.getPatientById(id);
		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/patients";
		}
		PatientDocumentForm pdForm = new PatientDocumentForm();
		pdForm.setPatientId(patient.getPatientId());

		model.addAttribute("documentForm", pdForm);
		model.addAttribute("patient", patient);

		return "consultations/visits/doctor/documents";
	}

	@RequestMapping(value = "/clerkindocuments/{id}", method = RequestMethod.POST)
	public String saveDocument(
			@Valid @ModelAttribute("documentForm") PatientDocumentForm patientDocumentForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		Patient patient = this.patientBo.getPatientById(patientDocumentForm
				.getPatientId());

		if (result.hasErrors()) {
			model.addAttribute("patient", patient);
			return "consultations/visits/doctor/documents";
		}

		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not upload document. Invalid resource");
			return "redirect:/patients";
		}

		PatientDocument patientDocument = new PatientDocument();
		try {
			@SuppressWarnings("deprecation")
			Blob blob = Hibernate.createBlob(patientDocumentForm.getDocument()
					.getInputStream());
			patientDocument.setFile(blob);

			String contentType = patientDocumentForm.getDocument()
					.getContentType();

			patientDocument.setContentType(contentType);
			patientDocument.setName(patientDocumentForm.getDocument()
					.getOriginalFilename());
			patientDocument.setPatient(patient);
			patientDocument.setOrganisation(patient.getOrganisation());
			patientDocument.setCreateDate(new GregorianCalendar().getTime());
			patientDocument.setIsDeleted(false);
			patient.getPatientDocuments().add(patientDocument);
			

			// Used for saving
			this.patientDocumentBo.save(patientDocument);

			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Image Uploaded successfully");

		} catch (IOException e) {
			e.printStackTrace();
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Document Upload failed");
		}

		return "redirect:/consultations/visits/doctor/clerkinpatients/documents/" + patient.getPatientId();
	}

	@ResponseBody
	@RequestMapping(value = "/clerkindocuments/view/{documentId}/{documentName}")
	public String viewDocumentAction(@PathVariable Integer documentId,
			@PathVariable("documentName") String documentName,
			RedirectAttributes redirectAttributes, HttpServletResponse response) {

		PatientDocument patientDocument = this.patientDocumentBo
				.getPatientDocumentById(documentId);

		if (null == patientDocument) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/patients";
		}

		try {
			response.setContentType(patientDocument.getContentType());

			response.setHeader("Content-Disposition", "inline;filename=\""
					+ patientDocument.getName() + "\"");

			OutputStream outputStream = response.getOutputStream();

			IOUtils.copy(patientDocument.getFile().getBinaryStream(),
					outputStream);

			outputStream.flush();
			outputStream.close();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Layout("layouts/blank")
	@RequestMapping(value = "/clerkindocuments/delete/{id}")
	public String documentDeleteAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		PatientDocument patientDocument = this.patientDocumentBo
				.getPatientDocumentById(id);

		if (null == patientDocument) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/patients";
		}

		PatientDocumentForm patientDocumentForm = new PatientDocumentForm();
		patientDocumentForm.setPatientDocumentId(patientDocument.getPatientDocumentsId());

		model.addAttribute("document", patientDocument);
		model.addAttribute("pForm", patientDocumentForm);

		return "consultations/visits/doctor/delete_document";
	}

	@RequestMapping(value = "/clerkindocuments/delete/{id}", method = RequestMethod.POST)
	public String confirmDocumentDeleteAction(
			@ModelAttribute("pForm") PatientDocumentForm patientDocumentForm,
			RedirectAttributes redirectAttributes) {

		PatientDocument patientDocument = this.patientDocumentBo
				.getPatientDocumentById(patientDocumentForm.getPatientDocumentId());

		if (null == patientDocument) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/patients";
		}

		int patientId = patientDocument.getPatient().getPatientId();
		
		this.patientDocumentBo.delete(patientDocument);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! File deleted");

		return "redirect:/consultations/visits/doctor/clerkinpatients/documents/" + patientId;
	}

	
	
	@RequestMapping(value = "/clerkinsearchqueue")
	@Layout(value = "layouts/blank")
	public String searchclerkingAction(
			@Valid @ModelAttribute("searchQueueForm") SearchQueueForm searchQueueForm,
			BindingResult result,Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		// Use the side to display the list of current visits based on the role
		// of person logged in
		Date date = new Date();
		//date.toString()
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(date);
		System.out.println(date);
		
		searchQueueForm.setFrom(formattedDate);
		//searchQueueForm.setVisitId(visitId)
		searchQueueForm.setTo(formattedDate);
		searchQueueForm.setMstatus(2);
		model.addAttribute("statusList",  visitStatusBo.fetchAll());
		model.addAttribute("searchQueueForm",searchQueueForm);
		System.out.println(userIdentity.getUserId());
		return "consultations/visits/searchqueue";
		
	}

	
	
	
	@Layout("layouts/blank")
	@RequestMapping(value = "/clerkinViewtest/{vid}")
	public String viewTest(@PathVariable("vid") Integer vid,Model model){
		
		Visit g =this.visitBo.getVisitationById(vid);
		 model.addAttribute("queue", g);
		return "consultations/visits/viewtest";
	}

	@Layout("layouts/blank")
	@RequestMapping(value = "/searchClerkinqueueresult")
	public String indexLab(Model model) {
		// Use the side to display the list of current visits based on the role
		// of person logged in
		//model.addAttribute("pForm", new PatientForm());
		//model.addAttribute("searchForm", new SearchQueueForm());
		model.addAttribute("queue", this.visitBo.fetchAllByUId(userIdentity.getUserId()));
		return "consultations/visits/searchqueueresult";
	}	
	
	@RequestMapping(value = "/Labsearchqueue/{patientId}")
	@Layout(value = "layouts/blank")
	public String searchLabAction(@PathVariable("patientId") Integer patientId,
			@Valid @ModelAttribute("searchQueueForm") SearchQueueForm searchQueueForm,
			BindingResult result,Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		// Use the side to display the list of current visits based on the role
		// of person logged in
		Date date = new Date();
		//date.toString()
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(date);
		System.out.println(date);
		searchQueueForm.setFrom(formattedDate);
		searchQueueForm.setTo(formattedDate);
		searchQueueForm.setPatientId(patientId);
		searchQueueForm.setMstatus(2);
		model.addAttribute("statusList",  visitStatusBo.fetchAll());
		model.addAttribute("searchQueueForm",searchQueueForm);
		System.out.println(userIdentity.getUserId());
		return "consultations/visits/doctor/searchClerkinLabqueue";
		
	}
	
	@Layout("layouts/blank")
	@RequestMapping(value = "/searchClerkinLabQueueresult1")
	public String clerkinLabQueueResult(Model model,@Valid @ModelAttribute("searchQueueForm") SearchQueueForm searchQueueForm,
			BindingResult result,
			RedirectAttributes redirectAttributes) throws java.text.ParseException {
		
		
	//int uid =userIdentity.getUserId();
		/*if (searchQueueForm.getChkothers()!= null)
				{
			
			//uid = (Integer) null;
			uid = 50;
				}*/
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateFrom = searchQueueForm.getFrom();
		String dateTo = searchQueueForm.getTo();
		System.out.println("patidis" + searchQueueForm.getPatientId());
		try {
			

			Date date1 = formatter.parse(dateFrom);
			Date date2 = formatter.parse(dateTo);
			model.addAttribute("queue", this.visitBo.fetchAllLabByThese(userIdentity.getUserId(), searchQueueForm.getPatientId(), date1,date2, searchQueueForm.getMstatus(), searchQueueForm.getChkothers()));
			System.out.println("iam here");
			
			System.out.println(userIdentity.getUserId());
			System.out.println(searchQueueForm.getFrom());
			System.out.println(searchQueueForm.getTo());
			System.out.println("value of open her");
			System.out.println(searchQueueForm.getMstatus());
			System.out.println(searchQueueForm.getChkothers());
			System.out.println("value of open stop");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "consultations/visits/searchqueueresult";
	}

	@Layout("layouts/blank")
	@RequestMapping(value = "/searchClerkinqueueresult1")
	public String clerkinLabqueue(Model model,@Valid @ModelAttribute("searchQueueForm") SearchQueueForm searchQueueForm,
			BindingResult result,
			RedirectAttributes redirectAttributes) throws java.text.ParseException {
		
		
	//int uid =userIdentity.getUserId();
		/*if (searchQueueForm.getChkothers()!= null)
				{
			
			//uid = (Integer) null;
			uid = 50;
				}*/
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateFrom = searchQueueForm.getFrom();
		String dateTo = searchQueueForm.getTo();
		try {
			

			Date date1 = formatter.parse(dateFrom);
			Date date2 = formatter.parse(dateTo);
			model.addAttribute("queue", this.visitBo.fetchAllByThese(userIdentity.getUserId(), date1,date2, searchQueueForm.getMstatus(), searchQueueForm.getChkothers()));
			System.out.println("iam here");
			
			System.out.println(userIdentity.getUserId());
			System.out.println(searchQueueForm.getFrom());
			System.out.println(searchQueueForm.getTo());
			System.out.println("value of open her");
			System.out.println(searchQueueForm.getMstatus());
			System.out.println(searchQueueForm.getChkothers());
			System.out.println("value of open stop");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "consultations/visits/searchqueueresult";
	}
	
	

	@RequestMapping(value = "/listDoctorsNote/{visitId}")
	@Layout("layouts/blank")
	public String listNote(@PathVariable("visitId") Integer visitId,
			Model model,RedirectAttributes redirectAttributes) {

		
		VisitDoctorForm visitDoctorForm = new VisitDoctorForm();
		visitDoctorForm.setVisitId(visitId);
		model.addAttribute("visitDoctorForm" ,visitDoctorForm);
		model.addAttribute("noteList", this.doctorBo.fetchAllByUIdAndOrg(userIdentity.getUserId(),userIdentity.getOrganisation().getId() ));
		return "consultations/visits/doctor/doctorsnotelist";
	}
	
	//consultations/visits/doctor/edit/{id}
	
	@Layout(value = "layouts/blank")
	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		//LabTest labTest = this.labTestBo.getLabtestById(id);
		Doctor doctor = this.doctorBo.getDoctorById(id);
		

		//LabTestForm labTestForm = new LabTestForm();
		VisitDoctorForm visitDoctorForm = new VisitDoctorForm();
		System.out.println("doccomment " + doctor.getComment());
		visitDoctorForm.setVisitId(doctor.getVisitId());
		visitDoctorForm.setComment(doctor.getComment());
		visitDoctorForm.setSummaryId(doctor.getSummaryId());
		
		model.addAttribute("visitDoctorForm", visitDoctorForm);
		//model.addAttribute("list", this.labTestCatBo.fetchAllCatByOrganisation(userIdentity.getOrganisation().getId()));
		auditor.before(request, "Doctor Form", visitDoctorForm);
		return "consultations/visits/doctor/edit";

	}
	
	@RequestMapping(value = "/update")
	public String updateAction(
			@Valid @ModelAttribute("visitDoctorForm") VisitDoctorForm visitDoctorForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		
		this.doctorBo.update(visitDoctorForm);
		auditor.after(request, "visitDoctorForm", visitDoctorForm,
				userIdentity.getUsername());
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! updated");
		//return "redirect:/consultations/visits/doctor/listDoctorsNote/"+visitDoctorForm.getVisitId();
		return "redirect:/consultations/visits/doctor/edit/"+visitDoctorForm.getSummaryId();
		//return "consultations/visits/doctor/index;
	}
	
	
}
