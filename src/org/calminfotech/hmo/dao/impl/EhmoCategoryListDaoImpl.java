package org.calminfotech.hmo.dao.impl;

import java.util.List;

import org.calminfotech.hmo.daoInterface.EhmoCategoryListDao;
import org.calminfotech.hmo.models.EhmoCategoryList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EhmoCategoryListDaoImpl implements EhmoCategoryListDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<EhmoCategoryList> fetchAll() {
		List<EhmoCategoryList> list = this.sessionFactory.getCurrentSession()
				.createQuery("from EhmoCategoryList").list();
		return list;
	}
	

	@Override
	public EhmoCategoryList getEhmoCategoryListById(int id) {
		List<EhmoCategoryList> list = this.sessionFactory.getCurrentSession()
				.createQuery("from EhmoCategoryList where rowid = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	

	@Override
	public EhmoCategoryList save(EhmoCategoryList ehmoCategoryList) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(ehmoCategoryList);
		return ehmoCategoryList;
		
	}
	
	@Override
	public void delete(EhmoCategoryList ehmoCategoryList) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(ehmoCategoryList);

	}

	@Override
	public void update(EhmoCategoryList ehmoCategoryList) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(ehmoCategoryList);

	}

}
