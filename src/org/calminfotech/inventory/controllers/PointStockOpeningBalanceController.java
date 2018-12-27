package org.calminfotech.inventory.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.error.custom.exception.DuplicateDataException;
import org.calminfotech.error.custom.exception.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.boInterface.PointStockOpeningBalanceManagerBo;
import org.calminfotech.inventory.forms.StockOpeningBalanceForm;
import org.calminfotech.inventory.models.PointStockOpeningBalance;
import org.calminfotech.inventory.models.StockOpeningBalance;
import org.calminfotech.inventory.utils.Text;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.CustomValidator;
import org.calminfotech.utils.GlobalItemList;
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
@RequestMapping(value = "/inventory/point/stock_opening_balance")
public class PointStockOpeningBalanceController extends AbstractBaseController {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private GlobalItemList globalItemList;

	@Autowired
	private GlobalItemBo globalItemBo;

	@Autowired
	private PointStockOpeningBalanceManagerBo stockOpeningBalanceManagerBo;

	@Layout("layouts/datatable")
	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String fetchOpeningBalances(ModelMap model) {

		return displayStockOpeningBalancesListPage(model);
	}

	private String displayStockOpeningBalancesListPage(ModelMap model) {
		// TODO Auto-generated method stub
		List<PointStockOpeningBalance> stockOpeningBalances = null;
		try {
			stockOpeningBalances = this.stockOpeningBalanceManagerBo
					.getStockOpeningBalances(4);
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.ERROR, Text.RECORD_NOT_FOUND, model);
		}
		model.addAttribute("stockOpeningBalanceList", stockOpeningBalances);
		return "/inventories/pharmacy/stock_opening_balance/index";
	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	// @Layout("layouts/datatable")
	public String openingBalanceForm(ModelMap model) {

		String id = null;
		StockOpeningBalanceForm stockOpeningBalanceForm = this
				.getStockOpeningBalanceForm(id);
		// try {
		this.loadStockOpeningBalanceForm(stockOpeningBalanceForm, model);
		// } catch (Exception e) {
		// }
		return this.displayStockOpeningBalanceFormPage(stockOpeningBalanceForm,
				model);
	}

