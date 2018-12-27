package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.CategoryDao;
import org.calminfotech.system.models.GlobalItemCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<GlobalItemCategory> fetchAll() {
		List<GlobalItemCategory> list = sessionFactory.getCurrentSession()
								   .createQuery("from Category").list();
		return list;
	}

	@Override
	public GlobalItemCategory getCategoryById(int categoryId) {
		List<GlobalItemCategory> list = sessionFactory.getCurrentSession()
				                   .createQuery("from Category where categoryId = ? ")
				                   .setParameter(0, categoryId).list();
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(GlobalItemCategory category) {
		sessionFactory.getCurrentSession().save(category);
	}

	@Override
	public void update(GlobalItemCategory category) {
		sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void delete(GlobalItemCategory category) {
		sessionFactory.getCurrentSession().delete(category);
	}

}
