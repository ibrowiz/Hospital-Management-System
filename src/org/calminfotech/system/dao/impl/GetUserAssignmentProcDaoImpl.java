package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.GetUserAssignmentProcDao;
import org.calminfotech.system.models.GetUserAssignmentProc;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GetUserAssignmentProcDaoImpl implements GetUserAssignmentProcDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<GetUserAssignmentProc> fetchUserPermission(Integer userId) {
		// call stored procedure using native createSQLQuery() method
		try {
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("spGetUserAssignment")
					.setParameter("userid", userId);
			List result = query.list();
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			sessionFactory.getCurrentSession().beginTransaction().rollback();
			System.out.println("Error for parameters is: " + ex.getMessage());
		}

		return null;
	}

}
