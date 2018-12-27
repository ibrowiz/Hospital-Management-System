package org.calminfotech.lab.dao.impl;

import java.util.List;

import org.calminfotech.lab.dao.LabTestCategoryDaoInter;
import org.calminfotech.lab.models.LabTestCategory;
import org.calminfotech.lab.models.LabTestCategoryView;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LabTestCategoryDaoImp implements LabTestCategoryDaoInter {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<LabTestCategory> fetchAllCatByOrganisation(Integer organisationId) {
		/*Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(LaboratoryTestCategory.class);

		List list = criteria.list();
		return list;*/
		List list = sessionFactory.getCurrentSession()
				.createQuery("from LabTestCategory where organisationId = ?").setParameter(0, organisationId).list();
		
		return (List<LabTestCategory>)list;
	}

	@Override
	public LabTestCategory getLabtestCatById(Integer id) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(LabTestCategory.class).add(Restrictions.eq("id", id));

		List list = criteria.list();

		if (list.size() > 0)
			return (LabTestCategory) list.get(0);

		return null;

	}

	@Override
	public void save(LabTestCategory labTestCat) {
		this.sessionFactory.getCurrentSession().save(labTestCat);
		
	}

	@Override
	public void update(LabTestCategory labTestCat) {
		this.sessionFactory.getCurrentSession().update(labTestCat);
		
	}

	@Override
	public void delete(LabTestCategory labTestCat) {
		this.sessionFactory.getCurrentSession().delete(labTestCat);
		
	}

	@Override
	public List<LabTestCategoryView> fetchAllCatByOrg(Integer organisationId) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from LabTestCategoryView where organisationId = ?").setParameter(0, organisationId).list();
		
		return (List<LabTestCategoryView>)list;
	}

}
