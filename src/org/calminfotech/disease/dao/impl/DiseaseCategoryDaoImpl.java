package org.calminfotech.disease.dao.impl;

import java.util.List;

import org.calminfotech.disease.daoInterface.DiseaseCategoryDao;
import org.calminfotech.disease.models.DiseaseCategory;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DiseaseCategoryDaoImpl implements DiseaseCategoryDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public DiseaseCategory getDiseaseCategoryById(int categoryId) {
		@SuppressWarnings("rawtypes")
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM DiseaseCategory WHERE diseaseCategoryId = ?")
				.setParameter(0, categoryId).list();

		if (list.size() > 0)
			return (DiseaseCategory) list.get(0);

		return null;
	}

	
	@Override
	public void save(DiseaseCategory diseaseCategory) {
		this.sessionFactory.getCurrentSession().save(diseaseCategory);
		
	}

	@Override
	public void update(DiseaseCategory diseaseCategory) {
		this.sessionFactory.getCurrentSession().update(diseaseCategory);
	}

	@Override
	public void delete(DiseaseCategory diseaseCategory) {
		this.sessionFactory.getCurrentSession().delete(diseaseCategory);
	}

	@Override
	public List<DiseaseCategory> fetchAllByOrganisation(int organisationId) {
		@SuppressWarnings("unchecked")
		List<DiseaseCategory> list = this.sessionFactory.getCurrentSession()
				   .createQuery("from DiseaseCategory where organisationId = ?").setParameter(0, organisationId).list();
		return list;
	}

}
