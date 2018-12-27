package org.calminfotech.inventory.dao.Impl;

import java.util.List;

import org.calminfotech.error.custom.exception.RecordNotFoundException;
import org.calminfotech.inventory.daoInterface.PointStockOpeningBalanceDao;
import org.calminfotech.inventory.models.PointStockOpeningBalance;
import org.calminfotech.inventory.models.StockOpeningBalance;
import org.calminfotech.inventory.utils.Text;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PointStockOpeningBalanceDaoImpl implements
		PointStockOpeningBalanceDao {

	@Autowired
	private SessionFactory sessionFactory;

	// stock opening balances
	@Override
	public void savePointStockOpeningBalance(PointStockOpeningBalance stockOpeningBalance)
			{
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(stockOpeningBalance);
	}

	@Override
	public List<PointStockOpeningBalance> getStockOpeningBalances(int pointId) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		
		List<PointStockOpeningBalance> list = this.sessionFactory
				.getCurrentSession()
				.createQuery("FROM PointStockOpeningBalance order by id desc")
				.list();
		if (list.size() > 0)
			return list;

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
	}

	@Override
	public StockOpeningBalance getStockOpeningBalanceDetailById(int id)
			throws RecordNotFoundException {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM StockOpeningBalance WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (StockOpeningBalance) list.get(0);

		throw new RecordNotFoundException(Text.RECORD_NOT_FOUND);
	}

	@Override
	public void updateStockOpeningBalance(
			StockOpeningBalance stockOpeningBalance) {

		this.sessionFactory.getCurrentSession().update(stockOpeningBalance);

	}

	@Override
	public void delete(StockOpeningBalance stockOpeningBalance) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(stockOpeningBalance);
	}

	
	@Override
	public boolean isExistPointStockOpeningBalanceGlobalItem(int globalItemID,
			int unitofMeasure,int currentPointId) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"select id FROM PointStockOpeningBalance WHERE global_item_id = ? and unit_of_measure_id=? and point_id=?");
		query.setParameter(0, globalItemID);
		query.setParameter(1, unitofMeasure);
		query.setParameter(2, currentPointId);

		List list = query.list();
		if (list.size() > 0)
			return true;

		return false;
	}

}
