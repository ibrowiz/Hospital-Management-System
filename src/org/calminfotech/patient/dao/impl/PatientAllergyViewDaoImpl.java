package org.calminfotech.patient.dao.impl;

import java.util.List;

import org.calminfotech.patient.daoInterface.PatientAllergyViewDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.patient.models.PatientAllergyView;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PatientAllergyViewDaoImpl implements PatientAllergyViewDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PatientAllergyView> fetchAllByOrganisation(int organisationId) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from PatientAllergyView where organisationId = ?")
				.setParameter(0,organisationId).list();
			return list;
	}

	@Override
	public List<PatientAllergyView> getPatientAllergyByPatientId(Patient patient) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from PatientAllergyView where patient = ?")
				.setParameter(0,patient).list();
			return list;
	}

	@Override
	public PatientAllergyView fetchPatientAllergyByPatientId(Patient patient) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientAllergyView WHERE patient = ?").setParameter(0,patient)
				.list();

		
		if (list.size() > 0)
			return (PatientAllergyView) list.get(0);

		return null;
	}

}
