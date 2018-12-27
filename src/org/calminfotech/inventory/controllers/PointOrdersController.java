package org.calminfotech.inventory.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.calminfotech.error.custom.exception.InvalidOrderException;
import org.calminfotech.error.custom.exception.InvalidStockLevelException;
import org.calminfotech.error.custom.exception.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.boInterface.OrderManagerBo;
import org.calminfotech.inventory.forms.DateSearchForm;
import org.calminfotech.inventory.forms.OrderForm;
import org.calminfotech.inventory.models.Order;
import org.calminfotech.inventory.models.PointRequest;
import org.calminfotech.inventory.utils.Text;
import org.calminfotech.inventory.utils.Utilities;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Alert;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/inventory/point/order")
public class PointOrdersController extends AbstractBaseController {

	@Autowired
	private GlobalItemList globalItemList;

	@Autowired
	private Utilities utilities;

	@Autowired
	private VisitWorkflowPointBo visitWorkflowPointBoImpl;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private OrderManagerBo orderManagerBo;

	@RequestMapping(value = { "/", "" })
	@Layout("layouts/datatable")
	public String indexAction(ModelMap model) {

		DateSearchForm orderSearchForm = new DateSearchForm();
		loadOrderSearchForm(orderSearchForm, model);
		return displayOrdersListPage(orderSearchForm, model);
	}

