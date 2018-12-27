package org.calminfotech.billing.controller;

import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.billing.boInterface.BillItemPriceBo;
import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.billing.boInterface.BillSchemeItemBo;
import org.calminfotech.billing.boInterface.BillUnitOfMeasureBo;
import org.calminfotech.billing.boInterface.BillGlobalItemBo;
import org.calminfotech.billing.boInterface.BillViewBo;
import org.calminfotech.billing.forms.BillItemPriceForm;
import org.calminfotech.billing.forms.BillSchemeForm;
import org.calminfotech.billing.forms.BillSchemeItemForm;
import org.calminfotech.billing.forms.BillUnitOfMeasureForm;
import org.calminfotech.billing.forms.GlobalItemForm;
import org.calminfotech.billing.models.BillItemPrice;
import org.calminfotech.billing.models.BillScheme;
import org.calminfotech.billing.models.BillSchemeItem;
import org.calminfotech.billing.models.BillUnitOfMeasure;
import org.calminfotech.billing.models.BillGlobalItem;
import org.calminfotech.billing.models.BillView;
import org.calminfotech.hr.models.Clockin;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemUnitOfMeasureBo;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemUnitOfMeasure;
import org.calminfotech.system.models.GlobalUnitofMeasure;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/admin/billScheme")
public class BillSchemeController {

	@Autowired
	private GlobalItemUnitOfMeasureBo globalItemUnitOfMeasureBo;
	@Autowired
	private BillSchemeBo billSchemeBo;
	
	@Autowired
	private BillViewBo billViewBo;
	
	@Autowired
	private BillSchemeItemBo billSchemeItemBo;
	@Autowired
	private BillItemPriceBo billItemPriceBo;
	@Autowired
	private BillUnitOfMeasureBo billUnitOfMeasureBo;
	@Autowired
	private BillGlobalItemBo globalItemBo;
	
	@Autowired
	private GlobalItemBo gItemBo;
	
	@Autowired
	private Alert alert;

	@Autowired
	private UserIdentity userIdentity;
	@Autowired
	private Auditor auditor;

