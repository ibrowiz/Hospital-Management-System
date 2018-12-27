package org.calminfotech.patient.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.calminfotech.admin.boInterface.AilmentBo;
import org.calminfotech.admin.boInterface.DrugBo;
import org.calminfotech.admin.boInterface.HmoBo;
import org.calminfotech.patient.boInterface.PatientAllergyBo;
import org.calminfotech.patient.boInterface.PatientAllergyViewBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.PatientDocumentBo;
import org.calminfotech.patient.boInterface.PatientDocumentBo;
import org.calminfotech.patient.boInterface.PatientEmergencyBo;
import org.calminfotech.patient.boInterface.PatientFamilyHistoryBo;
import org.calminfotech.patient.boInterface.PatientHmoPackageBo;
import org.calminfotech.patient.boInterface.PatientMedicalHistoryBo;
import org.calminfotech.patient.boInterface.PatientPaymentOptionBo;
import org.calminfotech.patient.boInterface.PatientSearchBo;
import org.calminfotech.patient.boInterface.PatientSocialHistoryBo;
import org.calminfotech.patient.boInterface.PatientSurgicalHistoryBo;
import org.calminfotech.patient.boInterface.RelatedPatientViewBo;
import org.calminfotech.patient.forms.PatientAllergyForm;
import org.calminfotech.patient.forms.PatientDocumentForm;
import org.calminfotech.patient.forms.PatientDocumentForm;
import org.calminfotech.patient.forms.PatientEmergencyForm;
import org.calminfotech.patient.forms.PatientFamilyHistoryForm;
import org.calminfotech.patient.forms.PatientForm;
import org.calminfotech.patient.forms.PatientForm;
import org.calminfotech.patient.forms.PatientHmoForm;
import org.calminfotech.patient.forms.PatientImageForm;
import org.calminfotech.patient.forms.PatientMedicalHistoryForm;
import org.calminfotech.patient.forms.PatientPaymentOptionForm;
import org.calminfotech.patient.forms.PatientSearchForm;
import org.calminfotech.patient.forms.PatientSocialHistoryForm;
import org.calminfotech.patient.forms.PatientSurgicalHistoryForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.patient.models.PatientAllergyView;
import org.calminfotech.patient.models.PatientDocument;
import org.calminfotech.patient.models.PatientDocument;
import org.calminfotech.patient.models.PatientEmergency;
import org.calminfotech.patient.models.PatientFamilyHistory;
import org.calminfotech.patient.models.PatientHmoPackage;
import org.calminfotech.patient.models.PatientMedicalHistory;
import org.calminfotech.patient.models.PatientPaymentOption;
import org.calminfotech.patient.models.PatientSocialHistory;
import org.calminfotech.patient.models.PatientSurgicalHistory;
import org.calminfotech.patient.models.RelatedPatientView;
import org.calminfotech.setup.boInterface.AllergyBo;
import org.calminfotech.setup.boInterface.AllergyCategoryBo;
import org.calminfotech.setup.boInterface.AllergyCategoryBo;
import org.calminfotech.setup.forms.AllergyForm;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.system.boInterface.BillingSchemeBo;
import org.calminfotech.system.boInterface.BillingSchemeItemBo;
//import org.calminfotech.system.boInterface.DepartmentBo;
import org.calminfotech.system.boInterface.EHmoPackagesBo;

import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.user.boInterface.LanguageBo;
import org.calminfotech.user.boInterface.TitleBo;
import org.calminfotech.user.models.Title;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.GenderDao;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.MaritalStatusList;
import org.calminfotech.utils.PaymentSchemeItemList;
import org.calminfotech.utils.PaymentSchemeList;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.SurgicalList;
import org.calminfotech.utils.annotations.Layout;
import org.calminfotech.utils.models.MaritalStatus;
import org.hibernate.Hibernate;
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
@RequestMapping(value = "/patients")
public class PatientsController1 {
	
	//@Autowired
	//private DepartmentBo departmentBo;
	

	@Autowired
	private PatientAllergyViewBo pallergyViewBo;
	
	@Autowired
	private PatientBo patientBo;

	@Autowired
	private AllergyBo allergyBo;
	
	@Autowired
	private RelatedPatientViewBo relatedPatientViewBo;

	@Autowired
	private AllergyCategoryBo allergyCategoryBo;

	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Auditor auditor;

	@Autowired
	private LocalGovernmentAreaList lgasList;

	@Autowired
	private MaritalStatusList MSList;

	@Autowired
	private StatesList stateList;

	@Autowired
	private HmoBo hmoBo;

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
	private SurgicalList surgicalList;

	@Autowired
	private PatientDocumentBo patientDocumentBo;

	@Autowired
	private PatientEmergencyBo patientEmergencyBo;

	@Autowired
	private PatientAllergyBo patientAllergyBo;

	@Autowired
	private PatientMedicalHistoryBo patientMedicalHistoryBo;

	@Autowired
	private PatientFamilyHistoryBo patientFamilyHistoryBo;

	@Autowired
	private PatientHmoPackageBo patientHmoPackageBo;

	@Autowired
	private EHmoPackagesBo organisationHmoPackageBo;

	@Autowired
	private BillingSchemeBo organisationPaymentSchemeBo;

	@Autowired
	private BillingSchemeItemBo organisationPaymentSchemeItemBo;

	@Autowired
	private PaymentSchemeList paymentSchemeList;
	
	@Autowired
	private PaymentSchemeItemList paymentSchemeItemList;

	@Autowired
	private PatientPaymentOptionBo patientPaymentOptionBo;

	@Autowired
	private PatientSearchBo searchBo;

	@Autowired
	private PatientSurgicalHistoryBo patientSurgicalHistoryBo;

	@Autowired
	private PatientSocialHistoryBo patientSocialHistoryBo;
	
	@RequestMapping(value = { "/list"})
	@Layout("layouts/datatable")
	public String listall(Model model) {
		List<Patient> plist = this.patientBo.fetchAllByOrganisation();
		model.addAttribute("plist",plist);
		model.addAttribute("patientSearch", new PatientSearchForm());
		return "customers/patients/list";
	}

