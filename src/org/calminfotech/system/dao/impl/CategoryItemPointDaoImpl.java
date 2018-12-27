package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.CategoryItemPointDao;
import org.calminfotech.system.models.CategoryItemPoint;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryItemPointDaoImpl implements CategoryItemPointDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<CategoryItemPoint> fetchAll() {
		List<CategoryItemPoint> list = sessionFactory.getCurrentSession()
								   .createQuery("from CategoryItemPoint").list();
		return list;
	}

	@Override
	public CategoryItemPoint getCategoryItemPointById(int id) {
		List<CategoryItemPoint> list = sessionFactory.getCurrentSession()
				                   .createQuery("from CategoryItemPoint where itemPointId = ? ")
				                   .setParameter(0, id).list();
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(CategoryItemPoint itemPoint) {
		sessionFactory.getCurrentSession().save(itemPoint);
	}

	@Override
	public void update(CategoryItemPoint itemPoint) {
		sessionFactory.getCurrentSession().update(itemPoint);
	}

	@Override
	public void delete(CategoryItemPoint itemPoint) {
		sessionFactory.getCurrentSession().delete(itemPoint);
	}
}
