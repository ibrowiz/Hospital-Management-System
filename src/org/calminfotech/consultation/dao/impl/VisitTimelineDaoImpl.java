package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.VisitTimelineDao;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitTimeline;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VisitTimelineDaoImpl implements VisitTimelineDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(VisitTimeline visitTimeline) {
		this.sessionFactory.getCurrentSession().save(visitTimeline);
	}
	
	@Override
	public List<VisitTimeline> getVTimelineByVisitId(
			Visit visit){
		List list = sessionFactory.getCurrentSession()
				.createQuery("from VisitTimeline where visit = ? ")
				.setParameter(0, visit).list();
		return list;
	}

	@Override
	public VisitTimeline getVisitTlineByVisitId(Visit visit) {
		
		List list = sessionFactory.getCurrentSession()
				.createQuery("from VisitTimeline where visit = ? ")
				.setParameter(0, visit).list();
		//return list;
		//List list = criteria.list();
		if (list.size() > 0)
			return (VisitTimeline) list.get(0);

		return null;
	}

	
}
