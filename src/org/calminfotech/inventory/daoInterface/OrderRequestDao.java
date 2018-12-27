package org.calminfotech.inventory.daoInterface;

import java.util.Collection;
import java.util.List;

import org.calminfotech.error.custom.exception.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.models.PointRequest;
import org.calminfotech.inventory.models.PointRequestLine;

public interface OrderRequestDao {

public void savePointRequest(PointRequest pointRequest);
	
	public void updatePointRequest(PointRequest pointRequest);

	public PointRequest getPointRequestById(int reqId)throws RecordNotFoundException ;

	public List<PointRequest> getPointRequests(String dateOfRequest,
			String searchCriteria);
	
	public void deletePointRequest(PointRequest pointRequest);


	public PointRequestLine getPointRequestLineById(int reqLineId)
			throws RecordNotFoundException ;

	public int getTotalGlobalItemRequestApproved(int globalItem)throws InvalidUnitOfMeasureConfiguration;

	public void updatePointRequestLine(PointRequestLine pointRequestLine);

	public List<PointRequest> getPointRequestsByPoint(String dateOfReq,int currentPointId)throws RecordNotFoundException ;

	public Collection<Character> getPntRequestLineRequestsStatuses(
			int pointRequestLineId, int pointRequestId);


	public void deletePointRequestLine(PointRequestLine pointRequestLine);
}
