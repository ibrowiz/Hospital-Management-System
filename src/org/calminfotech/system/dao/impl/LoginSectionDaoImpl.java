package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.LoginSectionDao;

import org.calminfotech.system.models.LoginSection;
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
public class LoginSectionDaoImpl implements LoginSectionDao{
	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<LoginSection> fetchAll() {
		// TODO Auto-generated method stub
		List<LoginSection> list = this.sessionFactory.getCurrentSession()
				.createQuery("from LoginSection").list();
		return list;
	}

	@Override
	public List<LoginSection> fetchAllByOrgainsation(Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(LoginSection.class)
		.createAlias("organisation", "organisation")
		 .add(Restrictions.eq("organisation.id", organisation.getId()));
	
		List list = criteria.list();
		return list;
	}

	@Override
	public List<LoginSection> fetchAllByloginsectn(LoginSection loginSection) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(LoginSection.class)
				.createAlias("loginSection", "loginSection")
				.add(Restrictions.eq("loginSection.id", loginSection.getId())).list();
	}

	@Override
	public void save(LoginSection loginSection) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(loginSection);	
	}

	@Override
	public LoginSection getLoginSectionlById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM LoginSection WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (LoginSection) list.get(0);

		return null;
	}

	@Override
	public void delete(LoginSection loginSection) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(loginSection);
	}

	/*@Override
	public List<LoginSection> getLoginSectionByUserId(User user) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from LoginSection where user = ? ")
				.setParameter(0, user).list();
		return list;
	}*/
}
