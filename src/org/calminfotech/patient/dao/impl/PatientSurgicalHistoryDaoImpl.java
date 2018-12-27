package org.calminfotech.patient.dao.impl;

import java.util.List;

import org.calminfotech.patient.daoInterface.PatientSurgicalHistoryDao;
import org.calminfotech.patient.models.PatientMedicalHistory;
import org.calminfotech.patient.models.PatientSurgicalHistory;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class PatientSurgicalHistoryDaoImpl implements PatientSurgicalHistoryDao{

	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<PatientSurgicalHistory> fetchAll() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientSurgicalHistory.class).list();
	}

	@Override
	public List<PatientSurgicalHistory> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientSurgicalHistory.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.list();
	}

	@Override
	public List<PatientSurgicalHistory> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		Criteria criteria =  this.sessionFactory.getCurrentSession()
				.createCriteria(PatientSurgicalHistory.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.patientId", patient.getPatientId()));
		
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public void save(PatientSurgicalHistory patientSurgicalHistory) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(patientSurgicalHistory);
	}

	@Override
	public PatientSurgicalHistory getPatientSurgicalHistoryById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientSurgicalHistory WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PatientSurgicalHistory) list.get(0);

		return null;
	}

	@Override
	public void delete(PatientSurgicalHistory patientSurgicalHistory) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(patientSurgicalHistory);	
	}

}
