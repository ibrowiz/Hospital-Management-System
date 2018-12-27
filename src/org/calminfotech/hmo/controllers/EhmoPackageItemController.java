package org.calminfotech.hmo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.hmo.boInterface.EhmoPackageItemBo;
import org.calminfotech.hmo.boInterface.EhmoPackageBo;
import org.calminfotech.hmo.boInterface.ItemServiceGroupBo;
import org.calminfotech.hmo.forms.EhmoPackageItemForm;
import org.calminfotech.hmo.forms.EhmoPackageForm;
import org.calminfotech.hmo.forms.ItemServiceGroupForm;
import org.calminfotech.hmo.models.EhmoPackage;
import org.calminfotech.hmo.models.EhmoPackageItem;

//import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;

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
@RequestMapping(value = "/admin/hmos/multilevel/Item")
public class EhmoPackageItemController {

	@Autowired
	private EhmoPackageBo ehmoPackageBo;
	
	@Autowired
	private EhmoPackageItemBo ehmoItemBo;
	
	@Autowired
	private ItemServiceGroupBo itemServiceGroupBo;

	@Autowired
	private Alert alert;
/*
	@Autowired
	private UserIdentity userIdentity;*/

	@RequestMapping(value = { "", "/" })
	@Layout(value = "layouts/datatable")
	public String indexAction(Model model) {

		model.addAttribute("category",ehmoItemBo.fetchAll());
		return "admin/hmos/multilevel/Item/index";
	}

