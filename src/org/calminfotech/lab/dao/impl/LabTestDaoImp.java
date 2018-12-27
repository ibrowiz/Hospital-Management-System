package org.calminfotech.lab.dao.impl;

import java.util.List;

import org.calminfotech.lab.bo.LabTestCategoryBo;
import org.calminfotech.lab.dao.LabTestDao;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.lab.models.LabTestCategory;
import org.calminfotech.lab.models.LabTestDocument;

import org.calminfotech.setup.models.HrUnitCategory;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LabTestDaoImp implements LabTestDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;
	
	
	
	@Override
	public List<LabTest> fetchAllByOrganisationId(Integer organisationId) {
		List<LabTest> list = this.sessionFactory.getCurrentSession()
				   .createQuery("from LabTest where organisationId = ?").setParameter(0, organisationId).list();
		return list;
	}

	@Override
	public LabTest getLabtestById(Integer id){
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(LabTest.class).add(Restrictions.eq("id", id));

		List list = criteria.list();

		if (list.size() > 0)
			return (LabTest) list.get(0);

		return null;

	}
	
	@Override
	public void save(LabTest labTest) {
		this.sessionFactory.getCurrentSession().save(labTest);
		
	}

	@Override
	public void update(LabTest labTest) {
		this.sessionFactory.getCurrentSession().update(labTest);
		
	}

	@Override
	public void delete(LabTest labTest) {
		this.sessionFactory.getCurrentSession().delete(labTest);
	}

	@Override
	public List<LabTest> getLaboratoryTestByCatId(
			LabTestCategory lCategory) {
		// TODO Auto-generated method stub
		/*List list = sessionFactory.getCurrentSession()
				.createCriteria(LaboratoryTest.class)
				.createAlias("lCategory", "lCategory")
				.add(Restrictions.eq("lCategory.id", lCategory.getId()))
				.addOrder(Order.asc("createdDate")).list();
		return list;*/
		List list = sessionFactory.getCurrentSession()
				.createQuery("from LabTest where lCategory = ?")
				.setParameter(0, lCategory).list();
		return list;
	}

	
}
