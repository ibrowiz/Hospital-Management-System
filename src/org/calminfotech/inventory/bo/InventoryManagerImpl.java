package org.calminfotech.inventory.bo;

import java.util.List;

import org.calminfotech.error.custom.exception.InvalidOpeningStockBalanceException;
import org.calminfotech.inventory.boInterface.InventoryManagerBo;
import org.calminfotech.inventory.daoInterface.InventoryDao;
import org.calminfotech.inventory.daoInterface.VendorDao;
import org.calminfotech.inventory.models.PointStockCurrentBalance;
import org.calminfotech.inventory.utils.UnitOfMeasureConverter;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryManagerImpl implements InventoryManagerBo {

	@Autowired
	private InventoryDao inventoryDao;

	@Autowired
	private VendorDao vendorDao;
	
	@Autowired
	private CodeGenerator codeGenerator;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private UnitOfMeasureConverter unitOfMeasureConverter;

	public void updateGlobalItemCurrentBalance(double newValue, int oldValue,
			int currentBalance, int globalItemId) {
		double currBalIncrementVal = 0;
		/*
		 * wen old val is 0 then this is new instance we simply increase current
		 * balance with new value
		 */
		if (oldValue == 0) {
			currBalIncrementVal = newValue;
		} else {
			double percentageChange = ((newValue - oldValue) / oldValue) * 100;
			currBalIncrementVal = (percentageChange / 100) * oldValue;
		}
		// System.out.print(currBalIncrementVal + "/in-updt/" + currentBalance);
		currentBalance += currBalIncrementVal;
		this.inventoryDao.updateGlobalItemCurrentBalance(currentBalance,
				globalItemId);

	}

	@Override
	public void updatePointGlobalItemCurrentBalance(double newValue,
			int oldValue, PointStockCurrentBalance pointStockCurrentBalance) {

		if (pointStockCurrentBalance != null) {
			double currBalIncrementVal = 0;
			int currentBalance = pointStockCurrentBalance.getCurrentBalance();
			/*
			 * wen old val is 0 then this is new instance we simply increase
			 * current balance with new value
			 */
			if (oldValue == 0) {
				currBalIncrementVal = newValue;
			} else {
				double percentageChange = ((newValue - oldValue) / oldValue) * 100;
				currBalIncrementVal = (percentageChange / 100) * oldValue;
			}
			// System.out.print(currBalIncrementVal + "/in-updt/" +
			// currentBalance);
			currentBalance += currBalIncrementVal;

			pointStockCurrentBalance.setCurrentBalance(currentBalance);

			this.inventoryDao
					.updatePointGlobalItemCurrentBalance(pointStockCurrentBalance);
		}

	}

	@Override
	public List<GlobalUnitofMeasure> fetchGlobalItemUnitOfMeasure(
			int globalItemId) {
		// TODO Auto-generated method stub
		return this.inventoryDao.fetchGlobalItemUnitOfMeasure(globalItemId);
	}

	@Override
	public int getGlobalItemCurrentBalance(int globalItemId)
			throws InvalidOpeningStockBalanceException {
		return this.inventoryDao.getGlobalItemCurrentBalance(globalItemId);
	}

	@Override
	public PointStockCurrentBalance getGlobalItemCurrentBalanceByPoint(int globalItem,int pointId) throws InvalidOpeningStockBalanceException{

		return this.inventoryDao.getGlobalItemCurrentBalanceByPoint(globalItem, pointId);

	}

	@Override
	public List<PointStockCurrentBalance> getPointStockCurrentBalances() {
		
		return this.inventoryDao.getPointStockCurrentBalances(this.userIdentity.getCurrentPointId());
	}

	@Override
     public int getPointGlobalItemCurrentBalance(int globalItem,
			int currentPointId) throws InvalidOpeningStockBalanceException {
		// TODO Auto-generated method stub
		return this.inventoryDao.getPointGlobalItemCurrentBalance(globalItem, currentPointId);
	}

	@Override
	public double getGlobalItemUnitPrice(int globalItemId, int unitOfMeasure) {
		// TODO Auto-generated method stub
		return 500;
	}

}
