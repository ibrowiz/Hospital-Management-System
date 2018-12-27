package org.calminfotech.admin.dao.impl;

import java.util.List;

import org.calminfotech.admin.daoInterface.AilmentDao;
import org.calminfotech.system.models.Ailment;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AilmentDaoImpl implements AilmentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Ailment> fetchAll() {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from Ailment").list();
		return list;
	}

	@Override
	public Ailment getAilmentById(int id) {
		// TODO Auto-generated method stub

		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from Ailment where id = ?").setParameter(0, id)
				.list();

		if (list.size() > 0)
			return (Ailment) list.get(0);

		return null;
	}

	@Override
	public void save(Ailment ailment) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(ailment);
	}

	@Override
	public void delete(Ailment ailment) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(ailment);
	}

	@Override
	public void update(Ailment ailment) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(ailment);
	}

	@Override
	public List<Ailment> fetchLikeName(String q) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Ailment.class)
				.add(Restrictions.ilike("name", q + "%")).addOrder(Order.asc("name"));
		List list = criteria.list();

		return list;
	}

}
