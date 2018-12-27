package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.BGetOuterrecursiveDao;
import org.calminfotech.system.models.BOuterRecursive;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BGetOuterrecursiveDaoImpl implements BGetOuterrecursiveDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<BOuterRecursive> fetchAllCategories() {
		// TODO Auto-generated method stub
		try {
			Query query = sessionFactory.getCurrentSession().getNamedQuery(
					"spGetAllCategories");
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
