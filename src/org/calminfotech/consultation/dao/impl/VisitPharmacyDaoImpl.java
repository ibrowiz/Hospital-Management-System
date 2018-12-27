package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.VisitPharmacyDao;
import org.calminfotech.consultation.models.VisitPharmacy;
import org.calminfotech.consultation.models.VisitVital;
import org.calminfotech.system.models.VisitStatus;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VisitPharmacyDaoImpl implements VisitPharmacyDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<VisitPharmacy> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VisitPharmacy> fetchAllForUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VisitPharmacy> fetchAllWithOpenStatusForUser(
			VisitStatus endStatus, VisitWorkflowPoint point) {

		// TODO Auto-generated method stub
		System.out.println("------------");
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(VisitPharmacy.class)
				.createAlias("user", "user")
				.add(Restrictions.eq("user.id", userIdentity.getUserId()))
				// Check if the status not the end point
				.createAlias("visit", "visit")
				.add(Restrictions.eq("visit.point.id", point.getId()))
				.add(Restrictions.ne("visit.status.id", endStatus.getId()));

		List list = criteria.list();
		System.out.println("------------");
		return list;
	}

}
