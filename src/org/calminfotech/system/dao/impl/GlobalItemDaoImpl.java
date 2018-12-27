package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.GlobalItemDao;
import org.calminfotech.system.models.GlobalItem;
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
public class GlobalItemDaoImpl implements GlobalItemDao {
	
	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public GlobalItem getglobalItemById(int id) {
		// TODO Auto-generated method stub
		List<GlobalItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("from GlobalItem where id = ?").setParameter(0, id)
				.list();

	//	System.out.println(list.size());
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(GlobalItem itemDistribution) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(itemDistribution);
	}

	@Override
	public void delete(GlobalItem itemDistribution) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(itemDistribution);
	}

	@Override
	public void update(GlobalItem itemDistribution) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(itemDistribution);	
	}

	@Override
	public List<GlobalItem> fetchAll() {
		// TODO Auto-generated method stub
		List<GlobalItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("from GlobalItem").list();
		return list;
	}

	@Override
	public List<GlobalItem> fetchAllByOrganisation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(GlobalItem.class)
		.createAlias("organisation", "organisation")
		 .add(Restrictions.eq("organisation.id", organisation.getId()));
	
		List list = criteria.list();
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
