package org.calminfotech.inventory.bo;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.calminfotech.error.custom.exception.InvalidOpeningStockBalanceException;
import org.calminfotech.error.custom.exception.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.boInterface.InventoryManagerBo;
import org.calminfotech.inventory.boInterface.StockInManagerBo;
import org.calminfotech.inventory.daoInterface.StockInDao;
import org.calminfotech.inventory.daoInterface.VendorDao;
import org.calminfotech.inventory.forms.StockInForm;
import org.calminfotech.inventory.forms.StockInLineForm;
import org.calminfotech.inventory.models.StockIn;
import org.calminfotech.inventory.models.StockInLine;
import org.calminfotech.inventory.models.Vendor;
import org.calminfotech.inventory.utils.UnitOfMeasureConverter;
import org.calminfotech.inventory.utils.Utilities;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockInManagerImpl implements StockInManagerBo {

	@Autowired
	private InventoryManagerBo inventoryManagerBo;
	
	@Autowired
	private StockInDao stockInDao;

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

	@Override
	public List<StockIn> getStockInBatchesList(String searchVal)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.stockInDao.getStockInBatchesList(searchVal);
	}

	@Override
	public StockIn getStockInBatchDetailsById(int id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.stockInDao.getStockInBatchDetailsById(id);
	}

	@Override
	public StockIn getBatchSupplyDetailsView(String batchId)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.stockInDao.getStockInBatchDetailsView(batchId);
	}

	@Override
	public StockIn saveStockInBatch(StockInForm stockInBatchForm) {
		// TODO Auto-generated method stub
		StockIn stockInBatch = null;
		try {
			Vendor vendor = this.vendorDao.getVendorDetailsById(Integer
					.parseInt(stockInBatchForm.getVendor()));
			stockInBatch = new StockIn();
			stockInBatch.setBatchId(this.codeGenerator.generateSupplyBatchCode());

			stockInBatch.setVendor(vendor);
			stockInBatch.setCreatedBy(this.userIdentity.getUsername());
			stockInBatch.setSupplyDate(stockInBatchForm.getDateOfSupply());
			stockInBatch.setCreateDate(new Date());

			this.stockInDao.saveStockInBatch(stockInBatch);
		} catch (RecordNotFoundException e) {
		} catch (NumberFormatException e) {
		}
		return stockInBatch;

	}

	@Override
	public StockIn editStockInBatch(StockInForm stockInBatchForm)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		StockIn stockInBatch = null;

		try {
			Vendor vendor = this.vendorDao.getVendorDetailsById(Integer
					.parseInt(stockInBatchForm.getVendor()));

			stockInBatch = this.stockInDao
					.getStockInBatchDetailsById(stockInBatchForm.getId());
			// stockInBatch.setVendor(stockInBatchForm.getVendor());
			stockInBatch.setVendor(vendor);
			stockInBatch.setSupplyDate(stockInBatchForm.getDateOfSupply());
			stockInBatch.setModifiedDate(new Date());

			this.stockInDao.editStockInBatch(stockInBatch);
		} catch (RecordNotFoundException e) {
		} catch (NumberFormatException e) {
		}
		return stockInBatch;
	}

	@Transactional
	@Override
	public void delete(StockIn stockInBatch)
			throws InvalidUnitOfMeasureConfiguration {
		//
		Set<StockInLine> stockInLineItems = stockInBatch.getStockInLines();
		if (stockInLineItems != null) {
			for (StockInLine stockInLineItem : stockInLineItems) {
				this.updateStockInLineItemCurrentBalance(stockInLineItem);
			}
		}
		this.stockInDao.delete(stockInBatch);

	}

	// line supply
	@Override
	public List<StockInLine> getStockInLineItems(int batchId)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.stockInDao.getStockInLineItems(batchId);
	}

	@Override
	public StockInLine saveStockInLineItem(StockInLineForm stockInLineItemForm)
			throws InvalidUnitOfMeasureConfiguration,
			InvalidOpeningStockBalanceException {
		// TODO Auto-generated method stub

		StockInLine stockInLineItem = null;
		
		System.out.print(stockInLineItemForm
				.getUnitOfMeasure());

		try {
			int globalItemId = Integer.parseInt(stockInLineItemForm
					.getGlobalItem());
			int unitofMeasure = Integer.parseInt(stockInLineItemForm
					.getUnitOfMeasure());

			int currentBalance = this.inventoryManagerBo
					.getGlobalItemCurrentBalance(globalItemId);

			int qty = 0;
			try {
				qty = Integer.parseInt(stockInLineItemForm.getQuantity());
			} catch (NumberFormatException e) {

			}
			int newQtyInUnits = this.unitOfMeasureConverter
					.convertUnitOfMeasureToUnitOptionA(globalItemId, unitofMeasure,qty);

			// update current balance b4 saving line item: newQtyInUnits is new
			// value and old value is value 0;
			this.inventoryManagerBo.updateGlobalItemCurrentBalance(newQtyInUnits, 0,
					currentBalance, globalItemId);

			// save line item now
			GlobalUnitofMeasure globalUnitofMeasure = new GlobalUnitofMeasure();
			GlobalItem globalItem = new GlobalItem();
			StockIn stockInBatch = new StockIn();

			System.out.print(unitofMeasure);
			
			globalUnitofMeasure.setId(unitofMeasure);
			globalItem.setId(globalItemId);
			stockInBatch.setId(stockInLineItemForm.getBatchId());

			stockInLineItem = new StockInLine();
			stockInLineItem.setGlobalUnitofMeasure(globalUnitofMeasure);
			stockInLineItem.setGlobalItem(globalItem);
			stockInLineItem.setStockInBatch(stockInBatch);

			stockInLineItem.setCreatedBy(this.userIdentity.getUsername());
			stockInLineItem.setCreateDate(new Date());
			stockInLineItem.setQuantity(qty);

			this.stockInDao.saveStockInLineItem(stockInLineItem);

		} catch (NumberFormatException e) {

		}
		return stockInLineItem;
	}

	@Override
	public StockInLine editStockInLineItem(StockInLineForm stockInLineItemForm)
			throws InvalidUnitOfMeasureConfiguration, RecordNotFoundException,
			InvalidOpeningStockBalanceException {

		StockInLine stockInLineItem = this.stockInDao
				.getStockInLineItemDetailById(stockInLineItemForm.getId());

		try {
			int newGlobalItem = Integer.parseInt(stockInLineItemForm
					.getGlobalItem());
			int newUnitofMeasure = Integer.parseInt(stockInLineItemForm
					.getUnitOfMeasure());

			int currentBalance;

			int currGlobalItem = 0;
			int currUnitOfMeasure = 0;

			GlobalItem g = stockInLineItem.getGlobalItem();
			if (g != null) {
				currGlobalItem = g.getId();
			}
			GlobalUnitofMeasure u = stockInLineItem.getGlobalUnitofMeasure();
			if (u != null) {
				currUnitOfMeasure = u.getId();
			}

			int newQtyInUnits;
			int currQtyInUnits;
			int qty = 0;
			try {
				qty = Integer.parseInt(stockInLineItemForm.getQuantity());
			} catch (NumberFormatException e) {
			}

			if (currGlobalItem != newGlobalItem) {

				currentBalance = this.inventoryManagerBo
						.getGlobalItemCurrentBalance(currGlobalItem);

				// update current product
				newQtyInUnits = 0;
				currQtyInUnits = this.unitOfMeasureConverter
						.convertUnitOfMeasureToUnitOptionA(currGlobalItem,
								currUnitOfMeasure,stockInLineItem.getQuantity());

				this.inventoryManagerBo.updateGlobalItemCurrentBalance(newQtyInUnits,
						currQtyInUnits, currentBalance, currGlobalItem);

				// update new product
				currentBalance = this.inventoryManagerBo
						.getGlobalItemCurrentBalance(newGlobalItem);

				// calculate new qty in units entered by user
				newQtyInUnits = this.unitOfMeasureConverter
						.convertUnitOfMeasureToUnitOptionA(newGlobalItem,
								newUnitofMeasure, qty);
				currQtyInUnits = 0;
				this.inventoryManagerBo.updateGlobalItemCurrentBalance(newQtyInUnits,
						currQtyInUnits, currentBalance, newGlobalItem);

			} else {

				currentBalance = this.inventoryManagerBo
						.getGlobalItemCurrentBalance(newGlobalItem);

				// calculate new qty in units entered by user
				newQtyInUnits = this.unitOfMeasureConverter
						.convertUnitOfMeasureToUnitOptionA(newGlobalItem,
								newUnitofMeasure, qty);

				/*
				 * calculate current qty in units already in dbase b4 reseting
				 * stock opening balance model to reflect new values
				 */
				currQtyInUnits = this.unitOfMeasureConverter
						.convertUnitOfMeasureToUnitOptionA(newGlobalItem,
								currUnitOfMeasure,
								stockInLineItem.getQuantity());

				// update current balance: newQtyInUnits is new value , old
				// value is
				// current value in store;
				this.inventoryManagerBo.updateGlobalItemCurrentBalance(newQtyInUnits,
						currQtyInUnits, currentBalance, newGlobalItem);

			}
			// update line item now
			StockIn stockInBatch = stockInLineItem.getStockInBatch();
			
			//do this cos hibernate can't reset id of persistent object --
			if (currUnitOfMeasure != newUnitofMeasure) {
				u = new GlobalUnitofMeasure();
			}
			if (currGlobalItem != newGlobalItem) {
				g = new GlobalItem();
			}
			//-- --		
			u.setId(newUnitofMeasure);
			g.setId(newGlobalItem);
			stockInBatch.setId(stockInLineItemForm.getBatchId());
			stockInLineItem.setGlobalUnitofMeasure(u);
			stockInLineItem.setGlobalItem(g);
			// stockInLineItem.setBatchSupply(stockInBatch);
			stockInLineItem.setModifiedDate(new Date());
			stockInLineItem.setQuantity(qty);

			this.stockInDao.editStockInLineItem(stockInLineItem);
		} catch (NumberFormatException e) {

		}
		return stockInLineItem;
	}

	@Override
	public StockInLine getStockInLineItemDetailById(int id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		return this.stockInDao.getStockInLineItemDetailById(id);
	}

	@Override
	public void deleteStockInLineItem(StockInLine stockInLineItem)
			throws InvalidUnitOfMeasureConfiguration {
		// update current balance b4 deletion
		this.updateStockInLineItemCurrentBalance(stockInLineItem);
		// soft delete stock opening balance
		this.stockInDao.deleteStockInLineItem(stockInLineItem);
	}

	private void updateStockInLineItemCurrentBalance(StockInLine stockInLineItem)
			throws InvalidUnitOfMeasureConfiguration {
		// update current balance b4 deletion
		int globalItem = 0;
		int currUnitOfMeasure = 0;

		GlobalItem g = stockInLineItem.getGlobalItem();
		if (g != null) {
			globalItem = g.getId();
		}

		GlobalUnitofMeasure u = stockInLineItem.getGlobalUnitofMeasure();
		if (u != null) {
			currUnitOfMeasure = u.getId();
		}
		int currQtyInUnits = this.unitOfMeasureConverter
				.convertUnitOfMeasureToUnitOptionA(globalItem, currUnitOfMeasure,
						stockInLineItem.getQuantity());

		this.inventoryManagerBo.updateGlobalItemCurrentBalance(0, currQtyInUnits, g.getQuantity(),
				globalItem);

	}

}