	@RequestMapping(value = "/view/{id}")
	@Layout(value = "layouts/datatable")
	public String viewAction(@PathVariable("id") Integer id,
	RedirectAttributes redirectAttributes, Model model) {
		
		EhmoPackageItem ehmoItem = ehmoItemBo.getEhmoItemById(id);
		
		if (null == ehmoItem) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource!");
			return "redirect:/admin/hmos/multilevel/Item";
		}
		model.addAttribute("category", ehmoItem);
		return "admin/hmos/multilevel/Item/view";
		
	}
	@RequestMapping(value = "/addItem")
	@Layout(value = "layouts/datatable")
	public String addAction(Model model) {

		model.addAttribute("aForm", new EhmoPackageItemForm());
		model.addAttribute("categories", this.ehmoPackageBo.fetchAll());
		//model.addAttribute("gForm", new ItemServiceGroupForm());
		model.addAttribute("service", this.itemServiceGroupBo.fetchAll());
		
		return "admin/hmos/multilevel/Item/addItem";
	}
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String saveAction(
			@Valid @ModelAttribute("aForm") EhmoPackageItem ehmoItem,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("categories", this.ehmoItemBo.fetchAll());
			return "admin/hmos/multilevel/Item/addItem";
		}
		EhmoPackageItem category = new EhmoPackageItem();
		category.setItemId(ehmoItem.getItemId());
		category.setName(ehmoItem.getName());
		category.setDescription(ehmoItem.getDescription());
		category.setPackageId(ehmoItem.getPackageId());
		category.setItemServiceId(ehmoItem.getItemServiceId());
		category.setSpendingLimit(ehmoItem.getSpendingLimit());
		category.setPeriod(ehmoItem.getPeriod());
		category.setPeriodNo(ehmoItem.getPeriodNo());
		category.setTimeNo(ehmoItem.getTimeNo());
	      this.ehmoItemBo.save(ehmoItem);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Item added!");
		return "admin/hmos/multilevel/ltem";
			//	+ ehmoItem.getItemId();
				//+ category.getCategoryId();
	}
	@RequestMapping(value = "/edit/{id}")
	@Layout(value = "layouts/datatable")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		EhmoPackageItem category = ehmoItemBo.getEhmoItemById(id);
		if (null == category) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
		}
		EhmoPackageItemForm ehmoItem = new EhmoPackageItemForm();
		ehmoItem.setPackageId(category.getPackageId());
		ehmoItem.setName(category.getName());
		ehmoItem.setDescription(category.getDescription());
		ehmoItem.setItemServiceId(category.getItemServiceId());
		ehmoItem.setSpendingLimit(category.getSpendingLimit());
		ehmoItem.setPeriodNo(category.getPeriodNo());
		ehmoItem.setPeriod(category.getPeriod());
		ehmoItem.setTimeNo(category.getTimeNo());
		
		model.addAttribute("category", category);
		model.addAttribute("aForm", ehmoItem);
		model.addAttribute("categories", this.ehmoItemBo.fetchAll());

		//auditor.before(request, "Category Form", categoryForm);

		return "admin/hmos/multilevel/Item/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String updateAction(
			@Valid @ModelAttribute("aForm") EhmoPackageItemForm ehmoItemForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("categories", this.ehmoPackageBo.fetchAll());
			return "admin/hmos/multilevel/Item/edit";	
		}
	EhmoPackageItem category  =ehmoItemBo.getEhmoItemById(ehmoItemForm.getItemId());
	//EhmoItem category = new EhmoItem();
	//categoryForm.setItemId(category.getItemId());
	category.setName(ehmoItemForm.getName());
	category.setDescription(ehmoItemForm.getDescription());
	category.setPackageId(ehmoItemForm.getPackageId());
	category.setSpendingLimit(ehmoItemForm.getSpendingLimit());
	category.setTimeNo(ehmoItemForm.getTimeNo());
	category.setPeriodNo(ehmoItemForm.getPeriodNo());
	category.setPeriod(ehmoItemForm.getPeriod());

		ehmoItemBo.update(category);

		/*auditor.after(request, "Category Form", multilevelCategoryForm,
				userIdentity.getUsername());
*/
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Category updated");

		return "redirect:/admin/hmos/multilevel/Item/view/"
				+ category.getItemId();
	}
	@RequestMapping(value = "/delete/{id}")
	@Layout(value = "layouts/datatable")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		EhmoPackageItem category = ehmoItemBo.getEhmoItemById(id);
		if (null == category) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/hmos/multilevel/Item";
		}
		EhmoPackageItemForm categoryForm = new EhmoPackageItemForm();
		categoryForm.setPackageId(category.getPackageId());
		model.addAttribute("aForm", categoryForm);
		model.addAttribute("category", category);

		return "admin/hmos/multilevel/Item/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String confirmDeleteAction(
			@ModelAttribute("aForm") EhmoPackageItemForm ehmoItemForm, Model model,
			RedirectAttributes redirectAttributes) {
		EhmoPackageItem ehmoItem = ehmoItemBo.getEhmoItemById(ehmoItemForm.getItemId());
		if (null == ehmoItem) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/hmos/multilevel/Item";
		}

		ehmoItemBo.delete(ehmoItem);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Item Deleted");

		return "redirect:/admin/hmo/multilevel/Item";
	}

	@RequestMapping(value = "/categories")
	public String multilevelCategories(Model model) {
	model.addAttribute("package", this.ehmoPackageBo.fetchAll());
	System.out.println("package size is: " + ehmoPackageBo.fetchAll().size());

		return "admin/hmos/multilevel/categories/index";

		
	}

	@RequestMapping(value = "/categories/add")
	public String addCategoryAction(Model model) {

		model.addAttribute("aForm", new EhmoPackageForm());

		return "admin/hmos/multilevel/categories/add";
	}

	@RequestMapping(value = "/categories/add", method = RequestMethod.POST)
	public String saveCategoryAction(
			@Valid @ModelAttribute("aForm") EhmoPackageForm ehmoPackageForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "admin/hmos/multilevel/categories/add";
		}
           EhmoPackage ehmoPackage = new EhmoPackage();
		this.ehmoPackageBo.save(ehmoPackage);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Category saved!");

		return "redirect:/admin/hmos/multilevel/categories";
	}

	@RequestMapping(value = "/categories/edit/{id}")
	public String editCategoryAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		EhmoPackage category = this.ehmoPackageBo.getEhmoPackageById(id);	

		if (null == category) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/admin/hmos/multilevel/categories";
		}

		EhmoPackageForm categoryform = new EhmoPackageForm();
		categoryform.setPackageId(category.getPackageId());
		categoryform.setName(category.getName());
	//	categoryform.setParent(category.getParent());

		model.addAttribute("aForm", categoryform);
		model.addAttribute("package", category);

		//this.auditor.before(request, "Category Category Form", categoryform);

		return "admin/hmos/multilevel/edit";
	}

	@RequestMapping(value = "/categories/edit/{id}", method = RequestMethod.POST)
	public String updateCategoryAction(
			@Valid @ModelAttribute("aForm") EhmoPackageForm ehmoPackageForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		if (result.hasErrors()) {
			return "admin/hmos/multilevel/edit";
		}
		
	//	this.ehmoPackageBo.update(ehmoPackageForm);
		/*this.auditor.after(request, "Category Category Form", multilevelCategoryForm,
				this.userIdentity.getUsername());*/
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Category Updated!");

		return "redirect:/admin/hmos/multilevel/categories";
	}

	@RequestMapping(value = "/categories/delete/{id}")
	public String deleteCategoryAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		EhmoPackage category = this.ehmoPackageBo.getEhmoPackageById(id);
		if (null == category) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/admin/hmos/multilevel/categories";
		}

		EhmoPackageForm form = new EhmoPackageForm();
		form.setPackageId(category.getPackageId());
		form.setName(category.getName());
		//form.setParent(category.getParent());

		model.addAttribute("aForm", form);
		model.addAttribute("package", category);

		return "admin/hmos/multilevel/delete";
	}

	@RequestMapping(value = "/categories/delete/{id}", method = RequestMethod.POST)
	public String removeCategoryAction(
			@ModelAttribute("aForm") EhmoPackageForm form,
			RedirectAttributes redirectAttributes) {

		EhmoPackage ehmoPackage = this.ehmoPackageBo
				.getEhmoPackageById(form.getPackageId());

		if (null == ehmoPackage) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/admin/hmos/multilevel/categories";
		}

		this.ehmoPackageBo.delete(ehmoPackage);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Category Deleted!");

		return "redirect:/admin/hmos/multilevel/categories";
	}

}
