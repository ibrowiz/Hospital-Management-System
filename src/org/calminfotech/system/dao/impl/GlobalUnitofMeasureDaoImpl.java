package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.GlobalUnitofMeasureDao;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class GlobalUnitofMeasureDaoImpl implements GlobalUnitofMeasureDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<GlobalUnitofMeasure> fetchAll() {
		// TODO Auto-generated method stub
		List<GlobalUnitofMeasure> list = sessionFactory.getCurrentSession()
				.createQuery("from GlobalUnitofMeasure").list();
		return list;
	}

	@Override
	public GlobalUnitofMeasure getGlobalUnitofMeasureById(int id) {
		// TODO Auto-generated method stub
		List<GlobalUnitofMeasure> list = sessionFactory.getCurrentSession()
				.createQuery("from GlobalUnitofMeasure where id = ?")
				.setParameter(0, id).list();
		if(list.size()>0)
			return (GlobalUnitofMeasure) list.get(0);
		return null;
	}

	@Override
	public void save(GlobalUnitofMeasure unitofMeasure) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(unitofMeasure);
	}

	@Override
	public void delete(GlobalUnitofMeasure unitofMeasure) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(unitofMeasure);
	}

	@Override
	public void update(GlobalUnitofMeasure unitofMeasure) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(unitofMeasure);
	}

}
