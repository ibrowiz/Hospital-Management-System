package org.calminfotech.views.dao.impl;

import java.util.List;

import org.calminfotech.views.daoInterface.VwItemDao;
import org.calminfotech.views.models.VwItem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VwItemDaoImpl implements VwItemDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<VwItem> fetchAllByPoint(Integer pointId) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<VwItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("from VwItem where point = ?").setParameter(0, pointId)
				.list();
		
		return list;
	}

	@Override
	public VwItem getVwItemById(Integer id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from VwItem where id = ?").setParameter(0, id)
				.list();
		if (list.size() > 0) {
			return (VwItem) list.get(0);
		}
		return null;
	}

}
