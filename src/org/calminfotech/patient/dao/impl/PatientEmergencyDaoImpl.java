package org.calminfotech.patient.dao.impl;

import java.util.List;

import org.calminfotech.patient.daoInterface.PatientEmergencyDao;
import org.calminfotech.patient.models.PatientEmergency;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


	@Repository
	@Transactional
	public class PatientEmergencyDaoImpl implements PatientEmergencyDao{

		
		@Autowired
		private SessionFactory sessionFactory;
		
	@Override
	public List<PatientEmergency> fetchAll() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientEmergency.class).list();
	}

	@Override
	public List<PatientEmergency> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientEmergency.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.list();
	}

	@Override
	public List<PatientEmergency> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientEmergency.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.patientId", patient.getPatientId())).list();
	}

	@Override
	public void save(PatientEmergency patientEmergency) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(patientEmergency);
	}

	@Override
	public PatientEmergency getPatientEmergencyById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientEmergency WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PatientEmergency) list.get(0);

		return null;
	}

	@Override
	public void delete(PatientEmergency patientEmergency) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(patientEmergency);	
	}
	
	

}
