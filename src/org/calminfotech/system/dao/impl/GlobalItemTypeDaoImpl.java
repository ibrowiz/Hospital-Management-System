package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.GlobalItemTypeDao;
import org.calminfotech.system.models.GlobalItemType;
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
public class GlobalItemTypeDaoImpl implements GlobalItemTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public GlobalItemType getGlobalItemTypeById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM GlobalItemType WHERE globalitemTypeId = ?")
				.setParameter(0, id).list();
		if(list.size()>0){
			return (GlobalItemType)list.get(0);
		}
		return null;
	}

	@Override
	public void save(GlobalItemType globalItemType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(globalItemType);
	}

	@Override
	public void delete(GlobalItemType globalItemType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(globalItemType);
	}

	@Override
	public void update(GlobalItemType globalItemType) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(globalItemType);
	}

	@Override
	public List<GlobalItemType> fetchAll() {
		// TODO Auto-generated method stub
		List<GlobalItemType> list = sessionFactory.getCurrentSession()
				.createQuery("FROM GlobalItemType").list();
		return list;
	}

	@Override
	public List<GlobalItemType> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(GlobalItemType.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));
		List list = criteria.list();
		return list;
	}

}
