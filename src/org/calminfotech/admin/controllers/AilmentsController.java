package org.calminfotech.admin.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.admin.boInterface.AilmentBo;
import org.calminfotech.admin.boInterface.AilmentClassificationBo;
import org.calminfotech.admin.forms.AilmentClassificationForm;
import org.calminfotech.admin.forms.AilmentForm;
import org.calminfotech.system.models.Ailment;
import org.calminfotech.system.models.AilmentClassification;
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
@RequestMapping(value = "/admin/medical/components/ailments")
public class AilmentsController {

	@Autowired
	private AilmentBo ailmentBo;

	@Autowired
	private AilmentClassificationBo ailmentClassificationBo;

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@RequestMapping(value = { "", "/" })
	@Layout("layouts/datatable")
	public String indexAction(Model model) {

		model.addAttribute("ailments", ailmentBo.fetchAll());
		return "admin/medical/components/ailments/index";
	}

	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Ailment ailment = ailmentBo.getAilmentById(id);
		if (null == ailment) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "/admin/medical/components/ailments";
		}
		model.addAttribute("ailment", ailment);
		return "admin/medical/components/ailments/view";
	}

	@RequestMapping(value = "/add")
	public String addAction(Model model) {

		AilmentForm ailmentForm = new AilmentForm();
		model.addAttribute("aForm", ailmentForm);
		model.addAttribute("classifications",
				ailmentClassificationBo.fetchAll());
		return "admin/medical/components/ailments/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("aForm") AilmentForm ailmentForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("classifications",
					ailmentClassificationBo.fetchAll());
			return "admin/medical/components/ailments/add";
		}

		Ailment ailment = ailmentBo.save(ailmentForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Ailment created");
		return "redirect:/admin/medical/components/ailments/view/"
				+ ailment.getId();
	}

	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		Ailment ailment = ailmentBo.getAilmentById(id);
		if (null == ailment) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/ailments";
		}

		AilmentForm ailmentForm = new AilmentForm();
		ailmentForm.setId(ailment.getId());
		ailmentForm.setName(ailment.getName());
		ailmentForm.setDescription(ailment.getDescription());
		ailmentForm.setClassificationId(ailment.getAilmentClassification()
				.getId());

		model.addAttribute("aForm", ailmentForm);
		model.addAttribute("ailment", ailment);
		model.addAttribute("classifications",
				ailmentClassificationBo.fetchAll());

		auditor.before(request, "Ailment Form", ailmentForm);

		return "admin/medical/components/ailments/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("aForm") AilmentForm ailmentForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("classifications",
					ailmentClassificationBo.fetchAll());
			return "admin/medical/components/ailments/edit";
		}

		ailmentBo.update(ailmentForm);

		auditor.after(request, "Ailment Form", ailmentForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Ailment updated");
		return "redirect:/admin/medical/components/ailments/view/"
				+ ailmentForm.getId();
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Ailment ailment = ailmentBo.getAilmentById(id);
		if (null == ailment) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/ailments";
		}
		AilmentForm ailmentForm = new AilmentForm();
		ailmentForm.setId(ailment.getId());

		model.addAttribute("aForm", ailmentForm);
		model.addAttribute("ailment", ailment);

		return "admin/medical/components/ailments/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("aForm") AilmentForm ailmentForm, Model model,
			RedirectAttributes redirectAttributes) {

		Ailment ailment = ailmentBo.getAilmentById(ailmentForm.getId());
		if (null == ailment) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/ailments";
		}

		ailmentBo.delete(ailment);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Ailment deleted");
		return "redirect:/admin/medical/components/ailments";
	}

	/**
	 * 
	 * Ailments Classification
	 * 
	 */

	@RequestMapping(value = "/classifications")
	@Layout("layouts/datatable")
	public String classIndexAction(Model model) {

		model.addAttribute("classifications",
				ailmentClassificationBo.fetchAll());
		return "admin/medical/components/ailments/classifications/index";
	}

	@RequestMapping(value = "/classifications/view/{id}")
	public String viewClassAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		AilmentClassification ailmentClassification = ailmentClassificationBo
				.getAilmentClassificationById(id);

		if (null == ailmentClassification) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");

			return "redirect:/admin/medical/components/ailments/classifications";
		}
		System.out.println("Count: "
				+ ailmentClassification.getAilments().size());
		model.addAttribute("classification", ailmentClassification);

		return "admin/medical/components/ailments/classifications/view";
	}

	@RequestMapping(value = "/classifications/add")
	public String addClassAction(Model model) {

		model.addAttribute("cForm", new AilmentClassificationForm());

		return "admin/medical/components/ailments/classifications/add";
	}

	@RequestMapping(value = "/classifications/add", method = RequestMethod.POST)
	public String saveClassAction(
			@Valid @ModelAttribute("cForm") AilmentClassificationForm ailmentClassificationForm,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "admin/medical/components/ailments/classifications/add";
		}

		AilmentClassification ailmentClassification = ailmentClassificationBo
				.save(ailmentClassificationForm);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Ailment Classification created");

		return "redirect:/admin/medical/components/ailments/classifications/view/"
				+ ailmentClassification.getId();
	}

	@RequestMapping(value = "/classifications/edit/{id}")
	public String editClassAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		AilmentClassification ailmentClassification = ailmentClassificationBo
				.getAilmentClassificationById(id);
		if (null == ailmentClassification) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/ailments/classifications";
		}

		AilmentClassificationForm ailmentClassificationForm = new AilmentClassificationForm();
		ailmentClassificationForm.setId(ailmentClassification.getId());
		ailmentClassificationForm.setName(ailmentClassification.getName());
		ailmentClassificationForm.setDescription(ailmentClassification
				.getDescription());

		model.addAttribute("cForm", ailmentClassificationForm);
		model.addAttribute("classification", ailmentClassification);

		auditor.before(request, "Ailment Classification Form",
				ailmentClassificationForm);

		return "admin/medical/components/ailments/classifications/edit";
	}

	@RequestMapping(value = "/classifications/edit/{id}", method = RequestMethod.POST)
	public String updateClassAction(
			@Valid @ModelAttribute("cForm") AilmentClassificationForm ailmentClassificationForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "admin/medical/components/ailments/classifications/edit";
		}
		ailmentClassificationBo.update(ailmentClassificationForm);
		auditor.after(request, "Ailment Classification Form",
				ailmentClassificationForm, userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Ailment Classification updated");

		return "redirect:/admin/medical/components/ailments/classifications/view/"
				+ ailmentClassificationForm.getId();
	}

	@RequestMapping(value = "/classifications/delete/{id}")
	public String deleteClassAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		AilmentClassification ailmentClassification = ailmentClassificationBo
				.getAilmentClassificationById(id);
		if (null == ailmentClassification) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/ailments/classifications";
		}
		AilmentClassificationForm ailmentClassificationForm = new AilmentClassificationForm();
		ailmentClassificationForm.setId(ailmentClassification.getId());
		ailmentClassificationForm.setName(ailmentClassification.getName());
		ailmentClassificationForm.setDescription(ailmentClassification
				.getDescription());

		model.addAttribute("cForm", ailmentClassificationForm);
		model.addAttribute("classification", ailmentClassification);
		return "admin/medical/components/ailments/classifications/delete";
	}

	@RequestMapping(value = "/classifications/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteClassAction(
			@ModelAttribute("cForm") AilmentClassificationForm ailmentClassificationForm,
			RedirectAttributes redirectAttributes) {

		AilmentClassification ailmentClassification = ailmentClassificationBo
				.getAilmentClassificationById(ailmentClassificationForm.getId());

		if (null == ailmentClassification) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/ailments/classifications";
		}

		ailmentClassificationBo.delete(ailmentClassification);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Ailment Classification deleted");

		return "redirect:/admin/medical/components/ailments/classifications";
	}
}
