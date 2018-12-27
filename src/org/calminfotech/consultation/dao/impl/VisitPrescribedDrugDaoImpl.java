package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.VisitPrescribedDrugDao;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitLaboratoryTest;
import org.calminfotech.consultation.models.VisitPrescribedDrug;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class VisitPrescribedDrugDaoImpl implements VisitPrescribedDrugDao{

	
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<VisitPrescribedDrug> fetchAll() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(VisitPrescribedDrug.class).list();
	}

	@Override
	public List<VisitPrescribedDrug> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(VisitPrescribedDrug.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.list();
	}

	@Override
	public List<VisitPrescribedDrug> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(VisitPrescribedDrug.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.id", patient.getPatientId())).list();
	}

	@Override
	public List<VisitPrescribedDrug> fetchAllByVisit(Visit visit) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(VisitPrescribedDrug.class)
				.createAlias("visit", "visit")
				.add(Restrictions.eq("visit.id", visit.getId())).list();
	}

	@Override
	public void save(VisitPrescribedDrug visitPrescribedDrug) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(visitPrescribedDrug);	
	}

	@Override
	public VisitPrescribedDrug getVisitPrescribedDrugById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM VisitPrescribedDrug WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (VisitPrescribedDrug) list.get(0);

		return null;
	}

	@Override
	public void delete(VisitPrescribedDrug visitPrescribedDrug) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(visitPrescribedDrug);
	}

}
