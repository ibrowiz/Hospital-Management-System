package org.calminfotech.hr.dao.impl;

import java.util.List;

import org.calminfotech.hr.daoInterface.GetClockingUnitProcDao;
import org.calminfotech.hr.models.GetClockingUnitProc;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class GetClockingUnitProcDaoImpl implements GetClockingUnitProcDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<GetClockingUnitProc> fetchClockinUnit(Integer userId) {
		try {
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("spGetClockingUnitProc")
					.setParameter("userid", userId);
			List result = query.list();
			System.out.println("clocking size is" + result.size());
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("error msg" + ex.getMessage());

			//sessionFactory.getCurrentSession().beginTransaction().rollback();
			System.out.println("Error for parameters is: " + ex.getMessage());
		}

		return null;
	}


}
