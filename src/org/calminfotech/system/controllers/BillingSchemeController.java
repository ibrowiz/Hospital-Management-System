package org.calminfotech.system.controllers;

import javax.validation.Valid;

import org.calminfotech.system.boInterface.BillingSchemeBo;
import org.calminfotech.system.boInterface.BillingSchemeItemBo;

import org.calminfotech.system.forms.BillingSchemeForm;

import org.calminfotech.system.models.BillingScheme;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/system/paymentscheme")
public class BillingSchemeController {

	@Autowired
	private BillingSchemeBo billingSchemeBo;

	@Autowired
	private BillingSchemeItemBo billingSchemeItemBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@RequestMapping(value = { "", "/index" })
	@Layout(value = "layouts/datatable")
	public String indexAction(Model model) {

		model.addAttribute("paymtschemes", billingSchemeBo.fetchAll());
		return "system/paymentscheme/index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(Model model, BillingSchemeForm paymentSchemeForm) {

		BillingScheme paymentScheme = new BillingScheme();

		paymentSchemeForm.setName(paymentScheme.getName());
		paymentSchemeForm.setDescription(paymentScheme.getDescription());

		model.addAttribute("pForm", paymentSchemeForm);

		return "system/paymentscheme/add";
	}

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAction(
			@Valid @ModelAttribute("pForm") BillingSchemeForm paymentSchemeForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "system/paymentscheme/add";
		}

		billingSchemeBo.save(paymentSchemeForm);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Title Created!");

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! New Scheme Succesfully Added! ");

		return "redirect:/system/paymentscheme/add/";

	}

}
