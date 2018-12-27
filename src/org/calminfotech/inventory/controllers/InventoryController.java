package org.calminfotech.inventory.controllers;

import java.util.List;

import org.calminfotech.inventory.models.PointStockCurrentBalance;
import org.calminfotech.inventory.utils.Text;
import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.utils.Alert;
import org.calminfotech.utils.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryController extends AbstractBaseController {

	@Autowired
	private GlobalItemBo globalItemBo;

	
	@Layout("layouts/datatable")
	@RequestMapping(value = "/stock", method = RequestMethod.GET)
	public String fetchStock(ModelMap model) {

		return displayStocksListPage(model);
	}

	private String displayStocksListPage(ModelMap model) {
		List<GlobalItem> globalItems = null;
		globalItems = this.globalItemBo.fetchAll();
		if (globalItems == null || globalItems.isEmpty()) {
			this.alert(true, Alert.ERROR, Text.RECORD_NOT_FOUND, model);
		}
		model.addAttribute("globalItemsStockList", globalItems);
		return "/inventories/stock";
	}
	
	@Layout("layouts/datatable")
	@RequestMapping(value = "point/stock", method = RequestMethod.GET)
	public String fetchPointStock(ModelMap model) {

		return displayPointStocksListPage(model);
	}

	private String displayPointStocksListPage(ModelMap model) {
		List<PointStockCurrentBalance> pointStockCurentBalancesList = null;
		pointStockCurentBalancesList = this.inventoriesManagerBo.getPointStockCurrentBalances();
		if (pointStockCurentBalancesList == null || pointStockCurentBalancesList.isEmpty()) {
			this.alert(true, Alert.ERROR, Text.RECORD_NOT_FOUND, model);
		}
		model.addAttribute("pointStockCurentBalancesList",pointStockCurentBalancesList);
		return "/inventories/point/stock";
	}
}
