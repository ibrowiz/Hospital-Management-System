package org.calminfotech.hmo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.hmo.boInterface.ItemServiceGroupBo;
import org.calminfotech.hmo.forms.EhmoForm;
import org.calminfotech.hmo.forms.EhmoPackageForm;
import org.calminfotech.hmo.forms.ItemServiceGroupForm;
import org.calminfotech.hmo.models.Ehmo;

import org.calminfotech.hmo.models.ItemServiceGroup;
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
@RequestMapping(value = "/admin/hmos/serviceGroup")
public class ItemServiceGroupController {

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

		model.addAttribute("service",itemServiceGroupBo.fetchAll());
		return "admin/hmos/serviceGroup/index";
	}

	@RequestMapping(value = "/view/{id}")
	@Layout(value = "layouts/datatable")
	public String viewAction(@PathVariable("id") Integer id,
	RedirectAttributes redirectAttributes, Model model) {
		
		ItemServiceGroup itemServiceGroup = itemServiceGroupBo.getItemServiceGroupById(id);
		
		if (null == itemServiceGroup) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource!");
			return "admin/hmos/serviceGroup/add";
		}
	
		model.addAttribute("service", itemServiceGroup);

		return "admin/hmos/serviceGroup/view";
		
	}
	@RequestMapping(value = "/add")
	@Layout(value = "layouts/datatable")
	public String addAction(Model model) {

		model.addAttribute("gForm", new ItemServiceGroupForm());
		model.addAttribute("service", this.itemServiceGroupBo.fetchAll());
		return "admin/hmos/serviceGroup/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String saveAction(
			@Valid @ModelAttribute("aForm") ItemServiceGroupForm itemServiceGroupForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("service", this.itemServiceGroupBo.fetchAll());
			return "admin/hmos/serviceGroup/add";
		}
		
		ItemServiceGroup itemServiceGroup = new ItemServiceGroup();
		
		itemServiceGroup.setItemServiceId(itemServiceGroup.getItemServiceId());
		itemServiceGroup.setName(itemServiceGroupForm.getName());
		itemServiceGroup.setDescription(itemServiceGroupForm.getDescription());
		
	      this.itemServiceGroupBo.save(itemServiceGroup);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Service Item added!");
		
		return "redirect:/admin/hmos/serviceGroup";
	//	+ itemServiceGroup.getItemServiceId();
	//	return  "admin/hmos/serviceGroup/view/" + itemServiceGroup.getItemServiceId();
			//	+ ehmoItem.getItemId();
				//+ category.getCategoryId();
	}

	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		ItemServiceGroup itemServiceGroup = itemServiceGroupBo.getItemServiceGroupById(id);
		if (null == itemServiceGroup) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
			return "redirect:/admin/hmos/serviceGroup";
		}
		ItemServiceGroupForm itemServiceGroupForm = new ItemServiceGroupForm();
		
		itemServiceGroupForm.setItemServiceId(itemServiceGroup.getItemServiceId());
		itemServiceGroupForm.setName(itemServiceGroup.getName());	
		itemServiceGroupForm.setDescription(itemServiceGroup.getDescription());
		
	    model.addAttribute("service", itemServiceGroup);
		model.addAttribute("aForm", itemServiceGroupForm);
		model.addAttribute("services", this.itemServiceGroupBo.fetchAll());

		return "admin/hmos/serviceGroup/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("aForm") ItemServiceGroupForm itemServiceGroupForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request){
		if (result.hasErrors()) {
			model.addAttribute("services", this.itemServiceGroupBo.fetchAll());
			return "admin/hmos/serviceGroup/edit";
		}
		ItemServiceGroup itemServiceGroup  = itemServiceGroupBo.getItemServiceGroupById(itemServiceGroupForm.getItemServiceId());
		itemServiceGroup.setName(itemServiceGroupForm.getName());
		itemServiceGroup.setDescription(itemServiceGroupForm.getDescription());

	//end	
		itemServiceGroupBo.update(itemServiceGroup);

	/*auditor.after(request, "Category Form", multilevelCategoryForm,
				userIdentity.getUsername());
*/
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Service Item updated");

		return "redirect:/admin/hmos/serviceGroup";
			
	}
	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		ItemServiceGroup category = itemServiceGroupBo.getItemServiceGroupById(id);
		if (null == category) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/hmos/multilevel";
		}
		ItemServiceGroupForm categoryForm = new ItemServiceGroupForm();
		categoryForm.setItemServiceId(category.getItemServiceId());
		model.addAttribute("aForm", categoryForm);
		model.addAttribute("service", category);

		return "admin/hmos/serviceGroup/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("aForm") ItemServiceGroupForm categoryform, Model model,
			RedirectAttributes redirectAttributes) {

		ItemServiceGroup ehmoPackageV = itemServiceGroupBo.getItemServiceGroupById(categoryform.getItemServiceId());
		if (null == ehmoPackageV) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/hmos/serviceGroup";
		}

		itemServiceGroupBo.delete(ehmoPackageV);
		alert.setAlert(redirectAttributes, Alert.ERROR,
				"Success!Item Service Deleted");

		return "redirect:/admin/hmos/serviceGroup";
	}

}
