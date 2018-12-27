package org.calminfotech.consultation.dao.impl;

import java.util.List;
import org.calminfotech.consultation.dao.ExaminationCategoryViewDao;
import org.calminfotech.consultation.models.ExaminationCategoryView;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExaminationCategoryViewDaoImpl implements ExaminationCategoryViewDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ExaminationCategoryView getExamViewById(int id) {
		@SuppressWarnings("rawtypes")
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM ExaminationCategoryView WHERE examCategoryId = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (ExaminationCategoryView) list.get(0);

		return null;
	}

	@Override
	public List<ExaminationCategoryView> fetchAllByOrganisation(int organisationid) {
		@SuppressWarnings("unchecked")
		List<ExaminationCategoryView> list = this.sessionFactory.getCurrentSession()
				   .createQuery("from ExaminationCategoryView where organisationId = ?").setParameter(0, organisationid).list();
		return list;
	}

}
