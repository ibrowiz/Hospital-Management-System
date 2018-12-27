package org.calminfotech.staffreg.controllers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.calminfotech.patient.forms.PatientAllergyForm;
import org.calminfotech.patient.forms.PatientImageForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergyView;
import org.calminfotech.setup.boInterface.UnitListBo;
import org.calminfotech.setup.forms.UnitCategoryForm;
import org.calminfotech.setup.models.HrUnitCategory;
import org.calminfotech.staffreg.boInterface.StaffRegBoInterface;
import org.calminfotech.staffreg.forms.StaffRegForm;
import org.calminfotech.staffreg.models.StaffRegistration;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.annotations.Layout;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
@RequestMapping(value = "/staffreg")
public class StaffRegController {
	
	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;
	
	@Autowired
	private UnitListBo unitListBo;
	
	@Autowired
	StaffRegBoInterface staffRegBo;
	
	@Autowired
	UserIdentity userIdentity;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listAll(Model model) {
		List<StaffRegistration> staffRegistration = this.staffRegBo
				.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		model.addAttribute("staffRegistration", staffRegistration);
		return "staffreg/index";
	}

	
	@Layout(value = "layouts/form_wizard_layout")
	@RequestMapping(value = "/add")
	public String addAction(Model model) throws HibernateException, SQLException {
		 System.out.println("Before query");
		  

		  Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session
		    .connection()
		    .prepareCall(
		      "{?= call unit_outerrecursiveproc}");

		  cs.registerOutParameter(1, Types.INTEGER);
		  
		  
		  
		  cs.execute();
		  System.out.println(cs.getInt(1));
		  
		  System.out.println("Done with the query");
		model.addAttribute("staffRegForm", new StaffRegForm());
		model.addAttribute("unit", unitListBo.fetchAllByOrganisationId(userIdentity.getOrganisation().getId()));

		return "staffreg/add";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("staffRegForm") StaffRegForm staffRegForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		/*if (result.hasErrors()) {
			model.addAttribute("categories", this.allergyCategoryBo.fetchAll());
			return "admin/medical/components/allergies/add";
		}*/
         //System.out.println("Id is" + allergyForm.getCategoryId());
		this.staffRegBo.save(staffRegForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Staff added!");
		/*return "redirect:/admin/medical/components/allergies/view/"
				+ allergy.getAllergyId();*/
		
		return "redirect:/staffreg/list";
	}

	@Layout("layouts/form_wizard_layout")
	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		StaffRegistration staffRegistration = this.staffRegBo.getStaffById(id);
		
		if (null == staffRegistration) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
		}
		StaffRegForm staffRegForm = new StaffRegForm();
		staffRegForm.setId(id);
		staffRegForm.setFirstName(staffRegistration.getFirstName());
		staffRegForm.setLastName(staffRegistration.getLastName());
		staffRegForm.setEmail(staffRegistration.getEmail());
		staffRegForm.setDob(staffRegistration.getDob());
		staffRegForm.setAddress(staffRegistration.getAddress());
		staffRegForm.setQualifications(staffRegistration.getQualifications());
		staffRegForm.setUnit(staffRegistration.getUnit());
		model.addAttribute("staffRegForm", staffRegForm);
		model.addAttribute("unit", unitListBo.fetchAllByOrganisationId(userIdentity.getOrganisation().getId()));
		auditor.before(request, "staff Form", staffRegForm);

		return "staffreg/edit";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("staffRegForm") StaffRegForm staffRegForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		/*
		 * if (result.hasErrors()) { model.addAttribute("categories",
		 * this.unitCategoryBo
		 * .fetchAllByOrganisation(userIdentity.getUserId())); return
		 * "admin/medical/components/allergies/edit"; }
		 */
	
		staffRegBo.update(staffRegForm);

			auditor.after(request, "staff Form", staffRegForm,
					userIdentity.getUsername());
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Staff updated");

		
		return "redirect:/staffreg/list";
	}

	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		StaffRegistration staffRegistration = this.staffRegBo.getStaffById(id);
		
		
		model.addAttribute("staffRegistration", staffRegistration);
		model.addAttribute("id", id);
		
		return "staffreg/view";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") int id,
			Model model, RedirectAttributes redirectAttributes) {

		StaffRegistration staffRegistration = this.staffRegBo.getStaffById(id);
		StaffRegForm staffRegForm = new StaffRegForm();
		staffRegForm.setId(id);
		staffRegForm.setFirstName(staffRegistration.getFirstName());

		model.addAttribute("staffRegForm", staffRegForm);
		model.addAttribute("staffRegistration", staffRegistration);

		return "staffreg/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String removeAction(
			@ModelAttribute("staffRegForm") StaffRegForm staffRegForm,
			RedirectAttributes redirectAttributes) {

		StaffRegistration staffRegistration = this.staffRegBo.getStaffById(staffRegForm.getId());

		if (null == staffRegistration) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/staffreg/list";
		}

		this.staffRegBo.delete(staffRegistration);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"staff Deleted!");

		return "redirect:/staffreg/list";
	}
}
