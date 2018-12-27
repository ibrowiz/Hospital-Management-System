package org.calminfotech.patient.dao.impl;

import java.util.List;

import org.calminfotech.patient.daoInterface.PatientSocialHistoryDao;
import org.calminfotech.patient.forms.PatientSocialHistoryForm;
import org.calminfotech.patient.models.PatientSocialHistory;
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
public class PatientSocialHistoryDaoImpl implements PatientSocialHistoryDao{
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PatientSocialHistory> fetchAll() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientSocialHistory.class).list();
	}

	@Override
	public List<PatientSocialHistory> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(PatientSocialHistory.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.list();
	}

	@Override
	public List<PatientSocialHistory> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(PatientSocialHistory.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.patientId", patient.getPatientId()));
		
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public void save(PatientSocialHistory patientSocialHistory) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(patientSocialHistory);
	}

	@Override
	public PatientSocialHistory getPatientSocialHistoryById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientSocialHistory WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (PatientSocialHistory) list.get(0);

		return null;
	}

	@Override
	public void delete(PatientSocialHistory patientSocialHistory) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(patientSocialHistory);	
	}

	
	
	

}
