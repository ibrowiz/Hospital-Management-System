package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.ExaminationResultSetupDao;
import org.calminfotech.consultation.models.Examination;
import org.calminfotech.consultation.models.ExaminationResultSetup;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
class ExaminationResultSetupDaoImpl implements ExaminationResultSetupDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ExaminationResultSetup> fetchAllByOrganisationId(
			Integer organisationId) {
		@SuppressWarnings("unchecked")
		List<ExaminationResultSetup> list = this.sessionFactory.getCurrentSession()
				   .createQuery("from ExaminationResultSetup where organisationId = ?").setParameter(0, organisationId).list();
		return list;
	}

	@Override
	public ExaminationResultSetup getExamResultSetupById(Integer id) {
		@SuppressWarnings("rawtypes")
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM ExaminationResultSetup WHERE examResultSetupId = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (ExaminationResultSetup) list.get(0);

		return null;
	}

	@Override
	public List<ExaminationResultSetup> getExamResultSetupByExamId(
			Examination exam) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from ExaminationResultSetup where exam = ?")
				.setParameter(0, exam).list();
		return list;
	}

	@Override
	public void save(ExaminationResultSetup examResultSetup) {
		this.sessionFactory.getCurrentSession().save(examResultSetup);
		
	}

	@Override
	public void update(ExaminationResultSetup examResultSetup) {
		this.sessionFactory.getCurrentSession().update(examResultSetup);
		
	}

	@Override
	public void delete(ExaminationResultSetup examResultSetup) {
		this.sessionFactory.getCurrentSession().delete(examResultSetup);
		
	}

	@Override
	public ExaminationResultSetup getExamResultSetupByRowId(Integer rowId) {
		@SuppressWarnings("rawtypes")
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM ExaminationResultSetup WHERE rowId = ?")
				.setParameter(0, rowId).list();

		if (list.size() > 0)
			return (ExaminationResultSetup) list.get(0);

		return null;
	}

}
