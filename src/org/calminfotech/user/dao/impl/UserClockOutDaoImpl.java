package org.calminfotech.user.dao.impl;

import java.util.List;

import org.calminfotech.user.daoInterface.UserClockOutDao;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserClockOut;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class UserClockOutDaoImpl implements UserClockOutDao{


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void save(UserClockOut userClockOut) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(userClockOut);
	}

	@Override
	public void delete(UserClockOut userClockOut) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(userClockOut);
	}

	@Override
	public void update(UserClockOut userClockOut) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(userClockOut);
	}

	@Override
	public UserClockOut getUserById(int userId) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from UserClockOut where userId = ?")
				.setParameter(0, userId).list();

		if (list.size() > 0)
			return (UserClockOut) list.get(0);
		return null;
	}

}
