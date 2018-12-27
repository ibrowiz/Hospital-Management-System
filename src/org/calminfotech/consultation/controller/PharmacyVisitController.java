package org.calminfotech.consultation.controller;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.admin.boInterface.DrugBo;
import org.calminfotech.consultation.bo.VisitBo;
import org.calminfotech.consultation.forms.ItemsPaymentForm;
import org.calminfotech.consultation.forms.VisitPharmacyForm;
import org.calminfotech.consultation.forms.VisitWorkflowUserConfigurationForm;
import org.calminfotech.consultation.models.PaymentItemType;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientHmoPackage;
import org.calminfotech.system.boInterface.VisitStatusBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.models.Drug;
import org.calminfotech.system.models.HmoPackageDrug;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.annotations.FormFieldName;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/consultations/visits/pharmacy")
public class PharmacyVisitController {

	@Autowired
	private VisitBo visitBo;

	@Autowired
	private PatientBo patientBo;

	@Autowired
	private DrugBo drugBo;

	@Autowired
	private VisitWorkflowPointBo wfPointBo;

	@Autowired
	private UserBo userBo;

	@Autowired
	private VisitStatusBo visitStatusBo;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Alert alert;

	@Layout("layouts/datatable")
	@RequestMapping(value = { "", "/index" })
	public String indexAction(Model model) {
		// Use the side to display the list of current visits based on the role
		// of person logged in
		model.addAttribute("visits", this.visitBo.fetchAllByWorkflowPoint(
				VisitWorkflowPoint.PHARMACY,
				this.visitStatusBo.getNotEndPointStatus()));
		return "consultations/visits/pharmacy/index";
	}

	@RequestMapping(value = "/{id}")
	public String pharmacyAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Visit visit = this.visitBo.getVisitationById(id);
		if (null == visit) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid Resource");
			return "redirect:/consultations/visits/pharmacy";
		}

		// Visit must be at this point else should redirect to another page
		if (!visit.getPoint().getName().equals(VisitWorkflowPoint.PHARMACY)) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid Resource");
			return "redirect:/consultations/visits/pharmacy";
		}

		ItemsPaymentForm form = new ItemsPaymentForm();
		// If null set form values
		form.setVisitId(visit.getId());
		// get the vitals details and populate the form;
		form.setPaymentType(PaymentItemType.Drug.toString());

		// On Submit of the payment form what happens?
		// Should the form be displayed? Can it be editable? what is there was a
		// mistake?

		VisitWorkflowUserConfigurationForm workflowForm = new VisitWorkflowUserConfigurationForm();
		workflowForm.setVisitId(visit.getId());

		// Send to the fronts
		model.addAttribute("drugs", this.drugBo.fetchAll());
		model.addAttribute("dForm", form);
		model.addAttribute("visit", visit);
		model.addAttribute("wfForm", workflowForm);
		model.addAttribute("points", this.wfPointBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));

		return "consultations/visit_forms/pharmacy";
	}

	/**
	 * Ajax return of all the drugs in the system!
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getdrugs")
	public String getAllDrugJSON() {
		String response = "";
		for (Drug drug : this.drugBo.fetchAll()) {
			response += "<option value='" + drug.getId() + "'>"
					+ drug.getName() + "</option>";
		}
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/getpatientdrugpackages/{pId}/{dId}")
	public String getUserPackages(@PathVariable("dId") Integer drugId,
			@PathVariable("pId") Integer patientId) {

		String response = "";
		Drug drug = this.drugBo.getDrugById(drugId);
		Patient patient = this.patientBo.getPatientById(patientId);

		System.out
				.println("Drug packages: " + drug.getDrugHmoPackages().size());
		System.out.println("Patients packages: " + patient.getHmoPackages().size());
		

		response = "<option value='' selected='selected'>Select package</option>"; 
		
		if (drug.getDrugHmoPackages().size() > 0) {
			for (HmoPackageDrug drugHmoPackage : drug.getDrugHmoPackages()) {
				// Compare the drug and patient package
				if (patient.getHmoPackages().size() > 0) {
					for (PatientHmoPackage patientHmoPackage : patient
							.getHmoPackages()) {  
						if (patientHmoPackage.getHmoPackage().getId() == drugHmoPackage
								.getHmoPackage().getId()) {
							// Means Patient has access to this health package
							response += "<option value='"
									+ drugHmoPackage.getHmoPackage().getId()
									+ "'>";
							response += drugHmoPackage.getHmoPackage()
									.getName()  
									+ " ["
									+ drugHmoPackage.getHmoPackage().getEhmo().getName() + "]"
							+ " ("
							+ drugHmoPackage.getPrice() + ")";
							response += "</option>";
						}
					}
				} else {
					response = "<option value='' selected='selected'>No package available</option>";
				}
			}
		} else
			response = "<option value='' selected='selected'>No package available</option>";
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String updatePharmacyAction(
			@Valid @ModelAttribute("wfForm") VisitPharmacyForm form,
			BindingResult result) {

		String response = "";
		String errors = "";
		boolean status;
		if (result.hasErrors()) {

			status = false;
			// List the form errors
			String formErrors = "<ul>";
			Class<VisitPharmacyForm> formObj = VisitPharmacyForm.class;

			for (FieldError field : result.getFieldErrors()) {

				// Sigh!!! :(
				try {
					Field formObjField = formObj.getDeclaredField(field
							.getField());

					if (formObjField.isAnnotationPresent(FormFieldName.class)) {
						// Get the annotation and get form name :D
						FormFieldName annotation = formObjField
								.getAnnotation(FormFieldName.class);
						String fieldError = "<b>" + annotation.name()
								+ " field</b> cannot be empty";
						System.out.println(fieldError);
						// Append it to formErrors
						formErrors += "<li>" + fieldError + "</li>";
					}

				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			formErrors += "</ul>";

			response = "{\"status\":" + status + ", \"formErrors\": \""
					+ formErrors + "\", \"msg\": \"Form contains errors\"}";
			return response;
		}

		this.visitBo.updatePharmacyVisit(form);
		status = true;
		response = "{\"status\":" + status + ", \"msg\": \"Data saved\"}";

		return response;
	}

	@RequestMapping(value = "/assigntopoint", method = RequestMethod.POST)
	public String pointAssignAction(
			@Valid @ModelAttribute("wfForm") VisitWorkflowUserConfigurationForm form,
			BindingResult result, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		if (result.hasErrors()) {
			// If the form has errors. Since the form errors cant be display at
			// point because method will be used my different points. Throw an
			// alert and make a callback to the url that made the request.
			// String callbackUrl as the application context removed from the
			// URI to reable redirect to function properly
			String callbackUrl = request.getRequestURI().substring(
					request.getContextPath().length());

			this.alert
					.setAlert(
							redirectAttributes,
							Alert.ERROR,
							"Error!. Could not transfer patient to point. "
									+ "Ensure that the assignment form is filled correctly");
			return "redirect:" + callbackUrl;
		}

		this.visitBo.assignUserToPoint(form);

		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Patient assigned to new point!");

		return "redirect:/consultations/visits/pharmacy";
	}

}
