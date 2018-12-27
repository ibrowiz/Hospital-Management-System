package org.calminfotech.inventory.boInterface;

import java.util.List;

import org.calminfotech.error.custom.exception.InvalidStockLevelException;
import org.calminfotech.error.custom.exception.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.forms.DateSearchForm;
import org.calminfotech.inventory.forms.OrderForm;
import org.calminfotech.inventory.models.Order;
import org.calminfotech.inventory.models.PointRequest;

public interface OrderManagerBo {

	public List<PointRequest> getOrders(DateSearchForm orderSearchForm);

	public Order saveOrder(OrderForm orderForm)throws InvalidStockLevelException,
	InvalidUnitOfMeasureConfiguration;

	public Order getOrderById(int id)throws RecordNotFoundException;
	
	public Order processOrder(OrderForm orderForm)throws InvalidStockLevelException,InvalidUnitOfMeasureConfiguration;
}
