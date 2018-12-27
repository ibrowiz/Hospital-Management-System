package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.ClockDao;
import org.calminfotech.system.models.Clock;

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
public class ClockDaoImpl implements ClockDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Override
	public List<Clock> fetchAllClock() {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from Clock").list();
		
		return (List<Clock>)list;
	}

	@Override
	public List<Clock> fetchAllByOrganisation(Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Clock.class)
		.createAlias("organisation", "organisation")
		 .add(Restrictions.eq("organisation.id", organisation.getId()));
	
		List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}

	@Override
	public Clock getClockById(Integer id) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Clock.class).add(Restrictions.eq("id", id));

		List list = criteria.list();

		if (list.size() > 0)
			return (Clock) list.get(0);

		return null;
	}

	@Override
	public void save(Clock clock) {
		this.sessionFactory.getCurrentSession().save(clock);
	}

	@Override
	public void update(Clock clock) {
		this.sessionFactory.getCurrentSession().update(clock);
	}

	@Override
	public void delete(Clock clock) {
		this.sessionFactory.getCurrentSession().delete(clock);
	}

}
