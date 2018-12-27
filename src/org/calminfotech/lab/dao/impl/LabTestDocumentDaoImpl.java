package org.calminfotech.lab.dao.impl;

import java.util.List;


import org.calminfotech.lab.dao.LabTestDocumentDao;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.lab.models.LabTestDocument;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public class LabTestDocumentDaoImpl implements LabTestDocumentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabTestDocument> fetchAll() {
		return this.sessionFactory.getCurrentSession()
				.createCriteria(LabTestDocument.class).list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<LabTestDocument> fetchAllByOrgainsation(
			Organisation organisation) {
		return this.sessionFactory.getCurrentSession()
				.createCriteria(LabTestDocument.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabTestDocument> fetchAllByTest(LabTest LabTest) {
		return this.sessionFactory.getCurrentSession()
				.createCriteria(LabTestDocument.class)
				.createAlias("labTest", "labTest")
				.add(Restrictions.eq("labTest.id", LabTest.getId())).list();
	}

	@Override
	public void save(LabTestDocument labDocument) {
		this.sessionFactory.getCurrentSession().save(labDocument);
	}

	@Override
	public LabTestDocument getLabDocumentById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM LabTestDocument WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (LabTestDocument) list.get(0);

		return null;
	}

	@Override
	public void delete(LabTestDocument labDocument) {
		this.sessionFactory.getCurrentSession().delete(labDocument);
	}

}
