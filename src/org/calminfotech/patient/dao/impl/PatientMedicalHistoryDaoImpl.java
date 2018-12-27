package org.calminfotech.patient.dao.impl;

import java.util.List;

import org.calminfotech.patient.daoInterface.PatientMedicalHistoryDao;
import org.calminfotech.patient.models.PatientMedicalHistory;
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
	public class PatientMedicalHistoryDaoImpl implements PatientMedicalHistoryDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<PatientMedicalHistory> fetchAll() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientMedicalHistory.class).list();
	}

	@Override
	public List<PatientMedicalHistory> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientMedicalHistory.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.list();
	}

	@Override
	public List<PatientMedicalHistory> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(PatientMedicalHistory.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.patientId", patient.getPatientId()));
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();	
	}

	@Override
	public void save(PatientMedicalHistory patientMedicalHistory) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(patientMedicalHistory);
	}

	@Override
	public PatientMedicalHistory getPatientMedicalHistoryById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientMedicalHistory WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PatientMedicalHistory) list.get(0);

		return null;
	}

	@Override
	public void delete(PatientMedicalHistory patientMedicalHistory) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(patientMedicalHistory);
	}

	@Override
	public void update(PatientMedicalHistory patientMedicalHistory) {
		this.sessionFactory.getCurrentSession().update(patientMedicalHistory);		
	}

}