	@RequestMapping(value = { "", "/" })
	@Layout("layouts/datatable")
	public String indexAction(Model model) {

		model.addAttribute("patientstable",
				this.patientBo.fetchAllByOrganisation());
		model.addAttribute("patientSearch", new PatientSearchForm());
		List<Patient> plist = this.patientBo.fetchAllByOrganisation();
		model.addAttribute("patient",plist);
		return "customers/patients/index";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@Layout("layouts/datatable")
	public String searchPatient(
			@ModelAttribute("patientSearch") PatientSearchForm searchForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		List patientList = searchBo.searchPatient(searchForm);
		model.addAttribute("patient", patientList);
		return "customers/patients/index";
	}

	@RequestMapping(value = "/add")
	@Layout(value = "layouts/form_wizard_layout")
	public String addAction(Model model) {

		model.addAttribute("pForm", new PatientForm());
		model.addAttribute("titles", this.titleBo.fetchAll());
		model.addAttribute("language", this.languageBo.fetchAllByOrganisation());
		model.addAttribute("genders", this.genderDao.fetchAll());

		model.addAttribute("lgas", this.lgasList.fetchAll());
		model.addAttribute("states", this.stateList.fetchAll());
		model.addAttribute("maritalstat", this.MSList.fetchAll());

		return "customers/patients/add";
	}

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("pForm") PatientForm patientForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("titles", this.titleBo.fetchAllByOrganisation());
			model.addAttribute("languages",
					this.languageBo.fetchAllByOrganisation());
			model.addAttribute("genders", this.genderDao.fetchAll());

			model.addAttribute("lgas", this.lgasList.fetchAll());
			model.addAttribute("states", this.stateList.fetchAll());

			return "customers/patients/add";
		}

		Patient patient = this.patientBo.save(patientForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! New Patient Succesfully Added! Patient id:  "
						+ patient.getPatientId());

