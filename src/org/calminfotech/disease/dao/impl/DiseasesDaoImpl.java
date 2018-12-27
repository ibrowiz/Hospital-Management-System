package org.calminfotech.disease.dao.impl;

import java.util.List;

import org.calminfotech.disease.daoInterface.DiseasesDao;
import org.calminfotech.disease.models.Diseases;
import org.calminfotech.setup.models.Allergy;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DiseasesDaoImpl implements DiseasesDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Diseases getDiseaseById(int id) {
		@SuppressWarnings("unchecked")
		List<Diseases> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Diseases where diseaseId = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<Diseases> fetchDiseaseById(Integer id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from Diseases where diseaseId = ?").setParameter(0, id)
				.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Diseases> fetchAllByOrganisation(int organisationId) {
		@SuppressWarnings("rawtypes")
		List list = sessionFactory.getCurrentSession()
				.createQuery("from Diseases where organisationId = ?")
				.setParameter(0,organisationId).list();
		
			return list;
	}

	@Override
	public void save(Diseases diseases) {
		this.sessionFactory.getCurrentSession().save(diseases);
	}

	@Override
	public void delete(Diseases diseases) {
		this.sessionFactory.getCurrentSession().delete(diseases);
	}

	@Override
	public void update(Diseases diseases) {
		this.sessionFactory.getCurrentSession().update(diseases);
	}

}
