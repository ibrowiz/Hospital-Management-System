package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.PaymentLogDao;
import org.calminfotech.system.models.PaymentLog;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PaymentLogDaoImpl implements PaymentLogDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<PaymentLog> fetchAll() {
		// TODO Auto-generated method stub
		List<PaymentLog> list = sessionFactory.getCurrentSession()
				.createQuery("FROM PaymentLog" ).list();
		return list;
	}

	@Override
	public List<PaymentLog> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		List<PaymentLog> list = sessionFactory.getCurrentSession()
				.createQuery("From PaymentLog WHERE organisation = ? ")
				.setParameter(0, organisation).list();
		if(list.size()>0){
			return (List<PaymentLog>) list.get(0);
		}
		return null;
	}

	@Override
	public void save(PaymentLog paymentLog) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(paymentLog);
	}

	@Override
	public void delete(PaymentLog paymentLog) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(paymentLog);
	}

	@Override
	public void update(PaymentLog paymentLog) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(paymentLog);
	}

	@Override
	public PaymentLog getPaymentLogById(int id) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<PaymentLog> list = sessionFactory.getCurrentSession()
				.createQuery("FROM PaymentLog WHERE id = ? ")
				.setParameter(0, id).list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
