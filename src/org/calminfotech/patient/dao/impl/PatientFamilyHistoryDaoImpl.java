package org.calminfotech.patient.dao.impl;

import java.util.List;

import org.calminfotech.patient.daoInterface.PatientFamilyHistoryDao;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.patient.models.PatientFamilyHistory;
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
public class PatientFamilyHistoryDaoImpl implements PatientFamilyHistoryDao {
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PatientFamilyHistory> fetchAll() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientFamilyHistory.class).list();
		
	}

	@Override
	public List<PatientFamilyHistory> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientFamilyHistory.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PatientFamilyHistory> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(PatientFamilyHistory.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.patientId", patient.getPatientId()));

		//List list = criteria.list();
		
		//return list;
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();	
		
	}
		
		
	

	@Override
	public void save(PatientFamilyHistory patientFamilyHistory) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(patientFamilyHistory);
	}

	@Override
	public PatientFamilyHistory getPatientFamilyHistoryById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientFamilyHistory WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PatientFamilyHistory) list.get(0);

		return null;
	}

	@Override
	public void delete(PatientFamilyHistory patientFamilyHistory) {
		this.sessionFactory.getCurrentSession().delete(patientFamilyHistory);
	}

	@Override
	public void update(PatientFamilyHistory patientFamilyHistory) {
		this.sessionFactory.getCurrentSession().update(patientFamilyHistory);		
	}
	
	
	
	

}
