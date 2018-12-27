package org.calminfotech.setup.controller;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.patient.forms.PatientAllergyForm;
import org.calminfotech.setup.boInterface.AllergyBo;
import org.calminfotech.setup.boInterface.AllergyCategoryViewBo;
import org.calminfotech.setup.boInterface.AllergyCategoryBo;
import org.calminfotech.setup.boInterface.AllergyCategoryBo;
import org.calminfotech.setup.boInterface.AllergyViewBo;
import org.calminfotech.setup.boInterface.CategoryListBo;
import org.calminfotech.setup.forms.AllergyCategoryForm;
import org.calminfotech.setup.forms.AllergyCategoryForm;
import org.calminfotech.setup.forms.AllergyForm;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.setup.models.AllergyCategoryView;

import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.setup.models.AllergyView;
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
@RequestMapping(value = "/admin/medical/components/allergies")
public class AllergiesController {

	@Autowired
	private AllergyBo allergyBo;
	
	@Autowired
	private AllergyCategoryViewBo aCatViewBo;
	
	@Autowired
	private AllergyViewBo aViewBo;

	@Autowired
	private AllergyCategoryBo allergyCategoryBo;
	
	@Autowired
	private AllergyCategoryBo allergyCategoryBo1;
	
	@Autowired
	private CategoryListBo catListBo;
	


	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@RequestMapping(value = { "", "/" })
	@Layout(value = "layouts/datatable")
	public String indexAction(Model model) {
		List<AllergyView> aView = aViewBo.fetchAll();
		model.addAttribute("aView", aView);
		model.addAttribute("allergies", aViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		return "admin/medical/components/allergies/index";
	}

	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes, Model model) {

		Allergy allergy = allergyBo.getAllergyById(id);
		if (null == allergy) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource!");
			return "redirect:/admin/medical/components/allergies";
		}

		model.addAttribute("allergy", allergy);
		return "admin/medical/components/allergies/view";
	}

	@RequestMapping(value = "/add")
	public String addAction(Model model) {
		 System.out.println("Before query");
		  

		/*  Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session
		    .connection()
		    .prepareCall(
		      "{?= call outerrecursiveproc}");

		  cs.registerOutParameter(1, Types.INTEGER);
		  
		  
		  
		  cs.execute();
		  System.out.println(cs.getInt(1));*/
		  
		  System.out.println("Done with the query");
		model.addAttribute("aForm", new AllergyForm());
		model.addAttribute("cat", this.catListBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));

		return "admin/medical/components/allergies/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("aForm") AllergyForm allergyForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		/*if (result.hasErrors()) {
			model.addAttribute("categories", this.allergyCategoryBo.fetchAll());
			return "admin/medical/components/allergies/add";
		}*/
         System.out.println("Id is" + allergyForm.getCategoryId());
		Allergy allergy = allergyBo.save(allergyForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Allergy added!");
		return "redirect:/admin/medical/components/allergies/";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		//List<AllergyCatView> aCatView = aCatViewBo.fetchAll();
		Allergy allergy = allergyBo.getAllergyById(id);
		if (null == allergy) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
		}
		AllergyForm allergyForm = new AllergyForm();
		allergyForm.setId(allergy.getAllergyId());
		allergyForm.setName(allergy.getName());
		allergyForm.setDescription(allergy.getDescription());
		allergyForm.setCategoryId(allergy.getCategory().getAllergyCategoryId());

		model.addAttribute("allergy", allergy);
		model.addAttribute("aForm", allergyForm);
		model.addAttribute("categories", this.aCatViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));

		auditor.before(request, "Allergies Form", allergyForm);

		return "admin/medical/components/allergies/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("aForm") AllergyForm allergyForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		/*if (result.hasErrors()) {
			model.addAttribute("categories", this.allergyCategoryBo.fetchAll());
			return "admin/medical/components/allergies/edit";
		}*/

		allergyBo.update(allergyForm);

		auditor.after(request, "Allergies Form", allergyForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Allergy updated");

		return "redirect:/admin/medical/components/allergies/view/"
				+ allergyForm.getId();
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Allergy allergy = allergyBo.getAllergyById(id);
		if (null == allergy) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/allergies";
		}
		AllergyForm allergyForm = new AllergyForm();
		allergyForm.setId(allergy.getAllergyId());
		model.addAttribute("aForm", allergyForm);
		model.addAttribute("allergy", allergy);

		return "admin/medical/components/allergies/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("aForm") AllergyForm allergyForm, Model model,
			RedirectAttributes redirectAttributes) {

		Allergy allergy = allergyBo.getAllergyById(allergyForm.getId());
		if (null == allergy) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/medical/components/allergies";
		}

		allergyBo.delete(allergy);
		alert.setAlert(redirectAttributes, Alert.ERROR,
				"Success! Allergy Deleted");

		return "redirect:/admin/medical/components/allergies";
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = "/categories")
	public String allergyCategories(Model model) {

		model.addAttribute("categories", this.aCatViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		System.out.println("Categories size is: " + aCatViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()).size());

		return "admin/medical/components/allergies/categories/index";
	}

	

}
