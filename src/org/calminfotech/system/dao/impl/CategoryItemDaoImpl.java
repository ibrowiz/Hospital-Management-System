package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.CategoryItemDao;
import org.hibernate.SessionFactory;
import org.calminfotech.system.models.CategoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryItemDaoImpl implements CategoryItemDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<CategoryItem> fetchAll() {
		List<CategoryItem> list = sessionFactory.getCurrentSession()
								   .createQuery("from CategoryItem").list();
		return list;
	}

	@Override
	public CategoryItem getCategoryItemById(int itemId) {
		List<CategoryItem> list = sessionFactory.getCurrentSession()
				                   .createQuery("from CategoryItem where itemId = ? ")
				                   .setParameter(0, itemId).list();
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(CategoryItem item) {
		sessionFactory.getCurrentSession().save(item);
	}

	@Override
	public void update(CategoryItem item) {
		sessionFactory.getCurrentSession().update(item);
	}

	@Override
	public void delete(CategoryItem item) {
		sessionFactory.getCurrentSession().delete(item);
	}

	

	

}
