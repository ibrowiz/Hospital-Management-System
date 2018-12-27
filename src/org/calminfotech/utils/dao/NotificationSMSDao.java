package org.calminfotech.utils.dao;

import java.util.List;

import org.calminfotech.utils.models.NotificationSMS;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class NotificationSMSDao {
	@Autowired
	private SessionFactory sessionFactory;

	public List<NotificationSMS> fetchAll() {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(NotificationSMS.class);

		List list = criteria.list();
		return list;
	}

	public List<NotificationSMS> fetchAllByOrganisation(
			Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(NotificationSMS.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));

		List list = criteria.list();
		return list;
	}

	public List<NotificationSMS> fetchAllUnSent(Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(NotificationSMS.class)
				.add(Restrictions.eq("isSent", false));

		List list = criteria.list();
		return list;
	}

	public List<NotificationSMS> fetchAllUnSent() {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(NotificationSMS.class)
				.add(Restrictions.eq("isSent", false));

		List list = criteria.list();
		return list;
	}

	public void save(NotificationSMS notificationSMS) {
		this.sessionFactory.getCurrentSession().save(notificationSMS);
	}

	public void update(NotificationSMS notificationSMS) {
		this.sessionFactory.getCurrentSession().update(notificationSMS);
	}

	public void delete(NotificationSMS notificationSMS) {
		this.sessionFactory.getCurrentSession().delete(notificationSMS);
	}

}
