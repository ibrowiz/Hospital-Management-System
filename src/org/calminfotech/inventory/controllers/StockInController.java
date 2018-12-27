package org.calminfotech.inventory.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.error.custom.exception.InvalidOpeningStockBalanceException;
import org.calminfotech.error.custom.exception.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.boInterface.StockInManagerBo;
import org.calminfotech.inventory.forms.StockInForm;
import org.calminfotech.inventory.forms.StockInLineForm;
import org.calminfotech.inventory.models.StockIn;
import org.calminfotech.inventory.models.StockInLine;
import org.calminfotech.inventory.models.Vendor;
import org.calminfotech.inventory.utils.Text;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.CustomValidator;
import org.calminfotech.utils.GlobalItemList;
import org.calminfotech.utils.VendorsList;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/inventory/stock_in")
public class StockInController extends AbstractBaseController {

	@Autowired
	private VendorsList vendorsList;

	@Autowired
	private GlobalItemList globalItemList;

	@Autowired
	private GlobalItemBo globalItemBo;

	@Autowired
	private StockInManagerBo stockInManagerBo;

	@RequestMapping(value = { "/", "" })
	@Layout("layouts/datatable")
	public String indexAction(ModelMap model) {
		return displayStockInBatchesListPage(model);
	}

	private String displayStockInBatchesListPage(ModelMap model) {

		List<StockIn> stockInBatches = null;
		try {
			stockInBatches = this.stockInManagerBo.getStockInBatchesList("");
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.ERROR, e.getExceptionMsg(), model);
		}
		model.addAttribute("stockInBatchesList", stockInBatches);
		return "/inventories/stock_in/index";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String addAction(ModelMap model,
			RedirectAttributes redirectAttributes) {

		String id = null;
		StockInForm stockInBatchForm = this.getStockInBatchForm(id);
		try {
			this.loadStockInBatchForm(stockInBatchForm, model);
			return this.displayStockInBatchFormPage(stockInBatchForm, model);

		} catch (RecordNotFoundException e) {
			Alert alert = (Alert) model.get("alert");
			alert.setAlert(redirectAttributes, Alert.ERROR, e.getExceptionMsg());
			return "redirect:/inventory/stock_in";
		}

	}

	@Layout("layouts/datatable")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBatchSupply(@PathVariable String id, ModelMap model,
			RedirectAttributes redirectAttributes) {

		StockInForm stockInBatchForm = this.getStockInBatchForm(id);

		try {
			this.loadStockInBatchForm(stockInBatchForm, model);
			model.addAttribute("pageEdit", true);
			return this.displayStockInBatchFormPage(stockInBatchForm, model);

		} catch (RecordNotFoundException e) {
			Alert alert = (Alert) model.get("alert");
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource (Resource id:" + id + ")");
			return "redirect:/inventory/stock_in";
		}
	}

	private StockInForm getStockInBatchForm(String id) {

		StockInForm stockInBatchForm = new StockInForm();
		if (id != null) {
			try {
				stockInBatchForm.setId(Integer.parseInt(id));
			} catch (NumberFormatException e) {
			}
		}
		return stockInBatchForm;
	}

