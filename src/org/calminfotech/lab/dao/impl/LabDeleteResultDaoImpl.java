package org.calminfotech.lab.dao.impl;

import java.util.List;

import org.calminfotech.lab.dao.LabDeleteResultDao;
import org.calminfotech.lab.models.LabDeleteResult;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LabDeleteResultDaoImpl implements LabDeleteResultDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<LabDeleteResult> deleteResult(Integer testId) {
		// call stored procedure using native createSQLQuery() method
				try {
					Query query = sessionFactory.getCurrentSession()
							.getNamedQuery("spGetDeleteResult")
							.setParameter("testid", testId);
					List result = query.list();
				
					/*sessionFactory.getCurrentSession().close();*/
				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("Error for parameters is: " + ex.getMessage());
				}

				return null;
				
				
	}

}
