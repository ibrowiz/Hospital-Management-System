package org.calminfotech.inventory.daoInterface;

import java.util.List;

import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.models.StockOpeningBalance;

public interface StockOpeningBalanceDao {

	// stock opening
	public void saveStockOpeningBalance(StockOpeningBalance stockOpeningBalance);

	public List<StockOpeningBalance> getStockOpeningBalances()
			throws RecordNotFoundException;

	public StockOpeningBalance getStockOpeningBalanceDetailById(int id)
			throws RecordNotFoundException;

	public void updateStockOpeningBalance(
			StockOpeningBalance stockOpeningBalance);

	public void delete(StockOpeningBalance stockOpeningBalance);

	public boolean isExistStockOpeningBalanceGlobalItem(String globalItemID);

	public boolean isExistStockOpeningBalanceGlobalItem(int newGlobalItemIdInt,
			int newUnitofMeasureInt);
}
