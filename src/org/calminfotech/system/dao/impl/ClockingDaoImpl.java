package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.patient.models.PatientMedicalHistory;
import org.calminfotech.system.daoInterface.ClockingDao;
import org.calminfotech.system.models.Clocking;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserLoginSession;
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
public class ClockingDaoImpl implements ClockingDao{
	
	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;
	
	

	@Override
	public List<Clocking> fetchAll() {
		// TODO Auto-generated method stub
		List<Clocking> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Clocking").list();
		return list;
	}

	@Override
	public List<Clocking> fetchAllByOrgainsation(Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Clocking.class)
		.createAlias("organisation", "organisation")
		 .add(Restrictions.eq("organisation.id", organisation.getId()));
	
		List list = criteria.list();
		return list;
	}

	/*@Override
	public List<Clocking> fetchAllByloginsectn(Clocking clocking) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(Clocking.class)
				.createAlias("clocking", "clocking")
				.add(Restrictions.eq("clocking.id", clocking.getId())).list();
	}*/

	@Override
	public void save(Clocking clocking) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(clocking);	
	}

	@Override
	public Clocking getClockingById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Clocking WHERE clocking_id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (Clocking) list.get(0);

		return null;
	}

	@Override
	public void delete(Clocking clocking) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(clocking);
	}

/*	@Override
	public List<Clocking> fetchAllByUserLoginSession(
			UserLoginSession userLoginSession) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(Clocking.class)
				.createAlias("userLoginSession", "userLoginSession")
				.add(Restrictions.eq("userLoginSession.id", userLoginSession.getId())).list();
	}
*/
	@Override
	public List<Clocking> fetchAllByUser(User user) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(Clocking.class)
				.createAlias("user", "user")
				.add(Restrictions.eq("user.id", user.getUserId())).list();
	}

}
