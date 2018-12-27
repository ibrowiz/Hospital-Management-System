package org.calminfotech.system.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.admin.boInterface.DrugBo;
import org.calminfotech.admin.boInterface.HmoBo;
import org.calminfotech.system.boInterface.HmoPackageItemBo;
import org.calminfotech.system.boInterface.HmoPckServiceBo;
import org.calminfotech.system.boInterface.EHmoPackagesBo;
import org.calminfotech.system.forms.HmoPackageItemsForm;
import org.calminfotech.system.forms.HmoPackageItemForm;
import org.calminfotech.system.forms.EHmoPackagesForm;
import org.calminfotech.system.models.Hmo;
import org.calminfotech.system.models.HmoPackageDrug;
import org.calminfotech.system.models.EhmoPackages;
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
@RequestMapping(value = "/system/hmos")
public class EHmoPackagesController {

	@Autowired
	private HmoPackageItemBo packageDrugBo;

	@Autowired
	private EHmoPackagesBo hmoPackageBo;

	@Autowired
	private DrugBo drugBo;

	@Autowired
	private Alert alert;

	@Autowired
	private HmoBo hmoBo;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private HmoPckServiceBo hmoPckServiceBo;
	
	/*@Autowired
	private HmoAddPackageServicesBo hmoAddPackageServicesBo;*/

	/*@RequestMapping(value = { "", "/index" })
	@Layout(value = "layouts/datatable")
	public String indexAction(Model model) {

		model.addAttribute("hmoPackages",
				this.hmoPackageBo.fetchAllByOrganisation());
		return "system/hmos/index";
	}*/
	
	@RequestMapping(value = {"/package/index/{id}" })
	@Layout(value = "layouts/datatable")
	public String indexAction(Model model) {

		model.addAttribute("hmoPackages",
				this.hmoPackageBo.fetchAllByOrganisation());
		/*return "system/hmos/index";*/
		return "admin/insurances/hmos/hmopackage";
	}

	@RequestMapping(value = "/view/{id}")
	@Layout(value = "layouts/datatable")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		EhmoPackages hmoPackage = this.hmoPackageBo
				.getPackageById(id);

		if (null == hmoPackage) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/hmos";
		}

		HmoPackageItemForm dForm = new HmoPackageItemForm();
		dForm.setPackageId(hmoPackage.getId());
		
		HmoPackageItemsForm hmoAddPackageServicesForm = new HmoPackageItemsForm();
		hmoAddPackageServicesForm.setOrganisationHmoPackage(hmoPackage.getId());
		
		model.addAttribute("hmoPackage", hmoPackage);
		model.addAttribute("dForm", dForm);
		model.addAttribute("hmoAddPackageServicesForm", hmoAddPackageServicesForm);
		//model.addAttribute("hmoAddPackageServicesForm", hmoAddPackageServicesBo.fetchAll());
		model.addAttribute("drugs", this.drugBo.fetchAll());
		model.addAttribute("hmoPckService", hmoPckServiceBo.fetchAll());

		return "system/hmos/view";
	}

	/*@RequestMapping(value = "/add")
	public String addPackageAction(Model model) {

		OrganisationHmoPackageForm form = new OrganisationHmoPackageForm();
		model.addAttribute("dForm", form);
		model.addAttribute("hmos", this.hmoBo.fetchAll());

		return "system/hmos/add";
	}*/

	/*@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String savePackageAction(
			@Valid @ModelAttribute("dForm") OrganisationHmoPackageForm form,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("hmos", this.hmoBo.fetchAll());
			return "system/hmos/add";
		}

		OrganisationHmoPackage hmoPackage = this.hmoPackageBo.save(form);

		this.alert
				.setAlert(redirectAttributes, Alert.SUCCESS, "Package saved!");

		return "redirect:/system/hmos/view/" + hmoPackage.getId();
	}*/

	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id,
			RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest request) {

		EhmoPackages hmoPackage = this.hmoPackageBo
				.getPackageById(id);

		if (null == hmoPackage) {
			this.alert.setAlert(redirectAttributes, Alert.DANGER,
					"Error! Invalid resource");
			return "redirect:/system/hmos";
		}

		EHmoPackagesForm form = new EHmoPackagesForm();

		form.setId(hmoPackage.getId());
		form.setName(hmoPackage.getName());
		form.setHmoId(hmoPackage.getHmo().getId());

		model.addAttribute("hmoPackage", hmoPackage);
		model.addAttribute("hmos", this.hmoBo.fetchAll());
		model.addAttribute("dForm", form);

		this.auditor.before(request, "Hmo Package Form", form);

		return "system/hmos/edit";
	}

	/*@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updatePackageAction(
			@Valid @ModelAttribute("dForm") OrganisationHmoPackageForm form,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("hmos", this.hmoBo.fetchAll());
			return "system/hmos/add";
		}

		OrganisationHmoPackage hmoPackage = this.hmoPackageBo
				.getPackageById(form.getId());

		this.hmoPackageBo.update(form);

		this.auditor.after(request, "Hmo Package Form", form,
				this.userIdentity.getUsername());

		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Package updated!");

		return "redirect:/system/hmos/view/" + hmoPackage.getId();
	}
*/
	@RequestMapping(value = "/details/{id}")
	public String detailsAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Hmo hmo = this.hmoBo.getHmoById(id);
		if (null == hmo) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/system/hmos";
		}

		model.addAttribute("hmo", hmo);
		return "system/hmos/details";
	}

	@RequestMapping(value = "/savedrug", method = RequestMethod.POST)
	public String saveDrug(
			@Valid @ModelAttribute("dForm") HmoPackageItemForm form,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			if (form.getPackageId() == null) {
				this.alert.setAlert(redirectAttributes, Alert.ERROR,
						"Invalid resource");
				return "redirect:/system/hmos";
			}

			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"Form contains errors! Field form correctly!");
			return "redirect:/system/hmos/view/" + form.getPackageId();
		}

		HmoPackageDrug packageDrug = this.packageDrugBo.getItem(
				form.getItemId(), form.getPackageId());

		if (null != packageDrug) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR,
					"This drug exists on this package!");
			return "redirect:/system/hmos/view/" + form.getPackageId();
		}

		this.packageDrugBo.save(form);
		this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Drug Price saved to Health package");

		return "redirect:/system/hmos/view/" + form.getPackageId();
	}
}
