package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.VisitPaymentDao;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitPayment;
import org.calminfotech.patient.models.PatientEmergency;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class VisitPaymentDaoImpl  implements VisitPaymentDao{

	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<VisitPayment> fetchAll() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(VisitPayment.class).list();
	}


	@Override
	public void save(VisitPayment visitPayment) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(visitPayment);	
	}

	@Override
	public void delete(VisitPayment visitPayment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(VisitPayment visitPayment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<VisitPayment> fetchAllByOrgainsation(Organisation organisation) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(VisitPayment.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.list();
	}


	@Override
	public List<VisitPayment> fetchAllByVisit(Visit visit) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(VisitPayment.class)
				.createAlias("visit", "visit")
				.add(Restrictions.eq("visit.id", visit.getId())).list();
	}


	@Override
	public VisitPayment getVisitPaymentById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM VisitPayment WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (VisitPayment) list.get(0);

		return null;
	}
	
	
}
