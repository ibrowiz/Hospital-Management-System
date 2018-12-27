package org.calminfotech.system.controllers;

import java.util.GregorianCalendar;

import javassist.runtime.Cflow;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.system.boInterface.BGlobalCategoryBo;
import org.calminfotech.system.boInterface.CategoryBo;
import org.calminfotech.system.boInterface.GlobalItemCategoryBo;
import org.calminfotech.system.boInterface.GlobalItemTypeBo;
import org.calminfotech.system.forms.BGlobalCategoryForm;
import org.calminfotech.system.forms.GlobalItemCategoryForm;
import org.calminfotech.system.models.BGlobalCategory;
import org.calminfotech.system.models.GlobalItemCategory;
import org.calminfotech.system.models.GlobalItemType;
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
@RequestMapping(value = "/system/bcategory")
public class BGlobalCategoryController {

	@Autowired
	private BGlobalCategoryBo globalCategoryBo;

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

	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String index(Model model) {
		model.addAttribute("category", globalCategoryBo.fetchAll());
		return "system/category/index";
	}

	@RequestMapping(value = "/view/{categoryId}")
	@Layout("layouts/datatable")
	public String view(@PathVariable("categoryId") Integer categoryId,
			Model model, RedirectAttributes redirectAttributes) {
		BGlobalCategory gCategory = globalCategoryBo
				.getCategoryById(categoryId);
		if (null == gCategory) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/category";
				}
		BGlobalCategoryForm cForm = new BGlobalCategoryForm();
		cForm.setCategoryId(gCategory.getCategoryId());
		model.addAttribute("gCategory", gCategory);
		model.addAttribute("cForm", cForm);
		return "system/category/viewcategory";
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("globalItemType",
				globalItemTypeBo.fetchAllByOrganisation());

		model.addAttribute("categories", globalCategoryBo.fetchAll());
		model.addAttribute("categoryForm", new BGlobalCategoryForm());
		return "system/category/addcategory";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("categoryForm") BGlobalCategoryForm cForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			System.out.println("Errors here!!!");
			return "system/category/addCategory";
		}
		
		

		BGlobalCategory category = new BGlobalCategory();

		category.setCategoryId(cForm.getCategoryId());

		category.setParentCategoryId(cForm.getParentCategoryId());

		category.setCategoryName(cForm.getCategoryName());		
		
		category.setOrganisationId(userIdentity.getOrganisation().getId());

		category.setDescription(cForm.getDescription());
		
		category.setCreatedBy(userIdentity.getUsername());
		
		category.setCreateDate(new GregorianCalendar().getTime());
		
		category.setModifiedBy(userIdentity.getUsername());
		
		category.setModifyDate(new GregorianCalendar().getTime());
	

		globalCategoryBo.save(category);

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + cForm.getCategoryName() + " added Successfully!");
		return "redirect:/system/bcategory/index";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletRequest httpRequest) {
		// GlobalItemCategory gCategory =
		// globalItemCategoryBo.getGlobalCategoryItemById(id);
		BGlobalCategory category = globalCategoryBo.getCategoryById(id);

		String parent = category.getParentCategoryId();


		if (category == null) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/bcategory";
		}

		BGlobalCategoryForm cForm = new BGlobalCategoryForm();

		cForm.setCategoryId(category.getCategoryId());
		cForm.setDescription(category.getDescription());
		cForm.setCategoryName(category.getCategoryName());

		cForm.setParentCategoryId(parent);

	//	 cForm.setItemTypeId(category.getItemTypeId().getGlobalitemTypeId());

		model.addAttribute("categories", globalCategoryBo.fetchAll());
		model.addAttribute("globalItemType",
				globalItemTypeBo.fetchAllByOrganisation());
		model.addAttribute("cForm", cForm);
		model.addAttribute("gCategory", category);
		// auditor
		auditor.before(httpRequest, "Category", cForm);

		return "system/category/editcategory";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("cForm") BGlobalCategoryForm cForm,
			BindingResult result, Model model, HttpServletRequest httpRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		BGlobalCategory category = this.globalCategoryBo.getCategoryById(cForm
				.getCategoryId());

		cForm.setModifiedBy(userIdentity.getUsername());
		cForm.setModifyDate(new GregorianCalendar().getTime());

		globalCategoryBo.update(category);

		 auditor.after(httpRequest, "HMO", cForm, userIdentity.getUsername());
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! " + cForm.getCategoryName() + " edited Successfully!");
		return "redirect:/system/category/index";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		GlobalItemCategory gCategory = globalItemCategoryBo
				.getGlobalCategoryItemById(id);
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
		GlobalItemCategory gCategory = globalItemCategoryBo
				.getGlobalCategoryItemById(cForm.getCategoryId());
		if (null == gCategory) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/category/index";
		}
		globalItemCategoryBo.delete(gCategory);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Global-Item Category " + cForm.getCategoryName()
						+ " deleted");
		return "redirect:/system/category/index";
	}

}
