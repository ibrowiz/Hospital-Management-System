package org.calminfotech.disease.dao.impl;

import java.util.List;

import org.calminfotech.disease.daoInterface.DiseaseViewDao;
import org.calminfotech.disease.models.DiseaseView;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DiseaseViewDaoImpl implements DiseaseViewDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public DiseaseView getDiseaseViewById(int id) {
		@SuppressWarnings("unchecked")
		List<DiseaseView> list = this.sessionFactory.getCurrentSession()
				.createQuery("from DiseaseView where diseaseId = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<DiseaseView> fetchAllByOrganisation(int organisationid) {
		@SuppressWarnings("unchecked")
		List<DiseaseView> list = this.sessionFactory.getCurrentSession()
				   .createQuery("from DiseaseView where organisationId = ?").setParameter(0, organisationid).list();
		return list;
	}

}
