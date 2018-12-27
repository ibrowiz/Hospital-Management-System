package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.AssetsDao;
import org.calminfotech.system.daoInterface.CategoryTypeDao;
import org.calminfotech.system.models.Assets;
import org.calminfotech.system.models.CategoryTypeXX;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryTypeDaoImpl implements CategoryTypeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<CategoryTypeXX> fetchAll() {
		List<CategoryTypeXX> list = sessionFactory.getCurrentSession()
								   .createQuery("from CategoryType").list();
		return list;
	}

	@Override
	public CategoryTypeXX getCategoryTypeById(int catTypeId) {
		List<CategoryTypeXX> list = sessionFactory.getCurrentSession()
				                   .createQuery("from CategoryType where categoryTypeId = ? ")
				                   .setParameter(0, catTypeId).list();
		if(list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(CategoryTypeXX categoryType) {
		sessionFactory.getCurrentSession().save(categoryType);
	}

	@Override
	public void update(CategoryTypeXX categoryType) {
		sessionFactory.getCurrentSession().update(categoryType);
	}

	@Override
	public void delete(CategoryTypeXX categoryType) {
		sessionFactory.getCurrentSession().delete(categoryType);
	}

}
