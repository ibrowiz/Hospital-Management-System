package org.calminfotech.setup.controller;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.setup.boInterface.AllergyCategoryViewBo;
import org.calminfotech.setup.boInterface.AllergyCategoryBo;
import org.calminfotech.setup.forms.AllergyCategoryForm;
import org.calminfotech.setup.forms.AllergyForm;
import org.calminfotech.setup.forms.OrgForm;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.setup.models.AllergyCategoryView;
import org.calminfotech.setup.models.AllergyCategory;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "/admin/allergy/category")
public class AllergyCategoryController {
	
	@Autowired
	private AllergyCategoryBo aCategoryBo;
	
	/*@Autowired
	private GlobalItemCategoryBo1 globalItemCategoryBo;*/
	
	/*@Autowired
	private GlobalItemTypeBo globalItemTypeBo;*/
	
	@Autowired
	private AllergyCategoryBo aCatBo;
	
	@Autowired
	private AllergyCategoryViewBo aCatViewBo;
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private Auditor auditor;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/list")
	public String allergyCategories(Model model) {
		
		List<AllergyCategoryView> aCatView = aCatViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		//List<AllergyCategory1> allergyCat = this.aCategoryBo.fetchAll();
		model.addAttribute("categories", aCatView);
		System.out.println("Categories size is: " + aCatViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()).size() );
		OrgForm orgForm = new OrgForm();
		orgForm.setOrgId(userIdentity.getOrganisation().getId());
		model.addAttribute("orgform", orgForm);
		return "admin/medical/components/allergies/categories/index";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String add(Model model){
		//model.addAttribute("globalItemType", globalItemTypeBo.fetchAllByOrganisation());
		
		List<AllergyCategory> allergyCat = this.aCategoryBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
	    model.addAttribute("allergyCat",allergyCat);
		model.addAttribute("aCategoryForm", new AllergyCategoryForm());
		return "admin/medical/components/allergies/categories/add";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("aCategoryForm") AllergyCategoryForm allergyCategoryForm, 
					   BindingResult result, Model model,
					   RedirectAttributes redirectAttributes){
		
		
		aCategoryBo.save(allergyCategoryForm);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! "+allergyCategoryForm.getName()+" added Successfully!");
		return "redirect:/admin/allergy/category/list";
		

	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		List<AllergyCategoryView> aCatView = aCatViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		AllergyCategoryView ac = aCatViewBo.getAllergyCatViewById(id);
		AllergyCategory aCat = aCategoryBo.getCategoryById(id);
		if (null == aCat) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
		}
		AllergyCategoryForm allergyCategoryForm = new AllergyCategoryForm();
		allergyCategoryForm.setAllergyCategoryId(id);
		System.out.println("the parent");
		System.out.println("the parent "+ac.getParentId());
		allergyCategoryForm.setParentId(ac.getParentId());
		allergyCategoryForm.setName(ac.getName());
		//allergyForm.setCategory(allergy.getCategory());

		model.addAttribute("aCat", aCat);
		model.addAttribute("aCatForm", allergyCategoryForm);
		model.addAttribute("aCatView", aCatView);

		auditor.before(request, "AllergCategory Form", allergyCategoryForm);

		return "admin/medical/components/allergies/categories/edit";
	}

	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("aCatForm") AllergyCategoryForm allergyCategoryForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("categories", this.aCategoryBo.fetchAllByOrganisation(userIdentity.getUserId()));
			return "admin/medical/components/allergies/edit";
		}
		
		aCategoryBo.update(allergyCategoryForm);
		auditor.after(request, "AllergyCategory Form", allergyCategoryForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Allergy updated");

		return "redirect:/admin/allergy/category/list/";
				
	}
	
	@RequestMapping(value = "/categories/delete/{id}")
	public String deleteCategoryAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		AllergyCategory category = this.aCatBo.getCategoryById(id);
		if (null == category) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/admin/medical/components/allergies/categories";
		}

		AllergyCategoryForm form = new AllergyCategoryForm();
		form.setAllergyCategoryId(category.getAllergyCategoryId());
		form.setName(category.getName());

		model.addAttribute("aForm", form);
		model.addAttribute("category", category);

		return "admin/medical/components/allergies/categories/delete";
	}

	@RequestMapping(value = "/categories/delete/{id}", method = RequestMethod.POST)
	public String removeCategoryAction(
			@ModelAttribute("aForm") AllergyCategoryForm form,
			RedirectAttributes redirectAttributes) {

		AllergyCategory category = this.aCatBo.getCategoryById(form.getAllergyCategoryId());
				

		if (null == category) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/admin/allergy/category/categories/list";
		}

		this.aCatBo.delete(category);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Category Deleted!");

		return "redirect:/admin/allergy/category/list";
	}
	
	
	@RequestMapping(value = "/refreshallergycategory/{organisationId}", method = RequestMethod.GET,consumes="application/json")
	@ResponseBody
	public void refreshallerycategory(@PathVariable("organisationId") Integer organisationId,Model model)throws HibernateException, SQLException  {
		
		System.out.println("inrefresh");
		
		Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session
		    .connection()
		    .prepareCall("{?= call allergy_outerrecursive(?)}");

		  cs.registerOutParameter(1, Types.INTEGER);
		  cs.setInt(2, organisationId);
		  
		  
		  cs.execute();
		  System.out.println(cs.getInt(1));
		  
		  System.out.println("Done with the query");
		  
		;
		
		
		
	}

}
