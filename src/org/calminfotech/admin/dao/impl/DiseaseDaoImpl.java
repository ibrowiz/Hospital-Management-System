package org.calminfotech.admin.dao.impl;

import java.util.List;

import org.calminfotech.admin.daoInterface.DiseaseDao;
import org.calminfotech.system.models.Disease;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DiseaseDaoImpl implements DiseaseDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Disease> fetchAll() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createQuery("from Disease").list();
	}

	@Override
	public Disease getDiseaseById(int id) {
		// TODO Auto-generated method stub
		List<Disease> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Disease where id = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(Disease disease) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(disease);
	}

	@Override
	public void delete(Disease disease) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(disease);
	}

	@Override
	public void update(Disease disease) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(disease);
	}

}
