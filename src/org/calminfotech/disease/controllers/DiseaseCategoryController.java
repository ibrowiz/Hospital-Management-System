package org.calminfotech.disease.controllers;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.disease.boInterface.DiseaseCategoryBo;
import org.calminfotech.disease.boInterface.DiseaseCategoryViewBo;
import org.calminfotech.disease.forms.DiseaseCategoryForm;
import org.calminfotech.disease.models.DiseaseCategory;
import org.calminfotech.disease.models.DiseaseCategoryView;
import org.calminfotech.setup.forms.AllergyCategoryForm;
import org.calminfotech.setup.forms.OrgForm;
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
@RequestMapping(value = "/disease/categories")
public class DiseaseCategoryController {
	
	@Autowired
	private Auditor auditor;
	
	@Autowired
	private DiseaseCategoryViewBo diseaseCatViewBo;
	
	
	@Autowired
	private DiseaseCategoryBo diseaseCatBo;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@RequestMapping(value = { "", "/" })
	@Layout(value = "layouts/datatable")
	public String indexAction(Model model) {
		List<DiseaseCategoryView> DCatView = diseaseCatViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		model.addAttribute("DCatView", DCatView);
		OrgForm orgForm = new OrgForm();
		orgForm.setOrgId(userIdentity.getOrganisation().getId());
		model.addAttribute("orgform", orgForm);
		//model.addAttribute("allergies", aViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		return "admin/medical/components/disease/categories/index";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String add(Model model){
		//model.addAttribute("globalItemType", globalItemTypeBo.fetchAllByOrganisation());
		
		List<DiseaseCategory> diseaseCat = this.diseaseCatBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
	    model.addAttribute("diseaseCat",diseaseCat);
		model.addAttribute("dCategoryForm", new DiseaseCategoryForm());
		return "admin/medical/components/disease/categories/add";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("dCategoryForm") DiseaseCategoryForm diseaseCategoryForm, 
					   BindingResult result, Model model,
					   RedirectAttributes redirectAttributes){
		
		
		diseaseCatBo.save(diseaseCategoryForm);
		
		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! "+diseaseCategoryForm.getName()+" added Successfully!");
		return "redirect:/disease/categories";
		

	}

	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		List<DiseaseCategoryView> dCatView = this.diseaseCatViewBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		DiseaseCategoryView dc = diseaseCatViewBo.getDiseaseCatViewById(id);
		DiseaseCategory dCat = this.diseaseCatBo.getDiseaseCategoryById(id);
		if (null == dCat) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
		}
		DiseaseCategoryForm diseaseCategoryForm = new DiseaseCategoryForm();
		diseaseCategoryForm.setDiseaseCategoryId(id);
		//System.out.println("the parent");
		//System.out.println("the parent "+ac.getParentId());
		diseaseCategoryForm.setParentId(dc.getParentId());
		diseaseCategoryForm.setName(dc.getName());
		//allergyForm.setCategory(allergy.getCategory());

		model.addAttribute("dCat", dCat);
		model.addAttribute("dCatForm", diseaseCategoryForm);
		model.addAttribute("dCatView", dCatView);

		auditor.before(request, "disease Category Form", diseaseCategoryForm);

		return "admin/medical/components/disease/categories/edit";
	}

	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("dCatForm") DiseaseCategoryForm diseaseCategoryForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("categories", this.diseaseCatBo.fetchAllByOrganisation(userIdentity.getUserId()));
			return "admin/medical/components/disease/edit";
		}
		
		diseaseCatBo.update(diseaseCategoryForm);
		auditor.after(request, "Disease Category Form", diseaseCategoryForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Allergy updated");

		return "redirect:/disease/categories";
				
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteCategoryAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		DiseaseCategory diseaceCategory = this.diseaseCatBo.getDiseaseCategoryById(id);
		if (null == diseaceCategory) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/disease/categories";
		}

		DiseaseCategoryForm diseaseCategoryForm = new DiseaseCategoryForm();
		diseaseCategoryForm.setDiseaseCategoryId(diseaceCategory.getDiseaseCategoryId());
		diseaseCategoryForm.setName(diseaceCategory.getName());

		model.addAttribute("diseaseCategoryForm", diseaseCategoryForm);
		model.addAttribute("diseaseCategory", diseaceCategory);

		return "admin/medical/components/disease/categories/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String removeCategoryAction(
			@ModelAttribute("diseaseCategoryForm") DiseaseCategoryForm diseaseCategoryForm,
			RedirectAttributes redirectAttributes) {

		DiseaseCategory diseaseCategory = this.diseaseCatBo.getDiseaseCategoryById(diseaseCategoryForm.getDiseaseCategoryId());
				

		if (null == diseaseCategory) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/disease/categories";
		}

		this.diseaseCatBo.delete(diseaseCategory);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Category Deleted!");

		return "redirect:/disease/categories";
	}
	
	@RequestMapping(value = "/refreshdiseasecategory/{organisationId}", method = RequestMethod.GET,consumes="application/json")
	@ResponseBody
	public void refreshallerycategory(@PathVariable("organisationId") Integer organisationId,Model model)throws HibernateException, SQLException  {
		
		System.out.println("inrefresh");
		
		Session session = sessionFactory.openSession();
		  CallableStatement cs = null;
		  cs = session
		    .connection()
		    .prepareCall("{?= call disease_outerrecursive(?)}");

		  cs.registerOutParameter(1, Types.INTEGER);
		  cs.setInt(2, organisationId);
		  
		  
		  cs.execute();
		  System.out.println(cs.getInt(1));
		  
		  System.out.println("Done with the query");
		  
		;
		
		
		
	}

}
