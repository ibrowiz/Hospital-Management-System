package org.calminfotech.disease.dao.impl;

import java.util.List;

import org.calminfotech.disease.daoInterface.DiseaseCategoryListDao;
import org.calminfotech.disease.models.DiseaseCategoryList;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DiseaseCategoryListDaoImpl implements DiseaseCategoryListDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<DiseaseCategoryList> fetchAllByOrganisation(
			Integer organisationId) {
		@SuppressWarnings("unchecked")
		List<DiseaseCategoryList> list = sessionFactory.getCurrentSession()
				   .createQuery("from DiseaseCategoryList where organisationId = ?").setParameter(0,organisationId).list();
		return list;
	}

}
