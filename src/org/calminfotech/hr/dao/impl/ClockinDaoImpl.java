package org.calminfotech.hr.dao.impl;

import java.util.List;

import org.calminfotech.hr.daoInterface.ClockinDao;
import org.calminfotech.hr.models.Clockin;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ClockinDaoImpl implements ClockinDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Clockin getClockinAssgnmentById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from Clockin where userId = ?")
				.setParameter(0, id).list();
		if (list.size() > 0) {
			return (Clockin) list.get(0);
		}
		return null;
	}

	@Override
	public void save(Clockin clockin) {
		System.out.println("inside save");
		this.sessionFactory.getCurrentSession().save(clockin);
		System.out.println("save");
		this.sessionFactory.getCurrentSession().flush();
		System.out.println("flush");
		this.sessionFactory.getCurrentSession().clear();
		System.out.println("clear");
	}

	@Override
	public List<Clockin> deleteAllCheckedValues(Integer userId) {
		try {
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("spGetDeleteClockingCheckedVal")
					.setParameter("userid", userId);
			List result = query.list();
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error for parameters is: " + ex.getMessage());
		}

		return null;
	}


	/*@Override
	public Clockin getClockinById(int id) {
		List<Clockin> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Clockin where id = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<Clockin> fetchAllByOrganisation(int organisationId) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from Clockin where organisationId = ?")
				.setParameter(0,organisationId).list();
		
			return list;
	}

	

	@Override
	public Clockin getClockinByUnitId(int unitId) {
		List<Clockin> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Clockin where unitId = ?").setParameter(0, unitId)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}*/
	@Override
	public List<Clockin> fetchAllByUnitId(int unitId) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from Clockin where unitId = ? ")
				.setParameter(0,unitId).list();
		
			return list;
	}

}
