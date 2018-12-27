package org.calminfotech.hmo.dao.impl;

import java.util.List;

import org.calminfotech.hmo.daoInterface.ItemServiceGroupDao;
import org.calminfotech.hmo.models.ItemServiceGroup;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ItemServiceGroupDaoImpl implements ItemServiceGroupDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ItemServiceGroup> fetchAll() {
		List<ItemServiceGroup> list = this.sessionFactory.getCurrentSession()
				.createQuery("from ItemServiceGroup").list();
		return list;
	}
	

	@Override
	public ItemServiceGroup getItemServiceGroupById(int id) {
		List<ItemServiceGroup> list = this.sessionFactory.getCurrentSession()
				.createQuery("from ItemServiceGroup where itemServiceId = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	

	@Override
	public void save(ItemServiceGroup itemServiceGroup) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(itemServiceGroup);
		//return itemServiceGroup;
		
	}
	
	@Override
	public void delete(ItemServiceGroup itemServiceGroup) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(itemServiceGroup);

	}

	@Override
	public void update(ItemServiceGroup itemServiceGroup) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(itemServiceGroup);

	}

}
