package org.calminfotech.system.dao.impl;

import java.util.List;


import org.calminfotech.system.daoInterface.BillingSchemeDao;
import org.calminfotech.system.models.BillingScheme;
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
public class BillingSchemeDaoImpl implements BillingSchemeDao{
	
	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public BillingScheme getBillingSchemeById(int id) {
		// TODO Auto-generated method stub
		List<BillingScheme> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingScheme where id = ?").setParameter(0, id)
				.list();
		System.out.println(list.size());
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(BillingScheme billingScheme) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(billingScheme);
	}

	@Override
	public void delete(BillingScheme billingScheme) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(billingScheme);
	}

	@Override
	public void update(BillingScheme billingScheme) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billingScheme);
	}

	@Override
	public List<BillingScheme> fetchAll() {
		// TODO Auto-generated method stub
		List<BillingScheme> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingScheme").list();
		return list;
	}

	@Override
	public List<BillingScheme> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(BillingScheme.class)
		.createAlias("organisation", "organisation")
		 .add(Restrictions.eq("organisation.id", organisation.getId()));
	
		List list = criteria.list();
		return list;
	}

	@Override
	public List<BillingScheme> fetchAllByType(Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(BillingScheme.class)
		.createAlias("organisation", "organisation")
		 .add(Restrictions.eq("type", 2))
		 .add(Restrictions.eq("organisation.id", organisation.getId()));
	
		List list = criteria.list();
		return list;
	}
}
