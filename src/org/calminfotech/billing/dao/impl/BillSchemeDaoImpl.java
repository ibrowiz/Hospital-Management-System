package org.calminfotech.billing.dao.impl;


import java.util.List;


import org.calminfotech.billing.daoInterface.BillSchemeDao;
import org.calminfotech.billing.models.BillScheme;

import org.calminfotech.user.utils.UserIdentity;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class BillSchemeDaoImpl implements BillSchemeDao{
	
	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public BillScheme getBillSchemeById(int id) {
		// TODO Auto-generated method stub
		List<BillScheme> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillScheme where billId = ? AND status = 'active' ORDER BY billId DESC" )
				.setParameter(0, id)
				.list();
		System.out.println(list.size());
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(BillScheme billScheme) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(billScheme);
	}

	@Override
	public void delete(BillScheme billScheme) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(billScheme);
	}

	@Override
	public void update(BillScheme billScheme) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billScheme);
	}

	

	@Override
	public List<BillScheme> fetchAllByOrganisationBytype(int organisationId,int billtype) {
		//System.out.println("name");
		List<BillScheme> list = sessionFactory.getCurrentSession()
				.createQuery("from BillScheme where organisationId = ? AND status = 'active' AND billingType = ? ORDER BY billId DESC")
		     .setParameter(0,organisationId)
		     .setParameter(1,billtype)
		     
			.list();
		//System.out.println(list.get(0).getHmoId());
			return list;
	}


	@Override
	public List<BillScheme> fetchAllByOrganisation(int organisationId) {
		//System.out.println("name");
		List<BillScheme> list = sessionFactory.getCurrentSession()
				.createQuery("from BillScheme where organisationId = ? AND status = 'active'  ORDER BY billId DESC")
		     .setParameter(0,organisationId)
			.list();
		//System.out.println(list.get(0).getHmoId());
			return list;
	}
}
