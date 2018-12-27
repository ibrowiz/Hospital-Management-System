package org.calminfotech.inventory.daoInterface;

import java.util.List;

import org.calminfotech.error.custom.exception.InvalidOpeningStockBalanceException;
import org.calminfotech.inventory.models.PointStockCurrentBalance;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.system.models.UnitItem;

public interface InventoryDao {

	public List<GlobalUnitofMeasure> fetchGlobalItemUnitOfMeasure(
			int globalItemId);

	public UnitItem getUnitOfMeasureToDetails(int globalItemId,
			int currentUnitOfMeasureFrom);

	
	public int getGlobalItemCurrentBalance(int globalItemIdInt)throws InvalidOpeningStockBalanceException;

	public void updateGlobalItemCurrentBalance(int currentBalance,int globalItemId);

	public UnitItem getUnitOfMeasureFromDetails(
			int globalItemId, int unitOfMeasureToConvert);

	public int getGlobalItemUnitOfMeasureContainedUnit(int globalItemId,
			int unitOfMeasure);

	public void updatePointGlobalItemCurrentBalance(
			PointStockCurrentBalance pointStockCurrentBalance);

	public PointStockCurrentBalance getGlobalItemCurrentBalanceByPoint(
			int globalItem, int pointId) throws InvalidOpeningStockBalanceException;

	public List<PointStockCurrentBalance> getPointStockCurrentBalances(
			int currentPointId);

	public int getPointGlobalItemCurrentBalance(int globalItem,
			int currentPointId) throws InvalidOpeningStockBalanceException;

}
