package org.calminfotech.inventory.dao.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.calminfotech.error.custom.exception.InvalidUnitOfMeasureConfiguration;
import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.daoInterface.OrderRequestDao;
import org.calminfotech.inventory.models.PointRequest;
import org.calminfotech.inventory.models.PointRequestLine;
import org.calminfotech.inventory.utils.PointRequestStatus;
import org.calminfotech.inventory.utils.Text;
import org.calminfotech.inventory.utils.UnitOfMeasureConverter;
import org.calminfotech.utils.DateUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderRequestDaoImpl implements OrderRequestDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UnitOfMeasureConverter unitOfMeasureConverter;

	@Override
	public void savePointRequest(PointRequest pointRequest) {

		this.sessionFactory.getCurrentSession().save(pointRequest);

	}
	
	@Override
	public void updatePointRequest(PointRequest pointRequest) {
		this.sessionFactory.getCurrentSession().update(pointRequest);
	}

	@Override
	public PointRequest getPointRequestById(int id)
			throws RecordNotFoundException {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PointRequest WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PointRequest) list.get(0);

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
	}

	@Override
	public List<PointRequest> getPointRequests(String dateOfRequest,
			String searchCriteria) {

		Date date = DateUtils.formatStringToDate(dateOfRequest);

		StringBuilder qryBuffer = new StringBuilder();
		List paramList = new ArrayList();
		qryBuffer.append("FROM PointRequest");
		// if (dateOfRequest != null && !dateOfRequest.isEmpty()) {
		qryBuffer.append(" WHERE  requestDate=? ");
		paramList.add(date);
		// }
		if (searchCriteria != null && !searchCriteria.isEmpty()) {
			qryBuffer.append(" and requestStatus=? ");
			paramList.add(searchCriteria);
		}

		qryBuffer.append(" order by id ");

		Query query = this.sessionFactory.getCurrentSession().createQuery(
				qryBuffer.toString());

		if (paramList != null && !paramList.isEmpty()) {
			int indx = 0;
			for (Object param : paramList) {
				query.setParameter(indx, param);
				indx++;
			}
		}
		List list = query.list();
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public PointRequestLine getPointRequestLineById(int id)
			throws RecordNotFoundException {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PointRequestLine WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PointRequestLine) list.get(0);

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);

	}

	@Override
	public int getTotalGlobalItemRequestApproved(int globalItem)
			throws InvalidUnitOfMeasureConfiguration {

		Query qry = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"From PointRequestLine where global_item_id=?"
								+ " and request_status=?");
		qry.setParameter(0, globalItem);
		qry.setParameter(1,
				String.valueOf(PointRequestStatus.Approved.getCode()));
		int totalQtyApproved = 0;
		// List<PointRequestLine> list = qry.list();
		List<Object[]> list = qry.list();

		if (list != null && list.size() > 0) {
			for (Object[] aRow : list) {
				// for (PointRequestLine line : list) {
				// convert current request, convert to unit and update total qty
				totalQtyApproved += this.unitOfMeasureConverter
						.convertUnitOfMeasureToUnitOptionA(globalItem,
								(Integer) aRow[0], (Integer) aRow[1]);
			}
		}
		return totalQtyApproved;
	}

	@Override
	public void updatePointRequestLine(PointRequestLine pointRequestLine) {
		this.sessionFactory.getCurrentSession().update(pointRequestLine);
	}

	@Override
	public List<PointRequest> getPointRequestsByPoint(String dateOfRequest,
			int currentPointId) throws RecordNotFoundException {

		Date date = DateUtils.formatStringToDate(dateOfRequest);

		Query qry = this.sessionFactory.getCurrentSession().createQuery(
				" From PointRequest "
						+ "where visitWorkflowPoint.id=? and requestDate=?");

		qry.setParameter(0, currentPointId);
		qry.setParameter(1, date);

		List<Object[]> list = qry.list();
		if (list != null && list.size() > 0) {

			PointRequest pointRequest = null;
			List<PointRequest> retList = new ArrayList();
			for (Object[] aRow : list) {
				pointRequest = new PointRequest();
				pointRequest.setId((Integer) aRow[0]);
				pointRequest.setRequestStatus((Character) aRow[1]);
				pointRequest.setRequestBatchId((String) aRow[2]);
				pointRequest.setRequestDate((Date) aRow[3]);
				retList.add(pointRequest);
			}
			return retList;
		}
		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
	}

	@Override
	 public Collection<Character> getPntRequestLineRequestsStatuses(
			int pointRequestLineId, int pointRequestId) {

		Query qry = this.sessionFactory.getCurrentSession().createQuery(
				"select requestStatus From PointRequestLine "
						+ "where pointRequest.id=? and id!=?");

		qry.setParameter(0,pointRequestId);
		qry.setParameter(1,pointRequestLineId);

		List<Character> list = qry.list();
		return list;
	}

	@Override
	public void deletePointRequest(PointRequest pointRequest) {
		this.sessionFactory.getCurrentSession().delete(pointRequest);
	}

	@Override
	public void deletePointRequestLine(PointRequestLine pointRequestLine) {
		this.sessionFactory.getCurrentSession().delete(pointRequestLine);
		
	}


}
