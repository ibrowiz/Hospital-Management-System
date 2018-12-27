package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.VisitLaboratoryTestDao;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitLaboratoryTest;
import org.calminfotech.consultation.models.VisitPresentingComplaint;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class VisitLaboratoryTestDaoImpl implements VisitLaboratoryTestDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<VisitLaboratoryTest> fetchAll() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(VisitLaboratoryTest.class).list();
	}

	@Override
	public List<VisitLaboratoryTest> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(VisitLaboratoryTest.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.list();
	}

	@Override
	public List<VisitLaboratoryTest> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(VisitLaboratoryTest.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.id", patient.getPatientId())).list();
	}

	@Override
	public List<VisitLaboratoryTest> fetchAllByVisit(Visit visit) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(VisitLaboratoryTest.class)
				.createAlias("visit", "visit")
				.add(Restrictions.eq("visit.id", visit.getId())).list();
	}

	@Override
	public void save(VisitLaboratoryTest visitLaboratoryTest) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(visitLaboratoryTest);
	}

	@Override
	public VisitLaboratoryTest getVisitLaboratoryTestById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM VisitLaboratoryTest WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (VisitLaboratoryTest) list.get(0);

		return null;
	}

	@Override
	public void delete(VisitLaboratoryTest visitLaboratoryTest) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(visitLaboratoryTest);
	}

}
