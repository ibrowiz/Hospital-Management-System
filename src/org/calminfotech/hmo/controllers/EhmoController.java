package org.calminfotech.hmo.controllers;

import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.billing.models.BillScheme;
import org.calminfotech.hmo.boInterface.EhmoBo;
import org.calminfotech.hmo.boInterface.EhmoCategoryListBo;
import org.calminfotech.hmo.boInterface.EhmoPackageItemBo;
import org.calminfotech.hmo.boInterface.EhmoPackageBo;
import org.calminfotech.hmo.boInterface.ItemServiceGroupBo;
import org.calminfotech.hmo.forms.EhmoForm;
import org.calminfotech.hmo.forms.EhmoPackageItemForm;
import org.calminfotech.hmo.forms.EhmoPackageForm;
import org.calminfotech.hmo.models.Ehmo;
import org.calminfotech.hmo.models.EhmoPackageItem;
import org.calminfotech.hmo.models.EhmoPackage;
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
@RequestMapping(value = "/admin/hmos")
public class EhmoController {

	@Autowired
	private Alert alert;
	@Autowired
	private EhmoPackageBo ehmoPackageBo;
	@Autowired
	private ItemServiceGroupBo itemServiceGroupBo;
	@Autowired
	private EhmoPackageItemBo ehmoItemBo;
	@Autowired
	private EhmoCategoryListBo ehmoCategoryListBo;
	@Autowired
	private UserIdentity userIdentity;
	@Autowired
	private EhmoBo ehmoBo;
	@Autowired
	private Auditor auditor;
	@Autowired
	private BillSchemeBo billSchemeBo;

	@RequestMapping(value = { "", "/" })
	@Layout(value = "layouts/datatable")
	public String indexAction(Model model) {
	List<Ehmo> list = ehmoBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
	// System.out.println(list.get(0).getHmoId()+ "yemi is here");
	model.addAttribute("hmos", list);
	return "admin/hmos/index";
	}

	@RequestMapping(value = "/listonedit")
	@Layout(value = "layouts/datatable")
	public String indexEditAction(Model model) {
	model.addAttribute("hmos", ehmoBo.fetchAllByOrganisationEdit(userIdentity.getOrganisation().getId()));
	return "admin/hmos/index";
	}

