package org.calminfotech.views.dao.impl;

import java.util.List;

import org.calminfotech.views.daoInterface.VwUnitDao;
import org.calminfotech.views.models.VwUnit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@SuppressWarnings("rawtypes")
@Repository
public class VwUnitDaoImpl implements VwUnitDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<VwUnit> fetchAllByItemId(Integer itemId) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<VwUnit> list = this.sessionFactory.getCurrentSession()
				.createQuery("from VwUnit where item = ?").setParameter(0, itemId)
				.list();
		return list;
	}

	@Override
	public VwUnit getVwUnitById(Integer id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from VwUnit where id = ?").setParameter(0, id)
				.list();
		if (list.size() > 0) {
			return (VwUnit) list.get(0);
		}
		return null;
	}

}