	private void loadStockInBatchForm(StockInForm stockInBatchForm,
			ModelMap model) throws RecordNotFoundException {

		// if BatchSupply id not empty then we are in edit mode so we populate
		// form
		if (stockInBatchForm.getId() != 0
				&& !model.containsKey("stockInBatchForm")) {
			// get BatchSupply details and populate form
			StockIn stockInBatch = this.stockInManagerBo
					.getStockInBatchDetailsById(stockInBatchForm.getId());

			String date = stockInBatch.getSupplyDate();
			if (date != null && date.contains(" ")) {
				stockInBatchForm.setDateOfSupply(date.split(" ")[0]);
			}
			stockInBatchForm.setVendor(String.valueOf(stockInBatch.getVendor()
					.getId()));

		}

		// load vendors list
		List<Vendor> vendorsList = this.vendorsList.fetchAll();
		if (vendorsList == null || vendorsList.isEmpty()) {
			this.modalAlert(true, Alert.ERROR,
					"No vendor record found in the database", model);
		}
		model.addAttribute("vendorsList", vendorsList);
		String dte = stockInBatchForm.getDateOfSupply();

		// set todays date as default if there exist none
		if (dte == null || dte.isEmpty()) {
			stockInBatchForm.setDateOfSupply(new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date()));
		}

	}

	/* method display batch supply page */
	private String displayStockInBatchFormPage(StockInForm stockInBatchForm,
			ModelMap model) {

		model.addAttribute("stockInBatchForm", stockInBatchForm);
		model.addAttribute("pageRegister", true);
		return this.displayStockInBatchesListPage(model);
	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("stockInBatchForm") StockInForm stockInBatchForm,
			BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {

		Alert alert = (Alert) model.get("alert");

		CustomValidator validator = new CustomValidator();
		String date = stockInBatchForm.getDateOfSupply();
		if (!date.isEmpty() && !validator.validateDate(date, "yyyy-mm-dd")) {
			result.rejectValue("dateOfSupply", "error.stockInBatchForm",
					Text.INVALID_DATE_FORMAT);
		}

		if (!result.hasErrors()) {
			StockIn stockInBatch = this.stockInManagerBo
					.saveStockInBatch(stockInBatchForm);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					" Success! New Stock-In Batch  Succesfully created! Stock In Batch id:  "
							+ stockInBatch.getBatchId());
			return "redirect:/inventory/stock_in";
		}
		try {
			this.loadStockInBatchForm(stockInBatchForm, model);
			return this.displayStockInBatchFormPage(stockInBatchForm, model);
		} catch (RecordNotFoundException e) {
			alert.setAlert(redirectAttributes, Alert.ERROR, e.getExceptionMsg());
			return "redirect:/inventory/stock_in";

		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String editBatchSupply(
			@Valid @ModelAttribute("stockInBatchForm") StockInForm stockInBatchForm,
			BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {

		Alert alert = (Alert) model.get("alert");
		CustomValidator validator = new CustomValidator();
		String date = stockInBatchForm.getDateOfSupply();
		if (!date.isEmpty() && !validator.validateDate(date, "yyyy-mm-dd")) {
			result.rejectValue("dateOfSupply", "error.stockInBatchForm",
					Text.INVALID_DATE_FORMAT);
		}

		if (!result.hasErrors()) {
			try {
				StockIn stockInBatch = this.stockInManagerBo
						.editStockInBatch(stockInBatchForm);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						" Success! Stock-In updated succesfully ! ");
				return "redirect:/inventory/stock_in";
			} catch (RecordNotFoundException e) {
				this.alert(true, Alert.ERROR, e.getExceptionMsg(), model);
			}
		}
		try {
			this.loadStockInBatchForm(stockInBatchForm, model);
			model.addAttribute("pageEdit", true);
			return this.displayStockInBatchFormPage(stockInBatchForm, model);
		} catch (RecordNotFoundException e) {
			alert.setAlert(
					redirectAttributes,
					Alert.ERROR,
					"Error! Invalid resource (Resource id:"
							+ stockInBatchForm.getId() + ")");
			return "redirect:/inventory/stock_in";
		}

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String deleteBatchSupply(@PathVariable int id,
			RedirectAttributes redirectAttributes, ModelMap model) {

		try {

			StockIn stockInBatch = this.stockInManagerBo
					.getStockInBatchDetailsById(id);
			model.addAttribute("stockInBatch", stockInBatch);
			model.addAttribute("pageDelete", true);
			return this.displayStockInBatchesListPage(model);

		} catch (RecordNotFoundException e) {
			Alert alert = (Alert) model.get("alert");
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource (Resource id:" + id + ")");
			return "redirect:/inventory/stock_in";
		}

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@RequestParam(value = "batchId", required = false) String id,
			RedirectAttributes redirectAttributes, ModelMap model) {

		Alert alert = (Alert) model.get("alert");
		StockIn stockInBatch = null;
		try {
			stockInBatch = this.stockInManagerBo
					.getStockInBatchDetailsById(Integer.parseInt(id));
			this.stockInManagerBo.delete(stockInBatch);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Stock-In Batch deleted successfully");
			return "redirect:/inventory/stock_in/";

		} catch (NumberFormatException e) {
		} catch (RecordNotFoundException e) {
		} catch (InvalidUnitOfMeasureConfiguration e) {
			this.modalAlert(true, Alert.ERROR, e.getExceptionMsg(), model);
			model.addAttribute("pageDelete", true);
			model.addAttribute("stockInBatch", stockInBatch);
			return this.displayStockInBatchesListPage(model);
		}
		alert.setAlert(redirectAttributes, Alert.ERROR,
				"Error! Invalid resource (Resource id:" + id + ")");
		return "redirect:/inventory/stock_in/";
	}

	// line supply

	@Layout("layouts/datatable")
	@RequestMapping(value = "/line_item/{batch_id}", method = RequestMethod.GET)
	public String findSupplyLine(@PathVariable int batch_id,
			RedirectAttributes redirectAttributes, ModelMap model) {

		try {
			return displayStockInLineItemsListPage(batch_id, model);
		} catch (RecordNotFoundException e) {//if the batch stockin is not available
			Alert alert = (Alert) model.get("alert");
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource (Resource id:" + batch_id + ")");
			return "redirect:/inventory/stock_in";
		}
	}

	/* method to display supply lines for given supply batchid */
	private String displayStockInLineItemsListPage(int batchId, ModelMap model)
			throws RecordNotFoundException {
		// List<StockInLine> stockInLineItems = null;

		// get the batch supply details
		StockIn stockInBatch = this.stockInManagerBo
				.getStockInBatchDetailsById(batchId);
		model.addAttribute("stockInBatch", stockInBatch);
		return "/inventories/stock_in/line_item/index";

	}

	@Layout("layouts/datatable")
	@RequestMapping(value = "/line_item/new/{batch_id}", method = RequestMethod.GET)
	public String newStockInLineItem(@PathVariable int batch_id,
			RedirectAttributes redirectAttributes, ModelMap model) {

		String id = null;
		StockInLineForm stockInLineItemForm = this.getStockInLineItemForm(id,
				batch_id);

		try {
			this.loadStockInLineItemForm(stockInLineItemForm, model);
			return this.displayStockInLineItemFormPage(batch_id,
					stockInLineItemForm, model);
		} catch (RecordNotFoundException e) {
			Alert alert = (Alert) model.get("alert");
			alert.setAlert(redirectAttributes, Alert.ERROR, e.getExceptionMsg());
			return "redirect:/inventory/stock_in/line_item/" + batch_id;
		}
	}

	private StockInLineForm getStockInLineItemForm(String id, int batchId) {

		StockInLineForm stockInLineItemForm = new StockInLineForm();
		stockInLineItemForm.setBatchId(batchId);
		if (id != null) {
			try {
				stockInLineItemForm.setId(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				stockInLineItemForm.setId(0);
			}
		}
		return stockInLineItemForm;
	}

	private void loadStockInLineItemForm(StockInLineForm stockInLineItemForm,
			ModelMap model) throws RecordNotFoundException {
		if (stockInLineItemForm.getId() != 0
				&& !model.containsKey("stockInLineItemForm")) {
			// get BatchSupply details and populate form
			int id = stockInLineItemForm.getId();
			StockInLine stockInLineItem = this.stockInManagerBo
					.getStockInLineItemDetailById(id);

			// setglobal item
			GlobalItem globalItem = stockInLineItem.getGlobalItem();
			if (globalItem != null) {
				stockInLineItemForm.setGlobalItem(String.valueOf(globalItem
						.getId()));
			}
			// set unit of measure
			GlobalUnitofMeasure globalUnitofMeasure = stockInLineItem
					.getGlobalUnitofMeasure();
			if (globalUnitofMeasure != null) {
				stockInLineItemForm.setUnitOfMeasure(String
						.valueOf(globalUnitofMeasure.getId()));
			}

			stockInLineItemForm.setQuantity(String.valueOf(stockInLineItem
					.getQuantity()));

		}

		// load products list
		model.addAttribute("globalItemsList", this.globalItemList.fetchAll());
		String globalItem = stockInLineItemForm.getGlobalItem();
		if (globalItem != null && !globalItem.isEmpty()) {
			try {
				model.addAttribute("unitOfMeasuresList",
						getGlobalItemUnitOfMeasureList(Integer
								.parseInt(globalItem)));
			} catch (NumberFormatException e) {

			}
		}

	}

	/* method display batch supply page */
	private String displayStockInLineItemFormPage(int batchId,
			StockInLineForm stockInLineItemForm, ModelMap model) throws RecordNotFoundException {

		model.addAttribute("stockInLineItemForm", stockInLineItemForm);
		model.addAttribute("pageRegister", true);
	    return displayStockInLineItemsListPage(batchId, model);
	
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = "/line_item/edit/{id}/batch/{bid}", method = RequestMethod.GET)
	public String editStockInLineItem(@PathVariable String id,
			@PathVariable int bid, RedirectAttributes redirectAttributes,
			ModelMap model) {

		StockInLineForm stockInLineItemForm = this.getStockInLineItemForm(id,
				bid);

		try {
			this.loadStockInLineItemForm(stockInLineItemForm, model);
			model.addAttribute("pageEdit", true);
			
			return this.displayStockInLineItemFormPage(bid,
					stockInLineItemForm, model);
		
		} catch (RecordNotFoundException e) {
			Alert alert = (Alert) model.get("alert");
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource (Resource id:" + id + ")");
			return "redirect:/inventory/stock_in/line_item/" + bid;
		}
		
	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/line_item/new/{batch_id}", method = RequestMethod.POST)
	public String addLineSupplyItem(
			@Valid @ModelAttribute("stockInLineItemForm") StockInLineForm stockInLineItemForm,
			BindingResult result, ModelMap model,
			@RequestParam(value = "batchId", required = false) String batchId,
			RedirectAttributes redirectAttributes, HttpServletRequest req) {

		Alert alert = (Alert) model.get("alert");

		// String[] arr = req.getParameterValues("unit_of_measures[]");
		/*
		 * List unitOfMeasures = null; if (arr != null) { unitOfMeasures =
		 * Arrays.asList(arr); }
		 */
		// List unitOfMeasuresDetails = processUnitOfMeasuresDetails
		// (unitOfMeasures, req, model,result);
		// lineSupplyForm.setUnitOfMeasureQuantities(unitOfMeasuresDetails);
		if (!result.hasErrors()) {
			try {
				StockInLine stockInLineItem = this.stockInManagerBo
						.saveStockInLineItem(stockInLineItemForm);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						" Success! item Succesfully added to supply chain!");

				StockIn stockInBatch = stockInLineItem.getStockInBatch();
				if (stockInBatch != null) {
					return "redirect:/inventory/stock_in/line_item/"
							+ stockInBatch.getId();
				}
				return "redirect:/inventory/stock_in";

			} catch (InvalidUnitOfMeasureConfiguration e) {
				this.modalAlert(true, Alert.ERROR, e.getExceptionMsg(), model);
			} catch (InvalidOpeningStockBalanceException e) {
				this.modalAlert(true, Alert.ERROR, e.getExceptionMsg(), model);
			}
		}

		try {
			this.loadStockInLineItemForm(stockInLineItemForm, model);
			model.addAttribute("pageRegister", true);
			return this.displayStockInLineItemFormPage(
					stockInLineItemForm.getBatchId(), stockInLineItemForm,
					model);
		} catch (RecordNotFoundException e) {
			alert.setAlert(redirectAttributes, Alert.ERROR, e.getExceptionMsg());
			return "redirect:/inventory/stock_in/line_item/" + batchId;
		}
	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/line_item/edit/{id}/batch/{bid}", method = RequestMethod.POST)
	public String editStockInLineItem(
			@Valid @ModelAttribute("stockInLineItemForm") StockInLineForm stockInLineItemForm,
			BindingResult result, ModelMap model,
			@RequestParam(value = "batchId", required = false) String batchId,
			RedirectAttributes redirectAttributes, HttpServletRequest req) {

		Alert alert = (Alert) model.get("alert");

		if (!result.hasErrors()) {
			try {
				StockInLine stockInLineItem = this.stockInManagerBo
						.editStockInLineItem(stockInLineItemForm);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						" Success! item updated Succesfully !");

				StockIn stockInBatch = stockInLineItem.getStockInBatch();
				if (stockInBatch != null) {
					return "redirect:/inventory/stock_in/line_item/"
							+ stockInBatch.getId();
				}
				return "redirect:/inventory/stock_in";

			} catch (InvalidUnitOfMeasureConfiguration e) {
				this.modalAlert(true, Alert.ERROR, e.getExceptionMsg(), model);
			} catch (RecordNotFoundException e) {
				this.alert(true, Alert.INFO, Text.RECORD_NOT_FOUND, model);
			} catch (InvalidOpeningStockBalanceException e) {
				this.modalAlert(true, Alert.ERROR, e.getExceptionMsg(), model);
			}
		}

		try {
			this.loadStockInLineItemForm(stockInLineItemForm, model);
			model.addAttribute("pageEdit", true);
			return this.displayStockInLineItemFormPage(
					stockInLineItemForm.getBatchId(), stockInLineItemForm,
					model);
		} catch (RecordNotFoundException e) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource (Resource id:"
							+ stockInLineItemForm.getId() + ")");
			return "redirect:/inventory/stock_in/line_item/" + batchId;
		}
	}

	@RequestMapping(value = "/line_item/delete/{id}/batch/{bid}", method = RequestMethod.GET)
	@Layout("layouts/datatable")
	public String deleteStockInLineItem(@PathVariable int id,
			@PathVariable int bid, ModelMap model,
			RedirectAttributes redirectAttributes) {

		try {
			StockInLine stockInLineItem = this.stockInManagerBo
					.getStockInLineItemDetailById(id);
			model.addAttribute("stockInLineItem", stockInLineItem);
			model.addAttribute("pageDelete", true);
			return this.displayStockInLineItemsListPage(bid, model);

		} catch (RecordNotFoundException e) {
			Alert alert = (Alert) model.get("alert");
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource (Resource id:" + id + ")");
			return "redirect:/inventory/stock_in/line_item/" + bid;
		}

	}

	@RequestMapping(value = "/line_item/delete/{id}/batch/{bid}", method = RequestMethod.POST)
	public String confirmStockInLineItemDeleteAction(
			@RequestParam(value = "line_item_id", required = false) String lineItemId,
			@RequestParam(value = "batch_id", required = false) String batchId,
			RedirectAttributes redirectAttributes, ModelMap model) {

		Alert alert = (Alert) model.get("alert");

		StockInLine stockInLineItem = null;
		int stockInBatchId = 0;
		try {
			stockInLineItem = this.stockInManagerBo
					.getStockInLineItemDetailById(Integer.parseInt(lineItemId));
			StockIn stockInBatch = stockInLineItem.getStockInBatch();
			if (stockInBatch != null) {
				stockInBatchId = stockInBatch.getId();
			}

			this.stockInManagerBo.deleteStockInLineItem(stockInLineItem);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! Stock-In Line Item deleted successfully");
			return "redirect:/inventory/stock_in/line_item/" + stockInBatchId;
		} catch (NumberFormatException e) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource (Resource id:" + lineItemId + ")");
		} catch (RecordNotFoundException e) {
			alert.setAlert(redirectAttributes, Alert.ERROR,
					"Error! Invalid resource (Resource id:" + lineItemId + ")");
		} catch (InvalidUnitOfMeasureConfiguration e) {
			alert.setAlert(redirectAttributes, Alert.ERROR, e.getExceptionMsg());
		}
		return "redirect:/inventory/stock_in/line_item/delete/" + lineItemId
				+ "/batch/" + batchId;
	}
}
