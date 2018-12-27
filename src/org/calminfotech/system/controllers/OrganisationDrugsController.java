package org.calminfotech.system.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.admin.boInterface.DrugBo;
import org.calminfotech.system.boInterface.OrganisationDrugBo;
import org.calminfotech.system.forms.OrganisationDrugForm;
import org.calminfotech.system.models.Drug;
import org.calminfotech.system.models.OrganisationDrug;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
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
@RequestMapping(value = "/system/pharmaceutical/drugs")
public class OrganisationDrugsController {

	@Autowired
	private OrganisationDrugBo organisationDrugBo;

	@Autowired
	private DrugBo drugBo;

	@Autowired
	private Auditor auditor;

	@Autowired
	private Alert alert;
	
	@Autowired
	private UserIdentity userIdentity;

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = { "", "/" })
	public String indexAction(Model model) {

		model.addAttribute("oDrugs",
				this.organisationDrugBo.fetchAllByOrganisation());
		return "/system/pharmaceutical/drugs/index";
	}

	@RequestMapping(value = "/add")
	public String addAction(Model model) {

		OrganisationDrugForm dForm = new OrganisationDrugForm();

		model.addAttribute("drugs", this.drugBo.fetchAll());
		model.addAttribute("dForm", dForm);
		return null;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("dForm") OrganisationDrugForm organisationDrugForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("drugs", this.drugBo.fetchAll());
			return "system/pharmaceutical/drugs/add";
		}

		Drug drug = this.drugBo.getDrugById(organisationDrugForm.getDrugId());

		OrganisationDrug testODrug = this.organisationDrugBo
				.getOrganisationDrugByDrugAndOrganisation(drug);

		if (null != testODrug) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Drug " + drug.getName() + " has already been listed");
			return "redirect:/system/pharmaceutical/drugs/add";
		}

		OrganisationDrug oDrug = this.organisationDrugBo
				.save(organisationDrugForm);

		this.alert.setAlert(redirectAttributes, Alert.SUCCESS, "Drug saved!");
		return "redirect:/system/pharmaceutical/drugs/view/"
				+ oDrug.getDrug().getId();
	}

	@RequestMapping(value = "/view/{drugId}")
	public String viewAction(@PathVariable("drugId") int drugId, Model model) {
		Drug drug = this.drugBo.getDrugById(drugId);

		OrganisationDrug oDrug = this.organisationDrugBo
				.getOrganisationDrugByDrugAndOrganisation(drug);

		model.addAttribute("oDrug", oDrug);
		return "system/pharmaceutical/drugs/view";
	}

	@RequestMapping(value = "/edit/{drugId}")
	public String editAction(@PathVariable("drugId") int drugId, Model model,
			HttpServletRequest request) {

		Drug drug = this.drugBo.getDrugById(drugId);

		OrganisationDrug oDrug = this.organisationDrugBo
				.getOrganisationDrugByDrugAndOrganisation(drug);

		OrganisationDrugForm dForm = new OrganisationDrugForm();
		dForm.setDrugId(drugId);
		dForm.setPrice(oDrug.getPrice());

		model.addAttribute("dForm", dForm);
		model.addAttribute("drug", drug);
		List<Drug> onlyOne = new ArrayList<Drug>();
		onlyOne.add(drug);
		model.addAttribute("drugs", onlyOne);

		this.auditor.before(request, "Organisation Drug Form", dForm);

		return "system/pharmaceutical/drugs/edit";
	}

	@RequestMapping(value = "/edit/{drugId}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("dForm") OrganisationDrugForm organisationDrugForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (result.hasErrors()) {
			Drug drug = this.drugBo.getDrugById(organisationDrugForm
					.getDrugId());
			model.addAttribute("drug", drug);
			model.addAttribute("drugs", this.drugBo.fetchAll());
			return "system/pharmaceutical/drugs/edit";
		}

		this.organisationDrugBo.update(organisationDrugForm);
		this.auditor.after(request, "Organisation Drug Form", organisationDrugForm, this.userIdentity.getUsername());

		this.alert.setAlert(redirectAttributes, Alert.SUCCESS, "Drug saved!");
		return "redirect:/system/pharmaceutical/drugs/view/"
				+ organisationDrugForm.getDrugId();
	}

	@RequestMapping(value = "/delete/{drugId}")
	public String deleteAction(@PathVariable("drugId") int drugId, Model model) {

		Drug drug = this.drugBo.getDrugById(drugId);

		OrganisationDrug oDrug = this.organisationDrugBo
				.getOrganisationDrugByDrugAndOrganisation(drug);

		OrganisationDrugForm dForm = new OrganisationDrugForm();
		dForm.setDrugId(drugId);

		model.addAttribute("dForm", dForm);
		model.addAttribute("oDrug", oDrug);
		return "system/pharmaceutical/drugs/delete";
	}

	@RequestMapping(value = "/delete/{drugId}", method = RequestMethod.POST)
	public String removeAction(
			@ModelAttribute("dForm") OrganisationDrugForm organisationDrugForm,
			RedirectAttributes redirectAttributes) {

		Drug drug = this.drugBo.getDrugById(organisationDrugForm.getDrugId());

		OrganisationDrug organisationDrug = this.organisationDrugBo
				.getOrganisationDrugByDrugAndOrganisation(drug);
		this.organisationDrugBo.delete(organisationDrug);

		this.alert.setAlert(redirectAttributes, Alert.SUCCESS, "Drug deleted");

		return "redirect:/system/pharmaceutical/drugs";
	}
}
