package org.calminfotech.lab.dao.impl;

import java.util.List;

import org.calminfotech.lab.dao.LabTestListDao;
import org.calminfotech.lab.models.LabTestList;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LabTestListDaoImpl implements LabTestListDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public List<LabTestList> fetchAllByOrganisation(Integer organisationId) {
		@SuppressWarnings("unchecked")
		List<LabTestList> list = sessionFactory.getCurrentSession()
				   .createQuery("from LabTestList where organisationId = ?").setParameter(0,organisationId).list();
		return list;
	}

}
