package org.calminfotech.admin.dao.impl;

import java.util.List;

import org.calminfotech.admin.daoInterface.DrugDao;
import org.calminfotech.system.models.Drug;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DrugDaoImpl implements DrugDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Drug> fetchAll() {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Drug ORDER BY name ASC").list();
		return list;
	}

	@Override
	public Drug getDrugById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Drug WHERE id = ?").setParameter(0, id)
				.list();

		if (list.size() > 0)
			return (Drug) list.get(0);
		return null;
	}

	@Override
	public void save(Drug drug) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(drug);
	}

	@Override
	public void delete(Drug drug) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(drug);
	}

	@Override
	public void update(Drug drug) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(drug);
	}

	@Override
	public List<Drug> fetchLikeName(String q) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Drug.class)
				.add(Restrictions.ilike("name", q + "%")).addOrder(Order.asc("name"));
		List list = criteria.list();

		return list;
	}

}
