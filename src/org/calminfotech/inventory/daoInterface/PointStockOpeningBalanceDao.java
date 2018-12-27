package org.calminfotech.inventory.daoInterface;

import java.util.List;

import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.models.PointStockOpeningBalance;
import org.calminfotech.inventory.models.StockOpeningBalance;

public interface PointStockOpeningBalanceDao {

	// stock opening
	public void savePointStockOpeningBalance(
			PointStockOpeningBalance stockOpeningBalance);

	public List<PointStockOpeningBalance> getStockOpeningBalances(int pointId)
			throws RecordNotFoundException;

	public StockOpeningBalance getStockOpeningBalanceDetailById(int id)
			throws RecordNotFoundException;

	public void updateStockOpeningBalance(
			StockOpeningBalance stockOpeningBalance);

	public void delete(StockOpeningBalance stockOpeningBalance);

	public boolean isExistPointStockOpeningBalanceGlobalItem(int globalItemId,
			int unitofMeasure, int currentPointId);
}
