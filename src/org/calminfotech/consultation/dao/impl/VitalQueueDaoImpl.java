package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.VitalQueueDao;
import org.calminfotech.consultation.models.VitalQueue;
import org.calminfotech.system.models.PaymentScheme;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class VitalQueueDaoImpl implements VitalQueueDao{
	
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<VitalQueue> fetchAll() {
		// TODO Auto-generated method stub
		
		
		
		List list = this.sessionFactory.getCurrentSession()
				.createCriteria(VitalQueue.class)
				.addOrder(Order.asc("create_date")).list();
		return list;
		
	
		
		
	}

	@Override
	public List<VitalQueue> fetchAllByUser(User user) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createCriteria(VitalQueue.class)
				.createAlias("user", "user")
				.add(Restrictions.eq("user.id", user.getUserId()))
				.addOrder(Order.asc("create_date")).list();

		return list;
	}

	@Override
	public List<VitalQueue> fetchAllByUser(int i) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM VitalQueue WHERE user_id = ?").setParameter(0, i)
				.list();

		

		return list;
	}
	
	

}
