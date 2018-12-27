package org.calminfotech.hmo.dao.impl;

import java.util.List;

import org.calminfotech.hmo.daoInterface.EhmoPackageItemDao;

import org.calminfotech.hmo.models.EhmoPackageItem;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EhmoPackageItemDaoImpl implements EhmoPackageItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<EhmoPackageItem> fetchAll() {
		List<EhmoPackageItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("from EhmoPackageItem").list();
		return list;
	}

	@Override
	public EhmoPackageItem getEhmoItemById(int id) {
		// TODO Auto-generated method stub
		List<EhmoPackageItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("from EhmoPackageItem where itemId = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	
	

	@Override
	public void save(EhmoPackageItem ehmoItem){
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(ehmoItem);
		//return ehmoItem;
		
	}
	
	@Override
	public void delete(EhmoPackageItem ehmoItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(ehmoItem);

	}

	@Override
	public void update(EhmoPackageItem ehmoItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(ehmoItem);

	}

}
