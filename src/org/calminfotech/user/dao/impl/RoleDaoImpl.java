package org.calminfotech.user.dao.impl;

import java.util.List;

import org.calminfotech.user.daoInterface.RoleDao;
import org.calminfotech.user.models.Role;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public void save(Role role) {
		this.sessionFactory.getCurrentSession().save(role);
	}

	@Override
	public void delete(Role role) {
		this.sessionFactory.getCurrentSession().delete(role);
	}

	@Override
	public void update(Role role) {
		this.sessionFactory.getCurrentSession().update(role);
	}

	@Override
	public Role getRoleById(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from Role where roleId = ?")
				.setParameter(0, id).list();
		if(list.size()>0){
			return (Role)list.get(0);
		}
		return null;
	}
	//select u from User u left join fetch u.roles where
	@Override
	public Role getRoleByIdJson(int id) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from Role where roleId = ?")
				.setParameter(0, id).list();
		if(list.size()>0){
			return (Role)list.get(0);
		}
		return null;
	}

	@Override
	public List<Role> fetchAll() {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from Role").list();
		return (List<Role>) list;
	}

	public List<Role> fetchAllRoleByOrganisation(Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Role.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));
		
		return (List<Role>) criteria.list();
	}

	public Role getRoleByAdmin(Organisation organisation) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Role.class)
				.add(Restrictions.eq("admin", true))
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));
		
		List list = criteria.list();
		if(list.size()>0){
			return (Role)list.get(0);
		}
		return null;
	}
}
