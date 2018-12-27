package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.CategoryItemUnitDao;
import org.calminfotech.system.models.CategoryItemUnit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryItemUnitDaoImpl implements CategoryItemUnitDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<CategoryItemUnit> fetchAll() {
		List<CategoryItemUnit> list = sessionFactory.getCurrentSession()
								   .createQuery("from CategoryItemUnit").list();
		return list;
	}

	@Override
	public CategoryItemUnit getCategoryItemUnitById(int id) {
		List<CategoryItemUnit> list = sessionFactory.getCurrentSession()
				                   .createQuery("from CategoryItemUnit where itemUnitId = ? ")
				                   .setParameter(0, id).list();
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(CategoryItemUnit itemUnit) {
		sessionFactory.getCurrentSession().save(itemUnit);
	}

	@Override
	public void update(CategoryItemUnit itemUnit) {
		sessionFactory.getCurrentSession().update(itemUnit);
	}

	@Override
	public void delete(CategoryItemUnit itemUnit) {
		sessionFactory.getCurrentSession().delete(itemUnit);
	}

	

	

}