	@RequestMapping(value = { "", "/" })
	@Layout(value = "layouts/datatable")
	public String indexAction(Model model) {
	    // model.addAttribute("scheme", billSchemeBo.fetchAll());
		List<BillScheme> list = billSchemeBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()); 
		model.addAttribute("scheme", list);
		List<BillSchemeItem> listItem = billSchemeItemBo.fetchAll();
		model.addAttribute("item", listItem);
		List<BillItemPrice> listPrice = billItemPriceBo.fetchAll();
		model.addAttribute("pric", listPrice);
		List<BillUnitOfMeasure> listUnit = this.billUnitOfMeasureBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		model.addAttribute("uni", listUnit);
		return "admin/billScheme/index";

	}
	@RequestMapping(value = "/view/{id}")
	@Layout(value = "layouts/datatable")
	/*public String viewAction(@PathVariable("id") Integer id,
	RedirectAttributes redirectAttributes, Model model) {*/
	public String viewAction(@PathVariable("id") Integer id, Model model,
	RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		BillScheme billScheme = billSchemeBo.getBillSchemeById(id);
		
		/*if (null == billScheme) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource!");
			return "admin/billScheme/index";
		}*/
		
		BillSchemeForm  billSchemeForm = new BillSchemeForm();
		model.addAttribute("bForm", billSchemeForm);
		model.addAttribute("scheme", this.billSchemeBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
	    model.addAttribute("scheme", billScheme);
	    
	    BillSchemeItemForm billSchemeItemForm = new BillSchemeItemForm();    
	    billSchemeItemForm.setBillId(billScheme.getBillId());
	    model.addAttribute("iForm", billSchemeItemForm);
	    model.addAttribute("item", this.billSchemeItemBo.fetchAll());
	    model.addAttribute("global",this.globalItemBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
	    
	   /* BillItemPriceForm billItemPriceForm = new BillItemPriceForm();
	    model.addAttribute("pForm", billItemPriceForm);
	    model.addAttribute("pric", billItemPriceBo.fetchAll());*/
		return "admin/billScheme/viewBill";
		
	}
	@RequestMapping(value = "/add")
	@Layout(value = "layouts/datatable")
	public String addAction(Model model) { 
		model.addAttribute("aForm", new BillSchemeForm());
		model.addAttribute("scheme", this.billSchemeBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		return "admin/billScheme/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String saveAction(
			@Valid @ModelAttribute("aForm") BillSchemeForm billSchemeForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("scheme", this.billSchemeBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
			return "admin/billScheme/add";
		}	
		BillScheme billScheme = new BillScheme();	
		billScheme.setBillId(billSchemeForm.getBillId());
		billScheme.setName(billSchemeForm.getName());
		billScheme.setDescription(billSchemeForm.getDescription());
		billScheme.setBillingType(billSchemeForm.getBillingType());
		billScheme.setCreatedBy(userIdentity.getUsername());
		billScheme.setOrganisationId(userIdentity.getOrganisation().getId());
		billScheme.setCreatedDate(new GregorianCalendar().getTime());
		billScheme.setStatus("Active");
	      this.billSchemeBo.save(billScheme);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Service Billing Scheme added!");
		return "redirect:/admin/billScheme";

	}

	@RequestMapping(value = "/edit/{id}")
	public String editAction(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		BillScheme billScheme = billSchemeBo.getBillSchemeById(id);
	//	System.out.println( "Before1");
		if (null == billScheme) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
			return "redirect:/admin/billScheme";
		}
		BillSchemeForm billSchemeForm = new BillSchemeForm();
		
		billSchemeForm.setBillId(billScheme.getBillId());
		billSchemeForm.setName(billScheme.getName());	
		billSchemeForm.setDescription(billScheme.getDescription());
		billSchemeForm.setBillingType(billScheme.getBillingType());

		model.addAttribute("eForm", billSchemeForm);
		model.addAttribute("scheme", this.billSchemeBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		//System.out.println( "Before2");
		this.auditor.before(request, "billing Scheme Form", billSchemeForm);

		return "admin/billScheme/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("eForm") BillSchemeForm billSchemeForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request){
		if (result.hasErrors()) {
			model.addAttribute("scheme", this.billSchemeBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
			return "admin/billScheme/edit";
		}
		BillScheme billScheme  = billSchemeBo.getBillSchemeById(billSchemeForm.getBillId());
		billScheme.setName(billSchemeForm.getName());
		billScheme.setDescription(billSchemeForm.getDescription());
		billScheme.setBillingType(billSchemeForm.getBillingType());
	
		billSchemeBo.update(billScheme);

	auditor.after(request, "Billing Form", billSchemeForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Billing Scheme updated");

		return "redirect:/admin/billScheme";
			
	}
	@RequestMapping(value = "/delete/{id}")
	public String deleteAction(@PathVariable("id") Integer id, Model model,
		RedirectAttributes redirectAttributes, HttpServletRequest request) {
		BillScheme billScheme = billSchemeBo.getBillSchemeById(id);
		if (null == billScheme) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "admin/billScheme/index";
		}
		BillSchemeForm billSchemeForm = new BillSchemeForm();
		billSchemeForm.setBillId(billScheme.getBillId());
		billSchemeForm.setDescription(billScheme.getDescription());
		billSchemeForm.setName(billScheme.getName());
		billSchemeForm.setBillingType(billScheme.getBillingType());
		model.addAttribute("dForm", billSchemeForm);
		model.addAttribute("scheme", billScheme);
		return "admin/billScheme/delete";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@ModelAttribute("dForm") BillSchemeForm billSchemeForm,
		BindingResult result, RedirectAttributes redirectAttributes,
		Model model) {
		BillScheme billScheme = billSchemeBo.getBillSchemeById(billSchemeForm.getBillId());
		System.out.println("name");
		if (null == billScheme) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource");
			return "admin/billScheme";
		}
	   this.billSchemeBo.delete(billScheme);
		alert.setAlert(redirectAttributes, Alert.ERROR,
				"Success! Bill Scheme Deleted");
		return "redirect:/admin/billScheme";
	}
              // global item	
	@RequestMapping(value = "/globalIndex")
	@Layout(value = "layouts/datatable")
	public String indexGlobal(Model model) {
		List<BillGlobalItem> globalItem = globalItemBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()); 
		model.addAttribute("global", globalItem);
		List<BillGlobalItem> list2 = globalItemBo.fetchAll();
		model.addAttribute("pric", list2);
		return "admin/billScheme/globalIndex";
	}
	@RequestMapping(value = "/addGlobal")
	@Layout(value = "layouts/datatable")
	public String addGlobal(Model model) {
    
		model.addAttribute("gForm", new GlobalItemForm());
		model.addAttribute("global", this.globalItemBo.fetchAll());
		return "admin/billScheme/addGlobal";
	}
	@RequestMapping(value = "/addGlobal", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String saveGlobal(
			@Valid @ModelAttribute("gForm") GlobalItemForm globalItemForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("global", this.globalItemBo.fetchAll());
			return "admin/billScheme/addGlobal";
		}	
		BillGlobalItem globalItemItem = new BillGlobalItem();	
		globalItemItem.setGlobalItemId(globalItemForm.getGlobalItemId());
		globalItemItem.setGlobalName(globalItemForm.getGlobalName());
		globalItemItem.setGlobalDescription(globalItemForm.getGlobalDescription());
		globalItemItem.setCreatedBy(userIdentity.getUsername());
		globalItemItem.setOrganisationId(userIdentity.getOrganisation().getId());
		globalItemItem.setCreatedDate(new GregorianCalendar().getTime());
		globalItemItem.setStatus("Active");
	      this.globalItemBo.save(globalItemItem);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Global Item added successfully!");
		return "redirect:/admin/billScheme/globalIndex";

	}

	@RequestMapping(value = "/editGlobal/{id}")
	public String editGlobal(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		BillGlobalItem globalItem = this.globalItemBo.getGlobalItemById(id);
		if (null == globalItem) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
			return "redirect:/admin/billScheme/globalIndex";
		}
		GlobalItemForm globalItemForm = new GlobalItemForm();
		
		globalItemForm.setGlobalItemId(globalItem.getGlobalItemId());
		globalItemForm.setGlobalDescription(globalItem.getGlobalDescription());	
		globalItemForm.setGlobalName(globalItem.getGlobalDescription());
	
		model.addAttribute("gForm", globalItemForm);
		model.addAttribute("global", this.globalItemBo.fetchAll());
		this.auditor.before(request, "global Form", globalItemForm);

		return "admin/billScheme/editGlobal";
	}

	@RequestMapping(value = "/editGlobal/{id}", method = RequestMethod.POST)
	public String updateGlobal(
			@Valid @ModelAttribute("gForm") GlobalItemForm globalItemForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request){
		if (result.hasErrors()) {
			model.addAttribute("global", this.globalItemBo.fetchAll());
			return "admin/billScheme/editGlobal";
		}
		BillGlobalItem globalItem  = globalItemBo.getGlobalItemById(globalItemForm.getGlobalItemId());
		globalItem.setGlobalName(globalItemForm.getGlobalName());
		globalItem.setGlobalDescription(globalItemForm.getGlobalDescription());
	
		globalItemBo.update(globalItem);

	auditor.after(request, "global Form", globalItemForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! global Item updated");

		return "redirect:/admin/billScheme/globalIndex";
			
	}
	    // Scheme item
	@RequestMapping(value = "/saveBillItem", method = RequestMethod.POST)
	public String saveItem(
			@Valid @ModelAttribute("iForm") BillSchemeItemForm billSchemeItemForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
	BillScheme billScheme = this.billSchemeBo.getBillSchemeById(billSchemeItemForm.getBillId());
			
		/*if (result.hasErrors()) {
			model.addAttribute("scheme", bill);
			return "redirect:/admin/billScheme";
		}*/

		/*if (null == bill) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not save billing scheme. Invalid resource");
			return "redirect:/admin/billScheme";
		}	*/
		BillSchemeItem billSchemeItem = new BillSchemeItem();
	
		billSchemeItem.setBillScheme(billScheme);
		billSchemeItem.setBillSchemeItemId(billSchemeItemForm.getBillSchemeItemId());
		billSchemeItem.setBillName(billSchemeItemForm.getBillName());
		billSchemeItem.setGlobalItemId(billSchemeItemForm.getGlobalItemId());
		billSchemeItem.setOrganisationId(userIdentity.getOrganisation().getId());
		billSchemeItem.setCreatedDate(new GregorianCalendar().getTime());
		billSchemeItem.setCreatedBy(userIdentity.getUsername());
	
	
              this.billSchemeItemBo.save(billSchemeItem);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Billing Item saved successfully");
		return "redirect:/admin/billScheme/view/" + billScheme.getBillId();
		
	}
	@RequestMapping(value = "/editItem/{id}")
	public String editItem(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		BillSchemeItem billSchemeItem = billSchemeItemBo.getBillSchemeItemById(id);
		if (null == billSchemeItem) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
			return "redirect:/admin/billScheme";
		}
		BillSchemeItemForm billSchemeItemForm = new BillSchemeItemForm();
		
		billSchemeItemForm.setBillSchemeItemId(billSchemeItem.getBillSchemeItemId());
		billSchemeItemForm.setGlobalItemId(billSchemeItem.getGlobalItemId());	
		billSchemeItemForm.setBillName(billSchemeItem.getBillName());
		
		model.addAttribute("iForm", billSchemeItemForm);
		model.addAttribute("item", this.billSchemeItemBo.fetchAll());
		model.addAttribute("global", this.globalItemBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		this.auditor.before(request, "Billing Item Form", billSchemeItemForm);

		return "admin/billScheme/editItem";
	}

	@RequestMapping(value = "/editItem/{id}", method = RequestMethod.POST)
	public String updateItem(
			@Valid @ModelAttribute("iForm") BillSchemeItemForm billSchemeItemForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request){
		if (result.hasErrors()) {
			model.addAttribute("item", this.billSchemeItemBo.fetchAll());
			return "admin/billScheme/edit";
		}
		BillSchemeItem billSchemeItem  = billSchemeItemBo.getBillSchemeItemById(billSchemeItemForm.getBillSchemeItemId());
		billSchemeItem.setBillName(billSchemeItemForm.getBillName());
		billSchemeItem.setGlobalItemId(billSchemeItemForm.getGlobalItemId());
		billSchemeItem.setModifiedDate(new GregorianCalendar().getTime());
		billSchemeItem.setModifiedBy(userIdentity.getUsername());
	
		billSchemeItemBo.update(billSchemeItem);
	auditor.after(request, "Billing Item Form", billSchemeItemForm,
				userIdentity.getUsername());
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Billing Scheme Item updated");
		return "redirect:/admin/billScheme/view/" + billSchemeItem.getBillScheme().getBillId();		
			
	}
	// bill item price
	@RequestMapping(value = "/viewBillPrice/{id}")
	@Layout(value = "layouts/datatable")
	/*public String viewPrice(@PathVariable("id") Integer id,
	RedirectAttributes redirectAttributes, Model model) {*/
	public String viewPrice(@PathVariable("id") Integer id, Model model,
	RedirectAttributes redirectAttributes, HttpServletRequest request) {
    BillSchemeItem billSchemeItem = billSchemeItemBo.getBillSchemeItemById(id);
		/*if (null == billScheme) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource!");
			return "admin/billScheme/index";
		}*/
	    BillSchemeItemForm billSchemeItemForm = new BillSchemeItemForm();    
	    model.addAttribute("iForm", billSchemeItemForm);
	    model.addAttribute("item", this.billSchemeItemBo.fetchAll());
	    model.addAttribute("item", billSchemeItem);
    
	    BillItemPriceForm billItemPriceForm = new BillItemPriceForm();
	    billItemPriceForm.setBillSchemeItemId(billSchemeItem.getBillSchemeItemId());
		    model.addAttribute("pForm", billItemPriceForm);
	    model.addAttribute("pric", billItemPriceBo.fetchAll());
	    model.addAttribute("global", globalItemBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
	    List<BillUnitOfMeasure> listUnit = this.billUnitOfMeasureBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
	    model.addAttribute("uni", listUnit);
		return "admin/billScheme/viewPrice";
	}
	
	@RequestMapping(value = "/saveBillPrice", method = RequestMethod.POST)
	public String savePrice(
			@Valid @ModelAttribute("pForm") BillItemPriceForm billItemPriceForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
            BillSchemeItem billSchemeItem = this.billSchemeItemBo.getBillSchemeItemById(billItemPriceForm.getBillSchemeItemId());

		/*if (result.hasErrors()) {
			model.addAttribute("scheme", bill);
			return "redirect:/admin/billScheme";
		}*/

		if (null == billSchemeItem) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Could not save billing item price. Invalid resource");
			return "redirect:/admin/billScheme";
		}	
		BillItemPrice billItemPrice = new BillItemPrice();

	    billItemPrice.setBillSchemeItem(billSchemeItem);
		billItemPrice.setBillItemPriceId(billItemPriceForm.getBillItemPriceId());
	    billItemPrice.setGlobalItemId(billItemPriceForm.getGlobalItemId());
		billItemPrice.setUnitOfMeasure(billItemPriceForm.getUnitOfMeasure());
		billItemPrice.setBillItemPrice(billItemPriceForm.getBillItemPrice());
		billItemPrice.setCreatedDate(new GregorianCalendar().getTime());
		billItemPrice.setCreatedBy(userIdentity.getUsername());
		billItemPrice.setOrganisationId(userIdentity.getOrganisation().getId());
              this.billItemPriceBo.save(billItemPrice);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Billing Scheme Item Price saved successfully");
		return "redirect:/admin/billScheme/viewBillPrice/" + billSchemeItem.getBillSchemeItemId();
		
	}
	@RequestMapping(value = "/editPrice/{id}")
	public String editPrice(@PathVariable("id") Integer id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		BillItemPrice billItemPrice = billItemPriceBo.getBillItemPriceById(id);
		if (null == billItemPrice) {
			alert.setAlert(redirectAttributes, Alert.ERROR, "Invalid resource");
			return "redirect:/admin/billScheme";
		}
		BillItemPriceForm billItemPriceForm = new BillItemPriceForm();
		
		billItemPriceForm.setBillItemPriceId(billItemPrice.getBillItemPriceId());
		billItemPriceForm.setBillItemPrice(billItemPrice.getBillItemPrice());
		billItemPriceForm.setUnitOfMeasure(billItemPrice.getUnitOfMeasure());
		
		model.addAttribute("pForm", billItemPriceForm);
		model.addAttribute("pric", this.billItemPriceBo.fetchAll());
        model.addAttribute("item", this.billSchemeItemBo.fetchAll());
        model.addAttribute("uni", this.billUnitOfMeasureBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		this.auditor.before(request, "Item Price Form", billItemPriceForm);

		return "admin/billScheme/editPrice";
	}

	@RequestMapping(value = "/editPrice/{id}", method = RequestMethod.POST)
	public String updatePrice(
			@Valid @ModelAttribute("pForm") BillItemPriceForm billItemPriceForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model, HttpServletRequest request){
		if (result.hasErrors()) {
			model.addAttribute("pric", this.billItemPriceBo.fetchAll());
			return "admin/billScheme/editPrice";
		}
		BillItemPrice billItemPrice  = billItemPriceBo.getBillItemPriceById(billItemPriceForm.getBillItemPriceId());
		billItemPrice.setUnitOfMeasure(billItemPriceForm.getUnitOfMeasure());
		billItemPrice.setBillItemPrice(billItemPriceForm.getBillItemPrice());
		billItemPrice.setModifiedDate(new GregorianCalendar().getTime());
		billItemPrice.setModifiedBy(userIdentity.getUsername());

		billItemPriceBo.update(billItemPrice);
	auditor.after(request, "Billing Item Price Form", billItemPriceForm,
				userIdentity.getUsername());

		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Billing Item Price updated");
		return "redirect:/admin/billScheme/viewBillPrice/" + billItemPrice.getBillSchemeItem().getBillSchemeItemId();
	}
	//unit of measure here
	@RequestMapping(value = "/unitIndex")
	@Layout(value = "layouts/datatable")
	public String indexUnit(Model model) {
		List<BillUnitOfMeasure> billUnitOfMeasure = billUnitOfMeasureBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId());
		model.addAttribute("uni", billUnitOfMeasure);
		return "admin/billScheme/unitIndex";
	}
	@RequestMapping(value = "/addUnit")
	@Layout(value = "layouts/datatable")
	public String addUnit(Model model) { 
		model.addAttribute("uForm", new BillUnitOfMeasureForm());
		model.addAttribute("uni", this.billUnitOfMeasureBo.fetchAllByOrganisation(userIdentity.getOrganisation().getId()));
		return "admin/billScheme/addUnit";
	}

	@RequestMapping(value = "/addUnit", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String saveUnit(
			@Valid @ModelAttribute("uForm") BillUnitOfMeasureForm billUnitOfMeasureForm,
			BindingResult result, RedirectAttributes redirectAttributes,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("uni", this.billUnitOfMeasureBo.fetchAll());
			return "admin/billScheme/addUnit";
		}	
		BillUnitOfMeasure billUnitOfMeasure = new BillUnitOfMeasure();	
		billUnitOfMeasure.setUnitOfMeasureId(billUnitOfMeasureForm.getUnitOfMeasureId());
		billUnitOfMeasure.setUnitOfMeasure(billUnitOfMeasureForm.getUnitOfMeasure());
		billUnitOfMeasure.setUnit(billUnitOfMeasureForm.getUnit());
		billUnitOfMeasure.setCreatedBy(userIdentity.getUsername());
		billUnitOfMeasure.setOrganisationId(userIdentity.getOrganisation().getId());
		billUnitOfMeasure.setCreatedDate(new GregorianCalendar().getTime());
		billUnitOfMeasure.setStatus("Active");
	      this.billUnitOfMeasureBo.save(billUnitOfMeasure);
		alert.setAlert(redirectAttributes, Alert.SUCCESS,
				"Success! Unit Of Measure added!");
		return "redirect:/admin/billScheme/unitIndex";

	}	
	
	@RequestMapping(value = "/request/unitOfMeasure/{globalItemId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getUnitOfMeasureByItemId(@PathVariable("globalItemId") int globalItemId) {
		System.out.println("unit dropdown onchange");
		String measureStr = "<option value='0'>Select</option>";
		//UnitList uList = this.unitListBo.getUnitListById(Id);
		//List<BillUnitOfMeasure> bUnitmeasure = globalItemUnitOfMeasureBo.listGlobalItemViaMeasure(globalItemId);
		GlobalItem gItem = this.gItemBo.getGlobalItemById(globalItemId);
		//System.out.println("bill size " + bUnitmeasure.size());
		if (gItem == null) {
			return measureStr;
		}
		//List<Clockin> clockin = this.clockinBo.fetchAllByUnitId(Id);
		//System.out.println("clock size"+clock.size());
		for (BillUnitOfMeasure bunit : gItem.getMeasurement())
		
		{
			measureStr += "<option value='" + bunit.getUnitOfMeasureId() + "'>"
					+ bunit.getUnitOfMeasure()+"</option>";
		System.out.println("user string"+ measureStr);
		}
		return measureStr;
	}
	
	@RequestMapping(value = "/request/price/{unitofMeasureId}/{globalItemId}/{schemeId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getPriceByItemUnitOfMeasureId(@PathVariable("unitofMeasureId") int unitofMeasureId, 
			@PathVariable("globalItemId") int globalItemId,
			@PathVariable("schemeId") int schemeId) {
		System.out.println("unit dropdown onchange");
		//String measureStr = "<option value='0'>Select</option>";
		String measureStr = "";
		//UnitList uList = this.unitListBo.getUnitListById(Id);
		//List<BillUnitOfMeasure> bUnitmeasure = globalItemUnitOfMeasureBo.listGlobalItemViaMeasure(globalItemId);
		//GlobalItem gItem = this.gItemBo.getGlobalItemById(globalItemId);
		BillView billView = this.billViewBo.getBillViewByGlobalItemUofMeasureSchemeId(globalItemId, unitofMeasureId,schemeId);
		//System.out.println("bill size " + bUnitmeasure.size());
		if (billView == null) {
			return measureStr;
		}
		//List<Clockin> clockin = this.clockinBo.fetchAllByUnitId(Id);
		//System.out.println("clock size"+clock.size());
		//for (BillView bView : billView)
		
		
			measureStr = billView.getBillItemPrice().toString();
		//System.out.println("user string"+ measureStr);
		
		return measureStr;
	}
	
}

