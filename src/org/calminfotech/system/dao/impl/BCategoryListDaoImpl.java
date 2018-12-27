package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.BCategoryListDao;
import org.calminfotech.system.models.BGlobalItem;
import org.calminfotech.system.models.BCategoryList;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BCategoryListDaoImpl implements BCategoryListDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<BCategoryList> fetchAll() {
		// TODO Auto-generated method stub

		List<BCategoryList> list = sessionFactory.getCurrentSession()
				.createQuery("from BCategoryList").list();
		return list;
	}

	@Override
	public BCategoryList getCategoryById(int id) {
		// TODO Auto-generated method stub
		List<BCategoryList> list = sessionFactory.getCurrentSession()
				.createQuery("from BCategoryList where rowId = ? ")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

}
