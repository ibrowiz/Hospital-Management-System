package org.calminfotech.inventory.bo;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.calminfotech.error.custom.exception.InvalidOpeningStockBalanceException;
import org.calminfotech.error.custom.exception.InvalidStockLevelException;
import org.calminfotech.error.custom.exception.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.boInterface.InventoryManagerBo;
import org.calminfotech.inventory.boInterface.OrderRequestManagerBo;
import org.calminfotech.inventory.daoInterface.OrderRequestDao;
import org.calminfotech.inventory.forms.DateSearchForm;
import org.calminfotech.inventory.forms.RequestForm;
import org.calminfotech.inventory.models.PointRequest;
import org.calminfotech.inventory.models.PointRequestLine;
import org.calminfotech.inventory.utils.PointRequestStatus;
import org.calminfotech.inventory.utils.UnitOfMeasureConverter;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderRequestManagerImpl implements OrderRequestManagerBo {

	@Autowired
	private OrderRequestDao orderRequestDao;

	@Autowired
	private InventoryManagerBo inventoryManagerBo;

	@Autowired
	private CodeGenerator codeGenerator;

	@Autowired
	private UnitOfMeasureConverter unitOfMeasureConverter;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public PointRequest savePointRequest(RequestForm orderRequestForm) {
		// TODO Auto-generated method stub
		PointRequest pointRequest = new PointRequest();
		pointRequest.setRequestBatchId(this.codeGenerator
				.generatePointRequestCode());

		Date date = new Date();
		pointRequest.setCreateDate(date);
		pointRequest.setRequestDate(date);
		pointRequest.setRequestStatus(PointRequestStatus.Pending.getCode());

		String userName = userIdentity.getUsername();
		pointRequest.setCreatedBy(userName);

		VisitWorkflowPoint visitWorkflowPoint = new VisitWorkflowPoint();
		visitWorkflowPoint.setId(userIdentity.getCurrentPointId());
		pointRequest.setVisitWorkflowPoint(visitWorkflowPoint);

		// get the request line
		Collection<Map> requestLinesMapList = orderRequestForm
				.getOptionalOrders();

		if (requestLinesMapList != null && !requestLinesMapList.isEmpty()) {

			Set<PointRequestLine> pointRequestLines = new HashSet();
			PointRequestLine pointRequestLine = null;

			GlobalItem globalItem = null;
			GlobalUnitofMeasure globalUnitofMeasure = null;

			for (Map requestLineMap : requestLinesMapList) {
				pointRequestLine = new PointRequestLine();
				pointRequestLine.setCreateDate(date);
				pointRequestLine.setCreatedBy(userName);

				globalItem = new GlobalItem();
				globalItem.setId(Integer.parseInt((String) requestLineMap
						.get("globalItem")));

				pointRequestLine.setGlobalItem(globalItem);
				pointRequestLine.setRequestStatus(PointRequestStatus.Pending
						.getCode());

				globalUnitofMeasure = new GlobalUnitofMeasure();
				globalUnitofMeasure.setId((Integer) requestLineMap
						.get("unitOfMeasure"));

				pointRequestLine.setGlobalUnitofMeasure(globalUnitofMeasure);
				pointRequestLine.setQuantity((Integer) requestLineMap
						.get("qty"));
				pointRequestLine.setPointRequest(pointRequest);
				pointRequestLines.add(pointRequestLine);

			}
			pointRequest.setPointRequestLines(pointRequestLines);
		}

		this.orderRequestDao.savePointRequest(pointRequest);
		return pointRequest;
	}

	@Override
	public PointRequest getPointRequestById(int reqId)
			throws RecordNotFoundException {
		return this.orderRequestDao.getPointRequestById(reqId);
	}

	@Override
	public List<PointRequest> getPointRequests(
			DateSearchForm pointRequestSearchForm) {

		return this.orderRequestDao.getPointRequests(
				pointRequestSearchForm.getDateOfRequest(),
				pointRequestSearchForm.getSearchCriteria());
	}

	@Override
	public void approvePointLineRequest(int reqLineId, int qtyToApprove)
			throws InvalidStockLevelException, RecordNotFoundException,
			InvalidOpeningStockBalanceException,
			InvalidUnitOfMeasureConfiguration {

		// get request line details
		PointRequestLine pointRequestLine = this.orderRequestDao
				.getPointRequestLineById(reqLineId);
		// get current balance of the global item associated with this request
		// line
		GlobalItem g = pointRequestLine.getGlobalItem();
		GlobalUnitofMeasure u = pointRequestLine.getGlobalUnitofMeasure();
		int globalItem = 0;
		String globalItemName = null;
		int unitOfMeasure = 0;
		String unitOfMeasureName = null;
		if (g != null) {
			globalItem = g.getId();
			globalItemName = g.getName();
		}
		// deduct all approved to get actual current balance

		int actualGlobalItemCurrBal = 0;
		try {
			actualGlobalItemCurrBal = this
					.getActualGlobalItemCurrentBalance(globalItem);
		} catch (InvalidUnitOfMeasureConfiguration e) {
			e.setExceptionMsg("Invalid Unit Of Measure for " + globalItemName);
			throw e;
		} catch (InvalidOpeningStockBalanceException e) {
			e.setExceptionMsg("No Opening Balance found for " + globalItemName);
			throw e;
		}

		// check stock level for qty of item to be approved
		if (u != null) {
			unitOfMeasure = u.getId();
			unitOfMeasureName = u.getUnit_of_measure();
		}
		int totalUnitsToApprove;
		try {
			totalUnitsToApprove = this.unitOfMeasureConverter
					.convertUnitOfMeasureToUnitOptionA(globalItem,
							unitOfMeasure, qtyToApprove);

		} catch (InvalidUnitOfMeasureConfiguration e) {
			e.setExceptionMsg("Invalid Unit Of Measure for " + globalItemName);
			throw e;
		}
		if (actualGlobalItemCurrBal < totalUnitsToApprove) {
			throw new InvalidStockLevelException("Invalid Stock Level for "
					+ globalItemName + ":" + "Quantity Requested:"
					+ qtyToApprove + " " + unitOfMeasureName
					+ ", Quantity Available:" + qtyToApprove + " "
					+ unitOfMeasureName);
		}

		pointRequestLine.setQuantityApproved(qtyToApprove);
		pointRequestLine
				.setRequestStatus(PointRequestStatus.Approved.getCode());

		this.orderRequestDao.updatePointRequestLine(pointRequestLine);
		// update parent point request status based on the status of this point
		// request line
		// parent point request is loaded along with each point line
		PointRequest pointRequest = pointRequestLine.getPointRequest();

		Collection<Character> pntRequestLineRequestsStatuses = this
				.getPointRequestLineRequestsStatuses(pointRequestLine.getId(),
						pointRequest.getId());

		if (pntRequestLineRequestsStatuses != null) {
			if (pntRequestLineRequestsStatuses
					.contains(PointRequestStatus.Pending.getCode())) {
				pointRequest.setRequestStatus(PointRequestStatus.Processing
						.getCode());
			} else {
				pointRequest.setRequestStatus(PointRequestStatus.Approved
						.getCode());
			}
		}
		this.orderRequestDao.updatePointRequest(pointRequest);

	}

	private Collection<Character> getPointRequestLineRequestsStatuses(
			int pointRequestLineId, int pointRequestId) {
		return this.orderRequestDao.getPntRequestLineRequestsStatuses(
				pointRequestLineId, pointRequestId);
	}

	private int getTotalGlobalItemRequestApproved(int globalItem)
			throws InvalidUnitOfMeasureConfiguration {
		// TODO Auto-generated method stub
		return this.orderRequestDao
				.getTotalGlobalItemRequestApproved(globalItem);
	}

	@Override
	public PointRequestLine getPointRequestLineById(int id)
			throws RecordNotFoundException {
		return this.orderRequestDao.getPointRequestLineById(id);
	}

	@Override
	public void disapprovePointRequestLine(PointRequestLine pointRequestLine) {
		pointRequestLine.setQuantityApproved(0);
		pointRequestLine.setRequestStatus(PointRequestStatus.Pending.getCode());

		this.orderRequestDao.updatePointRequestLine(pointRequestLine);

		// update parent point request status based on the status of this point
		// request line
		// parent point request is loaded along with each point line
		PointRequest pointRequest = pointRequestLine.getPointRequest();

		Collection<Character> pntRequestLineRequestsStatuses = this
				.getPointRequestLineRequestsStatuses(pointRequestLine.getId(),
						pointRequest.getId());

		if (pntRequestLineRequestsStatuses != null) {
			if (!pntRequestLineRequestsStatuses
					.contains(PointRequestStatus.Approved.getCode())
					|| !pntRequestLineRequestsStatuses
							.contains(PointRequestStatus.Issued.getCode())) {
				pointRequest.setRequestStatus(PointRequestStatus.Pending
						.getCode());
			}
		}
		this.orderRequestDao.updatePointRequest(pointRequest);
	}

	@Override
	/* method to issue a request line belonging to a point request */
	public void issuePointRequestLine(PointRequestLine pointRequestLine)
			throws InvalidOpeningStockBalanceException,
			InvalidUnitOfMeasureConfiguration {

		GlobalItem g = pointRequestLine.getGlobalItem();
		GlobalUnitofMeasure u = pointRequestLine.getGlobalUnitofMeasure();
		int currBalance = 0;
		int globalItem = 0;
		String globalItemName = null;
		int unitOfMeasure = 0;
		String unitOfMeasureName = null;
		if (g != null) {
			globalItem = g.getId();
			globalItemName = g.getName();
			try {
				currBalance = this.inventoryManagerBo
						.getGlobalItemCurrentBalance(globalItem);
			} catch (InvalidOpeningStockBalanceException e) {
				e.setExceptionMsg("No Opening Balance found for "
						+ globalItemName);
				throw e;
			}
		}
		if (u != null) {
			unitOfMeasure = u.getId();
			unitOfMeasureName = u.getUnit_of_measure();
		}

		int totalQtyIssued = 0;
		try {
			totalQtyIssued = this.unitOfMeasureConverter
					.convertUnitOfMeasureToUnitOptionA(globalItem,
							unitOfMeasure,
							pointRequestLine.getQuantityApproved());
		} catch (InvalidUnitOfMeasureConfiguration e) {
			e.setExceptionMsg("Invalid Unit Of Measure for " + globalItemName);
			throw e;
		}

		this.inventoryManagerBo.updateGlobalItemCurrentBalance(
				0 - totalQtyIssued, 0, currBalance, globalItem);
		pointRequestLine.setRequestStatus(PointRequestStatus.Issued.getCode());

		this.orderRequestDao.updatePointRequestLine(pointRequestLine);

		// update parent point request status based on the status of this point
		// request line
		// parent point request is loaded along with each point line
		PointRequest pointRequest = pointRequestLine.getPointRequest();

		Collection<Character> pntRequestLineRequestsStatuses = this
				.getPointRequestLineRequestsStatuses(pointRequestLine.getId(),
						pointRequest.getId());

		if (pntRequestLineRequestsStatuses != null) {
			if (pntRequestLineRequestsStatuses
					.contains(PointRequestStatus.Pending.getCode())
					|| pntRequestLineRequestsStatuses
							.contains(PointRequestStatus.Approved.getCode())) {
				pointRequest.setRequestStatus(PointRequestStatus.Processing
						.getCode());
			} else {
				pointRequest.setRequestStatus(PointRequestStatus.Approved
						.getCode());
			}
		}

		System.out.print(pntRequestLineRequestsStatuses + "/"
				+ pointRequest.getRequestStatus());

		this.orderRequestDao.updatePointRequest(pointRequest);

	}

	/* method to get the actual balance of global item */
	private int getActualGlobalItemCurrentBalance(int globalItem)
			throws InvalidOpeningStockBalanceException,
			InvalidUnitOfMeasureConfiguration {

		int currBalance = this.inventoryManagerBo
				.getGlobalItemCurrentBalance(globalItem);

		// deduct all approved to get actual current balance
		int currQtyApproved;
		currQtyApproved = this.getTotalGlobalItemRequestApproved(globalItem);

		return currBalance - currQtyApproved;
	}

	@Override
	public Map getGlobalItemsQuantityAvailable(
			Set<PointRequestLine> pointRequestLines) {
		// TODO Auto-generated method stub
		if (pointRequestLines != null) {
			GlobalItem globalItem = null;
			GlobalUnitofMeasure u = null;
			int containedUnit = 0;
			Map<Integer, Integer> m = new HashMap();
			int actualCurrentBal = 0;
			int globalItemId;
			int unitOfMeasure;
			for (PointRequestLine line : pointRequestLines) {
				globalItem = line.getGlobalItem();
				if (globalItem != null) {
					globalItemId = globalItem.getId();
					u = line.getGlobalUnitofMeasure();
					if (u != null) {
						unitOfMeasure = u.getId();
						try {

							/*
							 * if unit of measure is unit our contained unit is
							 * 1, otherwise get contained unit if other unit of
							 * measure
							 */
							if (unitOfMeasure == AppConfig.UNIT_OF_MEASURE_UNIT) {
								containedUnit = 1;
							} else {
								containedUnit = this.unitOfMeasureConverter
										.convertUnitOfMeasureToContainedUnitOptionA(
												globalItemId, unitOfMeasure);
							}

							/*
							 * get global item actual current balance from map
							 * if already cached
							 */
							if (m.containsKey(globalItemId)) {
								actualCurrentBal = m.get(globalItemId);
							} else {
								/*
								 * get global item actual current balance and
								 * cached
								 */
								actualCurrentBal = this
										.getActualGlobalItemCurrentBalance(globalItemId);
								m.put(globalItemId, actualCurrentBal);
							}
							line.setQuantityAvailable((int) actualCurrentBal
									/ containedUnit);
							// System.out.print(Math.ceil(actualCurrentBal/containedUnit)+"/ceil");
							// System.out.print(Math.floor(actualCurrentBal/containedUnit)+"/floor");
						} catch (InvalidUnitOfMeasureConfiguration e) {
						} catch (InvalidOpeningStockBalanceException e) {
						}
					}
				}
			}
			// System.out.print(m);
		}
		return null;
	}

	@Override
	public List<PointRequest> getUserPointRequests(
			DateSearchForm pointRequestSearchForm)
			throws RecordNotFoundException {
		return this.orderRequestDao.getPointRequestsByPoint(
				pointRequestSearchForm.getDateOfRequest(),
				this.userIdentity.getCurrentPointId());

	}

	@Override
	public void deletePointRequest(PointRequest pointRequest) {
		// TODO Auto-generated method stub
		this.orderRequestDao.deletePointRequest(pointRequest);
	}

	@Override
	public void deletePointRequestLine(PointRequestLine pointRequestLine) {
		// TODO Auto-generated method stub
		this.orderRequestDao.deletePointRequestLine(pointRequestLine);
	}
}
