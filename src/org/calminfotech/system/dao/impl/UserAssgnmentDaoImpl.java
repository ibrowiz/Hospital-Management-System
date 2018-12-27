package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.UserAssgnmentDao;
import org.calminfotech.system.models.UserAssgnment;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserAssgnmentDaoImpl implements UserAssgnmentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserAssgnment getUserAssgnmentById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from UserAssgnment where userId = ?")
				.setParameter(0, id).list();
		if (list.size() > 0) {
			return (UserAssgnment) list.get(0);
		}
		return null;
	}

	@Override
	public void save(UserAssgnment userAssignment) {
		this.sessionFactory.getCurrentSession().save(userAssignment);
		System.out.println("save");
		this.sessionFactory.getCurrentSession().flush();
		System.out.println("flush");
		this.sessionFactory.getCurrentSession().clear();
		System.out.println("clear");
	}

	@Override
	public List<UserAssgnment> deleteAllUserCheckedValues(Integer userId) {
		// call stored procedure using native createSQLQuery() method
		try {
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("spGetDeleteUserCheckedVal")
					.setParameter("userid", userId);
			List result = query.list();
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error for parameters is: " + ex.getMessage());
		}

		return null;
	}

}
