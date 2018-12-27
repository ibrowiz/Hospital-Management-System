package org.calminfotech.lab.dao.impl;

import java.util.List;

import org.calminfotech.lab.bo.LabTestBo;
import org.calminfotech.lab.dao.LabResultSetupDao;
import org.calminfotech.lab.models.LabResultSetup;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LabResultSetupDaoImpl implements LabResultSetupDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;
	
	private LabTestBo labTestBo;

	@Override
	public List<LabResultSetup> fetchAll() {
		
			Criteria criteria = this.sessionFactory.getCurrentSession()
					.createCriteria(LabResultSetup.class);
			List list = criteria.list();
			return list;
	}

	@Override
	public LabResultSetup getLabResultSetupById(Integer resultId) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(LabResultSetup.class).add(Restrictions.eq("resultId", resultId));

		List list = criteria.list();

		if (list.size() > 0)
			return (LabResultSetup) list.get(0);

		return null;
	}

	@Override
	public List<LabResultSetup> getLabResultSetupByTestId(LabTest labTest) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from LabResultSetup where labTest = ? ")
				.setParameter(0, labTest).list();
	
		return list;
	}

	@Override
	public void save(LabResultSetup labResult) {
		this.sessionFactory.getCurrentSession().save(labResult);
	}

	@Override
	public void update(LabResultSetup labResult) {
		this.sessionFactory.getCurrentSession().update(labResult);
	}

	@Override
	public void delete(LabResultSetup labResult) {
		this.sessionFactory.getCurrentSession().delete(labResult);
	}

	@Override
	public LabResultSetup getLabRSetupByTestId(LabTest labTest) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(LabResultSetup.class).add(Restrictions.eq("labTest", labTest));

		List list = criteria.list();

		if (list.size() > 0)
			return (LabResultSetup) list.get(0);

		return null;
	}

}
