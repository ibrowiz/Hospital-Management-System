package org.calminfotech.billing.dao.impl;

import java.util.List;

import org.calminfotech.billing.daoInterface.BillItemPriceDao;
import org.calminfotech.billing.models.BillItemPrice;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BillItemPriceDaoImpl implements BillItemPriceDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<BillItemPrice> fetchAll() {
		List<BillItemPrice> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillItemPrice").list();
		return list;
	}

	@Override
	public BillItemPrice getBillItemPriceById(int id) {
		// TODO Auto-generated method stub
		List<BillItemPrice> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillItemPrice where billItemPriceId = ?")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(BillItemPrice billItemPrice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(billItemPrice);
		// return ehmoItem;

	}

	@Override
	public void delete(BillItemPrice billItemPrice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(billItemPrice);

	}

	@Override
	public void update(BillItemPrice billItemPrice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billItemPrice);

	}

}
