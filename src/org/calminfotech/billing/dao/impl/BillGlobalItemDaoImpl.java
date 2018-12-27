package org.calminfotech.billing.dao.impl;


import java.util.List;

import org.calminfotech.billing.daoInterface.BillGlobalItemDao;
import org.calminfotech.billing.models.BillGlobalItem;

import org.calminfotech.user.utils.UserIdentity;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class BillGlobalItemDaoImpl implements BillGlobalItemDao{
	
	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public BillGlobalItem getGlobalItemById(int id) {
		// TODO Auto-generated method stub
		List<BillGlobalItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("from GlobalItemItem where globalItemId = ? AND status = 'active' ORDER BY GlobalItemId DESC" )
				.setParameter(0, id)
				.list();
		System.out.println(list.size());
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(BillGlobalItem globalItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(globalItem);
	}

	@Override
	public void delete(BillGlobalItem globalItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(globalItem);
	}

	@Override
	public void update(BillGlobalItem globalItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(globalItem);
	}

	@Override
	public List<BillGlobalItem> fetchAll() {
		// TODO Auto-generated method stub
		List<BillGlobalItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillGlobalItem").list();
		return list;
	}

	@Override
	public List<BillGlobalItem> fetchAllByOrganisation(int organisationId) {
		//System.out.println("name");
		List<BillGlobalItem> list = sessionFactory.getCurrentSession()
				.createQuery("from BillGlobalItem where organisationId = ? AND status = 'active'  ORDER BY globalItemId DESC")
		     .setParameter(0,organisationId)
			.list();
		//System.out.println(list.get(0).getHmoId());
			return list;
	}

	@Override
	public List<BillGlobalItem> fetchAllByOrganisationByType(
			int organisationId, int itemType) {
		List<BillGlobalItem> list = sessionFactory.getCurrentSession()
				.createQuery("from BillGlobalItem where organisationId = ? AND status = 'active' and type=?  ORDER BY globalItemId DESC")
		     .setParameter(0,organisationId)
		     .setParameter(1,itemType)
			.list();
		//System.out.println(list.get(0).getHmoId());
		
		return null;
	}
}
