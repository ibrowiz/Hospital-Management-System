package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.LaboratoryTestDocumentDaoInter;
import org.calminfotech.consultation.models.LaboratoryTestDocument;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public class LaboratoryTestDocumentDaoImpl implements LaboratoryTestDocumentDaoInter {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LaboratoryTestDocument> fetchAll() {
		return this.sessionFactory.getCurrentSession()
				.createCriteria(LaboratoryTestDocument.class).list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<LaboratoryTestDocument> fetchAllByOrgainsation(
			Organisation organisation) {
		return this.sessionFactory.getCurrentSession()
				.createCriteria(LaboratoryTestDocument.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LaboratoryTestDocument> fetchAllByTest(LabTest LabTest) {
		return this.sessionFactory.getCurrentSession()
				.createCriteria(LaboratoryTestDocument.class)
				.createAlias("labTest", "labTest")
				.add(Restrictions.eq("labTest.id", LabTest.getId())).list();
	}

	@Override
	public void save(LaboratoryTestDocument labDocument) {
		this.sessionFactory.getCurrentSession().save(labDocument);
	}

	@Override
	public LaboratoryTestDocument getLabDocumentById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM LaboratoryTestDocument WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (LaboratoryTestDocument) list.get(0);

		return null;
	}

	@Override
	public void delete(LaboratoryTestDocument labDocument) {
		this.sessionFactory.getCurrentSession().delete(labDocument);
	}

}
