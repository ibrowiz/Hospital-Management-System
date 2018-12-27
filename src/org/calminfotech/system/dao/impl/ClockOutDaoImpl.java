package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.ClockOutDao;
import org.calminfotech.system.daoInterface.ClockingDao;
import org.calminfotech.system.models.ClockOut;
import org.calminfotech.system.models.Clocking;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class ClockOutDaoImpl implements ClockOutDao{

	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void save(ClockOut clockOut) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(clockOut);	
	}

	@Override
	public ClockOut getClockOutById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM ClockOut WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (ClockOut) list.get(0);

		return null;
	}

	@Override
	public void delete(ClockOut clockOut) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(clockOut);
	}

}
