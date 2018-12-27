package org.calminfotech.inventory.boInterface;

import java.util.List;

import org.calminfotech.error.custom.exception.InvalidOpeningStockBalanceException;
import org.calminfotech.inventory.models.PointStockCurrentBalance;
import org.calminfotech.system.models.GlobalUnitofMeasure;

public interface InventoryManagerBo {
	
	public List<GlobalUnitofMeasure> fetchGlobalItemUnitOfMeasure(int globalItemId);

	public int getGlobalItemCurrentBalance(int globalItemId)throws InvalidOpeningStockBalanceException;

	public void updateGlobalItemCurrentBalance(double newValue, int oldValue,
			int currentBalance, int globalItemId);

	public PointStockCurrentBalance getGlobalItemCurrentBalanceByPoint(int globalItem,int pointId) throws InvalidOpeningStockBalanceException;

	void updatePointGlobalItemCurrentBalance(double newValue, int oldValue,
			PointStockCurrentBalance pointStockCurrentBalance);

	public List<PointStockCurrentBalance> getPointStockCurrentBalances();

	public int getPointGlobalItemCurrentBalance(int globalItem,
			int currentPointId) throws InvalidOpeningStockBalanceException;

	public double getGlobalItemUnitPrice(int globalItemId, int unitOfMeasure);
}
