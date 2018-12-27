package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.setup.models.AllergyCategoryView;
import org.calminfotech.system.daoInterface.VisitWorkflowPointDao;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VisitWorkflowPointDaoImpl implements VisitWorkflowPointDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<VisitWorkflowPoint> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		/*Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(VisitWorkflowPoint.class)
		.createAlias("organisation", "organisation")
		.add(Restrictions.eq("organisation.id", this.userIdentity
				.getOrganisation().getId()));

		List list = criteria.list();
		return list;*/
		List<VisitWorkflowPoint> list = this.sessionFactory.getCurrentSession()
				.createQuery("from VisitWorkflowPoint where organisation = ?")
				.setParameter(0, organisation).list();
		return list;
	}
	
	@Override
	public List<VisitWorkflowPoint> fetchFontDeskPoint(int id ,int section) {
		// TODO Auto-generated method stub
		List<VisitWorkflowPoint> list = this.sessionFactory.getCurrentSession()
				.createQuery("from VisitWorkflowPoint where id != ? and loginSection = ? ")
				.setParameter(0, id)
				.setParameter(1, section).list();
		return list;
	}

	@Override
	public VisitWorkflowPoint getWorkflowPointById(int id) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(VisitWorkflowPoint.class)
				.add(Restrictions.eq("id", id));
		List list = criteria.list();

		if (list.size() > 0)
			return (VisitWorkflowPoint) list.get(0);
		return null;
	}

	@Override
	public void save(VisitWorkflowPoint workflowPoint) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(workflowPoint);
	}

	@Override
	public void delete(VisitWorkflowPoint workflowPoint) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(workflowPoint);
	}

	@Override
	public void update(VisitWorkflowPoint workflowPoint) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(workflowPoint);
	}

	/*@Override
	public VisitWorkflowPoint getWorkflowStartPoint() {
		// TODO Auto-generated method stub

		Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(VisitWorkflowPoint.class)
				.add(Restrictions.eq("startPoint", true))
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", this.userIdentity
						.getOrganisation().getId()));

		List list = criteria.list();
		if (list.size() > 0)
			return (VisitWorkflowPoint) list.get(0);
		return null;
	}*/
	
	@Override
	public VisitWorkflowPoint getWorkflowStartPoint(Organisation organisation) {
		// TODO Auto-generated method stub
		List<VisitWorkflowPoint> list = this.sessionFactory.getCurrentSession()
				.createQuery("from VisitWorkflowPoint where organisation_id = ? and name = 'Vitals'").setParameter(0,27)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;

		/*Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(VisitWorkflowPoint.class)
				.add(Restrictions.eq("startPoint", true))
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", this.userIdentity
						.getOrganisation().getId()));

		List list = criteria.list();
		if (list.size() > 0)
			return (VisitWorkflowPoint) list.get(0);
		return null;*/
	}

	@Override
	public VisitWorkflowPoint getWorkflowEndPoint() {
		// TODO Auto-generated method stub

		Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(VisitWorkflowPoint.class)
				.add(Restrictions.eq("endPoint", true))
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", this.userIdentity
						.getOrganisation().getId()));

		List list = criteria.list();
		if (list.size() > 0)
			return (VisitWorkflowPoint) list.get(0);
		return null;
	}

	@Override
	public VisitWorkflowPoint getPointByName(String string) {
		// TODO Auto-generated method stub

		Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(VisitWorkflowPoint.class)
				.add(Restrictions.eq("name", string))
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", this.userIdentity
						.getOrganisation().getId()));

		List list = criteria.list();
		if (list.size() > 0)
			return (VisitWorkflowPoint) list.get(0);
		return null;
	}

	/*@Override
	public List<VisitWorkflowPoint> getvWorkflowPointByUnitId(Unit unit) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from VisitWorkflowPoint where unit = ? ")
				.setParameter(0, unit).list();
		return list;
	}*/
}
