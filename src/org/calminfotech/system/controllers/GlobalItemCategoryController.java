package org.calminfotech.system.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.GlobalItemCategoryBo;
import org.calminfotech.system.boInterface.GlobalItemTypeBo;
import org.calminfotech.system.forms.GlobalItemCategoryForm;
import org.calminfotech.system.models.GlobalItemCategory;
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
@RequestMapping(value = "/system/category" )
public class GlobalItemCategoryController {
	
	@Autowired
	private GlobalItemCategoryBo globalItemCategoryBo;
	
	@Autowired
	private GlobalItemTypeBo globalItemTypeBo;
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private Auditor auditor;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@RequestMapping(value = {"","/index"}, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String index(Model model){
		model.addAttribute("category", globalItemCategoryBo.fetchAllByOrganisation());
		return "system/category/index";
	}
	
	@RequestMapping(value = "/view/{id}")
	@Layout("layouts/datatable")
	public String view(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		GlobalItemCategory gCategory = globalItemCategoryBo.getGlobalCategoryItemById(id);
		if (null == gCategory) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/category";
		}
		GlobalItemCategoryForm cForm = new GlobalItemCategoryForm();
		cForm.setCategoryId(gCategory.getCategoryId());
		model.addAttribute("gCategory", gCategory);
		model.addAttribute("cForm", cForm);
		return "system/category/view";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("globalItemType", globalItemTypeBo.fetchAllByOrganisation());
		model.addAttribute("categoryForm", new GlobalItemCategoryForm());
		return "system/category/add";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("categoryForm") GlobalItemCategoryForm cForm, 
					   BindingResult result, Model model,
					   RedirectAttributes redirectAttributes){
		
		if (result.hasErrors()) {	
			System.out.println("Errors here!!!");
			return "system/category/add";
		}
		globalItemCategoryBo.saveCategoryForm(cForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! "+cForm.getCategoryName()+" added Successfully!");
		return "redirect:/system/category/index";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request
			, HttpServletRequest httpRequest) {
		GlobalItemCategory gCategory = globalItemCategoryBo.getGlobalCategoryItemById(id);
		if(gCategory == null){
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/category";
		}
		GlobalItemCategoryForm cForm = new GlobalItemCategoryForm();
		cForm.setCategoryId(gCategory.getCategoryId());
		cForm.setCategoryDescription(gCategory.getDescription());
		cForm.setCategoryName(gCategory.getName());
		cForm.setItemTypeId(gCategory.getItemTypeId().getGlobalitemTypeId());
		model.addAttribute("globalItemType", globalItemTypeBo.fetchAllByOrganisation());
		model.addAttribute("cForm", cForm);
		model.addAttribute("gCategory", gCategory);
		// auditor
		auditor.before(httpRequest, "Category", cForm);
		return "system/category/edit";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(
			@Valid @ModelAttribute("cForm") GlobalItemCategoryForm cForm,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		globalItemCategoryBo.updateForm(cForm);
		auditor.after(httpRequest, "HMO", cForm, userIdentity.getUsername());
		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! "+cForm.getCategoryName()+" edited Successfully!");
		return "redirect:/system/category/index";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		GlobalItemCategory gCategory = globalItemCategoryBo.getGlobalCategoryItemById(id);
		if (null == gCategory) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/category/index";
		}
		GlobalItemCategoryForm cForm = new GlobalItemCategoryForm();
		cForm.setCategoryId(gCategory.getCategoryId());
		cForm.setCategoryName(gCategory.getName());
		
		model.addAttribute("gCategory", gCategory);
		model.addAttribute("cForm", cForm);
		return "system/category/delete";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDelete(
			@ModelAttribute("cForm") GlobalItemCategoryForm cForm,
			RedirectAttributes redirectAttributes) {
		GlobalItemCategory gCategory = globalItemCategoryBo.getGlobalCategoryItemById(cForm.getCategoryId());
		if (null == gCategory) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/category/index";
		}
		globalItemCategoryBo.delete(gCategory);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Global-Item Category "+cForm.getCategoryName()+" deleted");
		return "redirect:/system/category/index";
	}
}
