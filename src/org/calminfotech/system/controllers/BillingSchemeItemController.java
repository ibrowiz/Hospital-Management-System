package org.calminfotech.system.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.calminfotech.patient.forms.PatientEmergencyForm;
import org.calminfotech.patient.forms.PatientFamilyHistoryForm;
import org.calminfotech.patient.forms.PatientImageForm;
import org.calminfotech.patient.models.PatientEmergency;
import org.calminfotech.system.boInterface.BillingItemPriceDetailBo;
import org.calminfotech.system.boInterface.BillingSchemeBo;
import org.calminfotech.system.boInterface.BillingSchemeItemBo;
import org.calminfotech.system.boInterface.GlobalUnitofMeasureBo;
import org.calminfotech.system.daoInterface.BillingSchemeDao;
import org.calminfotech.system.forms.ItemPriceDetailForm;
import org.calminfotech.system.forms.BillingSchemeForm;
import org.calminfotech.system.forms.BillingSchemeItemForm;
import org.calminfotech.system.models.HmoPckSubService;
import org.calminfotech.system.models.BillingItemPriceDetail;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.BillingScheme;
import org.calminfotech.system.models.BillingSchemeItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.user.models.Title;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.GlobalItemList;
import org.calminfotech.utils.GlobalItemTypeList;
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
@RequestMapping(value = "/system/paymentscheme/items")
public class BillingSchemeItemController {

	@Autowired
	private BillingSchemeItemBo billingSchemeItemBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private BillingSchemeBo billingSchemeBo;

	@Autowired
	private BillingItemPriceDetailBo billingItemPriceDetailBo;

	@Autowired
	private GlobalItemTypeList itemTypeList;

	@Autowired
	private GlobalItemList itemList;

	@Autowired
	private GlobalUnitofMeasureBo unitofMeasureBo;

	@RequestMapping(value = { "", "/index" })
	@Layout(value = "layouts/datatable")
	public String indexAction(Model model) {

		model.addAttribute("paymtitemschemes", billingSchemeItemBo.fetchAll());
		return "system/paymentscheme/items/index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(Model model, BillingSchemeItemForm billingSchemeItemForm) {

		BillingSchemeItem billingSchemeitem = new BillingSchemeItem();

		// paymentSchemeItemForm.setName(paymentSchemeitem.getName());
		billingSchemeItemForm.setPrice(billingSchemeitem.getPrice());

		model.addAttribute("pForm", billingSchemeItemForm);
		model.addAttribute("schemes",
				this.billingSchemeBo.fetchAllByOrganisation());
		model.addAttribute("itemtype", this.itemTypeList.fetchAll());
		model.addAttribute("item", this.itemList.fetchAll());
		// model.addAttribute(arg0, arg1)
		return "system/paymentscheme/items/add";
	}

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAction(
			@Valid @ModelAttribute("pForm") BillingSchemeItemForm billingSchemeitemForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "system/paymentscheme/items/add";
		}
		/* organisationPaymentSchemeItemBo.save(paymentSchemeitemForm); */
		BillingSchemeItem billingSchemeItem = this.billingSchemeItemBo
				.save(billingSchemeitemForm);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! New Scheme Item Succesfully Added! " + "("
						+ billingSchemeItem.getGlobalItem().getName() + ")");

		return "redirect:/system/paymentscheme/items/view/"
				+ billingSchemeItem.getId();

	}

	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		BillingSchemeItem billingSchemeItem = this.billingSchemeItemBo
				.getBillingSchemeitemById(id);

		if (null == billingSchemeItem) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/paymentscheme/items/add";

		}

		model.addAttribute("PSI", billingSchemeItem);
		// System.out.println("size: " + patient.getPatientallergies().size());
		return "system/paymentscheme/items/view";
	}

	// this is for price detail inside payment scheme item
	@RequestMapping(value = "/pricedetail/{id}")
	public String peicedetailAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		BillingSchemeItem billingSchemeItem = this.billingSchemeItemBo
				.getBillingSchemeitemById(id);

		if (null == billingSchemeItem) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/paymentscheme/items/pricedetail";
		}

		ItemPriceDetailForm IP = new ItemPriceDetailForm();
		IP.setPaymentSchemeItemId(billingSchemeItem.getId());

		model.addAttribute("myIPDform", IP);

		model.addAttribute("UMList", this.unitofMeasureBo.fetchAll());

		model.addAttribute("PSYI", billingSchemeItem);

		return "system/paymentscheme/items/pricedetail";
	}

	@RequestMapping(value = "/pricedetail/{id}", method = RequestMethod.POST)
	public String savePRICE(
			@Valid @ModelAttribute("myIPDform") ItemPriceDetailForm itemPriceDetailForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws IOException {

		BillingSchemeItem billingSchemeItem = this.billingSchemeItemBo
				.getBillingSchemeitemById(itemPriceDetailForm
						.getPaymentSchemeItemId());

		if (result.hasErrors()) {
			model.addAttribute("paymentSchemeItem", billingSchemeItem);
			return "system/paymentscheme/items/pricedetail";
		}

		if (null == billingSchemeItem) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not save price detail. Invalid resource");
			return "redirect:/system/paymentscheme/items";
		}

		BillingItemPriceDetail billingItemPriceDetail = new BillingItemPriceDetail();

		billingItemPriceDetail.setPrice(itemPriceDetailForm.getPrice());
		billingItemPriceDetail.setQuantity(itemPriceDetailForm.getQuantity());
		billingItemPriceDetail.setComment(itemPriceDetailForm.getComment());

		billingItemPriceDetail.setBillingSchemeItem(billingSchemeItem);
		GlobalUnitofMeasure unitofMeasure = this.unitofMeasureBo
				.getUnitofMeasureById(itemPriceDetailForm.getUnitofMeasureId());
		billingItemPriceDetail.setUnitofMeasure(unitofMeasure);

		billingItemPriceDetail.setOrganisation(billingSchemeItem.getOrganisation());

		// Saving

		this.billingItemPriceDetailBo.save(billingItemPriceDetail);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! saved successfully");

		return "redirect:/system/paymentscheme/items/pricedetail/"
				+ billingItemPriceDetail.getId();
	}

}
