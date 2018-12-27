package org.calminfotech.disease.dao.impl;

import java.util.List;
import org.calminfotech.disease.daoInterface.DiseaseCategoryViewDao;
import org.calminfotech.disease.models.DiseaseCategory;
import org.calminfotech.disease.models.DiseaseCategoryView;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DiseaseCategoryViewDaoImpl implements DiseaseCategoryViewDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public DiseaseCategoryView getDiseaseCatViewById(int id) {
		@SuppressWarnings("rawtypes")
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM DiseaseCategoryView WHERE diseaseCategoryId = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (DiseaseCategoryView) list.get(0);

		return null;
	}

	@Override
	public List<DiseaseCategoryView> fetchAllByOrganisation(int organisationid) {
		@SuppressWarnings("unchecked")
		List<DiseaseCategoryView> list = this.sessionFactory.getCurrentSession()
				   .createQuery("from DiseaseCategoryView where organisationId = ?").setParameter(0, organisationid).list();
		return list;
	}

	

}
