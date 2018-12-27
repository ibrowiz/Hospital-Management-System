package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.ExaminationCategoryDao;

import org.calminfotech.consultation.models.ExaminationCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class ExaminationCategoryDaoImpl implements ExaminationCategoryDao{
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ExaminationCategory> fetchAllByOrgainsation(Integer organisationId) {
		@SuppressWarnings("unchecked")
		List<ExaminationCategory> list = this.sessionFactory.getCurrentSession()
				   .createQuery("from ExaminationCategory where organisationId = ?").setParameter(0, organisationId).list();
		return list;
	}

	@Override
	public ExaminationCategory getExaminationById(Integer examId) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM ExaminationCategory WHERE examCategoryId = ?")
				.setParameter(0, examId).list();

		if (list.size() > 0)
			return (ExaminationCategory) list.get(0);

		return null;
	}

	@Override
	public void save(ExaminationCategory examination) {
		this.sessionFactory.getCurrentSession().save(examination);
	}

	@Override
	public void update(ExaminationCategory examination) {
		this.sessionFactory.getCurrentSession().update(examination);
	}

	@Override
	public void delete(ExaminationCategory examination) {
		this.sessionFactory.getCurrentSession().delete(examination);
		
	}

	


}
