package org.calminfotech.patient.dao.impl;

import java.util.List;

import org.calminfotech.patient.daoInterface.RelatedPatientDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.RelatedPatient;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RelatedPatientDaoImpl implements RelatedPatientDao {
	
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	/*@Override
	public List<RelatedPatient> fetchAll() {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(RelatedPatient.class);
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}*/
	
	@Override
	public List<RelatedPatient> fetchAll(Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria("from RelatedPatient where organisation = ? and status = 'active'")
				.addOrder(Order.asc("createDate"));
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}

	@Override
	public List<RelatedPatient> fetchAllByOrganisation(Organisation organisation) {
		List list = this.sessionFactory.getCurrentSession()
				.createCriteria(RelatedPatient.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.addOrder(Order.asc("createDate")).list();
		return list;
	}

	@Override
	public RelatedPatient getRelatedPatientByPatientId(Patient patient) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from RelatedPatient where patient = ? ")
				.setParameter(0, patient).list();
		if (list.size() > 0)
			return (RelatedPatient)list.get(0);
		
		return null;
	}

	@Override
	public RelatedPatient getRelPatientById(int id) {
		

		System.out.println("Allergy Patient Id: " + id);
		
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM RelatedPatient WHERE Id = ?").setParameter(0, id)
				.list();
		System.out.println("list size: " +list.size());
		if (list.size() > 0)
			return (RelatedPatient) list.get(0);
		return null;
	}

	@Override
	public RelatedPatient getRelPatientByPatientAndRel(Patient patient, int RelPatId) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM RelatedPatient WHERE patient = ? AND relPatientId = ?").setParameter(0, patient).setParameter(1, RelPatId)
				.list();
		System.out.println("list size: " +list.size());
		if (list.size() > 0)
			return (RelatedPatient) list.get(0);
		return null;
	}
	
	@Override
	public void save(RelatedPatient relPatient) {
		this.sessionFactory.getCurrentSession().save(relPatient);
	}

	@Override
	public void delete(RelatedPatient relPatient) {
		this.sessionFactory.getCurrentSession().delete(relPatient);
	}

	@Override
	public void update(RelatedPatient relPatient) {
		this.sessionFactory.getCurrentSession().update(relPatient);
	}

	@Override
	public RelatedPatient findByBirthDay(String subscriberDob) {
		List<RelatedPatient> list = sessionFactory
				.getCurrentSession()
				.createQuery("from RelatedPatient where relPatientId = (select max(relPatientId) from RelatedPatient where dob = ?)")
				.setParameter(0, subscriberDob)
				.list();				
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public RelatedPatient checkIfPatientIdExist(String patientId) {
		List<RelatedPatient> list = sessionFactory
				.getCurrentSession()
				.createQuery("from RelatedPatient where relPatientId = ? ")
				.setParameter(0, patientId).list();
if (list.size() > 0)
return list.get(0);
return null;
	}

	@Override
	public List<RelatedPatient> fetchRelatedPatientByPatientId(Patient patient) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from RelatedPatient where patient = ? ")
				.setParameter(0, patient).list();
			return list;
		
	}

	

}
