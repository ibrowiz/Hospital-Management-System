package org.calminfotech.admin.dao.impl;

import java.util.List;

import org.calminfotech.admin.daoInterface.OrganisationDao;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrganisationDaoImpl implements OrganisationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Organisation> fetchAll() {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Organisation ORDER BY name").list();
		return list;
	}

	@Override
	public Organisation getOrganisationById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Organisation WHERE id = ?")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return (Organisation) list.get(0);

		return null;
	}

	@Override
	public void save(Organisation organisation) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(organisation);
	}

	@Override
	public void delete(Organisation organisation) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(organisation);
	}

	@Override
	public void update(Organisation organisation) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(organisation);
	}

}
