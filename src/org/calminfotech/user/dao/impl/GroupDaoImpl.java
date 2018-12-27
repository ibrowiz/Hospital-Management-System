package org.calminfotech.user.dao.impl;

import java.util.List;

import org.calminfotech.user.daoInterface.GroupDao;
import org.calminfotech.user.models.Group;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Group group) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(group);
	}

	@Override
	public void update(Group group) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(group);
	}

	@Override
	public void delete(Group group) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(group);
	}

	@Override
	public Group getGroupById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from Group where id = ?").setParameter(0, id)
				.list();

		if (list.size() > 0)
			return (Group) list.get(0);
		return null;
	}

	@Override
	public List<Group> fetchAll() {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from Group order by createdDate DESC").list();

		return (List<Group>) list;
	}

	public List<Group> fetchAllByOrganisation(Organisation organisation) {

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Group.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));

		return (List<Group>) criteria.list();
	}
	
	public Group getAdminGroupByOrganisation(Organisation organisation){
		// TODO Auto-generated method stub

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Group.class)
				.add(Restrictions.eq("admin", true))
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()));

		List list = criteria.list();
		
		if (list.size() > 0)
			return (Group) list.get(0);
		return null;
	}
}
