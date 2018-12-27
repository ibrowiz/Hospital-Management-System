package org.calminfotech.inventory.dao.Impl;

import java.util.List;

import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.daoInterface.StockInDao;
import org.calminfotech.inventory.models.StockIn;
import org.calminfotech.inventory.models.StockInLine;
import org.calminfotech.inventory.utils.Text;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StockInDaoImpl implements StockInDao {

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public void saveStockInBatch(StockIn stockInBatch) {
		this.sessionFactory.getCurrentSession().save(stockInBatch);

	}

	@Override
	public void editStockInBatch(StockIn stockInBatch) {
		this.sessionFactory.getCurrentSession().update(stockInBatch);

	}

	@Override
	public StockIn getStockInBatchDetailsView(String batchId)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);

	}

	@Override
	public List<StockIn> getStockInBatchesList(String searchVal) {
		// TODO Auto-generated method stub
		List<StockIn> list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM StockIn order by id desc").list();
		if (list.size() > 0)
			return list;

		return null;

	}

	@Override
	public StockIn getStockInBatchDetailsById(int id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM StockIn WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (StockIn) list.get(0);

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
	}

	@Override
	public void delete(StockIn stockIn) {

		this.sessionFactory.getCurrentSession().delete(stockIn);

	}

	// line supply
	@Override
	public List<StockInLine> getStockInLineItems(int batchId) {
		// TODO Auto-generated method stub
		List<StockInLine> list = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM StockIn where id=? order by id desc")
				.setParameter(0, batchId).list();
		if (list.size() > 0)
			return list;

		return null;
	}

	@Override
	public void saveStockInLineItem(StockInLine stockInLineItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(stockInLineItem);

	}

	@Override
	public StockInLine getStockInLineItemDetailById(int id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM StockInLine WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (StockInLine) list.get(0);

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
	}

	@Override
	public void editStockInLineItem(StockInLine stockInLineItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(stockInLineItem);

	}

	@Override
	public void deleteStockInLineItem(StockInLine stockInLineItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(stockInLineItem);

	}

}
