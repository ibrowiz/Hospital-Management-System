package org.calminfotech.patient.dao.impl;

import java.util.List;

import org.calminfotech.patient.daoInterface.PatientSearchDao;
import org.calminfotech.patient.forms.PatientSearchForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.Patient;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PatientSearchDaoImpl implements PatientSearchDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List searchPatient(PatientSearchForm patientForm) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Patient.class);
		
		System.out.println("the patlist" + criteria.list().size());
		
		//Search Criteria for Patient
		
		//Surname Criteria
		if(patientForm.getSurname() !=null && !patientForm.getSurname().isEmpty()){
			System.out.println("Surname is: "  + patientForm.getSurname());
			criteria.add(Restrictions.like("surname", "%" + patientForm.getSurname().trim() + "%").ignoreCase());
		}
		
		//Email Criteria
		if(patientForm.getEmail() !=null && !patientForm.getEmail().isEmpty()){
			criteria.add(Restrictions.like("email", "%" + patientForm.getEmail().trim() + "%").ignoreCase());
		}
		
		//Phone Number Criteria
		if(patientForm.getPhoneNumber() !=null && !patientForm.getPhoneNumber().isEmpty()){
			criteria.add(Restrictions.like("phoneNumber", "%" + patientForm.getPhoneNumber().trim() + "%").ignoreCase());
		}
		
	System.out.println("the patlist" + criteria.list().size());
		
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setMaxResults(100).list();
	}

}
