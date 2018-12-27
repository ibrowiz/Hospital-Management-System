package org.calminfotech.system.dao.impl;

import java.util.List;


import org.calminfotech.system.daoInterface.LoginSectionPointDao;

import org.calminfotech.system.models.LoginSectionPoint;
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
public class LoginSectionPointDaoImpl implements LoginSectionPointDao{

	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<LoginSectionPoint> fetchAll() {
		// TODO Auto-generated method stub
		List<LoginSectionPoint> list = this.sessionFactory.getCurrentSession()
				.createQuery("from LoginSectionPoint").list();
		return list;
	}

	@Override
	public List<LoginSectionPoint> fetchAllByOrgainsation(Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(LoginSectionPoint.class)
		.createAlias("organisation", "organisation")
		 .add(Restrictions.eq("organisation.id", organisation.getId()));
	
		List list = criteria.list();
		return list;
	}

	@Override
	public List<LoginSectionPoint> fetchAllByLoginSectionPoint(LoginSectionPoint LoginSectionPoint) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(LoginSectionPoint.class)
				.createAlias("LoginSectionPoint", "LoginSectionPoint")
				.add(Restrictions.eq("LoginSectionPoint.id", LoginSectionPoint.getId())).list();
	}

	@Override
	public void save(LoginSectionPoint LoginSectionPoint) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(LoginSectionPoint);
	}

	@Override
	public LoginSectionPoint getLoginSectionPointById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM LoginSectionPoint WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (LoginSectionPoint) list.get(0);

		return null;
	}

	@Override
	public void delete(LoginSectionPoint LoginSectionPoint) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(LoginSectionPoint);
	}
	
	

}