	@RequestMapping(value = "/hmoView/{id}")
	public String HmoviewAction(@PathVariable("id") Integer id, Model model,
	RedirectAttributes redirectAttributes) {
    Ehmo ehmo = this.ehmoBo.getEhmoById(id);
		if (null == ehmo) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "admin/hmos/add";
		}
		model.addAttribute("hmo", ehmo);
		// return "redirect:/admin/hmos/";
		return "redirect:/admin/hmos/";
	}

	@RequestMapping(value = "/view/{id}")
	public String viewActionAll(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		// Ehmo ehmo = this.ehmoBo.getEhmoById(id);
		Ehmo ehmo = ehmoBo.getEhmoById(id);
		if (null == ehmo) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/hmos";
		}
		EhmoForm ehmoForm = new EhmoForm();

		EhmoPackageForm ehmoPackageForm = new EhmoPackageForm();
		ehmoPackageForm.setHmoId(ehmo.getHmoId());
		model.addAttribute("hForm", ehmoPackageForm);
		model.addAttribute("categories", this.ehmoPackageBo.fetchAll());

		model.addAttribute("iForm", new EhmoPackageItemForm());
		model.addAttribute("categorieslist", this.ehmoCategoryListBo.fetchAll());
		model.addAttribute("scheme", billSchemeBo
				.fetchAllByOrganisationBytype(userIdentity.getOrganisation().getId(),2 ));
		model.addAttribute("hmo", ehmo);
		return "admin/hmos/viewHmo";
	}

	@RequestMapping(value = "/viewPackage/{id}")
	public String viewAction2(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		EhmoPackage ehmoPackage = ehmoPackageBo.getEhmoPackageById(id);
		if (null == ehmoPackage) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/hmos";
		}
		EhmoPackageItemForm ehmoItemForm = new EhmoPackageItemForm();
		ehmoItemForm.setPackageId(ehmoPackage.getPackageId());
		ehmoItemForm.setHmoId(ehmoPackage.getPackageId());
		ehmoItemForm.setItemId(ehmoPackage.getEhmo().getHmoId());

		model.addAttribute("iForm", ehmoItemForm);
		model.addAttribute("categoriesItem", this.ehmoItemBo.fetchAll());
		model.addAttribute("service", this.itemServiceGroupBo.fetchAll());
		model.addAttribute("hForm", new EhmoPackageForm());
		model.addAttribute("categorieslist", this.ehmoCategoryListBo.fetchAll());
		model.addAttribute("package", ehmoPackage);

		return "admin/hmos/viewPackage";

	}

	@RequestMapping(value = "/viewAll/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Ehmo ehmo = ehmoBo.getEhmoById(id);
		if (null == ehmo) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/hmos/viewAll";
		}
		EhmoForm ehmoForm = new EhmoForm();
		EhmoPackageForm ehmoPackageForm = new EhmoPackageForm();
		ehmoPackageForm.setHmoId(ehmo.getHmoId());
		model.addAttribute("hForm", ehmoPackageForm);
		model.addAttribute("categories", this.ehmoPackageBo.fetchAll());

		model.addAttribute("iForm", new EhmoPackageItemForm());
		model.addAttribute("categorieslist", this.ehmoCategoryListBo.fetchAll());

		model.addAttribute("hmo", ehmo);
		return "admin/hmos/view/" + ehmo.getHmoId();
	}	
	@RequestMapping(value = "/add")
	public String addAction(Model model) {

		EhmoForm ehmoForm = new EhmoForm();
		model.addAttribute("hForm", ehmoForm);
		return "admin/hmos/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String saveAction(@Valid @ModelAttribute("hForm") EhmoForm ehmoForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("hmo", this.ehmoBo.fetchAll());
			return "admin/hmos/index";
		}
	     this.ehmoBo.save(ehmoForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! Hmo saved");
		return "redirect:/admin/hmos";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		Ehmo ehmo = this.ehmoBo.getEhmoById(id);
		if (null == ehmo) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/hmos";
		}

		EhmoForm ehmoForm = new EhmoForm();
		ehmoForm.setHmoId(ehmo.getHmoId());
		ehmoForm.setName(ehmo.getName());
		ehmoForm.setAddress(ehmo.getAddress());
		ehmoForm.setPhoneNumber(ehmo.getPhoneNumber());
		ehmoForm.setAdminName(ehmo.getAdminName());
		ehmoForm.setAdminEmail(ehmo.getAdminEmail());
		ehmoForm.setAdminPhone(ehmo.getAdminPhone());

		model.addAttribute("hForm", ehmoForm);
		model.addAttribute("hmo", ehmo);
		this.auditor.before(request, "HMO Form", ehmoForm);
		return "admin/hmos/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("hForm") EhmoForm ehmoForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (result.hasErrors()) {
			return "admin/hmos/edit";
		}
		ehmoBo.update(ehmoForm);
		this.auditor.after(request, "HMO Form", ehmoForm,
				this.userIdentity.getUsername());
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! HMO details updated");
		// return "redirect:/admin/hmos/hmoView/" + ehmoForm.getHmoId();
		return "redirect:/admin/hmos/listonedit";

	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Ehmo ehmo = this.ehmoBo.getEhmoById(id);
		if (null == ehmo) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/hmos";
		}
		EhmoForm ehmoForm = new EhmoForm();
		ehmoForm.setHmoId(ehmo.getHmoId());
		model.addAttribute("hForm", ehmoForm);
		model.addAttribute("hmo", ehmo);
		return "admin/hmos/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("hForm") EhmoForm ehmoForm,
			RedirectAttributes redirectAttributes) {

		Ehmo ehmo = this.ehmoBo.getEhmoById(ehmoForm.getHmoId());
		if (null == ehmo) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/hmos";
		}
		this.ehmoBo.delete(ehmo);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Hmo Deleted");
		return "redirect:/admin/hmos";

	}

	// copy ehmoPackage starts here
	@RequestMapping(value = "/savePackage", method = RequestMethod.POST)
	public String saveActionPackage(
			@Valid @ModelAttribute("hForm") EhmoPackageForm ehmoPackageForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("categories", this.ehmoPackageBo.fetchAll());
			return "admin/hmos/addPackage";
		}
		Ehmo ehmo = ehmoBo.getEhmoById(ehmoPackageForm.getHmoId());
		BillScheme billScheme = this.billSchemeBo.getBillSchemeById(ehmoPackageForm.getBillId());
		EhmoPackage ehmoPackage = new EhmoPackage();
		ehmoPackage.setEhmo(ehmo);
		ehmoPackage.setName(ehmoPackageForm.getName());
		ehmoPackage.setCreatedDate(new GregorianCalendar().getTime());
		ehmoPackage.setCreatedBy(userIdentity.getUsername());
		ehmoPackage.setOrganisationId(userIdentity.getOrganisation().getId());
		this.ehmoPackageBo.save(ehmoPackage);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Package added!");
		return "redirect:/admin/hmos/view/" + ehmo.getHmoId();

	}

	@RequestMapping(value = "/saveHmoItem", method = RequestMethod.POST)
	public String saveActionItem(
			@Valid @ModelAttribute("iForm") EhmoPackageItemForm ehmoItemForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("categories", this.ehmoItemBo.fetchAll());
			return "admin/hmos/index";
		}
		EhmoPackage ehmoPackage = ehmoPackageBo.getEhmoPackageById(ehmoItemForm.getPackageId());

		EhmoPackageItem ehmoItem = new EhmoPackageItem();
		ehmoItem.setEhmoPackage(ehmoPackage);
		ehmoItem.setName(ehmoItemForm.getName());
		ehmoItem.setPackageId(ehmoItemForm.getPackageId());
		ehmoItem.setServiceGroup(ehmoItemForm.getServiceGroup());
		ehmoItem.setSpendingLimit(ehmoItemForm.getSpendingLimit());
		ehmoItem.setPeriod(ehmoItemForm.getPeriod());
		ehmoItem.setPeriodNo(ehmoItemForm.getPeriodNo());
		ehmoItem.setTimeNo(ehmoItemForm.getTimeNo());
		ehmoItem.setItemServiceId(ehmoItemForm.getItemServiceId());
		ehmoItem.setCreatedDate(new GregorianCalendar().getTime());
		ehmoItem.setCreatedBy(userIdentity.getUsername());
		ehmoItem.setOrganisationId(userIdentity.getOrganisation().getId());

		this.ehmoItemBo.save(ehmoItem);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Item added!");
		return "redirect:/admin/hmos/viewPackage/" + ehmoPackage.getPackageId();
	}

	@RequestMapping(value = "/editPackage/{id}")
	public String editActionEditpackage(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		EhmoPackage ehmoPackage = ehmoPackageBo.getEhmoPackageById(id);
		if (null == ehmoPackage) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
			return "redirect:/admin/hmos/viewHmo";
		}
		EhmoPackageForm ehmoPackageForm = new EhmoPackageForm();

		ehmoPackageForm.setPackageId(ehmoPackage.getPackageId());
		ehmoPackageForm.setName(ehmoPackage.getName());
		//ehmoPackageForm.setBillingType(ehmoPackage.getBillingType());
		ehmoPackageForm.setParent(ehmoPackage.getPackageId());

		model.addAttribute("package", ehmoPackage);
		model.addAttribute("aForm", ehmoPackageForm);
		model.addAttribute("package", this.ehmoPackageBo.fetchAll());
		model.addAttribute("scheme", this.billSchemeBo
				.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));

		auditor.before(request, "HMO Package Form", ehmoPackageForm);

		return "admin/hmos/editPackage";
	}

	@RequestMapping(value = "/editPackage/{id}", method = RequestMethod.POST)
	public String updateActionEditpackage(
			@Valid @ModelAttribute("aForm") EhmoPackageForm ehmoPackageForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("package", this.ehmoPackageBo.fetchAll());
			return "admin/hmos/editPackage";
		}
		// NEWWLY ADDED
		EhmoPackage ehmoPackage = ehmoPackageBo
				.getEhmoPackageById(ehmoPackageForm.getPackageId());
		ehmoPackage.setName(ehmoPackageForm.getName());
		ehmoPackage.setPackageId(ehmoPackageForm.getPackageId());
	//	ehmoPackage.setBillingType(ehmoPackageForm.getBillingType());
		ehmoPackage.setModifiedDate(new GregorianCalendar().getTime());
		ehmoPackage.setModifiedBy(userIdentity.getUsername());

		// end
		ehmoPackageBo.update(ehmoPackage);
		auditor.after(request, "HMO Package Form", ehmoPackageForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Package updated");
		return "redirect:/admin/hmos/view/" + ehmoPackage.getEhmo().getHmoId();
	}

	@RequestMapping(value = "/editItem/{id}")
	public String editActionEdititem(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		EhmoPackageItem ehmoItem = ehmoItemBo.getEhmoItemById(id);
		if (null == ehmoItem) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
			return "redirect:/admin/hmos/viewPackage";
		}
		EhmoPackageItemForm ehmoItemForm = new EhmoPackageItemForm();

		ehmoItemForm.setPackageId(ehmoItem.getPackageId());
		ehmoItemForm.setName(ehmoItem.getName());
		ehmoItemForm.setItemId(ehmoItem.getItemId());
		ehmoItemForm.setServiceGroup(ehmoItem.getServiceGroup());
		ehmoItemForm.setSpendingLimit(ehmoItem.getSpendingLimit());
		ehmoItemForm.setPeriod(ehmoItem.getPeriod());
		ehmoItemForm.setTimeNo(ehmoItem.getTimeNo());
		ehmoItemForm.setPeriodNo(ehmoItem.getPeriodNo());

		model.addAttribute("package", ehmoItem);
		model.addAttribute("iForm", ehmoItemForm);
		model.addAttribute("categoriesItem", this.ehmoItemBo.fetchAll());
		model.addAttribute("service", this.itemServiceGroupBo.fetchAll());
		auditor.before(request, "HMO Package Item Form", ehmoItemForm);

		return "admin/hmos/editItem";
	}

	@RequestMapping(value = "/editItem/{id}", method = RequestMethod.POST)
	public String updateActionEdititem(
			@Valid @ModelAttribute("iForm") EhmoPackageItemForm ehmoItemForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("categoriesItem", this.ehmoItemBo.fetchAll());
			return "admin/hmos/editItem";
		}
		// NEWWLY ADDED
		EhmoPackageItem ehmoPackageItem = ehmoItemBo
				.getEhmoItemById(ehmoItemForm.getItemId());
		ehmoPackageItem.setItemId(ehmoItemForm.getItemId());
		ehmoPackageItem.setName(ehmoItemForm.getName());
		ehmoPackageItem.setServiceGroup(ehmoItemForm.getServiceGroup());
		ehmoPackageItem.setPackageId(ehmoItemForm.getPackageId());
		ehmoPackageItem.setSpendingLimit(ehmoItemForm.getSpendingLimit());
		ehmoPackageItem.setPeriod(ehmoItemForm.getPeriod());
		ehmoPackageItem.setTimeNo(ehmoItemForm.getTimeNo());
		ehmoPackageItem.setPeriodNo(ehmoItemForm.getPeriodNo());
		ehmoPackageItem.setModifiedDate(new GregorianCalendar().getTime());
		ehmoPackageItem.setModifiedBy(userIdentity.getUsername());

		// end
		ehmoItemBo.update(ehmoPackageItem);
		auditor.after(request, "HMO Package Item Form", ehmoItemForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Item updated");
		/*
		 * return "redirect:/admin/hmos/viewSaveItem/" + ehmoItem.getItemId();
		 */
		return "redirect:/admin/hmos/viewPackage/"
				+ ehmoPackageItem.getEhmoPackage().getPackageId();

	}
	// Hmo Packages
	@RequestMapping(value = { "/package/index/{id}" })
	@Layout(value = "layouts/datatable")
	public String indexHmoPackage(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		Ehmo ehmo = ehmoBo.getEhmoById(id);
		if (null == ehmo) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "admin/hmos/hmopackage";
		}
		EhmoPackageForm form = new EhmoPackageForm();
		form.setPackageId(ehmo.getHmoId());
		model.addAttribute("dForm", form);
		model.addAttribute("hmos", this.ehmoBo.fetchAll());
		model.addAttribute("hmo", ehmo);
		model.addAttribute("hmoPackages", this.ehmoBo.fetchAll());
		return "admin/hmos/hmopackage";
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = { "/package/index/{id}" }, method = RequestMethod.POST)
	public String saveHmoPackageAction(
			@Valid @ModelAttribute("dForm") EhmoPackageForm ehmoPackageForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Form contain errors");
			return "admin/hmos/hmopackage/" + ehmoPackageForm.getPackageId();
		}
		Ehmo hmo = ehmoBo.getEhmoById(ehmoPackageForm.getPackageId());
		EhmoPackage ehmoPackage = ehmoPackageBo
				.getEhmoPackageById(ehmoPackageForm.getPackageId());

		if (result.hasErrors()) {
			model.addAttribute("hmos", this.ehmoBo.fetchAll());
			return "system/hmos/add";
		}
		model.addAttribute("hmo", hmo);
		this.ehmoPackageBo.save(ehmoPackage);

		this.alert
				.setAlert(redirectAttributes, Alert.SUCCESS, "Package saved!");

		return "redirect:/admin/hmos/package/index/" + hmo.getHmoId();
	}

	@RequestMapping(value = "/package/view/{id}")
	public String view(@PathVariable("id") Integer id, Model model) {

		EhmoPackage hmoPackage = ehmoPackageBo.getEhmoPackageById(id);
		model.addAttribute("hmoPackage", hmoPackage);

		return "admin/hmos/hmopackageview";

	}

	@RequestMapping(value = { "/addHmoItem/{id}" })
	@Layout(value = "layouts/datatable")
	public String addHmoItem(@PathVariable("id") Integer pid, Model model,
			RedirectAttributes redirectAttributes) {
		Ehmo ehmo = ehmoBo.getEhmoById(pid);
		if (null == ehmo) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "admin/hmos";
		}
		model.addAttribute("iForm", new EhmoPackageItemForm());
		model.addAttribute("categories", this.ehmoCategoryListBo.fetchAll());

		return "admin/hmos/addHmoItem";
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = { "/addHmoItem/{id}" }, method = RequestMethod.POST)
	public String saveaddHmoItemAction(
			@Valid @ModelAttribute("iForm") EhmoPackageItemForm ehmoItemForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("categories", this.ehmoCategoryListBo.fetchAll());
			return "admin/hmos/addHmoItem";
		}
		Ehmo ehmo = ehmoBo.getEhmoById(ehmoItemForm.getHmoId());
		EhmoPackageItem ehmoItem = new EhmoPackageItem();
		ehmoItem.setEhmo(ehmo);
		ehmoItem.setName(ehmoItemForm.getName());
		ehmoItem.setDescription(ehmoItem.getDescription());
		ehmoItem.setPackageId(ehmoItem.getPackageId());
		ehmoItem.setItemId(ehmoItem.getItemId());

		this.ehmoItemBo.save(ehmoItem);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Item added!");

		return "redirect:/admin/hmos/viewItem/" + ehmo.getHmoId();
	}

}