package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.user.models.UserType;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserTypesListDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<UserType> fetchAll() {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM UserType").list();
		return list;
	}

	public UserType getUserTypeById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM UserType WHERE id = ?").setParameter(0, id)
				.list();

		if (list.size() > 0)
			return (UserType) list.get(0);
		return null;
	}

	public UserType getUserTypeByType(String type) {

		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM UserType  WHERE type = ?")
				.setParameter(0, type).list();

		if (list.size() > 0)
			return (UserType) list.get(0);
		return null;
	}
}
