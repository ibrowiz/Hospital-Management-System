package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.RescheduledVisitDao;
import org.calminfotech.consultation.models.RescheduledVisit;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RescheduledVisitDaoImpl implements RescheduledVisitDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<RescheduledVisit> fetchAll() {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(RescheduledVisit.class);

		return criteria.list();
	}

	@Override
	public List<RescheduledVisit> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(RescheduledVisit.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", this.userIdentity
						.getOrganisation().getId()));

		return criteria.list();
	}

	@Override
	public RescheduledVisit getRescheduledVisitById(int id) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(RescheduledVisit.class)
				.add(Restrictions.eq("id", id))
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", this.userIdentity
						.getOrganisation().getId()));

		List list = criteria.list();
		if (list.size() > 0)
			return (RescheduledVisit) list.get(0);
		return null;
	}

	@Override
	public void save(RescheduledVisit rescheduledVisit) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(rescheduledVisit);
	}

	@Override
	public void delete(RescheduledVisit rescheduledVisit) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(rescheduledVisit);
	}

	@Override
	public void update(RescheduledVisit rescheduledVisit) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(rescheduledVisit);
	}

}
