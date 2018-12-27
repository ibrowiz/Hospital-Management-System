package org.calminfotech.admin.dao.impl;

import java.util.List;

import org.calminfotech.admin.daoInterface.AilmentClassificationDao;
import org.calminfotech.system.models.AilmentClassification;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AilmentClassificationDaoImpl implements AilmentClassificationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<AilmentClassification> fetchAll() {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from AilmentClassification").list();
		return list;
	}

	@Override
	public AilmentClassification getAilmentClassificationById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from AilmentClassification where id = ?")
				.setParameter(0, id).list();
		if (list.size() > 0)
			return (AilmentClassification) list.get(0);

		return null;
	}

	@Override
	public void save(AilmentClassification ailmentClassification) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(ailmentClassification);
	}

	@Override
	public void delete(AilmentClassification ailmentClassification) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(ailmentClassification);
	}

	@Override
	public void update(AilmentClassification ailmentClassification) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(ailmentClassification);
	}

}
