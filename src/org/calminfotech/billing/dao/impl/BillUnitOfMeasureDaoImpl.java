package org.calminfotech.billing.dao.impl;


import java.util.List;


import org.calminfotech.billing.daoInterface.BillUnitOfMeasureDao;

import org.calminfotech.billing.models.BillUnitOfMeasure;

import org.calminfotech.user.utils.UserIdentity;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class BillUnitOfMeasureDaoImpl implements BillUnitOfMeasureDao{
	
	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public BillUnitOfMeasure getBillUnitOfMeasureById(int id) {
		// TODO Auto-generated method stub
		List<BillUnitOfMeasure> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillUnitOfMeasure where unitOfMeasureId = ? AND status = 'active' ORDER BY unitOfMeasureId DESC" )
				.setParameter(0, id)
				.list();
		System.out.println(list.size());
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(BillUnitOfMeasure billUnitOfMeasure) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(billUnitOfMeasure);
	}

	@Override
	public void delete(BillUnitOfMeasure billUnitOfMeasure) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(billUnitOfMeasure);
	}

	@Override
	public void update(BillUnitOfMeasure billUnitOfMeasure) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billUnitOfMeasure);
	}

	@Override
	public List<BillUnitOfMeasure> fetchAll() {
		// TODO Auto-generated method stub
		List<BillUnitOfMeasure> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillUnitOfMeasure").list();
		return list;
	}

	@Override
	public List<BillUnitOfMeasure> fetchAllByOrganisation(int organisationId) {
		//System.out.println("name");
		List<BillUnitOfMeasure> list = sessionFactory.getCurrentSession()
				.createQuery("from BillUnitOfMeasure where organisationId = ? AND status = 'active' ORDER BY unitOfMeasureId DESC")
		     .setParameter(0,organisationId)
			.list();
		//System.out.println(list.get(0).getHmoId());
			return list;
	}
}
