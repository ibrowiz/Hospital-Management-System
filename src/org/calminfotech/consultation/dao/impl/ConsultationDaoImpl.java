package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.ConsultationDao;
import org.calminfotech.consultation.models.Consultation;
import org.calminfotech.patient.models.Patient;
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
@Transactional
public class ConsultationDaoImpl implements ConsultationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<Consultation> fetchAll() {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createCriteria(Consultation.class)
				.addOrder(Order.asc("createDate")).list();
		return list;
	}

	@Override
	public List<Consultation> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createCriteria(Consultation.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.addOrder(Order.asc("createDate")).list();
		System.out.println(list.size());
		return list;
	}

	@Override
	public Consultation getConsultationById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createCriteria(Consultation.class)
				.add(Restrictions.eq("id", id)).list();

		if (list.size() > 0)
			return (Consultation) list.get(0);

		return null;
	}

	@Override
	public void save(Consultation consultation) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(consultation);
	}

	@Override
	public void delete(Consultation consultation) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(consultation);
	}

	@Override
	public void update(Consultation consultation) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(consultation);
	}

	@Override
	public Consultation getOnGoingConsultationByPatient(Patient patient) {
		// TODO Auto-generated method stub

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Consultation.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.id", patient.getPatientId()))
				// Patient restrictions
				.createAlias("status", "status")
				// Where end point is false
				.add(Restrictions.eq("status.endPoint", false));

		List list = criteria.list();
		if (list.size() > 0)
			return (Consultation) list.get(0);
		return null;
	}

	@Override
	public Consultation getLastConsultation() {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(Consultation.class)
				.addOrder(Order.desc("createDate"))
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", this.userIdentity
						.getOrganisation().getId()));
		List<Consultation> list = criteria.list();
		if (list.size() > 0)
			return (Consultation) list.get(0);
		
		return null;
	}
}
