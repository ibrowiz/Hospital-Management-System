package org.calminfotech.hmo.dao.impl;

import java.util.List;

import org.calminfotech.hmo.daoInterface.EhmoItemDao;

import org.calminfotech.hmo.models.EhmoItem;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EhmoItemDaoImpl implements EhmoItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<EhmoItem> fetchAll() {
		List<EhmoItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("from EhmoItem").list();
		return list;
	}

	@Override
	public EhmoItem getEhmoItemById(int id) {
		// TODO Auto-generated method stub
		List<EhmoItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("from EhmoItem where item_id = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	
	

	@Override
	public void save(EhmoItem ehmoItem){
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(ehmoItem);
		//return ehmoItem;
		
	}
	
	@Override
	public void delete(EhmoItem ehmoItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(ehmoItem);

	}

	@Override
	public void update(EhmoItem ehmoItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(ehmoItem);

	}

}
