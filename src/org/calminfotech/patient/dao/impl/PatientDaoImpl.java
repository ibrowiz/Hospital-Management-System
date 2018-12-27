package org.calminfotech.patient.dao.impl;

import java.util.List;

import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.RelatedPatient;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PatientDaoImpl implements PatientDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Patient> fetchAll() {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Patient.class)
				.addOrder(Order.asc("createDate"));
		List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}
	
	
	/*@Override
	public List<RelatedPatient> fetchAll(Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria("from RelatedPatient where organisation = ? and status = 'active'")
				.addOrder(Order.asc("createDate"));
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}*/

	@Override
	public List<Patient> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Patient.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.addOrder(Order.desc("patientId"));
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setMaxResults(300).list();
	}

	@Override
	public Patient getPatientById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Patient WHERE patientId = ?").setParameter(0, id)
				.list();

		if (list.size() > 0)
			return (Patient) list.get(0);

		return null;
	}

	@Override
	public void save(Patient patient) {
		this.sessionFactory.getCurrentSession().save(patient);
	}

	@Override
	public void delete(Patient patient) {
		this.sessionFactory.getCurrentSession().delete(patient);

	}

	@Override
	public void update(Patient patient) {
		this.sessionFactory.getCurrentSession().update(patient);
	}

	@Override
	public Patient findByBirthDay(String patientDob) {
		List<Patient> list = sessionFactory
				.getCurrentSession()
				.createQuery("from Patient where patientId = (select max(id) from Patient where dob = ?)")
				.setParameter(0, patientDob)
				.list();				
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public Patient checkIfPatientIdExist(String patientId) {	

		List<Patient> list = sessionFactory
							.getCurrentSession()
							.createQuery("from Patient where patientId = ? ")
							.setParameter(0, patientId).list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}	
	
}
