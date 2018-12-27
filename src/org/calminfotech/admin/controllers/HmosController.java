package org.calminfotech.admin.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.admin.boInterface.HmoBo;
import org.calminfotech.admin.forms.SubServiceItemForm;
import org.calminfotech.hmo.boInterface.EhmoBo;
import org.calminfotech.hmo.forms.EhmoPackageForm;
import org.calminfotech.hmo.models.Ehmo;
import org.calminfotech.hmo.models.EhmoPackage;
import org.calminfotech.system.boInterface.CategoryItemBo;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.HmoPackageItemsBo;
import org.calminfotech.system.boInterface.HmoPckServiceBo;
import org.calminfotech.system.boInterface.HmoPckSubServiceBo;
import org.calminfotech.system.boInterface.HmoSubserviceItemBo;
import org.calminfotech.system.boInterface.EHmoPackagesBo;
import org.calminfotech.system.forms.HmoForm;
import org.calminfotech.system.forms.HmoPackageItemsForm;
import org.calminfotech.system.forms.HmoPckServiceForm;
import org.calminfotech.system.forms.HmoPckSubServiceForm;
import org.calminfotech.system.forms.EHmoPackagesForm;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.Hmo;
import org.calminfotech.system.models.HmoPckService;
import org.calminfotech.system.models.HmoPckSubService;
import org.calminfotech.system.models.HmoSubserviceItem;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.Auditor;
import org.calminfotech.utils.BankList;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.StatesList;
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
@RequestMapping(value = "/admin/insurances/hmos")
public class HmosController {

	@Autowired
	private Alert alert;

	@Autowired
	private Auditor auditor;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private HmoBo hmoBo;

	@Autowired
	private LocalGovernmentAreaList lgasList;

	@Autowired
	private StatesList stateList;

	@Autowired
	private BankList bankList;

	@Autowired
	private EHmoPackagesBo hmoPackageBo;
	
	@Autowired
	private HmoPackageItemsBo hmoPackageItemsBo;
	
	@Autowired
	private EhmoBo ehmoBo;
	
	@Autowired	
	private HmoPckServiceBo hmoPckServiceBo;
	
	@Autowired
	private HmoPckSubServiceBo hmoPckSubServiceBo;
	
	@Autowired
	private HmoSubserviceItemBo subServiceItemBo;
	
	@Autowired
	private GlobalItemBo ItemBo;
	
	@RequestMapping(value = { "", "/" })
	@Layout("layouts/datatable")
	public String indexAction(Model model) {
		List<Ehmo> ehmo  = this.ehmoBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		System.out.println("list size " + ehmo.size());
		model.addAttribute("ehmo", ehmo);		
		return "admin/insurances/hmos/index";
	}

	@RequestMapping(value = "/view/{id}")
	public String viewAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Ehmo ehmo = this.ehmoBo.getEhmoById(id);
		/*if (null == hmo) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/insurances/hmos";
		}*/

