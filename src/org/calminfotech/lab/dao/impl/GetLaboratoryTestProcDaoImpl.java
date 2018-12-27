package org.calminfotech.lab.dao.impl;

import java.util.List;

import org.calminfotech.lab.dao.GetLaboratoryTestProcDao;
import org.calminfotech.lab.models.GetLaboratoryTestProc;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class GetLaboratoryTestProcDaoImpl implements GetLaboratoryTestProcDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GetLaboratoryTestProc> fetchResult(Integer testId) {
		try{
		//	System.out.println("I am in Dao");
			
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("splaboratorytest")
					.setParameter("testid", testId);
			System.out.println("the size is" + query.list().size());
			List<GetLaboratoryTestProc> result = query.list();
			System.out.println("the size is" + query.list().size());
			return result;
		}
		catch(Exception ex){
			ex.printStackTrace();
			///sessionFactory.getCurrentSession().beginTransaction().rollback();
			System.out.println("Error for parameters is: " + ex.getMessage());
		}
		
		return null;
	}

}
