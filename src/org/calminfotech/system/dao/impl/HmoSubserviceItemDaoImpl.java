package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.HmoSubserviceItemDao;
import org.calminfotech.system.models.HmoSubserviceItem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class HmoSubserviceItemDaoImpl implements HmoSubserviceItemDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public HmoSubserviceItem getHmoSubserviceItemById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from HmoSubserviceItem where id = ?")
				.setParameter(0, id).list();
		if (list.size() > 0) {
			return (HmoSubserviceItem) list.get(0);
		}
		return null;
	}

	@Override
	public void save(HmoSubserviceItem hmoSubserviceItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(hmoSubserviceItem);
		System.out.println("save");
		this.sessionFactory.getCurrentSession().flush();
		System.out.println("flush");
		this.sessionFactory.getCurrentSession().clear();
		System.out.println("clear");
	}

	@Override
	public List<HmoSubserviceItem> deleteAll(HmoSubserviceItem hmoSubserviceItem, Integer organisation) {
		// TODO Auto-generated method stub
		List<HmoSubserviceItem> list = this.sessionFactory.getCurrentSession()
				.createQuery("DELETE FROM HmoSubserviceItem where id = ? AND organisation = ? ")
				.setParameter("hmoSubserviceItem.id", hmoSubserviceItem)
				.setParameter("organisation.id", organisation).list();
		return list;
	}

	@Override
	public HmoSubserviceItem getHmoSubserviceItemByCategoryItem(int itemId,
			Integer organisation) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from HmoSubserviceItem where itemId = ? AND organisation = ? ")
				.setParameter(0, itemId)
				.setParameter(1, organisation).list();
		if (list.size() > 0) {
			return (HmoSubserviceItem) list.get(0);
		}
		return null;
	}

	@Override
	public HmoSubserviceItem checksubservice(int itemid, int packid) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from HmoSubserviceItem where itemId = ? and packId = ?")
				.setParameter(0, itemid)
				.setParameter(1, packid).list();
		if (list.size() > 0) {
			return (HmoSubserviceItem) list.get(0);
		}
		return null;
	}
}
