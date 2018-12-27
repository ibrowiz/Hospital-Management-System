package org.calminfotech.consultation.controller;

import java.math.BigDecimal;
import java.math.MathContext;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.consultation.bo.VisitBo;
import org.calminfotech.consultation.forms.PaymentDescriptionForm;
import org.calminfotech.consultation.forms.VisitPaymentForm;
import org.calminfotech.consultation.models.PaymentItem;
import org.calminfotech.consultation.models.PaymentItemType;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.system.boInterface.HmoPackageServiceBo;
import org.calminfotech.system.models.HmoPackageService;
import org.calminfotech.utils.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/payments")
public class PaymentsController {

	@Autowired
	private VisitBo visitBo;

	@Autowired
	private HmoPackageServiceBo serviceBo;

	@Autowired
	private Alert alert;

	@RequestMapping(value = "/raiseBilling", method = RequestMethod.POST)
	public String raiseBill(@Valid @ModelAttribute("pForm") VisitPaymentForm form,    
			BindingResult result, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes){
		String callbackUrl = request.getRequestURI().substring(
				request.getContextPath().length());
		System.out.println(" callbackUrl ==== "+callbackUrl);
		return null;
	}
	
	@RequestMapping(value = "/visit", method = RequestMethod.POST)
	public String paymentAction(
			@Valid @ModelAttribute("pForm") VisitPaymentForm form,    
			BindingResult result, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		String callbackUrl = request.getRequestURI().substring(
				request.getContextPath().length());
		if (result.hasErrors()) {
			// If the form has errors. Since the form errors cant be display
			// at
			// point because method will be used my different points. Throw
			// an
			// alert and make a callback to the url that made the request.
			// String callbackUrl as the application context removed from
			// the
			// URI to readable redirect to function properly

			this.alert
					.setAlert(
							redirectAttributes,
							Alert.ERROR,
							"Error!. Could not confirm payment details. "
									+ "Ensure that the payment form is filled correctly");
			return "redirect:" + callbackUrl;
		}
		// Create an object to display info. collect items to display items.
		// Before sending..
		Visit visit = this.visitBo.getVisitationById(form.getVisitId());
		// Get the payment type
		PaymentDescriptionForm description = new PaymentDescriptionForm();
		String patientName = visit.getPatient().getTitle().getAcronym() + " "
				+ visit.getPatient().getSurname() + " "
				+ visit.getPatient().getOthernames();
		description.setPatientName(patientName);
		description.setPatientCode(visit.getPatient().getPatientCode());
		description.setVisitCode(visit.getCode());
		if (form.getPaymentType().equals(PaymentItemType.Service.toString())) {

			for (Integer i : form.getItem()) {
				// Get the type of service that was brought
				HmoPackageService service = this.serviceBo.getServiceById(i);
				PaymentItem item = new PaymentItem();
				// Add Item properties
				// service Name
				item.setItemDescription(service.getName());
				// Set price
				item.setPrice(service.getPrice());

				description.getItems().add(item);
			}
			// /item.set
		}
		BigDecimal totalAmountToBePaid = new BigDecimal(0);
		for (PaymentItem item : description.getItems()) {
			totalAmountToBePaid = totalAmountToBePaid.add(item.getPrice());
		}
		description.setTotalAmountToBePaid(totalAmountToBePaid);

		model.addAttribute("description", description);
		model.addAttribute("visit", visit);
		model.addAttribute("pForm", form);
		return "payments/visit/form";
	}
}
