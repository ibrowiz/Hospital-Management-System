package org.calminfotech.user.dao.impl;

import java.util.List;

import org.calminfotech.consultation.models.Visit;
import org.calminfotech.user.daoInterface.UserDao;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserType;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> fetchAll() {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from User order by createdDate desc").list();
		return (List<User>) list;
		
		/*Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(User.class).addOrder(Order.desc("createdDate"));

		List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;*/
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(user);

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public User getUserByEmail(String email) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from User where email = ?")
				.setParameter(0, email).list();
		if (list.size() > 0)
			return (User) list.get(0);
		return null;
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from User where userId = ?")
				.setParameter(0, userId).list();

		if (list.size() > 0)
			return (User) list.get(0);
		return null;
	}

	// Can't use to check login credential
	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from User where email = ? and password = ?")
				.setParameter(0, email).setParameter(1, password).list();

		if (list.size() > 0)
			return (User) list.get(0);
		return null;
	}

	@Override
	public List<User> checkUserCredentialsByEmailAndPassword(String email,
			String password) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from User where email = ? and password = ?")
				.setParameter(0, email).setParameter(1, password).list();
		return list;
	}

	@Override
	public List<User> fetchAllByOrganisation(Organisation organisation) {

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(User.class)

				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))

				.createAlias("userType", "userType")
				.add(Restrictions.ne("userType.name", UserType.SYSTEM_USER));

		List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}

}