		return "redirect:/patients/view/" + patient.getPatientId();
	}
	
	
	
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Patient patient = this.patientBo.getPatientById(id);
		
		List<PatientAllergyView> pAllergyview = this.pallergyViewBo.getPatientAllergyByPatientId(id);
		List<RelatedPatientView> relatedPatient = this.relatedPatientViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId(),id);
		/*if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/patients";
		}
		
		System.out.println("Allergy Patient Id: " + patient.getPatientId());
		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/patients";
		}*/

		PatientAllergyForm patientAllergyForm = new PatientAllergyForm();
		patientAllergyForm.setPatientId(patient.getPatientId());

		model.addAttribute("aForm", patientAllergyForm);
		model.addAttribute("categories", this.allergyCategoryBo.fetchAll());
		model.addAttribute("pAllergyview", pAllergyview);
		model.addAttribute("relatedPatient",relatedPatient);
		PatientImageForm piForm = new PatientImageForm();
		piForm.setId(patient.getPatientId());
		//model.addAttribute("aForm", new AllergyForm());
		//model.addAttribute("list",  departmentBo.fetchAllDept());
		model.addAttribute("allergies", this.allergyBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		model.addAttribute("imageForm", piForm);
		model.addAttribute("patient", patient);
		model.addAttribute("id", id);
		
		// System.out.println("size: " + patient.getPatientallergies().size());
		return "customers/patients/view";
	}

	
	@Layout("layouts/datatable")
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
		/*PatientAllergy pAllergy = this.patientAllergyBo
				.getPatientAllergyByPatientAndAllergy(patient, allergy);
		if (null != pAllergy) {
			// That means it exist and cannot save
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Patient already has allergy saved on profile");
			return "redirect:/patients/view/" + patient.getPatientId();
		}*/

		this.patientAllergyBo.save(form);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! saved successfully");

		return "redirect:/patients/view/" + patient.getPatientId();
	}
		
	
	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		Patient patient = this.patientBo.getPatientById(id);
		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}
		System.out.println("the status is " + patient.getStatus());
		PatientForm patientForm = new PatientForm();
		patientForm.setPatientId(patient.getPatientId());
		patientForm.setAddress(patient.getAddress());
		patientForm.setEmail(patient.getEmail());
		patientForm.setOthernames(patient.getOthernames());
		patientForm.setFirstName(patient.getFirstName());
		patientForm.setStatusId(patient.getMaritalStatus().getId());
		patientForm.setSurname(patient.getSurname());
		patientForm.setGenotype(patient.getGenotype());
		patientForm.setBldgrp(patient.getBldgrp());
		patientForm.setHeight(patient.getHeight());
		/*patientForm.setHeightFeet(patient.getHeightFeet());
		patientForm.setHeightInch(patient.getHeightInch());*/
		patientForm.setPhoneNumber(patient.getPhoneNumber());
		patientForm.setHomeNumber(patient.getHomeNumber());
		patientForm.setTitleId(patient.getTitle().getId());
		patientForm.setGenderId(patient.getGender().getId());
		patientForm.setStateId(patient.getState().getStateId());
		patientForm.setLgaId(patient.getLga().getLocalGovernmentAreaId());
		patientForm.setLanguageId(patient.getLanguage().getId());
		patientForm.setDob(patient.getDob());
		patientForm.setOccupation(patient.getOccupation());
		//patientForm.setStatusId(patient.getMaritalStatus().getId());
		patientForm.setStatus(patient.getStatus());

		model.addAttribute("pForm", patientForm);
		model.addAttribute("titles", this.titleBo.fetchAllByOrganisation());
		model.addAttribute("genders", this.genderDao.fetchAll());
		model.addAttribute("lgas", this.lgasList.fetchAll());
		model.addAttribute("states", this.stateList.fetchAll());
		model.addAttribute("maritalstat", this.MSList.fetchAll());
		model.addAttribute("language", this.languageBo.fetchAll());
		auditor.before(request, "Patient Form", patientForm);

		return "customers/patients/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("pForm") PatientForm patientForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("titles", this.titleBo.fetchAllByOrganisation());
			System.out.println("errror!");
			return "customers/patients/edit";
		}

		this.patientBo.update(patientForm);
		auditor.after(request, "Patient Form", patientForm,
				userIdentity.getUsername());
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Patient profile updated");
		return "redirect:/patients/view/" + patientForm.getPatientId();
	}

	
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public String processImage(
			@ModelAttribute("imageForm") PatientImageForm imageForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		Patient patient = this.patientBo.getPatientById(imageForm.getId());

		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not upload image. Invalid resource");
			return "redirect:/patients";
		}

		try {
			@SuppressWarnings("deprecation")
			Blob blob = Hibernate.createBlob(imageForm.getImageFile()
					.getInputStream());
			patient.setImage(blob);

			String contentType = imageForm.getImageFile().getContentType();
			patient.setImageContentType(contentType);
			// userProfile.setModifiedDate(new
			// Date(System.currentTimeMillis()));

			// Used for updating image and image content type only
			this.patientBo.update(patient);

			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Image Uploaded successfully");

		} catch (IOException e) {
			e.printStackTrace();
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Image Upload failed");
		}

		return "redirect:/patients/view/" + patient.getPatientId();
	}

	@RequestMapping(value = "/patientallergy/edit/{id}/{patientAllergyId}")
	public String editAllergyAction(@PathVariable("id") Integer id,
			@PathVariable("patientAllergyId") int patientAllergyId,
			Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		//PatientAllergy patientallergy = this.patientAllergyBo.getPatientAllergyById(id);
		PatientAllergy patientallergy = this.patientAllergyBo.getByIdAndPallergyId(id, patientAllergyId);
		System.out.println("Reactions "+patientallergy.getReactions());
		/*if (null == patientallergy) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}*/

		PatientAllergyForm patientAllergyForm = new PatientAllergyForm();
		System.out.println("pallergyid "+patientallergy.getId());
		patientAllergyForm.setId(patientallergy.getId());
		patientAllergyForm.setPatientId(patientallergy.getPatient().getPatientId());
		patientAllergyForm.setAllergyId(patientallergy.getAllergyId());
		patientAllergyForm.setDescription(patientallergy.getDescription());
		patientAllergyForm.setReactions(patientallergy.getReactions());
		model.addAttribute("allergies", this.allergyBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		model.addAttribute("pAllergyForm", patientAllergyForm);
		auditor.before(request, "Patient Allergy Form", patientAllergyForm);

		return "customers/patientallergy/edit";
	}
	
	@RequestMapping(value = "/patientallergy/edit/{id}/{patientAllergyId}", method = RequestMethod.POST)
	public String update(
			@ModelAttribute("pAllergyForm") PatientAllergyForm pAllergyForm,
			BindingResult result,Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		

		patientAllergyBo.update(pAllergyForm);
		auditor.after(request, "Patient Allergy Form", pAllergyForm,
				userIdentity.getUsername());
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Patient Allergy updated");

		return "redirect:/patients/view/"+pAllergyForm.getPatientId();
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Patient patient = this.patientBo.getPatientById(id);
		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/patients/view/"+id;
		}
		PatientForm patientForm = new PatientForm();
		patientForm.setPatientId(patient.getPatientId());
		model.addAttribute("patientForm", patientForm);
		model.addAttribute("patient", patient);
		

		return "customers/patients/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("patientForm") PatientForm patientForm, Model model,
			RedirectAttributes redirectAttributes) {

		Patient patient = this.patientBo.getPatientById(patientForm.getPatientId());
		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/patients/view/"+patientForm.getPatientId();
		}

		patientBo.delete(patient);
		alert.setAlert(redirectAttributes, Alert.ERROR,
				"Success! Patient Deleted");

		return "redirect:/patients";
	}
	
	@RequestMapping(value = "/patientallergy/delete/{id}/{patientId}")
	public String deleteAllergyAction(@PathVariable("id") Integer id,
			@PathVariable("patientId") Integer patientId,
			Model model,
			RedirectAttributes redirectAttributes) {
		PatientAllergy pAllergy = this.patientAllergyBo.getPatientAllergyById(id);
		PatientAllergyView pAllergyView = this.pallergyViewBo.fetchPatientAllergyByPatientId(id);
		if (null == pAllergy) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/patients/view/"+id;
		}
		PatientAllergyForm pAllergyForm = new PatientAllergyForm();
		pAllergyForm.setId(id);
		pAllergyForm.setPatientId(patientId);
		model.addAttribute("pAllergyForm", pAllergyForm);
		model.addAttribute("pAllergy", pAllergy);
		model.addAttribute("pAllergyView", pAllergyView);

		return "customers/patientallergy/delete";
	}

	@RequestMapping(value = "/patientallergy/delete/{id}/{patientId}", method = RequestMethod.POST)
	public String confirmAllergyDeleteAction(
			@ModelAttribute("pAllergyForm") PatientAllergyForm pAllergyForm, Model model,
			RedirectAttributes redirectAttributes) {

		PatientAllergy pAllergy = this.patientAllergyBo.getPatientAllergyById(pAllergyForm.getId());
		if (null == pAllergy) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/patients/view/"+pAllergyForm.getPatientId();
		}

		patientAllergyBo.delete(pAllergy);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Allergy Deleted");

		return "redirect:/patients/view/"+pAllergyForm.getPatientId();
	}
	
	@ResponseBody
	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	public String viewImage(@PathVariable("id") Integer id,
			HttpServletResponse response) {

		Patient patient = this.patientBo.getPatientById(id);

		if (null != patient.getImage()) {
			try {
				response.setContentType(patient.getImageContentType());

				response.setHeader("Content-Disposition", "inline;filename=\""
						+ patient.getSurname() + "\"");

				OutputStream outputStream = response.getOutputStream();

				IOUtils.copy(patient.getImage().getBinaryStream(), outputStream);

				outputStream.flush();
				outputStream.close();

			} catch (IOException e) {
				e.printStackTrace();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	// this is for document inside patient
	@Layout("layouts/datatable")
	@RequestMapping(value = "/documents/{id}", method = RequestMethod.GET)
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

		return "customers/patients/documents";
	}

	@RequestMapping(value = "/documents/{id}", method = RequestMethod.POST)
	public String saveDocument(
			@Valid @ModelAttribute("documentForm") PatientDocumentForm patientDocumentForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		Patient patient = this.patientBo.getPatientById(patientDocumentForm
				.getPatientId());

		if (result.hasErrors()) {
			model.addAttribute("patient", patient);
			return "customers/patients/documents";
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

		return "redirect:/patients/documents/" + patient.getPatientId();
	}

	@ResponseBody
	@RequestMapping(value = "/documents/view/{documentId}/{documentName}")
	public String viewDocumentAction(@PathVariable Integer documentId,
			@PathVariable("documentName") String documentName,
			RedirectAttributes redirectAttributes, HttpServletResponse response) {

		PatientDocument patientDocument = this.patientDocumentBo
				.getPatientDocumentById(documentId);

		if (null == patientDocument) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
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

	@RequestMapping(value = "/documents/delete/{id}")
	public String documentDeleteAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		PatientDocument patientDocument = this.patientDocumentBo
				.getPatientDocumentById(id);

		if (null == patientDocument) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}

		PatientDocumentForm patientDocumentForm = new PatientDocumentForm();
		patientDocumentForm.setPatientDocumentId(patientDocument.getPatientDocumentsId());

		model.addAttribute("document", patientDocument);
		model.addAttribute("pForm", patientDocumentForm);

		return "customers/patients/delete_document";
	}

	@RequestMapping(value = "/documents/delete/{id}", method = RequestMethod.POST)
	public String confirmDocumentDeleteAction(
			@ModelAttribute("pForm") PatientDocumentForm patientDocumentForm,
			RedirectAttributes redirectAttributes) {

		PatientDocument patientDocument = this.patientDocumentBo
				.getPatientDocumentById(patientDocumentForm.getPatientDocumentId());

		if (null == patientDocument) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}

		int patientId = patientDocument.getPatient().getPatientId();
		
		this.patientDocumentBo.delete(patientDocument);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! File deleted");

		return "redirect:/patients/documents/" + patientId;
	}

	// this is for emergency contact inside patient
	@RequestMapping(value = "/nok/{id}")
	public String EMERAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Patient patient = this.patientBo.getPatientById(id);
		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}
		PatientEmergencyForm pEmerForm = new PatientEmergencyForm();
		pEmerForm.setPatientId(patient.getPatientId());

		model.addAttribute("EMERForm", pEmerForm);
		model.addAttribute("titles", this.titleBo.fetchAllByOrganisation());
		model.addAttribute("patient", patient);

		return "customers/patients/nok";
	}

	@RequestMapping(value = "/nok/{id}", method = RequestMethod.POST)
	public String saveEMER(
			@Valid @ModelAttribute("EMERForm") PatientEmergencyForm patientEmergencyForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		Patient patient = this.patientBo.getPatientById(patientEmergencyForm
				.getPatientId());

		if (result.hasErrors()) {
			model.addAttribute("patient", patient);
			return "customers/patients/nok";
		}

		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not save emergency contact. Invalid resource");
			return "redirect:/customers/patients";
		}

		PatientEmergency patientEmer = new PatientEmergency();

		patientEmer.setFullName(patientEmergencyForm.getFull_name());
		patientEmer.setRelationship(patientEmergencyForm.getRelationship());
		patientEmer.setEmail_id(patientEmergencyForm.getEmail_id());

		patientEmer.setHomeAddress(patientEmergencyForm.getHome_address());
		patientEmer.setMobileNo1(patientEmergencyForm.getMobile_no1());
		patientEmer.setMobileNo2(patientEmergencyForm.getMobile_no2());
		patientEmer.setOtherDetail(patientEmergencyForm.getOther_detail());
		patientEmer.setPatient(patient);

		Title title = this.titleBo.getTitleById(patientEmergencyForm
				.getTitleId());

		patientEmer.setTitle(title);

		patientEmer.setOrganisation(patient.getOrganisation());

		// Saving patient emergency
		this.patientEmergencyBo.save(patientEmer);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! saved successfully");

		return "redirect:/customers/patients/nok/" + patient.getPatientId();
	}

	@RequestMapping(value = "/nok/delete/{id}")
	public String emerDeleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		PatientEmergency patientEmer = this.patientEmergencyBo
				.getPatientEmergencyById(id);
		if (null == patientEmer) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}

		Patient patient = this.patientBo.getPatientById(id);

		PatientEmergencyForm pEmerForm = new PatientEmergencyForm();
		pEmerForm.setId(patientEmer.getId());

		model.addAttribute("patient", patient);

		model.addAttribute("emer", patientEmer);
		model.addAttribute("eForm", pEmerForm);

		return "customers/patients/delete_nok";
	}

	@RequestMapping(value = "/nok/delete/{id}", method = RequestMethod.POST)
	public String confirmemerDeleteAction(
			@ModelAttribute("eForm") PatientEmergencyForm patientEmergencyForm,
			RedirectAttributes redirectAttributes) {

		PatientEmergency patientEmer = this.patientEmergencyBo
				.getPatientEmergencyById(patientEmergencyForm.getId());

		if (null == patientEmer) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}

		int patientId = patientEmer.getPatient().getPatientId();

		this.patientEmergencyBo.delete(patientEmer);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! emergency contact removed");

		return "redirect:/customers/patients/nok/" + patientId;
	}

	// this is for medicalhistory inside patient
	@RequestMapping(value = "/history/{id}")
	public String medHistoryAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Patient patient = this.patientBo.getPatientById(id);

		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}
		PatientMedicalHistoryForm medhis = new PatientMedicalHistoryForm();
		medhis.setPatientId(patient.getPatientId());

		model.addAttribute("myhistoryform", medhis);
		model.addAttribute("ailmentlist", this.ailmentBo.fetchAll());
		model.addAttribute("druglist", this.drugBo.fetchAll());
		model.addAttribute("patient", patient);

		return "customers/patients/history";
	}

	@RequestMapping(value = "/history/{id}", method = RequestMethod.POST)
	public String savemedhs(
			@Valid @ModelAttribute("myhistoryform") PatientMedicalHistoryForm patientMedicalHistoryForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		Patient patient = this.patientBo
				.getPatientById(patientMedicalHistoryForm.getPatientId());

		if (result.hasErrors()) {
			model.addAttribute("patient", patient);
			return "customers/patients/history";
		}

		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not save history. Invalid resource");
			return "redirect:/customers/patients";
		}

		PatientMedicalHistory patientMedical = new PatientMedicalHistory();

		patientMedical.setPatient(patient);

		patientMedical.setDrug(this.drugBo
				.getDrugById(patientMedicalHistoryForm.getDrug_id()));
		patientMedical.setOther_drugs(patientMedicalHistoryForm
				.getOther_drugs());
		patientMedical.setOther_detail(patientMedicalHistoryForm
				.getOther_detail());
		patientMedical.setOrganisation(patient.getOrganisation());

		patient.getPatientmedicalhistory().add(patientMedical);

		this.patientMedicalHistoryBo.save(patientMedical);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! saved successfully");

		return "redirect:/customers/patients/history/" + patient.getPatientId();
	}

	@RequestMapping(value = "/history/edit/{id}", method = RequestMethod.GET)
	public String editMedicalHistory(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		Patient patient = patientBo.getPatientById(id);
		System.out.println("Edit Patient Id is::: " + patient.getPatientId());
		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}
		PatientMedicalHistory history = patientMedicalHistoryBo
				.getPatientMedicalHistoryById(id);
		PatientMedicalHistoryForm historyForm = new PatientMedicalHistoryForm();

		System.out.println("History Form id is:: " + history.getId());

		historyForm.setPatientId(patient.getPatientId());
		historyForm.setId(history.getId());
		historyForm.setOther_detail(history.getOther_detail());
		historyForm.setOther_drugs(history.getOther_drugs());

		model.addAttribute("historyForm", historyForm);
		model.addAttribute("ailmentlist", ailmentBo.fetchAll());
		model.addAttribute("druglist", drugBo.fetchAll());

		return "customers/patients/editmedicalhistory";
	}

	@RequestMapping(value = "/history/edit/{id}", method = RequestMethod.POST)
	public String updateMedicalHistory(
			@ModelAttribute("historyForm") PatientMedicalHistoryForm medHistoryForm,
			Model model, RedirectAttributes redirectAttributes) {

		System.out.println("MedicalHistory Update is:: "
				+ medHistoryForm.getId());
		System.out.println("MedicalHistory Update for Patient is:: "
				+ medHistoryForm.getPatientId());

		Patient patient = patientBo.getPatientById(medHistoryForm
				.getPatientId());
		System.out.println("Update Patient Id is: " + patient.getPatientId());

		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}

		PatientMedicalHistory history = patientMedicalHistoryBo
				.getPatientMedicalHistoryById(patient.getPatientId());
		
		history.setOther_detail(medHistoryForm.getOther_detail());
		history.setOther_drugs(medHistoryForm.getOther_drugs());

		model.addAttribute("historyForm", history);
		model.addAttribute("ailment", ailmentBo.fetchAll());
		model.addAttribute("drug", drugBo.fetchAll());

		patientMedicalHistoryBo.update(history);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! saved successfully");

		return "redirect:/customers/patients/history/" + patient.getPatientId();

	}

	@RequestMapping(value = "/history/delete/{id}")
	public String medicalhistoryDeleteAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		PatientMedicalHistory PatientMedicalHistory = this.patientMedicalHistoryBo
				.getPatientMedicalHistoryById(id);

		if (null == PatientMedicalHistory) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}

		Patient patient = this.patientBo.getPatientById(id);

		PatientMedicalHistoryForm medicalhistoryForm = new PatientMedicalHistoryForm();
		medicalhistoryForm.setId(PatientMedicalHistory.getId());

		model.addAttribute("patient", patient);
		model.addAttribute("medhistory", PatientMedicalHistory);
		model.addAttribute("MHForm", medicalhistoryForm);

		return "customers/patients/delete_history";
	}

	// this is for new allergy category and detail inside patient
		@Layout("layouts/datatable")
		@RequestMapping(value = "/allergy/{id}")
		public String allergyAction(@PathVariable("id") Integer id, Model model,
				RedirectAttributes redirectAttributes) {

			Patient patient = this.patientBo.getPatientById(id);
			System.out.println("Allergy Patient Id: " + patient.getPatientId());
			if (null == patient) {
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Invalid resource");
				return "redirect:/patients";
			}

			PatientAllergyForm patientAllergyForm = new PatientAllergyForm();
			patientAllergyForm.setPatientId(patient.getPatientId());

			model.addAttribute("aForm", patientAllergyForm);
			model.addAttribute("categories", this.allergyCategoryBo.fetchAll());
			model.addAttribute("patient", patient);

			return "customers/patients/allergy";
		}

		

		/*@RequestMapping(value = "/allergy/delete/{patientId}/{allergyId}")
		public String allergydelecteAction(
				@PathVariable("patientId") Integer patientId,
				@PathVariable("allergyId") Integer allergyId, Model model,
				RedirectAttributes redirectAttributes) {

			Patient patient = this.patientBo.getPatientById(patientId);
			Allergy allergy = this.allergyBo.getAllergyById(allergyId);
			
			PatientAllergy Pallergy = this.patientAllergyBo.getPatientAllergyByPatientAndAllergy(patient, allergy);


			if (null == patient) {
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Invalid resource");
				return "redirect:/patients";
			}

			if (null != allergy) {
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Invalid resource");
				return "redirect:/patients";
			}

			PatientAllergy patientAllergy = this.patientAllergyBo
					.getPatientAllergyByPatientAndAllergy(patient, allergy);

			if (null == patientAllergy) {
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Invalid resource");
				return "redirect:/patients";
			}

			PatientAllergyForm form = new PatientAllergyForm();
			form.setId(form.getId());
			form.setPatientId(patientId);

			model.addAttribute("patientAllergy", patientAllergy);
			model.addAttribute("aForm", form);

			return "customers/patients/delete_allergies";
		}*/

		/*@RequestMapping(value = "/allergy/delete/{patientId}/{allergyId}", method = RequestMethod.POST)
		public String confirledallergydeleteAction(
				@ModelAttribute("Allergyform") PatientAllergyForm form,
				RedirectAttributes redirectAttributes) {

			Patient patient = this.patientBo.getPatientById(form.getPatientId());
			PatientAllergy PatientAllergy = this.patientAllergyBo.getPatientAllergyByPatientAndAllergy(patient, PatientAllergy));

			if (null == patient) {
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Invalid resource");
				return "redirect:/patients";
			}

			if (null != allergy) {
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Invalid resource");
				return "redirect:/patients";
			}

			PatientAllergy patientAllergy = this.patientAllergyBo
					.getPatientAllergyByPatientAndAllergy(patient, allergy);

			if (null == patientAllergy) {
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Invalid resource");
				return "redirect:/patients";
			}

			this.patientAllergyBo.delete(patientAllergy);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success!Allergy removed");

			return "redirect:/patients/view/" + form.getPatientId();
		}
	*/
		
		/*@RequestMapping(value = "/allergy/edit/{patientId}/{allergyId}")
		public String allergyeditAction(
				@PathVariable("patientId") Integer patientId,
				@PathVariable("allergyId") Integer allergyId, Model model,
				RedirectAttributes redirectAttributes,HttpServletRequest request) {

			Patient patient = this.patientBo.getPatientById(patientId);
			Allergy allergy = this.allergyBo.getAllergyById(allergyId);
			List<Allergy> allergyList = this.allergyBo.fetchAll();
			List<AllergyCategory> aCategory = this.allergyCategoryBo.fetchAll();
			PatientAllergy pAllergy = this.patientAllergyBo.getPatientAllergyByPatientAndAllergy(patient, allergy);
			PatientAllergyForm aForm = new PatientAllergyForm();
			aForm.setAllergyCategoryId(pAllergy.getCategoryId());
			aForm.setAllergyId(allergyId);
			aForm.setPatientId(patientId);
			aForm.setDescription(pAllergy.getDescription());
			aForm.setReactions(pAllergy.getReactions());
			if (null == patient) {
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Invalid resource");
				return "redirect:/patients";
			}

			if (null != allergy) {
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Invalid resource");
				return "redirect:/patients";
			}

			model.addAttribute("aCategory", aCategory);
			model.addAttribute("allergyList", allergyList);
			model.addAttribute("patientAllergy", pAllergy);
			model.addAttribute("aForm", aForm);
			
			auditor.before(request, "Patient Allergy Form", aForm);
		
			return "customers/patients/editpatientallergy";
		}
		
		
		
		
		/*@RequestMapping(value = "/allergy/view/{patientId}/{allergyId}")
		public String viewAllergy(
				@PathVariable("patientId") Integer patientId,
				@PathVariable("allergyId") Integer allergyId,Model model,
				RedirectAttributes redirectAttributes, HttpServletRequest request) {
			Patient patient = this.patientBo.getPatientById(patientId);
			Allergy allergy = this.allergyBo.getAllergyById(allergyId);
			PatientAllergy pAllergy = this.patientAllergyBo.getPatientAllergyByPatientAndAllergy(patient, allergy);
			
			model.addAttribute("pAllergy", pAllergy);
			return "customers/patients/patientAllergyview";
		}*/
		
		
		
	/*@RequestMapping(value = "/history/delete/{id}", method = RequestMethod.POST)
	public String confirmmedicalhistoryDeleteAction(
			@ModelAttribute("MHForm") PatientMedicalHistoryForm patientMedicalHistoryForm,
			RedirectAttributes redirectAttributes) {

		PatientMedicalHistory PatientMedicalHistory = this.patientMedicalHistoryBo
				.getPatientMedicalHistoryById(patientMedicalHistoryForm.getId());

		if (null == PatientMedicalHistory) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}

		int patientId = PatientMedicalHistory.getPatient().getId();

		this.patientMedicalHistoryBo.delete(PatientMedicalHistory);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! medical history removed");

		return "redirect:/customers/patients/history/" + patientId;
	}

	// This is for family history inside patient
	@RequestMapping(value = "/familyhistory/{id}")
	public String familyhistoryyAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		Patient1 patient = this.patientBo.getPatientById(id);
		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}
		PatientFamilyHistoryForm family = new PatientFamilyHistoryForm();
		family.setPatientId(patient.getId());

		model.addAttribute("myfamilyhistoryform", family);
		model.addAttribute("ailmentlist", this.ailmentBo.fetchAll());

		model.addAttribute("patient", patient);

		return "customers/patients/familyhistory";
	}

	@RequestMapping(value = "/familyhistory/{id}", method = RequestMethod.POST)
	public String savefamilyhistory(
			@Valid @ModelAttribute("myfamilyhistoryform") PatientFamilyHistoryForm patientFamilyHistoryForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		Patient1 patient = this.patientBo
				.getPatientById(patientFamilyHistoryForm.getPatientId());

		if (result.hasErrors()) {
			model.addAttribute("patient", patient);
			return "customers/patients/history";
		}

		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not save history. Invalid resource");
			return "redirect:/customers/patients";
		}

		PatientFamilyHistory patientfamilyhistory = new PatientFamilyHistory();

		patientfamilyhistory.setPatient(patient);
		patientfamilyhistory.setAilment(this.ailmentBo
				.getAilmentById(patientFamilyHistoryForm.getAilment_id()));
		patientfamilyhistory.setFamily_line(patientFamilyHistoryForm
				.getFamily_line());
		patientfamilyhistory.setNo_of_occurence(patientFamilyHistoryForm
				.getNo_of_occurence());
		patientfamilyhistory.setNo_of_casualties(patientFamilyHistoryForm
				.getNo_of_casualties());
		patientfamilyhistory.setOther_detail(patientFamilyHistoryForm
				.getOther_detail());
		patientfamilyhistory.setOrganisation(patient.getOrganisation());

		patient.getPatientfamilyhistory().add(patientfamilyhistory);

		this.patientFamilyHistoryBo.save(patientfamilyhistory);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! saved successfully");

		return "redirect:/customers/patients/familyhistory/" + patient.getId();
	}

	// Update Patient Family History
	@RequestMapping(value = "{pid}/familyhistory/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id,
			@PathVariable("pid") Integer pid, Model model,
			RedirectAttributes redirectAttributes) {

		Patient1 patient = patientBo.getPatientById(pid);
		System.out.println("Patient Id is: " + patient.getId());

		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}
		PatientFamilyHistory family = patientFamilyHistoryBo
				.getPatientFamilyHistoryById(id);
		PatientFamilyHistoryForm familyForm = new PatientFamilyHistoryForm();
		// Allergy allergy =
		// allergyBo.getAllergyById(family.getAilment().getId());

		familyForm.setPatientId(patient.getId());
		familyForm.setId(family.getId());
		// familyForm.setAilment_id(allergy.getId());
		familyForm.setFamily_line(family.getFamily_line());
		familyForm.setNo_of_occurence(family.getNo_of_occurence());
		familyForm.setNo_of_casualties(family.getNo_of_casualties());
		familyForm.setOther_detail(family.getOther_detail());

		model.addAttribute("familyForm", familyForm);
		model.addAttribute("ailment", ailmentBo.fetchAll());

		return "customers/patients/editfamilyhistory";
	}

	
	 * @RequestMapping(value = "/familyhistory/edit/{id}", method =
	 * RequestMethod.POST)
	 
	@RequestMapping(value = "{pid}/familyhistory/edit/{id}", method = RequestMethod.POST)
	public String update(
			@ModelAttribute("familyForm") PatientFamilyHistoryForm familyForm,
			Model model) {

		Patient1 patient = patientBo.getPatientById(familyForm.getPatientId());
		PatientFamilyHistory family = patientFamilyHistoryBo
				.getPatientFamilyHistoryById(familyForm.getId());

		family.setPatient(patientBo.getPatientById(familyForm.getPatientId()));
		family.setAilment(ailmentBo.getAilmentById(familyForm.getAilment_id()));
		family.setFamily_line(familyForm.getFamily_line());
		family.setNo_of_occurence(familyForm.getNo_of_occurence());
		family.setNo_of_casualties(familyForm.getNo_of_casualties());
		family.setOther_detail(familyForm.getOther_detail());

		patientFamilyHistoryBo.update(family);

		return "redirect:/customers/patients/familyhistory/" + patient.getId();
	}

	@RequestMapping(value = "/familyhistory/delete/{id}")
	public String familyhistoryDeleteAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		PatientFamilyHistory patientFamilyHistory = this.patientFamilyHistoryBo
				.getPatientFamilyHistoryById(id);

		if (null == patientFamilyHistory) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}

		Patient1 patient = this.patientBo.getPatientById(id);

		PatientFamilyHistoryForm familyhistoryForm = new PatientFamilyHistoryForm();

		familyhistoryForm.setId(patientFamilyHistory.getId());

		model.addAttribute("patient", patient);

		model.addAttribute("famhistory", patientFamilyHistory);
		model.addAttribute("FHForm", familyhistoryForm);

		return "customers/patients/delete_familyhistory";
	}

	@RequestMapping(value = "/familyhistory/delete/{id}", method = RequestMethod.POST)
	public String confirmfamilyhistoryDeleteAction(
			@ModelAttribute("FHForm") PatientFamilyHistoryForm patientFamilyHistoryForm,
			RedirectAttributes redirectAttributes) {

		PatientFamilyHistory patientFamilyHistory = this.patientFamilyHistoryBo
				.getPatientFamilyHistoryById(patientFamilyHistoryForm.getId());

		if (null == patientFamilyHistory) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}

		int patientId = patientFamilyHistory.getPatient().getId();

		this.patientFamilyHistoryBo.delete(patientFamilyHistory);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! family history removed");

		return "redirect:/customers/patients/familyhistory/" + patientId;
	}

	

	@Layout("layouts/datatable")
	@RequestMapping(value = "/hmo/{id}")
	public String patientHmoPackageAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		Patient1 patient = this.patientBo.getPatientById(id);
		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}

		PatientHmoForm form = new PatientHmoForm();
		form.setPatientId(patient.getId());

		model.addAttribute("dForm", form);
		model.addAttribute("patient", patient);
		model.addAttribute("hmos", this.hmoBo.fetchAll());

		List list = this.patientHmoPackageBo.fetchAllByPatient(patient);

		return "customers/patients/hmo";
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = "/hmo/{id}", method = RequestMethod.POST)
	public String savePatientHmoPackage(
			@Valid @ModelAttribute("dForm") PatientHmoForm form,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Form contain errors");
			return "redirect:/customers/patients/hmo/" + form.getPatientId();
		}

		Patient1 patient = this.patientBo.getPatientById(form.getPatientId());
		OrganisationHmoPackage hmoPackage = this.organisationHmoPackageBo
				.getPackageById(form.getPackageId());

		PatientHmoPackage patientHmoPackage = this.patientHmoPackageBo
				.getHmoPackageByPatientAndPackage(patient, hmoPackage);

		if (null != patientHmoPackage) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Patient is already using the selected HMO Package");
			return "redirect:/customers/patients/hmo/" + form.getPatientId();
		}

		this.patientHmoPackageBo.save(form);

		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Patient Hmo Package saved");

		return "redirect:/customers/patients/hmo/" + form.getPatientId();
	}

	// Billing scheme
	@RequestMapping(value = "/payment/{id}")
	public String paymentAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Patient1 patient = this.patientBo.getPatientById(id);
		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}
		PatientPaymentOptionForm paymt = new PatientPaymentOptionForm();
		paymt.setPatientId(patient.getId());

		model.addAttribute("mypayschemeform", paymt);
		model.addAttribute("patient", patient);
		model.addAttribute("paymentscheme", this.paymentSchemeList.fetchAll());
		return "customers/patients/payment";
	}

	@RequestMapping(value = "/payment/{id}", method = RequestMethod.POST)
	public String savePay(
			@Valid @ModelAttribute("mypayschemeform") PatientPaymentOptionForm patientPaymentOptionForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		Patient1 patient = this.patientBo
				.getPatientById(patientPaymentOptionForm.getPatientId());

		if (result.hasErrors()) {
			model.addAttribute("patient", patient);
			return "customers/patients/payment";
		}

		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not save payment scheme. Invalid resource");
			return "redirect:/customers/patients";
		}

		PatientPaymentOption patientPaymentOption = new PatientPaymentOption();

		patientPaymentOption.setPaymentScheme(this.organisationPaymentSchemeBo
				.getBillingSchemeById(patientPaymentOptionForm.getPaymentSchemeId()));

		System.out.println("Payment Scheme:"
				+ patientPaymentOptionForm.getPaymentSchemeId());


		patientPaymentOption.setComment(patientPaymentOptionForm.getComment());
		patientPaymentOption.setCreatedBy(userIdentity.getUsername());

		patientPaymentOption.setPatient(patient);

		patientPaymentOption.setOrganisation(patient.getOrganisation());

		// Saving patient emergency
		this.patientPaymentOptionBo.save(patientPaymentOption);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! saved successfully");

		return "redirect:/customers/patients/payment/" + patient.getId();
	}

	// this is for social history inside patient
	@RequestMapping(value = "/socialhistory/{id}")
	public String socialhistoryyAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		Patient1 patient = this.patientBo.getPatientById(id);
		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}
		PatientSocialHistoryForm social = new PatientSocialHistoryForm();
		social.setPatientId(patient.getId());

		model.addAttribute("mysocialhistoryform", social);

		model.addAttribute("patient", patient);

		return "customers/patients/socialhistory";
	}

	@RequestMapping(value = "/socialhistory/{id}", method = RequestMethod.POST)
	public String savesocial(
			@Valid @ModelAttribute("mysocialhistoryform") PatientSocialHistoryForm patientSocialHistoryForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		Patient1 patient = this.patientBo
				.getPatientById(patientSocialHistoryForm.getPatientId());

		if (result.hasErrors()) {
			model.addAttribute("patient", patient);
			return "customers/patients/socialhistory";
		}

		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not save social history. Invalid resource");
			return "redirect:/customers/patients";
		}

		PatientSocialHistory patientsocial = new PatientSocialHistory();

		patientsocial.setPatient(patient);

		patientsocial.setCurrentorprevious(patientSocialHistoryForm
				.getCurrentorprevious());
		patientsocial.setCycle(patientSocialHistoryForm.getCycle());
		patientsocial.setDisabled(patientSocialHistoryForm.getDisabled());
		patientsocial.setEducation_level(patientSocialHistoryForm
				.getEducation_level());
		patientsocial.setEmployment_status(patientSocialHistoryForm
				.getEmployment_status());
		patientsocial.setFrequency(patientSocialHistoryForm.getFrequency());
		patientsocial.setOther_detail(patientSocialHistoryForm
				.getOther_detail());
		patientsocial.setSexual_orientation(patientSocialHistoryForm
				.getSexual_orientation());
		patientsocial.setStart_year(patientSocialHistoryForm.getStart_year());
		patientsocial.setStop_year(patientSocialHistoryForm.getStop_year());
		patientsocial.setSubstance(patientSocialHistoryForm.getSubstance());

		patientsocial.setOrganisation(patient.getOrganisation());

		patient.getPatientSocialHistory().add(patientsocial);

		this.patientSocialHistoryBo.save(patientsocial);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success!Social history saved successfully");

		return "redirect:/customers/patients/socialhistory/" + patient.getId();
	}

	// this is for surgical history inside patient
	@RequestMapping(value = "/surgicalhistory/{id}")
	public String surgicalhistoryyAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		Patient1 patient = this.patientBo.getPatientById(id);
		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/customers/patients";
		}
		PatientSurgicalHistoryForm surgical = new PatientSurgicalHistoryForm();
		surgical.setPatientId(patient.getId());

		model.addAttribute("mysurgicalhistoryform", surgical);

		model.addAttribute("surgicallist", this.surgicalList.fetchAll());

		model.addAttribute("patient", patient);

		return "customers/patients/surgicalhistory";
	}

	@RequestMapping(value = "/surgicalhistory/{id}", method = RequestMethod.POST)
	public String savesurgery(
			@Valid @ModelAttribute("mysurgicalhistoryform") PatientSurgicalHistoryForm patientSurgicalHistoryForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		Patient1 patient = this.patientBo
				.getPatientById(patientSurgicalHistoryForm.getPatientId());

		if (result.hasErrors()) {
			model.addAttribute("patient", patient);
			return "customers/patients/surgicalhistory";
		}

		if (null == patient) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not save surgical history. Invalid resource");
			return "redirect:/customers/patients";
		}

		PatientSurgicalHistory patientsurgical = new PatientSurgicalHistory();

		patientsurgical.setPatient(patient);

		patientsurgical.setSurgicalProcedures(this.surgicalList
				.getSurgicalProceduresById(patientSurgicalHistoryForm
						.getSurgicalProceduresid()));
		patientsurgical.setAnasthetic_complication(patientSurgicalHistoryForm
				.getAnasthetic_complication());
		patientsurgical.setComplication_detail(patientSurgicalHistoryForm
				.getComplication_detail());
		patientsurgical.setHospital_address(patientSurgicalHistoryForm
				.getHospital_address());
		patientsurgical.setHospital_email(patientSurgicalHistoryForm
				.getHospital_email());
		patientsurgical.setHospital_phone(patientSurgicalHistoryForm
				.getHospital_phone());
		patientsurgical.setOther_detail(patientSurgicalHistoryForm
				.getOther_detail());
		patientsurgical.setSurgeon_full_name(patientSurgicalHistoryForm
				.getSurgeon_full_name());
		patientsurgical.setSurgeryyear(patientSurgicalHistoryForm
				.getSurgeryyear());
		patientsurgical.setSurgery_hospital(patientSurgicalHistoryForm
				.getSurgery_hospital());

		patientsurgical.setOrganisation(patient.getOrganisation());

		patient.getPatientSurgicalHistory().add(patientsurgical);

		this.patientSurgicalHistoryBo.save(patientsurgical);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success!Surgery history saved successfully");

		return "redirect:/customers/patients/surgicalhistory/"
				+ patient.getId();
	}*/


}
