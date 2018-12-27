package org.calminfotech.inventory.bo;

import java.util.Date;
import java.util.List;

import org.calminfotech.error.custom.exception.DuplicateDataException;
import org.calminfotech.error.custom.exception.InvalidOpeningStockBalanceException;
import org.calminfotech.error.custom.exception.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.boInterface.InventoryManagerBo;
import org.calminfotech.inventory.boInterface.StockOpeningBalanceManagerBo;
import org.calminfotech.inventory.daoInterface.StockOpeningBalanceDao;
import org.calminfotech.inventory.daoInterface.VendorDao;
import org.calminfotech.inventory.forms.StockOpeningBalanceForm;
import org.calminfotech.inventory.models.StockOpeningBalance;
import org.calminfotech.inventory.utils.UnitOfMeasureConverter;
import org.calminfotech.inventory.utils.Utilities;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockOpeningBalanceManagerImpl implements StockOpeningBalanceManagerBo {

	@Autowired
	private InventoryManagerBo inventoryManagerBo;
	
	@Autowired
	private StockOpeningBalanceDao StockOpeningBalanceDao;

	@Autowired
	private VendorDao vendorDao;

	@Autowired
	private Utilities utilities;

	@Autowired
	private CodeGenerator codeGenerator;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private UnitOfMeasureConverter unitOfMeasureConverter;


	

	// stock opening bal
	@Override
	public List<StockOpeningBalance> getStockOpeningBalances()
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.StockOpeningBalanceDao.getStockOpeningBalances();
	}

	@Override
	public StockOpeningBalance getStockOpeningBalanceDetailById(int id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub

		return this.StockOpeningBalanceDao.getStockOpeningBalanceDetailById(id);
	}

	@Transactional
	@Override
	public void saveStockOpeningBalance(
			StockOpeningBalanceForm stockOpeningBalanceForm)
			throws DuplicateDataException, InvalidUnitOfMeasureConfiguration {
		// TODO Auto-generated method stub
		// check if global item stock opening balance has not yet been set
		// if already set then duplicate data exception is thrown else continue

		// get object by natural id to
		StockOpeningBalance stockOpeningBalance = null;

		try {
			int globalItemId = Integer.parseInt(stockOpeningBalanceForm
					.getGlobalItem());
			int unitofMeasure = Integer.parseInt(stockOpeningBalanceForm
					.getUnitOfMeasure());

			if (this.StockOpeningBalanceDao.isExistStockOpeningBalanceGlobalItem(
					globalItemId, unitofMeasure)) {
				throw new DuplicateDataException(
						"Opening balance has already been registered for this Global item");
			}

			int openingBalance = 0;

			try {
				openingBalance = Integer.parseInt(stockOpeningBalanceForm
						.getOpeningBalance());
			} catch (NumberFormatException e) {

			}
			/*int newQtyInUnits = this.unitOfMeasureConverter
					.convertUnitOfMeasureToUnitOptionB(globalItemId, unitofMeasure,
							openingBalance, AppConfig.UNIT_OF_MEASURE_UNIT);*/
			int newQtyInUnits = this.unitOfMeasureConverter
					.convertUnitOfMeasureToUnitOptionA(globalItemId, unitofMeasure,
							openingBalance);

			GlobalUnitofMeasure globalUnitofMeasure = new GlobalUnitofMeasure();
			GlobalItem globalItem = new GlobalItem();

			globalUnitofMeasure.setId(unitofMeasure);
			globalItem.setId(globalItemId);

			stockOpeningBalance = new StockOpeningBalance();

			stockOpeningBalance.setGlobalUnitofMeasure(globalUnitofMeasure);
			stockOpeningBalance.setGlobalItem(globalItem);
			stockOpeningBalance.setCreatedBy(this.userIdentity.getUsername());
			stockOpeningBalance.setCreateDate(new Date());
			stockOpeningBalance.setOpeningBalance(openingBalance);
			stockOpeningBalance.setDateAdded(stockOpeningBalanceForm
					.getDateAdded());

			this.StockOpeningBalanceDao.saveStockOpeningBalance(stockOpeningBalance);

			// update current balance:newQtyInUnits is new value, old value is
			// zero;
			int currentBalance = 0;
			try {
				currentBalance = this.inventoryManagerBo
						.getGlobalItemCurrentBalance(globalItemId);
			} catch (InvalidOpeningStockBalanceException e) {
			}

			this.inventoryManagerBo.updateGlobalItemCurrentBalance(newQtyInUnits, 0,
					currentBalance, globalItemId);
		} catch (NumberFormatException e) {

		}
	}

	@Transactional
	@Override
	public StockOpeningBalance editStockOpeningBalance(
			StockOpeningBalanceForm stockOpeningBalanceForm)
			throws DuplicateDataException, RecordNotFoundException,
			InvalidUnitOfMeasureConfiguration {

		// check if global item stock opening balance and corresponding unit of
		// measure has not yet been set
		// if already set then duplicate data exception is thrown else continue
		StockOpeningBalance stockOpeningBalance = null;

		try {
			stockOpeningBalance = this
					.getStockOpeningBalanceDetailById(stockOpeningBalanceForm
							.getId());

			int newGlobalItem = Integer.parseInt(stockOpeningBalanceForm
					.getGlobalItem());
			int newUnitofMeasure = Integer.parseInt(stockOpeningBalanceForm
					.getUnitOfMeasure());

			int currGlobalItem = 0;
			int currUnitOfMeasure = 0;

			GlobalItem g = stockOpeningBalance.getGlobalItem();
			if (g != null) {
				currGlobalItem = g.getId();
			}
			GlobalUnitofMeasure u = stockOpeningBalance
					.getGlobalUnitofMeasure();
			if (u != null) {
				currUnitOfMeasure = u.getId();
			}

			if (currGlobalItem != newGlobalItem
					|| currUnitOfMeasure != newUnitofMeasure) {
				if (this.StockOpeningBalanceDao.isExistStockOpeningBalanceGlobalItem(
						newGlobalItem, newUnitofMeasure)) {
					throw new DuplicateDataException(
							"Opening balance has already been registered for this Global item");
				}
			}

			// check if product has changed, if yes update both products else
			// update only edited product
			int newQtyInUnits;
			int currQtyInUnits;
			int currentBalance;
			int openingBalance = 0;

			try {
				openingBalance = Integer.parseInt(stockOpeningBalanceForm
						.getOpeningBalance());
			} catch (NumberFormatException e) {
			}

			if (currGlobalItem != newGlobalItem) {

				// update current product
				newQtyInUnits = 0;
				/*currQtyInUnits = this.unitOfMeasureConverter
						.convertUnitOfMeasureToUnitOptionB(currGlobalItem,
								currUnitOfMeasure,
								stockOpeningBalance.getOpeningBalance(),
								AppConfig.UNIT_OF_MEASURE_UNIT);*/
				currQtyInUnits = this.unitOfMeasureConverter
						.convertUnitOfMeasureToUnitOptionA(currGlobalItem,
								currUnitOfMeasure,
								stockOpeningBalance.getOpeningBalance());

				currentBalance = this.getCurrentBalance(currGlobalItem);

				this.inventoryManagerBo.updateGlobalItemCurrentBalance(newQtyInUnits,
						currQtyInUnits, currentBalance, currGlobalItem);

				// update new product
				// calculate new qty in units entered by user
				/*newQtyInUnits = this.unitOfMeasureConverter
						.convertUnitOfMeasureToUnitOptionB(newGlobalItem,
								newUnitofMeasure, openingBalance,
								AppConfig.UNIT_OF_MEASURE_UNIT);*/
				
				newQtyInUnits = this.unitOfMeasureConverter
						.convertUnitOfMeasureToUnitOptionA(newGlobalItem,
								newUnitofMeasure, openingBalance);
				
				currQtyInUnits = 0;
				currentBalance = this.getCurrentBalance(newGlobalItem);

				this.inventoryManagerBo.updateGlobalItemCurrentBalance(newQtyInUnits,
						currQtyInUnits, currentBalance, newGlobalItem);

			} else {

				// calculate new qty in units entered by user
				/*newQtyInUnits = this.unitOfMeasureConverter
						.convertUnitOfMeasureToUnitOptionB(newGlobalItem,
								newUnitofMeasure, openingBalance,
								AppConfig.UNIT_OF_MEASURE_UNIT);*/
				newQtyInUnits = this.unitOfMeasureConverter
						.convertUnitOfMeasureToUnitOptionA(newGlobalItem,
								newUnitofMeasure, openingBalance);
				/*
				 * calculate current qty in units already in dbase b4 reseting
				 * stock opening balance model to reflect new values
				 */
				/*currQtyInUnits = this.unitOfMeasureConverter
						.convertUnitOfMeasureToUnitOptionB(newGlobalItem,
								currUnitOfMeasure,
								stockOpeningBalance.getOpeningBalance(),
								AppConfig.UNIT_OF_MEASURE_UNIT);*/
				currQtyInUnits = this.unitOfMeasureConverter
						.convertUnitOfMeasureToUnitOptionA(newGlobalItem,
								currUnitOfMeasure,
								stockOpeningBalance.getOpeningBalance());

				// update current balance b4 table: newQtyInUnits is new value
				// and old value is current value in store;
				currentBalance = this.getCurrentBalance(newGlobalItem);
				this.inventoryManagerBo.updateGlobalItemCurrentBalance(newQtyInUnits,
						currQtyInUnits, currentBalance, newGlobalItem);

			}

			// update table now
			if (currUnitOfMeasure != newUnitofMeasure) {
				u = new GlobalUnitofMeasure();
			}

			if (currGlobalItem != newGlobalItem) {
				g = new GlobalItem();
			}

			g.setId(newGlobalItem);
			u.setId(newUnitofMeasure);

			stockOpeningBalance.setId(stockOpeningBalanceForm.getId());
			stockOpeningBalance.setGlobalItem(g);
			stockOpeningBalance.setGlobalUnitofMeasure(u);
			stockOpeningBalance.setOpeningBalance(openingBalance);
			stockOpeningBalance.setModifiedDate(new Date());
			stockOpeningBalance.setDateAdded(stockOpeningBalanceForm
					.getDateAdded());
			this.StockOpeningBalanceDao
					.updateStockOpeningBalance(stockOpeningBalance);

		} catch (NumberFormatException e) {

		}
		return stockOpeningBalance;
	}

	private int getCurrentBalance(int globalItem) {
		// TODO Auto-generated method stub
		int currentBalance;
		try {
			currentBalance = this.inventoryManagerBo
					.getGlobalItemCurrentBalance(globalItem);
		} catch (InvalidOpeningStockBalanceException e) {
			currentBalance = 0;
		}
		return currentBalance;
	}

	@Transactional
	@Override
	public void delete(StockOpeningBalance stockOpeningBalance)
			throws InvalidUnitOfMeasureConfiguration {

		// update current balance b4 deletion 		
		//set current balance to null or do below
		int globalItem = 0;
		int currUnitOfMeasure = 0;

		GlobalItem g = stockOpeningBalance.getGlobalItem();
		if (g != null) {
			globalItem = g.getId();
		}

		GlobalUnitofMeasure u = stockOpeningBalance.getGlobalUnitofMeasure();
		if (u != null) {
			currUnitOfMeasure = u.getId();
		}

		/*int currQtyInUnits = this.unitOfMeasureConverter
				.convertUnitOfMeasureToUnitOptionB(globalItem, currUnitOfMeasure,
						stockOpeningBalance.getOpeningBalance(),
						AppConfig.UNIT_OF_MEASURE_UNIT);*/
		int currQtyInUnits = this.unitOfMeasureConverter
				.convertUnitOfMeasureToUnitOptionA(globalItem, currUnitOfMeasure,
						stockOpeningBalance.getOpeningBalance());

		this.inventoryManagerBo.updateGlobalItemCurrentBalance(0, currQtyInUnits, g.getQuantity(),
				globalItem);

		// soft delete stock opening balance
		this.StockOpeningBalanceDao.delete(stockOpeningBalance);
	}

}
