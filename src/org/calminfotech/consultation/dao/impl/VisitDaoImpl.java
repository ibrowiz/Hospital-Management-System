package org.calminfotech.consultation.dao.impl;

import java.util.Date;
import java.util.List;

import org.calminfotech.consultation.dao.VisitDao;
import org.calminfotech.consultation.models.Consultation;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitLaboratoryTest;
import org.calminfotech.consultation.models.VisitPrescribedDrug;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.VisitStatus;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VisitDaoImpl implements VisitDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<Visit> fetchAll() {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Visit.class);

		List list = criteria.list();
		return list;
	}

	@Override
	public List<Visit> fetchAllByWorkflowPoint(String point) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(Visit.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", this.userIdentity
						.getOrganisation().getId()))
				.createAlias("point", "point")
				.add(Restrictions.eq("point.name", point));

		List list = criteria.list();
		return list;
	}

	@Override
	public List<Visit> fetchAllByWorkflowPoint(String point, VisitStatus status) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(Visit.class)
				.createAlias("status", "status")
				.add(Restrictions.eq("status.id", status.getId()))
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", this.userIdentity
						.getOrganisation().getId()))
				.createAlias("point", "point")
				.add(Restrictions.eq("point.name", point));

		List list = criteria.list();
		return list;
	}

	@Override
	public List<Visit> fetchAll(Consultation consultation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Visit.class)
				.createAlias("consultation", "consultation")
				.add(Restrictions.eq("consultation.id", consultation.getId()));

		List list = criteria.list();
		return list;
	}

	@Override
	public List<Visit> fetchAll(Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Visit.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));

		List list = criteria.list();
		return list;
	}

	@Override
	public List<Visit> fetchAll(Patient patient) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Visit.class).createAlias("patient", "patient")
				.add(Restrictions.eq("patient.patientId", patient.getPatientId()));

		List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}

	@Override
	public Visit getVisitationById(int id) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Visit.class).add(Restrictions.eq("id", id));

		List list = criteria.list();

		if (list.size() > 0)
			return (Visit) list.get(0);

		return null;
	}

	@Override
	public void save(Visit visit) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(visit);
	}

	@Override
	public void delete(Visit visit) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(visit);
	}

	@Override
	public void update(Visit visit) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(visit);
	}

	@Override
	public Visit getLastVisit1(Patient patient) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Visit.class)
				.addOrder(Order.desc("createDate"))
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.patientId", patient.getPatientId()));

		List list = criteria.list();
		if (list.size() > 0)
			return (Visit) list.get(0);

		return null;
	}

	@Override
	public Visit getLastVisit(Consultation consultation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Visit.class)
				.addOrder(Order.desc("createDate"))
				.createAlias("consultation", "consultation")
				.add(Restrictions.eq("consultation.id", consultation.getId()));

		List list = criteria.list();
		if (list.size() > 0)
			return (Visit) list.get(0);

		return null;
	}

	@Override
	public void clearLabTests(Visit visit) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(VisitLaboratoryTest.class)
				.createAlias("visit", "visit")
				.add(Restrictions.eq("visit.id", visit.getId()));
		
		List<VisitLaboratoryTest> list = criteria.list();
		for (VisitLaboratoryTest test : list)
			this.sessionFactory.getCurrentSession().delete(test);

	}

	@Override
	public void clearPrescribedDrugs(Visit visit) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(VisitPrescribedDrug.class)
				.createAlias("visit", "visit")
				.add(Restrictions.eq("visit.id", visit.getId()));
		
		List<VisitPrescribedDrug> list = criteria.list();
		for (VisitPrescribedDrug drug : list)
			this.sessionFactory.getCurrentSession().delete(drug);

	}

	@Override
	public List<Visit> fetchByPatientId(int patientId) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Visit> list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Visit WHERE patient_id = ? ORDER BY create_date DESC")
				.setParameter(0, patientId).list();
		return list;
	}

	@Override
	public List<Visit> fetchAllByUId(int userId) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Visit.class);
		criteria.add(Restrictions.eq("userId", userId));

		List list = criteria.list();
		return list;
	}

	@Override
	public List<Visit> fetchAllByThese(int userId,Date from, Date to, int mstatus, String chkothers) {
		
		if (chkothers == null){
			System.out.println("I have not been checked");
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Visit.class);
		
		int y =6;
		/*Date mydate = new Date(2015-05-10);
		Date mydate1 = new Date(2015-05-11);*/
		criteria.add(Restrictions.eq("point.id",y));
		criteria.add(Restrictions.eq("userId", userId));
		criteria.add(Restrictions.ge("createDate", from));
		criteria.add(Restrictions.le("createDate", to));
		criteria.add(Restrictions.eq("status.id",mstatus ));
		
		/*Conjunction objConjunction = Restrictions.conjunction();
		objConjunction.add(Restrictions.and(Restrictions.eq("point.id",y), 
				Restrictions.eq("userId", 46)));
		Date mydate = new Date(2015-05-10);
		Date mydate1 = new Date(2015-05-11);
		objConjunction.add(Restrictions.and(Restrictions.ge("createDate",mydate ), 
				Restrictions.le("createDate",mydate1 )));
		objConjunction.add(Restrictions.eq("status.id", 2));
		
		criteria.add(objConjunction);*/
	//	List list = criteria.list();
		//return list;
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		}
		
		else
		{
			System.out.println("I have been checked");
			Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Visit.class);
			//Conjunction objConjunction = Restrictions.conjunction();
			int y =6;
			criteria.add(Restrictions.eq("point.id",y));
			criteria.add(Restrictions.ge("createDate", from));
			criteria.add(Restrictions.le("createDate", to));
			criteria.add(Restrictions.eq("status.id", mstatus));
			
		//	List list = criteria.list();
			
			//return list;
			return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();	
		}
			
			
			
		
		
	}
	
	@Override
	public Visit getOnGoingConsultationByPatient(Patient patient) {
		// TODO Auto-generated method stub

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Visit.class)
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.patientId", patient.getPatientId()));
				// Patient restrictions
				/*.createAlias("status", "status")
				// Where end point is false
				.add(Restrictions.eq("status.endPoint", false));
*/
		List list = criteria.list();
		if (list.size() > 0)
			return (Visit) list.get(0);
		return null;
	}

	@Override
	public Visit getLastVisit(Visit visit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Visit getVisitByPatient(Patient patient) {
/*		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Visit.class)
				.add(Restrictions.eq("patient.patientId", patientId));

		List list = criteria.list();
		if (list.size() > 0)*/
		List<Visit> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Visit where patient = ?").setParameter(0,patient).list();
		if (list.size() > 0)
			return (Visit) list.get(0);

		return null;
	}

	@Override
	public Visit getVisitByCode(int visitcode) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Visit.class)
				.add(Restrictions.eq("code", visitcode));

		List list = criteria.list();
		if (list.size() > 0)
			return (Visit) list.get(0);

		return null;
	}

	@Override
	public Visit getLastVisit2(Patient patient) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Visit.class)
				.addOrder(Order.desc("createDate"))
				.createAlias("patient", "patient")
				.add(Restrictions.eq("patient.patientId", patient.getPatientId()));

		List list = criteria.list();
		if (list.size() > 0)
			return (Visit) list.get(0);

		return null;
	}

	@Override
	public Visit getVisitByCodvisie(int visitcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visit> fetchAllLabByThese(int userId, Patient patient,
			Date from, Date to, int mstatus, String chkothers) {
		if (chkothers == null){
			System.out.println("I have not been checked");
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Visit.class);
		
		int y =6;
		
		//Integer patients = 7;
		/*Date mydate = new Date(2015-05-10);
		Date mydate1 = new Date(2015-05-11);*/
		criteria.add(Restrictions.eq("point.id",y));
		criteria.add(Restrictions.eq("userId", userId));
		criteria.add(Restrictions.eq("patient", patient));
		criteria.add(Restrictions.ge("createDate", from));
		criteria.add(Restrictions.le("createDate", to));
		criteria.add(Restrictions.eq("status.id",mstatus ));
		
		/*Conjunction objConjunction = Restrictions.conjunction();
		objConjunction.add(Restrictions.and(Restrictions.eq("point.id",y), 
				Restrictions.eq("userId", 46)));
		Date mydate = new Date(2015-05-10);
		Date mydate1 = new Date(2015-05-11);
		objConjunction.add(Restrictions.and(Restrictions.ge("createDate",mydate ), 
				Restrictions.le("createDate",mydate1 )));
		objConjunction.add(Restrictions.eq("status.id", 2));
		
		criteria.add(objConjunction);*/
	//	List list = criteria.list();
		//return list;
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		}
		
		else
		{
			System.out.println("I have been checked");
			Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Visit.class);
			//Conjunction objConjunction = Restrictions.conjunction();
			int y =6;
			criteria.add(Restrictions.eq("point.id",y));
			criteria.add(Restrictions.eq("patient", patient));
			criteria.add(Restrictions.ge("createDate", from));
			criteria.add(Restrictions.le("createDate", to));
			criteria.add(Restrictions.eq("status.id", mstatus));
			
		//	List list = criteria.list();
			
			//return list;
			return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	}
}
	/*@Override
	public Visit getVisitByPatient(int patientId,int id) {
		DetachedCriteria maxId = DetachedCriteria.forClass(Visit.class)
			    .setProjection( Projections.max("id") );
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Visit.class)
				.add( Property.forName("id").eq(maxId) )
				.add(Restrictions.eq("patient.id", patientId));

		List list = criteria.list();
		if (list.size() > 0)
			return (Visit) list.get(0);

		return null;
	}
*/


