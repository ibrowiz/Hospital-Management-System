package org.calminfotech.patient.dao.impl;

import java.util.List;

import org.calminfotech.patient.daoInterface.PatientHmoDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientHmoBillSchemeView;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class PatientHmoDaoImpl implements PatientHmoDao  {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PatientHmoBillSchemeView> fetchPatientHmoByPatient(Patient patient) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(PatientHmoBillSchemeView.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.patientId", patient.getPatientId()));

		//List list = criteria.list();
		
		//return list;
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();	
	}

}
