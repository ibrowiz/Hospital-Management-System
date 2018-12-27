package org.calminfotech.system.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.VisitStatusBo;
import org.calminfotech.system.forms.VisitStatusForm;
import org.calminfotech.system.models.VisitStatus;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
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
@RequestMapping(value = "system/visits/statuses")
public class VisitationStatusController {

	@Autowired
	private VisitStatusBo visitStatusBo;

	@Autowired
	private Alert alert;
	
	@Autowired
	private Auditor auditor;
	
	@Autowired
	private UserIdentity userIdentity;

	@RequestMapping(value = { "", "/index" })
	public String indexAction(Model model) {

		model.addAttribute("statuses",
				this.visitStatusBo.fetchAllByOrganisation());
		return "system/visits/statuses/index";
	}

	// No need for it sha!
	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") int id, Model model,
			RedirectAttributes redirectAttributes) {
		VisitStatus status = this.visitStatusBo.getStatusById(id);

		if (null == status) {
			this.alert.setAlert(redirectAttributes, Alert.WARNING,
					"Invalid Resource");
			return "redirect:/system/visits/statuses/index";
		}

		model.addAttribute("status", status);
		return "system/visits/statuses/view";
	}

	@RequestMapping(value = "/add")
	public String addAction(Model model) {
		VisitStatusForm dForm = new VisitStatusForm();

		model.addAttribute("dForm", dForm);
		return null;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("dForm") VisitStatusForm form,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "system/visits/statuses/add";
		}

		this.visitStatusBo.save(form);
		this.alert
				.setAlert(redirectAttributes, Alert.SUCCESS, "Status created");

		return "redirect:/system/visits/statuses";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editAtcion(@PathVariable("id") int id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		VisitStatus status = this.visitStatusBo.getStatusById(id);
		if (null == status) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/system/visits/statuses/index";
		}

		VisitStatusForm dForm = new VisitStatusForm();
		dForm.setType(status.getType());
		dForm.setId(status.getId());

		this.auditor.before(request, "Visit Status", dForm);
		model.addAttribute("status", status);
		model.addAttribute("dForm", dForm);

		return "system/visits/statuses/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("dForm") VisitStatusForm form,
			BindingResult result, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		if (result.hasErrors()) {
			return "system/visits/statuses/edit";
		}

		this.visitStatusBo.update(form);

		this.auditor.after(request, "Consultation Status", form,
				this.userIdentity.getUsername());
		this.alert
				.setAlert(redirectAttributes, Alert.SUCCESS, "Status updated");

		return "redirect:/system/visits/statuses/index";
	}

	public String deleteAction() {
		// First check if any consultation is attached before deleting
		return null;
	}

	@RequestMapping(value = "/startpoint/{id}")
	public String startPointAction(@PathVariable("id") int id, Model model,
			RedirectAttributes redirectAttributes) {
		VisitStatus status = this.visitStatusBo.getStatusById(id);
		if (null == status) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/system/visits/statuses/index";
		}

		VisitStatusForm dForm = new VisitStatusForm();
		dForm.setId(status.getId());

		model.addAttribute("dForm", dForm);
		model.addAttribute("status", status);

		return "system/visits/statuses/start_point";
	}

	@RequestMapping(value = "/startpoint/{id}", method = RequestMethod.POST)
	public String changeStartPoint(
			@ModelAttribute("dForm") VisitStatusForm form,
			RedirectAttributes redirectAttributes) {
		
		// Re-evaluate if on going process can affect!!!
		
		this.visitStatusBo.changeStartPoint(form);

		this.alert
				.setAlert(redirectAttributes, Alert.SUCCESS, "Visit Start point changed");
		return "redirect:/system/visits/statuses/index";
	}
	


	@RequestMapping(value = "/endpoint/{id}")
	public String endPointAction(@PathVariable("id") int id, Model model,
			RedirectAttributes redirectAttributes) {
		VisitStatus status = this.visitStatusBo.getStatusById(id);
		if (null == status) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/system/visits/statuses/index";
		}

		VisitStatusForm dForm = new VisitStatusForm();
		dForm.setId(status.getId());

		model.addAttribute("dForm", dForm);
		model.addAttribute("status", status);

		return "system/visits/statuses/end_point";
	}

	@RequestMapping(value = "/endpoint/{id}", method = RequestMethod.POST)
	public String changeEndPoint(
			@ModelAttribute("dForm") VisitStatusForm form,
			RedirectAttributes redirectAttributes) {
		
		// Re-evaluate if on going process can affect!!!
		
		this.visitStatusBo.changeEndPoint(form);

		this.alert
				.setAlert(redirectAttributes, Alert.SUCCESS, "Visit End point changed");
		return "redirect:/system/visits/statuses/index";
	}

}
