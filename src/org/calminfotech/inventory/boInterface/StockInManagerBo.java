package org.calminfotech.inventory.boInterface;

import java.util.List;

import org.calminfotech.error.custom.exception.InvalidOpeningStockBalanceException;
import org.calminfotech.error.custom.exception.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.forms.StockInForm;
import org.calminfotech.inventory.forms.StockInLineForm;
import org.calminfotech.inventory.models.StockIn;
import org.calminfotech.inventory.models.StockInLine;

public interface StockInManagerBo {

	public List<StockIn> getStockInBatchesList(String searchVal)
			throws RecordNotFoundException;

	public StockIn getStockInBatchDetailsById(int id)
			throws RecordNotFoundException;

	public StockIn saveStockInBatch(StockInForm stockInBatchForm);

	public StockIn editStockInBatch(StockInForm stockInBatchForm)
			throws RecordNotFoundException;

	public StockIn getBatchSupplyDetailsView(String batchId)
			throws RecordNotFoundException;

	public void delete(StockIn stockInBatch)
			throws InvalidUnitOfMeasureConfiguration;

	// line supply

	public List<StockInLine> getStockInLineItems(int batchId)
			throws RecordNotFoundException;

	public StockInLine saveStockInLineItem(StockInLineForm stockInlineItemForm)
			throws InvalidUnitOfMeasureConfiguration,
			InvalidOpeningStockBalanceException;

	public StockInLine getStockInLineItemDetailById(int id)
			throws RecordNotFoundException;

	public StockInLine editStockInLineItem(StockInLineForm stockInLineItemForm)
			throws InvalidUnitOfMeasureConfiguration, RecordNotFoundException,
			InvalidOpeningStockBalanceException;

	public void deleteStockInLineItem(StockInLine stockInLineItem)
			throws InvalidUnitOfMeasureConfiguration;
}