	private StockOpeningBalanceForm getStockOpeningBalanceForm(String id) {
		StockOpeningBalanceForm stockOpeningBalanceForm = new StockOpeningBalanceForm();
		if (id != null) {
			try {
				stockOpeningBalanceForm.setId(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				// stockOpeningBalanceForm.setId(0);
			}
		}
		return stockOpeningBalanceForm;
	}

	private void loadStockOpeningBalanceForm(
			StockOpeningBalanceForm stockOpeningBalanceForm, ModelMap model) {

		// if BatchSupply id not empty then we are in edit mode so we populate
		// form
		if (stockOpeningBalanceForm.getId() != 0
				&& !model.containsKey("stockOpeningBalanceForm")) {
			// get BatchSupply details and populate form
			try {
				int id = stockOpeningBalanceForm.getId();
				StockOpeningBalance stockOpeningBalance = this.stockOpeningBalanceManagerBo
						.getStockOpeningBalanceDetailById(id);

				// set opening balance
				stockOpeningBalanceForm.setOpeningBalance(String
						.valueOf(stockOpeningBalance.getOpeningBalance()));

				// set global item
				GlobalItem globalItem = stockOpeningBalance.getGlobalItem();
				if (globalItem != null) {
					stockOpeningBalanceForm.setGlobalItem(String
							.valueOf(globalItem.getId()));
				}
				// set unit of measure
				GlobalUnitofMeasure globalUnitofMeasure = stockOpeningBalance
						.getGlobalUnitofMeasure();
				if (globalUnitofMeasure != null) {
					stockOpeningBalanceForm.setUnitOfMeasure(String
							.valueOf(globalUnitofMeasure.getId()));
				}

				// set date added
				stockOpeningBalanceForm.setDateAdded(stockOpeningBalance
						.getDateAdded());

			} catch (RecordNotFoundException e) {

			}
		}

		// load vendors list
		model.addAttribute("globalItemsList", this.globalItemList.fetchAll());

		String globalItem = stockOpeningBalanceForm.getGlobalItem();
		if (globalItem != null && !globalItem.isEmpty()) {
			try {
				model.addAttribute("unitOfMeasuresList",
						getGlobalItemUnitOfMeasureList(Integer
								.parseInt(globalItem)));
			} catch (NumberFormatException e) {

			}
		}

		String dateAdded = stockOpeningBalanceForm.getDateAdded();
		// set todays date as default if there exist none
		if (dateAdded == null || dateAdded.isEmpty()) {
			stockOpeningBalanceForm.setDateAdded(new SimpleDateFormat(
					"yyyy-MM-dd").format(new Date()));
		}

	}

	private String displayStockOpeningBalanceFormPage(
			StockOpeningBalanceForm stockOpeningBalanceForm, ModelMap model) {
		model.addAttribute("stockOpeningBalanceForm", stockOpeningBalanceForm);
		model.addAttribute("pageRegister", true);
		return this.displayStockOpeningBalancesListPage(model);

	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String saveOpeningBal(
			@Valid @ModelAttribute("stockOpeningBalanceForm") StockOpeningBalanceForm stockOpeningBalanceForm,
			BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes, HttpServletRequest req) {

		Alert alert = (Alert) model.get("alert");
		CustomValidator validator = new CustomValidator();
		String date = stockOpeningBalanceForm.getDateAdded();
		if (!date.isEmpty() && !validator.validateDate(date, "yyyy-mm-dd")) {
			result.rejectValue("dateAdded", "error.stockOpeningBalanceForm",
					"Date must be in specified format");
		}

		if (!result.hasErrors()) {
			try {
				this.stockOpeningBalanceManagerBo
						.savePointStockOpeningBalance(stockOpeningBalanceForm);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						" Success! New Stock Opening Balance Succesfully added! ");
				return "redirect:/inventory/stock_opening_balance";
			} catch (DuplicateDataException e) {
				// this.alert(true, Alert.ERROR, e.getExceptionMsg(), model);
				result.rejectValue("globalItem",
						"error.stockOpeningBalanceForm", e.getExceptionMsg());
			} catch (InvalidUnitOfMeasureConfiguration e) {
				this.modalAlert(true, Alert.ERROR, e.getExceptionMsg(), model);
			}
		}
		this.loadStockOpeningBalanceForm(stockOpeningBalanceForm, model);
		return this.displayStockOpeningBalanceFormPage(stockOpeningBalanceForm,
				model);

	}

	public GlobalItemList getGlobalItemList() {
		return globalItemList;
	}

	public void setGlobalItemList(GlobalItemList globalItemList) {
		this.globalItemList = globalItemList;
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editStockOpeningBalance(@PathVariable String id,
			ModelMap model) {

		StockOpeningBalanceForm stockOpeningBalanceForm = this
				.getStockOpeningBalanceForm(id);

		this.loadStockOpeningBalanceForm(stockOpeningBalanceForm, model);
		model.addAttribute("pageEdit", true);

		return this.displayStockOpeningBalanceFormPage(stockOpeningBalanceForm,
				model);
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	@Layout(value = "layouts/datatable")
	public String editVendor(
			@Valid @ModelAttribute("stockOpeningBalanceForm") StockOpeningBalanceForm stockOpeningBalanceForm,
			BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {

		Alert alert = (Alert) model.get("alert");
		CustomValidator validator = new CustomValidator();
		String date = stockOpeningBalanceForm.getDateAdded();
		if (!date.isEmpty() && !validator.validateDate(date, "yyyy-mm-dd")) {
			result.rejectValue("dateAdded", "error.stockOpeningBalanceForm",
					Text.INVALID_DATE_FORMAT);
		}
		if (!result.hasErrors()) {

			try {
				StockOpeningBalance stockOpeningBalance = this.stockOpeningBalanceManagerBo
						.editStockOpeningBalance(stockOpeningBalanceForm);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						" Success! Stock Opening Balance updated succesfully ! ");
				return "redirect:/inventory/stock_opening_balance/view/"
						+ stockOpeningBalance.getId();
			} catch (DuplicateDataException e) {
				// this.alert(true, Alert.ERROR, e.getExceptionMsg(), model);
				result.rejectValue("globalItem",
						"error.stockOpeningBalanceForm", e.getExceptionMsg());
			} catch (RecordNotFoundException e) {
				this.alert(true, Alert.ERROR, e.getExceptionMsg(), model);
			} catch (InvalidUnitOfMeasureConfiguration e) {
				this.modalAlert(true, Alert.ERROR, e.getExceptionMsg(), model);
			}
		}

		this.loadStockOpeningBalanceForm(stockOpeningBalanceForm, model);
		return this.displayStockOpeningBalanceFormPage(stockOpeningBalanceForm,
				model);
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String viewVendor(@PathVariable String id, ModelMap model) {

		return displayStockOpeningBalanceDetailPage(id, model);

	}

	private String displayStockOpeningBalanceDetailPage(String id,
			ModelMap model) {
		// TODO Auto-generated method stub
		try {
			StockOpeningBalance stockOpeningBalance = this.stockOpeningBalanceManagerBo
					.getStockOpeningBalanceDetailById(Integer.parseInt(id));
			model.addAttribute("stockOpeningBalance", stockOpeningBalance);
		} catch (NumberFormatException e) {
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.ERROR, e.getExceptionMsg(), model);
		}

		model.addAttribute("pageView", true);
		return "/inventories/stock_opening_balance/view";

	}

	// stock opening balance delete

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@Layout(value = "layouts/datatable")
	public String deleteVendor(@PathVariable int id,
			RedirectAttributes redirectAttributes, ModelMap model) {

		try {

			StockOpeningBalance stockOpeningBalance = this.stockOpeningBalanceManagerBo
					.getStockOpeningBalanceDetailById(id);
			model.addAttribute("stockOpeningBalance", stockOpeningBalance);

		} catch (RecordNotFoundException e) {
			this.modalAlert(true, Alert.ERROR, e.getExceptionMsg(), model);
		}
		model.addAttribute("pageDelete", true);
		return this.displayStockOpeningBalancesListPage(model);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String confirmDeleteAction(
			@RequestParam(value = "id", required = false) int id,
			RedirectAttributes redirectAttributes, ModelMap model) {

		Alert alert = (Alert) model.get("alert");

		try {
			StockOpeningBalance stockOpeningBalance = this.stockOpeningBalanceManagerBo
					.getStockOpeningBalanceDetailById(id);

			this.stockOpeningBalanceManagerBo.delete(stockOpeningBalance);
			alert.setAlert(redirectAttributes, Alert.SUCCESS,
					"Success! opening balance deleted successfully");
			return "redirect:/inventory/stock_opening_balance";

		} catch (RecordNotFoundException e) {
		} catch (InvalidUnitOfMeasureConfiguration e) {
			alert.setAlert(redirectAttributes, Alert.ERROR, e.getExceptionMsg());
			return "redirect:/inventory/stock_opening_balance/delete/" + id;
		}
		alert.setAlert(redirectAttributes, Alert.ERROR,
				"Error! Invalid resource (Resource id:" + id + ")");
		return "redirect:/inventory/stock_opening_balance";
	}

	@Layout("layouts/datatable")
	@RequestMapping(value = "/stock", method = RequestMethod.GET)
	public String fetchStock(ModelMap model) {

		return displayStocksListPage(model);
	}

	private String displayStocksListPage(ModelMap model) {
		List<GlobalItem> globalItems = null;
		globalItems = this.globalItemBo.fetchAll();
		model.addAttribute("globalItemsStockList", globalItems);
		return "/inventories/stock_opening_balance/stock";
	}
}
