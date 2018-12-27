package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.VisitPresentingCompliantDao;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitPresentingComplaint;
import org.calminfotech.patient.models.PatientMedicalHistory;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VisitPresentingCompliantDaoImpl implements VisitPresentingCompliantDao{

	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<VisitPresentingComplaint> fetchAll() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(VisitPresentingComplaint.class).list();
	}

	@Override
	public List<VisitPresentingComplaint> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(VisitPresentingComplaint.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.list();
	}

	@Override
	public List<VisitPresentingComplaint> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(VisitPresentingComplaint.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.id", patient.getPatientId())).list();
	}

	@Override
	public List<VisitPresentingComplaint> fetchAllByVisit(Visit visit) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(VisitPresentingComplaint.class)
				.createAlias("visit", "visit")
				.add(Restrictions.eq("visit.id", visit.getId())).list();
	}

	@Override
	public void save(VisitPresentingComplaint visitPresentingComplaint) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(visitPresentingComplaint);
	}

	@Override
	public VisitPresentingComplaint getVisitPresentingComplaintById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM VisitPresentingComplaint WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (VisitPresentingComplaint) list.get(0);

		return null;
	}

	@Override
	public void delete(VisitPresentingComplaint visitPresentingComplaint) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(visitPresentingComplaint);
	}
	
	
	
	
	

}
