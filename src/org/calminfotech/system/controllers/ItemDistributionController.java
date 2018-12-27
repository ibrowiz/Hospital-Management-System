package org.calminfotech.system.controllers;

import javax.validation.Valid;

import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.forms.GlobalItemForm;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.GlobalItemTypeList;
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
@RequestMapping(value = "/system/globalitem")
public class ItemDistributionController {

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private GlobalItemBo itemDistributionBo;

	@Autowired
	private GlobalItemTypeList itemTypeList;

	@RequestMapping(value = { "", "/index" })
	@Layout(value = "layouts/datatable")
	public String indexAction(Model model) {
		model.addAttribute("itemdistritable", itemDistributionBo.fetchAll());
		return "system/globalitem/index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String add(Model model, GlobalItemForm globalItemForm) {
		GlobalItem globalItem = new GlobalItem();
		model.addAttribute("itemtype", this.itemTypeList.fetchAll());
		globalItemForm.setItem_name(globalItem.getName());
		model.addAttribute("pForm", globalItemForm);
		return "system/globalitem/add";
	}

	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAction(
			@Valid @ModelAttribute("pForm") GlobalItemForm globalItemForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return "system/globalitem/add";
		}

		itemDistributionBo.save(globalItemForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				" Success! New Item Succesfully Added! ");
		return "redirect:/system/globalitem/add/";
	}
}
