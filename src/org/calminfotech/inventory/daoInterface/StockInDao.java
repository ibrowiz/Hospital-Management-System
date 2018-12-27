package org.calminfotech.inventory.daoInterface;

import java.util.List;

import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.models.StockIn;
import org.calminfotech.inventory.models.StockInLine;

public interface StockInDao {

	public void saveStockInBatch(StockIn stockInBatch);

	public StockIn getStockInBatchDetailsView(String batchId)
			throws RecordNotFoundException;

	public List<StockIn> getStockInBatchesList(String searchVal);

	public StockIn getStockInBatchDetailsById(int id)
			throws RecordNotFoundException;

	public void editStockInBatch(StockIn stockInBatch);

	public void delete(StockIn stockInBatch);

	// line supply
	public void saveStockInLineItem(StockInLine stockInLineItem);

	public List<StockInLine> getStockInLineItems(int batchId);
	
	public StockInLine getStockInLineItemDetailById(int id)throws RecordNotFoundException ;

	public void editStockInLineItem(StockInLine stockInLineItem);

	public void deleteStockInLineItem(StockInLine stockInLineItem);
}