	private void loadOrderSearchForm(DateSearchForm orderSearchForm,
			ModelMap model) {
		if (orderSearchForm.getId() != 0
				&& !model.containsKey("orderSearchForm")) {
			/*
			 * try { } catch (RecordNotFoundException e) { }
			 */
		}
		// load vendors list
		// model.addAttribute("vendorsList", this.vendorsList.fetchAll());
		String dte = orderSearchForm.getDateOfRequest();
		// set todays date as default if there exist none
		if (dte == null || dte.isEmpty()) {
			orderSearchForm.setDateOfRequest(new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date()));
		}

	}

	private String displayOrdersListPage(DateSearchForm orderSearchForm,
			ModelMap model) {
		// TODO Auto-generated method stub

		List<PointRequest> pointRequests = this.orderManagerBo
				.getOrders(orderSearchForm);
		model.addAttribute("orderSearchForm", orderSearchForm);
		model.addAttribute("pointRequestsList", pointRequests);
		if (pointRequests == null) {
			this.alert(true, Alert.ERROR, Text.RECORD_NOT_FOUND + " !!", model);
		}
		return "/inventories/pharmacy/order/index";
	}

	@Layout(value = "layouts/datatable")
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String saveAction(
			@Valid @ModelAttribute("orderSearchForm") DateSearchForm orderSearchForm,
			BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {

		// if (!result.hasErrors())
		this.loadOrderSearchForm(orderSearchForm, model);
		return this.displayOrdersListPage(orderSearchForm, model);
		// }

	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String addAction(ModelMap model) {

		String id = null;
		OrderForm orderForm = this.getOrderForm(id);
		// try {
		this.loadOrderForm(orderForm, model);
		// } catch (Exception e) {
		// }
		return this.displayOrderFormPage(orderForm, model);
	}

	private void loadOrderForm(OrderForm orderForm, ModelMap model) {
		/*
		 * if (orderRequestForm.getId() != 0 &&
		 * !model.containsKey("orderRequestForm")) { }
		 */

		// load products list for the point current logged in user belongs
		// model.addAttribute("globalItemsList",
		// this.globalItemList.fetchAll());
		VisitWorkflowPoint visitWorkflowPoint = this.visitWorkflowPointBoImpl
				.getWorkflowPointById(this.userIdentity.getCurrentPointId());
		model.addAttribute("globalItemsList",
				visitWorkflowPoint.getGlobalItem());

		/*
		 * try { model.addAttribute("unitOfMeasuresList1",
		 * getGlobalItemUnitOfMeasureList(Integer
		 * .parseInt(orderRequestForm.getGlobalItem1()))); } catch
		 * (NumberFormatException e) {
		 * 
		 * } try { model.addAttribute("unitOfMeasuresList2",
		 * getGlobalItemUnitOfMeasureList(Integer
		 * .parseInt(orderRequestForm.getGlobalItem2()))); } catch
		 * (NumberFormatException e) {
		 * 
		 * } try { model.addAttribute("unitOfMeasuresList3",
		 * getGlobalItemUnitOfMeasureList(Integer
		 * .parseInt(orderRequestForm.getGlobalItem3()))); } catch
		 * (NumberFormatException e) {
		 * 
		 * }
		 */
		Collection optionalOrders = orderForm.getOptionalOrders();
		if (optionalOrders != null && !optionalOrders.isEmpty()) {
			Map request = null;
			for (Object obj : optionalOrders) {

				if (obj != null) {
					request = (Map) obj;
					if (request.containsKey("globalItem")
							&& request.get("globalItem") != null) {
						try {
							request.put("unitOfMeasuresList", this
									.getGlobalItemUnitOfMeasureList(Integer
											.parseInt((String) request
													.get("globalItem"))));
						} catch (NumberFormatException e) {

						}
					}
				}
			}
		}
	}

	private OrderForm getOrderForm(String id) {

		OrderForm orderForm = new OrderForm();
		if (id != null) {
			try {
				orderForm.setId(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				orderForm.setId(-1);
			}
		}
		return orderForm;
	}

	private String displayOrderFormPage(OrderForm orderForm, ModelMap model) {
		model.addAttribute("orderForm", orderForm);
		return "/inventories/pharmacy/order/add";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@Layout(value = "layouts/form_wizard_layout")
	public String saveAction(
			@Valid @ModelAttribute("orderForm") OrderForm orderForm,
			BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes, HttpServletRequest req) {

		Alert alert = (Alert) model.get("alert");
		Set validOrders = new HashSet();
		Collection orders = new ArrayList();
		/*
		 * this.validate(orderRequestForm.getGlobalItem1(),
		 * orderRequestForm.getQty1(), orderRequestForm.getUnitOfMeasure1(),
		 * result, utilities, 1, validOrders);
		 */
		boolean success = this.processOptionalOrders(orderForm, req);
		if (!result.hasErrors() && success) {

			try {

				// if optional orders list is not empty then we merge to
				// compulsory orders
				if (orderForm.getOptionalOrders() != null
						&& !orderForm.getOptionalOrders().isEmpty()) {
					orders.addAll(orderForm.getOptionalOrders());
				}

				if ((orders == null || (orders != null && orders.isEmpty()))) {
					throw new InvalidOrderException("No order selected");
				}
				orderForm.setOptionalOrders(orders);

				Order order = this.orderManagerBo.saveOrder(orderForm);
				alert.setAlert(redirectAttributes, Alert.SUCCESS,
						" Success! order has been submitted succesfully ! ");
				return "redirect:/inventory/pharmacy/order/order_success/"
						+ order.getId();
				// } catch (Exception e) {
				// alert.setAlert(redirectAttributes,
				// Alert.ERROR,Text.UNKNOWN_ERROR);

			} catch (InvalidOrderException e) {
				this.alert(true, Alert.ERROR, e.getExceptionMsg(), model);
			} catch (InvalidStockLevelException e) {
				this.alert(true, Alert.ERROR, e.getExceptionMsg(), model);
			} catch (InvalidUnitOfMeasureConfiguration e) {
				this.alert(true, Alert.ERROR, e.getExceptionMsg(), model);
			}
		}
		this.loadOrderForm(orderForm, model);
		return this.displayOrderFormPage(orderForm, model);
	}

	private void validate(String globalItem, String qty, String unitOfMeasure,
			BindingResult result, Utilities utilities, int index,
			Set<Integer> validOrders) {
		int validOrderCnt = 0;

		if (globalItem != null && !globalItem.isEmpty()) {

			if (qty != null && utilities.isInteger(qty)) {
				validOrderCnt++;
			} else {
				result.rejectValue("qty" + index, "error.orderRequestForm",
						Text.ERROR_QUANTITY_NOT_NUMBER);
			}
			if (unitOfMeasure != null && !unitOfMeasure.isEmpty()) {
				validOrderCnt++;
			} else {
				result.rejectValue("unitOfMeasure" + index,
						"error.orderRequestForm", Text.INVALID_UNIT_OF_MEASURE);
			}
			if (validOrderCnt >= 2) {
				validOrders.add(index);
			}

		} else {
			if ((qty != null && utilities.isInteger(qty))
					|| (unitOfMeasure != null && !unitOfMeasure.isEmpty())) {
				result.rejectValue("globalItem" + index,
						"error.orderRequestForm", Text.INVALID_PRODUCT);
			}
		}
	}

	/* method to process optional request from user if any */
	public boolean processOptionalOrders(OrderForm orderForm,
			HttpServletRequest req) {
		// throw new UnsupportedOperationException("Not yet implemented");
		Collection<Map> optionalRequests = new ArrayList();

		orderForm.setOptionalOrders(optionalRequests);
		Map request = null;
		Enumeration<String> parameters = req.getParameterNames();
		String currentParameter = null;
		int indx = -1;
		String currqty = null;
		String currUnitOfmeasure = null;
		boolean error = false;
		boolean storeData = false;
		// duplicate too
		while (parameters.hasMoreElements()) {
			currentParameter = parameters.nextElement();
			if (currentParameter.contains("globalItem")) {
				try {

					indx = Integer.parseInt(currentParameter
							.substring(("globalItem").length()));
					if (indx <= 3) {
						continue;
					}
					storeData = false;
					request = new HashMap();
					currqty = req.getParameter("qty" + indx);
					currUnitOfmeasure = req
							.getParameter("unitOfMeasure" + indx);
					request.put("prodErr", "");
					request.put("globalItem", "");
					request.put("qty", -1);
					request.put("unitOfMeasure", -1);
					request.put("unitOfMeasuresList", "");

					if (req.getParameter(currentParameter) != null) {
						// prepare to store data
						// we get the quantity associated wit prod
						if (req.getParameter(currentParameter) != null
								&& !req.getParameter(currentParameter)
										.isEmpty()) {
							storeData = true;
							if (this.utilities.isInteger(currqty)) {
							} else {
								error = true;
								// map.put("qty_error",
								// Statics.ERROR_QUANTITY_NOT_NUMBER);
							}

							if (currUnitOfmeasure != null
									&& !currUnitOfmeasure.isEmpty()) {
							} else {
								error = true;
								// map.put("qty_error",
								// Statics.ERROR_QUANTITY_NOT_NUMBER);
							}
						} else {
							if (this.utilities.isInteger(currqty)
									|| currUnitOfmeasure != null
									&& !currUnitOfmeasure.isEmpty()) {
								error = true;
								request.put("prodErr", Text.INVALID_PRODUCT);
								storeData = true;
							}
						}
					}
					if (storeData) {
						request.put("globalItem",
								req.getParameter(currentParameter));
						try {
							request.put("qty", Integer.parseInt(currqty));
						} catch (NumberFormatException nfe) {
							request.put("qty", -1);
						}

						try {
							request.put("unitOfMeasure",
									Integer.parseInt(currUnitOfmeasure));
						} catch (NumberFormatException nfe) {
							request.put("unitOfMeasure", -1);
						}
						request.put("indx", indx);

						optionalRequests.add(request);

					}
				} catch (IndexOutOfBoundsException e) {
					logger.info(e.getMessage());
				} catch (NumberFormatException e) {
					logger.info(e.getMessage());
				}
			}
		}
		if (error) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * @Layout(value = "layouts/form_wizard_layout")
	 * 
	 * @RequestMapping(value = "/process/{id}", method = RequestMethod.GET)
	 * public String processRequest(@PathVariable int id, ModelMap model) {
	 * 
	 * ProcessPointRequestForm processPointRequestForm = new
	 * ProcessPointRequestForm();
	 * loadProcessPointRequestForm(processPointRequestForm, model); return
	 * this.displayProcessPointRequestFormPage(id, processPointRequestForm,
	 * model); }
	 * 
	 * private void loadProcessPointRequestForm(
	 * 
	 * ProcessPointRequestForm processPointRequestForm, ModelMap model) {
	 * model.addAttribute("pointRequestStatusList",
	 * PointRequestStatus.values()); }
	 * 
	 * private String displayProcessPointRequestFormPage(int id,
	 * ProcessPointRequestForm processPointRequestForm, ModelMap model) { try {
	 * PointRequest pointRequest = this.orderManager .getPointRequestById(id);
	 * model.addAttribute("pointRequest", pointRequest);
	 * model.addAttribute("processPointRequestForm", processPointRequestForm);
	 * 
	 * 
	 * Map m = this.orderManager .getGlobalItemsQuantityAvailable(pointRequest
	 * .getPointRequestLines());
	 * 
	 * } catch (RecordNotFoundException e) { this.alert(true, Alert.ERROR,
	 * e.getExceptionMsg(), model); } return
	 * "/inventories/order_requests/process"; }
	 */
	@RequestMapping(value = "/order_success/{id}", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String requestSuccess(@PathVariable int id, ModelMap model) {
		try {
			Order order = this.orderManagerBo.getOrderById(id);
			model.addAttribute("order", order);
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.ERROR, e.getExceptionMsg(), model);
		}
		return "/inventories/pharmacy/order/order_success";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String deleteRequest(@PathVariable int id, ModelMap model) {

		try {
			Order order = this.orderManagerBo.getOrderById(id);
			model.addAttribute("order", order);
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.ERROR, e.getExceptionMsg(), model);
		}
		return "/inventories/pharmacy/order/order_success";
	}

	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	@Layout(value = "layouts/form_wizard_layout")
	public String viewPointrequestByUser(@PathVariable int id, ModelMap model) {
		try {
			Order order = this.orderManagerBo.getOrderById(id);
			model.addAttribute("order", order);
		} catch (RecordNotFoundException e) {
			this.alert(true, Alert.ERROR, e.getExceptionMsg(), model);
		}
		return "/inventories/pharmacy/order/view";
	}
}
