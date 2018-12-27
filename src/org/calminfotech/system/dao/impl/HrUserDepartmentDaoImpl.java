package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.HrUserDepartmentDao;
import org.calminfotech.system.models.HrUserDepartment;
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
public class HrUserDepartmentDaoImpl implements HrUserDepartmentDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<HrUserDepartment> fetchAll() {
		List<HrUserDepartment> list = this.sessionFactory.getCurrentSession()
				.createQuery("from HrUserDepartment").list();
		return list;
	}

	@Override
	public List<HrUserDepartment> fetchAllByOrgainsation(
			Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(HrUserDepartment.class)
		.createAlias("organisation", "organisation")
		 .add(Restrictions.eq("organisation.id", organisation.getId()));
	
		List list = criteria.list();
		return list;
	}

	@Override
	public List<HrUserDepartment> fetchAllByloginsectn(LoginSection loginSection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HrUserDepartment> getHrUserDeptByUserId(User user) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from HrUserDepartment where user = ? ")
				.setParameter(0, user).list();
		return list;
	}

	@Override
	public void save(HrUserDepartment hrUserDepartment) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(hrUserDepartment);
	}

	@Override
	public HrUserDepartment getHrUserDepartmentlById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM HrUserDepartment WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (HrUserDepartment) list.get(0);
		return null;
	}

	@Override
	public void delete(HrUserDepartment hrUserDepartment) {
		this.sessionFactory.getCurrentSession().delete(hrUserDepartment);
	}

	@Override
	public void update(HrUserDepartment hrUserDepartment) {
		this.sessionFactory.getCurrentSession().update(hrUserDepartment);
		
	}

}
