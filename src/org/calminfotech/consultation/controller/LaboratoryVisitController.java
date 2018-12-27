package org.calminfotech.consultation.controller;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.consultation.bo.VisitBo;
import org.calminfotech.consultation.forms.VisitLaboratoryForm;
import org.calminfotech.consultation.forms.VisitWorkflowUserConfigurationForm;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.system.boInterface.VisitStatusBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
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
@RequestMapping(value = "/consultations/visits/laboratory")
public class LaboratoryVisitController {

	@Autowired
	private VisitBo visitBo;

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
		model.addAttribute("visits", this.visitBo
				.fetchAllByWorkflowPoint(VisitWorkflowPoint.LABORATORY,
						this.visitStatusBo.getNotEndPointStatus()));
		return "consultations/visits/laboratory/index";
	}

	@RequestMapping(value = "/{id}")
	public String laboratoryAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Visit visit = this.visitBo.getVisitationById(id);
		if (null == visit) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid Resource");
			return "redirect:/consultations/visits/laboratory";
		}

		// Visit must be at this point else should redirect to another page
		if (!visit.getPoint().getName().equals(VisitWorkflowPoint.LABORATORY)) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Invalid Resource");
			return "redirect:/consultations/visits/laboratory";
		}

		VisitLaboratoryForm form = new VisitLaboratoryForm();
		// If null set form values
		form.setVisitId(visit.getId());
		// get the vitals details and populate the form;
		if (null != visit.getLaboratoryVisit()) {

			// Prefill the form here
			form.setSampleField1(visit.getLaboratoryVisit().getSampleFieldOne());
			form.setSampleField2(visit.getLaboratoryVisit().getSampleFieldTwo());
			form.setComments(visit.getLaboratoryVisit().getComments());
		}

		VisitWorkflowUserConfigurationForm workflowForm = new VisitWorkflowUserConfigurationForm();
		workflowForm.setVisitId(visit.getId());

		// Send to the fronts
		model.addAttribute("dForm", form);
		model.addAttribute("visit", visit);
		model.addAttribute("wfForm", workflowForm);
		model.addAttribute("points", this.wfPointBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));

		return "consultations/visit_forms/laboratory";
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String updateLaboratoryAction(
			@Valid @ModelAttribute("wfForm") VisitLaboratoryForm form,
			BindingResult result) {

		String response = "";
		String errors = "";
		boolean status;
		if (result.hasErrors()) {

			status = false;
			// List the form errors
			String formErrors = "<ul>";
			Class<VisitLaboratoryForm> formObj = VisitLaboratoryForm.class;

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

		this.visitBo.updateLaboratoryVisit(form);
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

		return "redirect:/consultations/visits/laboratory";
	}
}
