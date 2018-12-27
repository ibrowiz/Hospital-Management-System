package org.calminfotech.patient.dao.impl;

import java.util.List;

import org.calminfotech.patient.daoInterface.PatientAllergyDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.patient.models.Patient;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PatientAllergyDaoImpl implements PatientAllergyDao {

	@Autowired
	private SessionFactory sessionFactory;

	/*@Override
	public List<PatientAllergy> fetchAll() {
		return null;
	}*/

	@Override
	public PatientAllergy getByIdAndPallergyId(
			Patient patient, int id) {
		/*Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(PatientAllergy.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.id", patient.getPatientId()))
				.add(Restrictions.eq("id", id));*/
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientAllergy WHERE patient = ? AND id = ?").setParameter(0,patient).setParameter(1, id)
				.list();

		
		if (list.size() > 0)
			return (PatientAllergy) list.get(0);

		return null;
	}

	/*@Override
	public List<PatientAllergy> fetchAllPatientsByAllergy(int allergyId) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(PatientAllergy.class)
				.createAlias("allergy", "allergy")
				.add(Restrictions.eq("allergy.id", allergy.getAllergyId()));

		List list = criteria.list();
		return list;
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientAllergy WHERE allergyId = ?").setParameter(0,allergyId)
				.list();

		if (list.size() > 0)
			return (PatientAllergy) list.get(0);

		return null;
	}
*/
	@Override
	public PatientAllergy fetchAllAllergiesByPatient(Patient patient) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(PatientAllergy.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.id", patient.getPatientId()));

		List list = criteria.list();
		if (list.size() > 0)
			return (PatientAllergy) list.get(0);

		return null;
	}

	@Override
	public void save(PatientAllergy patientAllergy) {
		this.sessionFactory.getCurrentSession().save(patientAllergy);
	}

	@Override
	public void delete(PatientAllergy patientAllergy) {		
		this.sessionFactory.getCurrentSession().delete(patientAllergy);
	}

	@Override
	public void update(PatientAllergy patientAllergy) {
		this.sessionFactory.getCurrentSession().update(patientAllergy);
	}

	@Override
	public PatientAllergy getPatientAllergyById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM PatientAllergy WHERE id = ?").setParameter(0,id)
				.list();

		if (list.size() > 0)
			return (PatientAllergy) list.get(0);

		return null;
	}

	@Override
	public List<PatientAllergy> fetchAllpAllergiesByPatient(Patient patient) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(PatientAllergy.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.id", patient.getPatientId()));

		//List list = criteria.list();
		
		//return list;
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();	
		
	}
}
