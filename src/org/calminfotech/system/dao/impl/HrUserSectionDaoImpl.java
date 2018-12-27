package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.HrUserSectionDao;
import org.calminfotech.system.models.HrUserSection;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.user.models.User;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HrUserSectionDaoImpl implements HrUserSectionDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<HrUserSection> fetchAll() {
		List<HrUserSection> list = this.sessionFactory.getCurrentSession()
				.createQuery("from HrUserSection").list();
		return list;
	}

	@Override
	public List<HrUserSection> fetchAllByOrgainsation(Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(HrUserSection.class)
		.createAlias("organisation", "organisation")
		 .add(Restrictions.eq("organisation.id", organisation.getId()));
	
		List list = criteria.list();
		return list;
	}

	@Override
	public List<HrUserSection> fetchAllByloginsectn(LoginSection loginSection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HrUserSection> getHrUserSectionByUserId(User user) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from HrUserSection where user = ? ")
				.setParameter(0, user).list();
		return list;
	}

	@Override
	public void save(HrUserSection hrUserSection) {
		this.sessionFactory.getCurrentSession().save(hrUserSection);	
	}
	
	@Override
	public void update(HrUserSection hrUserSection) {
		this.sessionFactory.getCurrentSession().update(hrUserSection);	
	}

	@Override
	public HrUserSection getHrUserSectionlById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM HrUserSection WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (HrUserSection) list.get(0);
		return null;

	}

	@Override
	public void delete(HrUserSection hrUserSection) {
		this.sessionFactory.getCurrentSession().delete(hrUserSection);
		
	}

}
