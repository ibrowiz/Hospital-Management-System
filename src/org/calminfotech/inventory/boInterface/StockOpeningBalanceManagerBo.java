package org.calminfotech.inventory.boInterface;

import java.util.List;

import org.calminfotech.error.custom.exception.DuplicateDataException;
import org.calminfotech.error.custom.exception.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.forms.StockOpeningBalanceForm;
import org.calminfotech.inventory.models.StockOpeningBalance;

public interface StockOpeningBalanceManagerBo {

	// stock opening balance
	public List<StockOpeningBalance> getStockOpeningBalances()
			throws RecordNotFoundException;

	public StockOpeningBalance getStockOpeningBalanceDetailById(int id)
			throws RecordNotFoundException;

	public void saveStockOpeningBalance(
			StockOpeningBalanceForm stockOpeningBalanceForm)
			throws DuplicateDataException, InvalidUnitOfMeasureConfiguration;

	public StockOpeningBalance editStockOpeningBalance(
			StockOpeningBalanceForm stockOpeningBalanceForm)
			throws DuplicateDataException, RecordNotFoundException,
			InvalidUnitOfMeasureConfiguration;

	public void delete(StockOpeningBalance stockOpeningBalance)
			throws InvalidUnitOfMeasureConfiguration;
}
