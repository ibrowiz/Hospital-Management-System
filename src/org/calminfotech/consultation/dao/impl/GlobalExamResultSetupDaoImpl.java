package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.GlobalExamResultSetupDao;
import org.calminfotech.consultation.models.GlobalExaminationResultSetup;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class GlobalExamResultSetupDaoImpl implements GlobalExamResultSetupDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<GlobalExaminationResultSetup> fetchAllByOrganisationId(
			Integer organisationId) {
			@SuppressWarnings("unchecked")
			List<GlobalExaminationResultSetup> list = this.sessionFactory.getCurrentSession()
					   .createQuery("from GlobalExaminationResultSetup where organisationId = ?").setParameter(0, organisationId).list();
			return list;
	}

	@Override
	public GlobalExaminationResultSetup getExamResultSetupById(Integer id) {
		@SuppressWarnings("rawtypes")
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM GlobalExaminationResultSetup WHERE examResultSetupId = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (GlobalExaminationResultSetup) list.get(0);

		return null;
	}

	

	@Override
	public void save(GlobalExaminationResultSetup examResultSetup) {
		this.sessionFactory.getCurrentSession().save(examResultSetup);
	}

	@Override
	public void update(GlobalExaminationResultSetup examResultSetup) {
		this.sessionFactory.getCurrentSession().update(examResultSetup);
	}

	@Override
	public void delete(GlobalExaminationResultSetup examResultSetup) {
		this.sessionFactory.getCurrentSession().delete(examResultSetup);
	}

	

	
}

	
