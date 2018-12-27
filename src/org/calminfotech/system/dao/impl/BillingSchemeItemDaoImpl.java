package org.calminfotech.system.dao.impl;

import java.util.List;


import org.calminfotech.system.daoInterface.BillingSchemeItemDao;

import org.calminfotech.system.models.BillingSchemeItem;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BillingSchemeItemDaoImpl implements  BillingSchemeItemDao{
	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;
	
	

	@Override
	public BillingSchemeItem getBillingSchemeItemById(int id) {
		// TODO Auto-generated method stub
		List<BillingSchemeItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingSchemeItem where id = ?").setParameter(0, id)
				.list();

		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(BillingSchemeItem billingSchemeitem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(billingSchemeitem);
	}

	@Override
	public void delete(BillingSchemeItem billingSchemeitem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(billingSchemeitem);
	}

	@Override
	public void update(BillingSchemeItem billingSchemeitem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billingSchemeitem);
	}

	@Override
	public List<BillingSchemeItem> fetchAll() {
		// TODO Auto-generated method stub
		List<BillingSchemeItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingSchemeItem").list();
		return list;
	}

	@Override
	public List<BillingSchemeItem> fetchAllByOrganisation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(BillingSchemeItem.class)
		.createAlias("organisation", "organisation")
		 .add(Restrictions.eq("organisation.id", organisation.getId()));
	
		List list = criteria.list();
		return list;
	}
}
