package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.LaboratoryDeleteResultDao;
import org.calminfotech.system.models.LaboratoryDeleteResult;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LaboratoryDeleteResultDaoImpl implements LaboratoryDeleteResultDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<LaboratoryDeleteResult> deleteResult(Integer testId) {
		// call stored procedure using native createSQLQuery() method
				try {
					Query query = sessionFactory.getCurrentSession()
							.getNamedQuery("sGetDeleteResult")
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
