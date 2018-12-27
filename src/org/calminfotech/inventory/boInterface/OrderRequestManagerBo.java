package org.calminfotech.inventory.boInterface;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.calminfotech.error.custom.exception.InvalidOpeningStockBalanceException;
import org.calminfotech.error.custom.exception.InvalidStockLevelException;
import org.calminfotech.error.custom.exception.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.forms.DateSearchForm;
import org.calminfotech.inventory.forms.RequestForm;
import org.calminfotech.inventory.models.PointRequest;
import org.calminfotech.inventory.models.PointRequestLine;

public interface OrderRequestManagerBo {

	public PointRequest savePointRequest(RequestForm orderRequestForm);

	public PointRequest getPointRequestById(int reqId)
			throws RecordNotFoundException;

	public List<PointRequest> getPointRequests(
			DateSearchForm pointRequestSearchForm);

	public List<PointRequest> getUserPointRequests(
			DateSearchForm pointRequestSearchForm)
			throws RecordNotFoundException;

	public void deletePointRequest(PointRequest pointRequest);

	public void approvePointLineRequest(int reqLineId, int qtyToApprove)
			throws InvalidStockLevelException, RecordNotFoundException,
			InvalidUnitOfMeasureConfiguration,
			InvalidOpeningStockBalanceException;

	public PointRequestLine getPointRequestLineById(int id)
			throws RecordNotFoundException;

	public void disapprovePointRequestLine(PointRequestLine pointRequestLine);

	public void issuePointRequestLine(PointRequestLine pointRequestLine)
			throws InvalidOpeningStockBalanceException,
			InvalidUnitOfMeasureConfiguration;

	public Map getGlobalItemsQuantityAvailable(
			Set<PointRequestLine> pointRequestLines);

	public void deletePointRequestLine(PointRequestLine pointRequestLine);

}
