package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.GlobalItemCategoryDao;
import org.calminfotech.system.models.GlobalItemCategory;
import org.calminfotech.system.models.GlobalItemType;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GlobalItemCategoryDaoImpl implements GlobalItemCategoryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Override
	public GlobalItemCategory getGlobalCategoryItemById(int id) {
		// TODO Auto-generated method stub
		List<GlobalItemCategory> list = sessionFactory.getCurrentSession()
				.createQuery("FROM GlobalItemCategory WHERE categoryId = ? ")
				.setParameter(0, id).list();
		if(list.size()>0)
			return list.get(0);
		return null;
	}
	

	@Override
	public List<GlobalItemCategory> fetchGlobalItemCategoryByItemTypeId(
			GlobalItemType globalItemType) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(GlobalItemCategory.class)
				.createAlias("globalItemType", "globalItemType")
				.add(Restrictions.eq("globalItemType.globalitemTypeId", globalItemType.getGlobalitemTypeId()));
		List list = criteria.list();
		return list;
	}

	@Override
	public void save(GlobalItemCategory globalCategoryItem) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(globalCategoryItem);
	}

	@Override
	public void delete(GlobalItemCategory globalCategoryItem) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(globalCategoryItem);
	}

	@Override
	public void update(GlobalItemCategory globalCategoryItem) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(globalCategoryItem);
	}

	@Override
	public List<GlobalItemCategory> fetchAll() {
		// TODO Auto-generated method stub
		List<GlobalItemCategory> list = sessionFactory.getCurrentSession()
				.createQuery("FROM GlobalItemCategory").list();
		return null;
	}

	@Override
	public List<GlobalItemCategory> fetchAllByOrganisation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(GlobalItemCategory.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));
		List list = criteria.list();
		return list;
	}

}
