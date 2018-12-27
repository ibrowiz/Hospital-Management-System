package org.calminfotech.admin.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.admin.boInterface.DrugBo;
import org.calminfotech.admin.boInterface.DrugClassificationBo;
import org.calminfotech.admin.forms.DrugClassificationForm;
import org.calminfotech.admin.forms.DrugForm;
import org.calminfotech.system.models.Drug;
import org.calminfotech.system.models.DrugClassification;
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
@RequestMapping(value = "/admin/pharmaceutical/drugs")
public class DrugsController {

	@Autowired
	private DrugClassificationBo drugClassificationBo;

	@Autowired
	private DrugBo drugBo;

	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private Auditor auditor;

	@RequestMapping(value = { "", "/" })
	@Layout("layouts/datatable")
	public String indexAction(Model model) {

		model.addAttribute("drugs", this.drugBo.fetchAll());
		return "admin/pharmaceutical/drugs/index";
	}

	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Drug drug = this.drugBo.getDrugById(id);

		if (null == drug) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/pharmaceutical/drugs";
		}

		model.addAttribute("drug", drug);

		return "admin/pharmaceutical/drugs/view";
	}

	@RequestMapping(value = "/add")
	public String addAction(Model model) {

		model.addAttribute("dForm", new DrugForm());
		model.addAttribute("classifications",
				this.drugClassificationBo.fetchAll());
		/**
		 * Other description of drugs should be included in the form
		 */
		return "admin/pharmaceutical/drugs/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveAction(@Valid @ModelAttribute("dForm") DrugForm drugForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("classifications",
					this.drugClassificationBo.fetchAll());
			return "admin/pharmaceutical/drugs/add";
		}

		Drug drug = this.drugBo.save(drugForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! Drug saved");

		return "redirect:/admin/pharmaceutical/drugs/view/" + drug.getId();
	}

	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		Drug drug = this.drugBo.getDrugById(id);
		if (null == drug) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/pharmaceutical/drugs";
		}

		DrugForm drugForm = new DrugForm();
		drugForm.setId(drug.getId());
		drugForm.setName(drug.getName());
		drugForm.setDescription(drug.getDescription());
		drugForm.setClassificationId(drug.getDrugClassification().getId());

		model.addAttribute("dForm", drugForm);
		model.addAttribute("drug", drug);
		model.addAttribute("classifications",
				this.drugClassificationBo.fetchAll());

		auditor.before(request, "Drug Entry Form", drugForm);

		return "admin/pharmaceutical/drugs/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("dForm") DrugForm drugForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (result.hasErrors()) {
			Drug drug = this.drugBo.getDrugById(drugForm.getId());
			model.addAttribute("drug", drug);
			model.addAttribute("classifications",
					this.drugClassificationBo.fetchAll());
			return "admin/pharmaceutical/drugs/edit";
		}

		this.drugBo.update(drugForm);
		auditor.after(request, "Drug Entry Form", drugForm,
				userIdentity.getUsername());
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Drug updated");

		return "redirect:/admin/pharmaceutical/drugs/view/" + drugForm.getId();
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Drug drug = this.drugBo.getDrugById(id);
		if (null == drug) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/pharmaceutical/drugs";
		}
		DrugForm drugForm = new DrugForm();
		drugForm.setId(drug.getId());

		model.addAttribute("dForm", drugForm);
		model.addAttribute("drug", drug);

		return "admin/pharmaceutical/drugs/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("dForm") DrugForm drugForm,
			RedirectAttributes redirectAttributes) {
		Drug drug = this.drugBo.getDrugById(drugForm.getId());
		if (null == drug) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/pharmaceutical/drugs";
		}

		this.drugBo.delete(drug);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Drug deleted");
		return "redirect:/admin/pharmaceutical/drugs";
	}

	/**
	 * 
	 * Drugs classifications
	 * 
	 */
	@RequestMapping(value = { "/classifications", "/classifications/" })
	@Layout("layouts/datatable")
	public String classIndexAction(Model model) {
		model.addAttribute("classifications",
				this.drugClassificationBo.fetchAll());
		return "admin/pharmaceutical/drugs/classifications/index";
	}

	@RequestMapping(value = "/classifications/view/{id}")
	public String classViewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		DrugClassification drugClassification = this.drugClassificationBo
				.getDrugClassificationById(id);

		if (null == drugClassification) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/pharmaceutical/drugs/classifications";
		}

		model.addAttribute("classification", drugClassification);
		return "admin/pharmaceutical/drugs/classifications/view";
	}

	@RequestMapping(value = "/classifications/add")
	public String classAddAction(Model model) {

		model.addAttribute("cForm", new DrugClassificationForm());

		return "admin/pharmaceutical/drugs/classifications/add";
	}

	@RequestMapping(value = "/classifications/add", method = RequestMethod.POST)
	public String classSaveAction(
			@Valid @ModelAttribute("dForm") DrugClassificationForm drugClassificationForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "admin/pharmaceutical/drugs/classifications/add";
		}

		DrugClassification drugClassification = this.drugClassificationBo
				.save(drugClassificationForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Classification Added");
		return "redirect:/admin/pharmaceutical/drugs/classifications/view/"
				+ drugClassification.getId();

	}

	@RequestMapping(value = "/classifications/edit/{id}")
	public String classEditAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		DrugClassification drugClassification = this.drugClassificationBo
				.getDrugClassificationById(id);

		if (null == drugClassification) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/pharmaceutical/drugs/classifications";
		}

		DrugClassificationForm drugClassificationForm = new DrugClassificationForm();

		drugClassificationForm.setId(drugClassification.getId());
		drugClassificationForm.setName(drugClassification.getName());
		drugClassificationForm.setDescription(drugClassification
				.getDescription());
		model.addAttribute("cForm", drugClassificationForm);
		model.addAttribute("classification", drugClassification);

		auditor.before(request, "Drug Classification Form",
				drugClassificationForm);

		return "admin/pharmaceutical/drugs/classifications/edit";
	}

	@RequestMapping(value = "/classifications/edit/{id}", method = RequestMethod.POST)
	public String classUpdateAction(
			@Valid @ModelAttribute("cForm") DrugClassificationForm drugClassificationForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "admin/pharmaceutical/drugs/classifications/edit";
		}

		this.drugClassificationBo.update(drugClassificationForm);
		auditor.after(request, "Drug Classification Form",
				drugClassificationForm, userIdentity.getUsername());

		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Drug Classification updated");
		return "redirect:/admin/pharmaceutical/drugs/classifications/view/"
				+ drugClassificationForm.getId();
	}

	@RequestMapping(value = "/classifications/delete/{id}")
	public String classDeleteAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		DrugClassification drugClassification = this.drugClassificationBo
				.getDrugClassificationById(id);

		if (null == drugClassification) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/pharmaceutical/drugs/classifications";
		}

		DrugClassificationForm drugClassificationForm = new DrugClassificationForm();

		drugClassificationForm.setId(drugClassification.getId());
		drugClassificationForm.setName(drugClassification.getName());
		drugClassificationForm.setDescription(drugClassification
				.getDescription());
		model.addAttribute("cForm", drugClassificationForm);
		model.addAttribute("classification", drugClassification);

		return "admin/pharmaceutical/drugs/classifications/delete";
	}

	@RequestMapping(value = "/classifications/delete/{id}", method = RequestMethod.POST)
	public String classConfirmDeleteAction(
			@ModelAttribute("cForm") DrugClassificationForm drugClassificationForm,
			Model model, RedirectAttributes redirectAttributes) {
		DrugClassification drugClassification = this.drugClassificationBo
				.getDrugClassificationById(drugClassificationForm.getId());
		if (null == drugClassification) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/pharmaceutical/drugs/classifications";
		}

		this.drugClassificationBo.delete(drugClassification);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Classification Deleted");

		return "redirect:/admin/pharmaceutical/drugs/classifications";
	}
}
