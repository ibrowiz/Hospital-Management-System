package org.calminfotech.inventory.bo;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.calminfotech.error.custom.exception.InvalidStockLevelException;
import org.calminfotech.error.custom.exception.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.boInterface.InventoryManagerBo;
import org.calminfotech.inventory.boInterface.OrderManagerBo;
import org.calminfotech.inventory.forms.DateSearchForm;
import org.calminfotech.inventory.forms.OrderForm;
import org.calminfotech.inventory.models.Order;
import org.calminfotech.inventory.models.OrderLine;
import org.calminfotech.inventory.models.PointRequest;
import org.calminfotech.inventory.utils.UnitOfMeasureConverter;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderManagerImpl implements OrderManagerBo {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private UnitOfMeasureConverter unitOfMeasureConverter;

	@Autowired
	private InventoryManagerBo inventoryManagerdao;

	@Override
	public List<PointRequest> getOrders(DateSearchForm orderSearchForm) {
		return null;
	}

	@Override
	public Order saveOrder(OrderForm orderForm)
			throws InvalidStockLevelException,
			InvalidUnitOfMeasureConfiguration {
		return null;
	}

	@Override
	public Order processOrder(OrderForm orderForm)
			throws InvalidStockLevelException,
			InvalidUnitOfMeasureConfiguration {

		Collection<Map> orderLinesMapList = orderForm.getOptionalOrders();

		Date date = new Date();
		Order order = null;

		if (orderLinesMapList != null && !orderLinesMapList.isEmpty()) {

			Set<OrderLine> orderLines = new HashSet();
			OrderLine orderLine = null;
			StringBuilder msgBuffer = new StringBuilder();

			GlobalItem globalItem = null;
			GlobalUnitofMeasure globalUnitofMeasure = null;
			int unitOfMeasure = 0;
			int globalItemId = 0;
			int qtyOrderedInMeasure = 0;
			double totalPrice = 0;

			// used to cache global item current balance while checkin stock
			// level
			Map<Integer, Integer> checkedQtyMap = new HashMap();
			int currQtyChecked = 0;
			int qtyOrderedInUnit = 0;

			for (Map orderLineMap : orderLinesMapList) {

				unitOfMeasure = (Integer) orderLineMap.get("unitOfMeasure");
				globalItemId = Integer.parseInt((String) orderLineMap
						.get("globalItem"));
				qtyOrderedInMeasure = (Integer) orderLineMap.get("qty");

				qtyOrderedInUnit = this.unitOfMeasureConverter
						.convertUnitOfMeasureToUnitOptionA(globalItemId,
								unitOfMeasure, qtyOrderedInMeasure);

				if (checkedQtyMap.containsKey(globalItemId)) {
					currQtyChecked = checkedQtyMap.get(globalItemId);
					// increment qty ordered by qty checked to get actual qty
					// ordered since we are doin
					// incremental processing
					checkedQtyMap.put(globalItemId,
							qtyOrderedInUnit += currQtyChecked);
				} else {
					checkedQtyMap.put(globalItemId, qtyOrderedInUnit);
				}

				// we then check stock availablity
				if (this.isStockAvailable(globalItemId, unitOfMeasure,
						qtyOrderedInUnit, 0)) {
					// calculatePrice(globalItemId,
					// unitOfMeasure,qtyOrderedInMeasure);

				} else {
					msgBuffer.append("Invalid stock level:<br>");
				}
			}
			String msg = msgBuffer.toString();

			// pointRequest.setPointRequestLines(orderLines);
		}
		return order;
	}

	private boolean isStockAvailable(int globalItem, int unitOfMeasure,
			int qtyOrderedInUnit, int existingOrder)
			throws InvalidUnitOfMeasureConfiguration {
		// inventoryManagerImpl
		int currentBalance = 0;
		/*
		 * currentBalance = this.inventoryManagerdao
		 * .getPointGlobalItemCurrentBalance(globalItem);
		 */
		// currentBalance =
		// this.inventoryManagerdao.getGlobalItemCurrentBalance(globalItem);

		currentBalance += existingOrder;
		if (currentBalance >= qtyOrderedInUnit) {
			return true;
		}
		return false;
	}

	@Override
	public Order getOrderById(int id) throws RecordNotFoundException {
		return null;
	}

}