		model.addAttribute("hmo", ehmo);
		return "admin/insurances/hmos/view";
	}

	@RequestMapping(value = "/add")
	public String addAction(Model model) {

		HmoForm hmoForm = new HmoForm();
		model.addAttribute("hForm", hmoForm);

		model.addAttribute("lgas", this.lgasList.fetchAll());
		model.addAttribute("states", this.stateList.fetchAll());
		model.addAttribute("banks", this.bankList.fetchAll());

		return "admin/insurances/hmos/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveAction(@Valid @ModelAttribute("hForm") HmoForm hmoForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("lgas", this.lgasList.fetchAll());
			model.addAttribute("states", this.stateList.fetchAll());
			model.addAttribute("banks", this.bankList.fetchAll());
			return "admin/insurances/hmos/add";
		}

		Hmo hmo = this.hmoBo.save(hmoForm);
		alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! Hmo saved");

		return "redirect:/admin/insurances/hmos/view/" + hmo.getId();
	}

	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		Hmo hmo = this.hmoBo.getHmoById(id);
		if (null == hmo) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/insurances/hmos";
		}

		HmoForm hmoForm = new HmoForm();
		hmoForm.setId(hmo.getId());
		hmoForm.setName(hmo.getName());
		hmoForm.setAddress(hmo.getAddress());
		hmoForm.setPhoneNumber(hmo.getPhoneNumber());
		hmoForm.setPostalNumber(hmo.getPostalNumber());
		hmoForm.setEmail(hmo.getEmail());
		hmoForm.setAdminName(hmo.getAdminName());
		hmoForm.setAdminEmail(hmo.getAdminEmail());
		hmoForm.setAdminPhone(hmo.getAdminPhone());
		hmoForm.setLgaId(hmo.getLga().getLocalGovernmentAreaId());
		hmoForm.setStateId(hmo.getState().getStateId());
		hmoForm.setBankId(hmo.getBank().getId());
		hmoForm.setBankAccount(hmo.getBankAccount());

		model.addAttribute("hForm", hmoForm);
		model.addAttribute("hmo", hmo);
		model.addAttribute("lgas",
				this.stateList.getStateById(hmo.getState().getStateId())
						.getLocalGovernmentArea());
		model.addAttribute("states", this.stateList.fetchAll());
		model.addAttribute("banks", this.bankList.fetchAll());

		this.auditor.before(request, "HMO Form", hmoForm);

		return "admin/insurances/hmos/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(@Valid @ModelAttribute("hForm") HmoForm hmoForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("lgas", this.lgasList.fetchAll());
			model.addAttribute("states", this.stateList.fetchAll());
			model.addAttribute("banks", this.bankList.fetchAll());

			return "admin/insurances/hmos/edit";
		}

		hmoBo.update(hmoForm);

		this.auditor.after(request, "HMO Form", hmoForm,
				this.userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! HMO details updated");
		return "redirect:/admin/insurances/hmos/view/" + hmoForm.getId();
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {

		Hmo hmo = this.hmoBo.getHmoById(id);
		if (null == hmo) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/insurances/hmos";
		}

		HmoForm hmoForm = new HmoForm();
		hmoForm.setId(hmo.getId());
		model.addAttribute("hForm", hmoForm);
		model.addAttribute("hmo", hmo);

		return "admin/insurances/hmos/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(@ModelAttribute("hForm") HmoForm hmoForm,
			RedirectAttributes redirectAttributes) {

		Hmo hmo = this.hmoBo.getHmoById(hmoForm.getId());
		if (null == hmo) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "redirect:/admin/insurances/hmos";
		}

		this.hmoBo.delete(hmo);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Hmo Deleted");
		return "redirect:/admin/insurances/hmos";
	}

	//Hmo Packages
	@RequestMapping(value = {"/package/index/{id}"})
	@Layout(value = "layouts/datatable")
	public String indexHmoPackage(@PathVariable("id") Integer id, 
								  Model model, RedirectAttributes redirectAttributes) {

		Ehmo ehmo = ehmoBo.getEhmoById(id);		
		/*if(null == hmo){
			alert.setAlert(redirectAttributes, Alert.ERROR, "Error! Invalid resource");
			return "admin/insurances/hmos/hmopackage";
		}*/
		EHmoPackagesForm form = new EHmoPackagesForm();
		form.setHmoId(ehmo.getHmoId());
		
		List<Ehmo> ehmolist = this.ehmoBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		
		//OrganisationHmoPackage organisationHmoPackage = this.hmoPackageBo.getPackageById(id);
		/*OrganisationHmoPackageForm editform = new OrganisationHmoPackageForm();
		editform.setHmoId(organisationHmoPackage.getHmo().getId());
		editform.setName(organisationHmoPackage.getName());*/
		
		model.addAttribute("dForm", form);
		//model.addAttribute("editForm", editform);
		model.addAttribute("hmopackage", this.hmoPackageBo.fetchAllByHMO(id));
		//model.addAttribute("organisationHmoPackage", organisationHmoPackage);
		model.addAttribute("ehmolist", ehmolist);
		model.addAttribute("hmo", ehmo);
		//model.addAttribute("hmoPackages", this.hmoPackageBo.fetchAllByOrganisation());
		return "admin/insurances/hmos/hmopackage";
	}
	
	
	@Layout("layouts/datatable")
	@RequestMapping(value = {"/package/index/{id}"}, method = RequestMethod.POST)
	public String saveHmoPackageAction(
			@Valid @ModelAttribute("dForm") EHmoPackagesForm form,			
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {		
		
		if (result.hasErrors()) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR, "Form contain errors");
			return "admin/insurances/hmos/hmopackage/" + form.getHmoId();
		}
		Ehmo ehmo = ehmoBo.getEhmoById(form.getHmoId());
		//OrganisationHmoPackage hmoPackage = hmoPackageBo.getPackageById(form.getId());		

		/*if (result.hasErrors()) {
			model.addAttribute("hmos", this.hmoBo.fetchAll());
			return "system/hmos/add";
		}		*/	
		model.addAttribute("hmo", ehmo);
		this.hmoPackageBo.save(form);

		this.alert.setAlert(redirectAttributes, Alert.SUCCESS, "Package saved!");
		
		return "redirect:/admin/insurances/hmos/package/index/" + ehmo.getHmoId();
	}
	
	@RequestMapping(value = "/package/edit/{id}")
	public String editCategoryAction(@PathVariable("id") Integer id,
			Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		//Hmo hmo = hmoBo.getHmoById(id);		

		

		EhmoPackages organisationHmoPackage = this.hmoPackageBo.getPackageById(id);
		EHmoPackagesForm editform = new EHmoPackagesForm();
		editform.setId(id);
		editform.setHmoId(organisationHmoPackage.getEhmo().getHmoId());
		editform.setName(organisationHmoPackage.getName());
	//	ehmoPackageForm.setParent(ehmoPackage.getParent());

		model.addAttribute("hmos", this.ehmoBo.fetchAll());
		
		model.addAttribute("editForm", editform);
		
		//model.addAttribute("organisationHmoPackage", organisationHmoPackage);
		
		//model.addAttribute("hmo", hmo);

		
		//this.auditor.before(request, "Category Category Form", categoryform);

		return "admin/insurances/hmos/package/edithmopackage";
	}
	
  @RequestMapping(value = "/package/edit/{id}", method = RequestMethod.POST)	
	public String editAction(
			@Valid @ModelAttribute("editForm") EHmoPackagesForm editform,			
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {			
		
		/*if (result.hasErrors()) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR, "Form contain errors");
			return "admin/insurances/hmos/hmopackage/" + itemForm.getOrganisationHmoPackage();
		}	*/			
		
		//OrganisationHmoPackage hmoPackage = hmoPackageBo.getPackageById(editform.getId());
		
		/*if (result.hasErrors()) {
			model.addAttribute("hmoPackage", this.hmoPackageBo.fetchAllByOrganisation());
			return "admin/insurances/hmos/hmopackage";
		}*/			
		//model.addAttribute("hmoPackage", hmoPackage);
		this.hmoPackageBo.update(editform);	

		this.alert.setAlert(redirectAttributes, Alert.SUCCESS, "saved!");
		return "redirect:/admin/insurances/hmos/package/index/" + editform.getHmoId();
	}
	
	
	
	@RequestMapping(value = "/package/view/{id}")
	public String view(@PathVariable("id") Integer id, Model model){
		
		EhmoPackages hmoPackage = hmoPackageBo.getPackageById(id);
		model.addAttribute("hmoPackage", hmoPackage);
		
		return "admin/insurances/hmos/hmopackageview";
		
	}
	
	//Hmo Package items
	@RequestMapping(value = {"/packageitems/index/{pid}"})
	@Layout(value = "layouts/datatable")
	public String indexHmoPackageItems(@PathVariable("pid") Integer pid, 
								  Model model, RedirectAttributes redirectAttributes) {
		
		EhmoPackages hmoPackage = hmoPackageBo.getPackageById(pid);
		if(null == hmoPackage){
			alert.setAlert(redirectAttributes, Alert.ERROR, "Error! Invalid resource");
			return "admin/insurances/hmos/hmopackage";
		}
		
		//Add Package Items here		
		HmoPackageItemsForm itemsForm = new HmoPackageItemsForm();
		itemsForm.setOrganisationHmoPackage(hmoPackage.getId());	
		
		model.addAttribute("iForm", itemsForm);		
		model.addAttribute("hmoPackageItems", hmoPackageItemsBo.fetchAll());
		model.addAttribute("hmoPckService", hmoPckServiceBo.fetchAll());		
		model.addAttribute("hmoPackage", hmoPackage);
		
		return "admin/insurances/hmos/hmopackageitem";
	}
	
	
	@Layout("layouts/datatable")
	@RequestMapping(value = {"/packageitems/index/{pid}"}, method = RequestMethod.POST)	
	public String saveHmoPackageItemAction(
			@Valid @ModelAttribute("iForm") HmoPackageItemsForm itemForm,			
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {			
		
		if (result.hasErrors()) {
			this.alert.setAlert(redirectAttributes, Alert.ERROR, "Form contain errors");
			return "admin/insurances/hmos/hmopackage/" + itemForm.getOrganisationHmoPackage();
		}				
		
		EhmoPackages hmoPackage = hmoPackageBo.getPackageById(itemForm.getOrganisationHmoPackage());
		itemForm.setId(hmoPackage.getId());
		
		if (result.hasErrors()) {
			model.addAttribute("hmoPackage", this.hmoPackageBo.fetchAllByOrganisation());
			return "admin/insurances/hmos/hmopackage";
		}			
		model.addAttribute("hmoPackage", hmoPackage);
		this.hmoPackageItemsBo.save(itemForm);	

		this.alert.setAlert(redirectAttributes, Alert.SUCCESS, "Package Items saved!");
		return "redirect:/admin/insurances/hmos/packageitems/index/" + itemForm.getId();
	}
	
	//Hmo Package Services
		@RequestMapping(value = {"/packageservice/index"})
		@Layout(value = "layouts/datatable")
		public String indexHmoPackageService(Model model, 
									         RedirectAttributes redirectAttributes) {			
			
			model.addAttribute("service", hmoPckServiceBo.fetchAll());					
			return "admin/insurances/hmos/services/index";
		}
	
		@RequestMapping(value="/packageservice/add", method = RequestMethod.GET)
		public String addHmoPackageServiceAction(Model model){		
			
			HmoPckServiceForm hmoPckServiceForm = new HmoPckServiceForm();
			model.addAttribute("hmoPckServiceForm", hmoPckServiceForm);	
			return "system/hmoservice/add";		
		}
		
		@RequestMapping(value = "/packageservice/add", method = RequestMethod.POST)
		public String saveAction(@Valid @ModelAttribute("hmoPckServiceForm") HmoPckServiceForm hmoPckServiceForm,
								  BindingResult result, RedirectAttributes redirectAttributes,
								  Model model){
			
			if(result.hasErrors()){
				return "system/hmoservice/add";
			}		
			
			HmoPckService hmoPckService = hmoPckServiceBo.save(hmoPckServiceForm);
					//hmoPckServiceBo.save(hmoPckServiceForm);
			alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! Hmo Service Package Created");
			return "redirect:/admin/insurances/hmos/packageservice/index";		
		}
		
		//HMO Sub Service Package
		@Layout(value = "layouts/datatable")
		@RequestMapping(value="/packagesubservice/index", method = RequestMethod.GET)
		public String subserviceIndex(Model model){
			
			List<HmoPckSubService> hmoPckSubService = hmoPckSubServiceBo.fetchAll();
			model.addAttribute("hmoPckSubService", hmoPckSubService);
			return "admin/insurances/hmos/services/subservice";
		}
	
		@RequestMapping(value="/packagesubservice/add", method = RequestMethod.GET)
		public String subserviceaddAction(Model model){		
			
			HmoPckSubServiceForm hmoPckSubServiceForm = new HmoPckSubServiceForm();
			model.addAttribute("hmoPckSubServiceForm", hmoPckSubServiceForm);
			model.addAttribute("hmoPckServ", hmoPckServiceBo.fetchAll());		
			return "system/hmosubservice/add";		
		}
		
		@RequestMapping(value = "/packagesubservice/add", method = RequestMethod.POST)
		public String saveAction(@Valid @ModelAttribute("hmoPckSubServiceForm") HmoPckSubServiceForm hmoPckSubServiceForm,
								  BindingResult result, RedirectAttributes redirectAttributes,
								  Model model){
			
			if(result.hasErrors()){
				model.addAttribute("hmoPckService", hmoPckServiceBo.fetchAll());
				return "system/hmosubservice/add";
			}		
			
			HmoPckSubService hmoPckSubService = hmoPckSubServiceBo.save(hmoPckSubServiceForm);
				
			alert.setAlert(redirectAttributes, Alert.SUCCESS, "Success! Hmo SubService Package Created");
			
			return "redirect:/admin/insurances/hmos/packagesubservice/index";		
			
		}
		//sub-services assignment
		//grid categoryItem view
		@SuppressWarnings("unused")
		@RequestMapping(value = { "/services/subservice/index/{id}" })
		@Layout(value = "layouts/datatable")
		public String categoryItem(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
			HmoPckSubService hmoPckSubService = hmoPckSubServiceBo.getHmoPckSubServiceById(id);
			Set<GlobalItem> items = hmoPckSubService.getGlobalItem();
			SubServiceItemForm siForm = new SubServiceItemForm();
			siForm.setSubServiceId(id);
			if(null == hmoPckSubService ){
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Invalid resource");
				model.addAttribute("siForm", new SubServiceItemForm());
				model.addAttribute("asitems", this.ItemBo.fetchAll());
				model.addAttribute("items", items );
				return "admin/insurances/hmos/services/subserviceitem/index";
			}
			model.addAttribute("siForm", siForm);
			model.addAttribute("asitems", this.ItemBo.fetchAll());
			model.addAttribute("SubServiceId", hmoPckSubService.getId());
			model.addAttribute("items", items );		
			return "admin/insurances/hmos/services/subserviceitem/index";
		}
		
				
		@RequestMapping(value = { "/services/subservice/assign" }, method = RequestMethod.POST)
		public String indexSubservice(@Valid @ModelAttribute("hmoPckSubServiceForm") SubServiceItemForm siForm,
				BindingResult result, RedirectAttributes redirectAttributes,Model model) {
			/*if(result.hasErrors()){
				model.addAttribute("hmoPckService", hmoPckServiceBo.fetchAll());
				return "admin/insurances/hmos/services/subserviceitem/index";
			}	*/
			HmoSubserviceItem sItem = new HmoSubserviceItem();
			sItem.setItemId(siForm.getItemName());
			sItem.setSubserviceId(siForm.getSubServiceId());
			sItem.setCreatedBy(userIdentity.getUsername());
			sItem.setOrganisation(userIdentity.getOrganisation());
			subServiceItemBo.save(sItem);
			model.addAttribute("siForm", siForm);
			model.addAttribute("items", this.ItemBo.fetchAll());
			return "redirect:/admin/insurances/hmos/services/subservice/index/"+ siForm.getSubServiceId();
		}
		
		@RequestMapping(value = "/services/subservice/delete/{id}")
		public String deleteItem(@PathVariable("id") Integer id, Model model,
				RedirectAttributes redirectAttributes) {
			GlobalItem sItem = ItemBo.getGlobalItemById(id);
			HmoSubserviceItem item = subServiceItemBo.getHmoSubserviceItemByCategoryItem(sItem.getId());
			if (null == item) {
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Invalid resource");
				return "redirect:/admin/insurances/hmos";
			}
			SubServiceItemForm sitemForm = new SubServiceItemForm();
			sitemForm.setId(item.getId());
			
			model.addAttribute("item", item);
			model.addAttribute("sitemForm", sitemForm);
			model.addAttribute("sItem", sItem);
			return "/admin/insurances/hmos/services/subserviceitem/delete";
		}
		
		@RequestMapping(value = "/services/subservice/delete/{id}", method = RequestMethod.POST)
		public String deleteItem(@ModelAttribute("sitemForm") SubServiceItemForm sitemForm,
				RedirectAttributes redirectAttributes) {

			HmoSubserviceItem sItem = subServiceItemBo.getHmoSubserviceItemById(sitemForm.getId());
			if (null == sItem) {
				alert.setAlert(redirectAttributes, Alert.ERROR,
						"Error! Invalid resource");
				return "redirect:/admin/insurances/hmos";
			}
			this.subServiceItemBo.deleteAll(sItem);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Item Deleted");
			return "redirect:/admin/insurances/hmos/services/subservice/index/"+ sItem.getSubserviceId();
		}

}