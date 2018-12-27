package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.BillingInvoiceDao;
import org.calminfotech.system.models.BillingInvoice;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class BillingInvoiceDaoImpl implements BillingInvoiceDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<BillingInvoice> fetchAll() {
		// TODO Auto-generated method stub
		List<BillingInvoice> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingInvoice").list();
		if(list.size()>0)
			return list;
		return null;
	}

	@Override
	public List<BillingInvoice> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		List<BillingInvoice> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingInvoice where organisation = ?")
				.setParameter(0, organisation).list();
		return list;
	}

	@Override
	public void save(BillingInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(billingInvoice);
	}

	@Override
	public void delete(BillingInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(billingInvoice);
	}

	@Override
	public void update(BillingInvoice billingInvoice) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billingInvoice);
	}

	@Override
	public BillingInvoice getBillingInvoiceById(int id) {
		// TODO Auto-generated method stub
		List<BillingInvoice> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingInvoice where id = ? ")
				.setParameter(0, id).list();
		if(list.size()>0)
			return (BillingInvoice)list.get(0);
		return null;
	}

}
