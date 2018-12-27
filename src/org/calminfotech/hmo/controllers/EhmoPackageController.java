package org.calminfotech.hmo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.billing.models.BillScheme;
import org.calminfotech.hmo.boInterface.EhmoBo;
import org.calminfotech.hmo.boInterface.EhmoPackageBo;
import org.calminfotech.hmo.forms.EhmoPackageForm;
import org.calminfotech.hmo.models.Ehmo;
import org.calminfotech.hmo.models.EhmoPackageItem;
import org.calminfotech.hmo.models.EhmoPackage;

//import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
//import org.calminfotech.utils.Auditor;
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
@RequestMapping(value = "/admin/hmos/multilevel")
public class EhmoPackageController {

	@Autowired
	private EhmoPackageBo ehmoPackageBo;
	
	@Autowired
	private EhmoBo ehmoBo;

	@Autowired
	private Alert alert;
	
	@Autowired
	private BillSchemeBo billSchemeBo;
/*
	@Autowired
	private UserIdentity userIdentity;*/

	@RequestMapping(value = { "", "/" })
	@Layout(value = "layouts/datatable")
	public String indexAction(Model model) {
	model.addAttribute("package", this.ehmoPackageBo.fetchAll());
	return "admin/hmos/multilevel/index";
	}

	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id,
	RedirectAttributes redirectAttributes, Model model) {
		
		EhmoPackage ehmoPackage = ehmoPackageBo.getEhmoPackageById(id);
		if (null == ehmoPackage) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource!");
			return "redirect:/admin/hmos/multilevel";
		}
	
		model.addAttribute("package", ehmoPackage);
		return "admin/hmos/multilevel/view";
		
		
	}

	@RequestMapping(value = "/addPackage")
	public String addAction(Model model) {

		model.addAttribute("aForm", new EhmoPackageForm());
		model.addAttribute("package", this.ehmoPackageBo.fetchAll());

		return "admin/hmos/multilevel/addPackage";
	}

	@RequestMapping(value = "/savePackage", method = RequestMethod.POST)
	public String saveAction2(
			@Valid @ModelAttribute("aForm") EhmoPackageForm ehmoPackageForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("package", this.ehmoPackageBo.fetchAll());	
			return "admin/hmos/multilevel/addPackage";
		}
		
		BillScheme billScheme = this.billSchemeBo.getBillSchemeById(ehmoPackageForm.getBillId());
	    EhmoPackage ehmoPackage = new EhmoPackage();
	    ehmoPackage.setName(ehmoPackageForm.getName());
	    ehmoPackage.setPackageId(ehmoPackageForm.getPackageId());
		ehmoPackageBo.save(ehmoPackage);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Category added!");
		return "redirect:/admin/hmos/multilevel/view/"
				+ ehmoPackage.getPackageId();
	

	}
	
	@RequestMapping(value = "/editPackage")
	public String editAction(Model model) {

		model.addAttribute("aForm", new EhmoPackageForm());
		model.addAttribute("ehmo", this.ehmoBo.fetchAll());

		return "admin/hmos/multilevel/addPackage";
	}
	
	@RequestMapping(value = "/addPackage/{id}")
	public String addAction(@PathVariable("id") Integer id,Model model) {
		Ehmo ehmo = ehmoBo.getEhmoById(id);

		model.addAttribute("aForm", new EhmoPackageForm());
		model.addAttribute("categories", this.ehmoPackageBo.fetchAll());

		return "admin/hmos/multilevel/addPackage";
	}

	@RequestMapping(value = "/addPackage/{id}", method = RequestMethod.POST)
	public String saveAction(@PathVariable("id") Integer id,
			@Valid @ModelAttribute("aForm") EhmoPackageForm ehmoPackageForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("categories", this.ehmoPackageBo.fetchAll());
		return "admin/hmos/multilevel/addPackage";
		}
			Ehmo ehmo = ehmoBo.getEhmoById(id);
			
			EhmoPackage ehmoPackage = new EhmoPackage();
			ehmoPackage.setEhmo(ehmo);
			ehmoPackage.setName(ehmo.getName());
			//ehmoPackageT.setPackageId(ehmo.get)
		
		this.ehmoPackageBo.save(ehmoPackage);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Category added!");
		return "redirect:/admin/hmos/view/"
		+ ehmo.getHmoId();
	}
	
	
	
	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		EhmoPackage ehmoPackage = ehmoPackageBo.getEhmoPackageById(id);
		if (null == ehmoPackage) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
			return "redirect:/admin/hmos/multilevel";
		}
		EhmoPackageForm ehmoPackageForm = new EhmoPackageForm();
		
		ehmoPackageForm.setPackageId(ehmoPackage.getPackageId());
		ehmoPackageForm.setName(ehmoPackage.getName());	
		ehmoPackageForm.setParent(ehmoPackage.getPackageId());
		
	    model.addAttribute("aForm", ehmoPackageForm);
	
		model.addAttribute("package", this.ehmoPackageBo.fetchAll());
		model.addAttribute("package", ehmoPackage);

		return "admin/hmos/multilevel/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("aForm") EhmoPackageForm ehmoPackageForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request){
		if (result.hasErrors()) {
			model.addAttribute("categories", this.ehmoPackageBo.fetchAll());
			return "admin/hmos/multilevel/edit";
		}

		EhmoPackage ehmoPackage  =ehmoPackageBo.getEhmoPackageById(ehmoPackageForm.getPackageId());	
		ehmoPackage.setName(ehmoPackageForm.getName());
		//ehmoPackage.setParent(ehmoPackageForm.getPackageId());
	
		ehmoPackageBo.update(ehmoPackage);

	alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Category updated");

		return "redirect:/admin/hmos/multilevel/view/"
				+ ehmoPackageForm.getPackageId();
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		EhmoPackage ehmoPackage = ehmoPackageBo.getEhmoPackageById(id);
		if (null == ehmoPackage) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/hmos/multilevel";
		}
		EhmoPackageForm ehmoPackageForm = new EhmoPackageForm();
		ehmoPackageForm.setPackageId(ehmoPackage.getPackageId());
		model.addAttribute("aForm", ehmoPackageForm);
		model.addAttribute("package", ehmoPackage);

		return "admin/hmos/multilevel/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("aForm") EhmoPackageForm ehmoPackageForm, Model model,
			RedirectAttributes redirectAttributes) {

		EhmoPackage ehmoPackage = ehmoPackageBo.getEhmoPackageById(ehmoPackageForm.getPackageId());
		if (null == ehmoPackage) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/hmos/multilevel";
		}

		ehmoPackageBo.delete(ehmoPackage);
		alert.setAlert(redirectAttributes, Alert.ERROR,
				"Success! Category Deleted");

		return "redirect:/admin/hmos/multilevel";
	}

	@RequestMapping(value = "/categories")
	public String multilevelCategories(Model model) {
	model.addAttribute("categories", this.ehmoPackageBo.fetchAll());
	System.out.println("Categories size is: " + ehmoPackageBo.fetchAll().size());

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
       EhmoPackage ehmoPackage =new EhmoPackage();
		this.ehmoPackageBo.save(ehmoPackage);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Category saved!");

		return "redirect:/admin/hmos/multilevel/categories";
	}

	@RequestMapping(value = "/categories/edit/{id}")
	public String editCategoryAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		EhmoPackage ehmoPackage = this.ehmoPackageBo.getEhmoPackageById(id);	

		if (null == ehmoPackage) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/admin/hmos/multilevel/categories";
		}

		EhmoPackageForm ehmoPackageForm = new EhmoPackageForm();
		ehmoPackageForm.setPackageId(ehmoPackage.getPackageId());
		ehmoPackageForm.setName(ehmoPackage.getName());
	//	ehmoPackageForm.setParent(ehmoPackage.getParent());

		model.addAttribute("aForm", ehmoPackageForm);
		model.addAttribute("package", ehmoPackage);

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
		
//		this.ehmoPackageBo.update(ehmoPackageForm);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Category Updated!");

		return "redirect:/admin/hmos/multilevel/categories";
	}

	@RequestMapping(value = "/categories/delete/{id}")
	public String deleteCategoryAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes) {

		EhmoPackage ehmoPackage = this.ehmoPackageBo.getEhmoPackageById(id);
		if (null == ehmoPackage) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Invalid resource");
			return "redirect:/admin/hmos/multilevel/categories";
		}

		EhmoPackageForm form = new EhmoPackageForm();
		form.setPackageId(ehmoPackage.getPackageId());
		form.setName(ehmoPackage.getName());
		//form.setParent(ehmoPackage.getParent());

		model.addAttribute("aForm", form);
		model.addAttribute("package", ehmoPackage);

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
