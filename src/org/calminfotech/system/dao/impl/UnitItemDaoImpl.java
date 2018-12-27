package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.UnitItemDao;
import org.calminfotech.system.models.UnitItem;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UnitItemDaoImpl implements UnitItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(UnitItem unitItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(unitItem);
	}

	@Override
	public UnitItem getItemById(Integer id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from UnitItem where id = ?").setParameter(0, id)
				.list();
		if (list.size() > 0) {
			return (UnitItem) list.get(0);
		}
		return null;
	}

	@Override
	public void deleteUnitItem(UnitItem unitItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(unitItem);
	}

	@Override
	public void edit(UnitItem unitItem) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(unitItem);
	}

	@Override
	public List<UnitItem> fetchItemByUnit(UnitItem unitItem) {
		// TODO Auto-generated method stub
		try {
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("spGetassignedunit")
					.setParameter("unitId", unitItem);
			List result = query.list();
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error for parameters is: " + ex.getMessage());
		}

		return null;
	}

	@Override
	public UnitItem getByUnitIdAndItemId(Integer unitId, Integer itemId) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from UnitItem where unitId = ? and itemId = ? ")
				.setParameter(0, unitId)
				.setParameter(1, itemId).list();
		if(list.size()>0){
			return (UnitItem) list.get(0);
		}
		return null;
	}
}
