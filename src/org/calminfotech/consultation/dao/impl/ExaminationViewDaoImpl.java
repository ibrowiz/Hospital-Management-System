package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.ExaminationViewDao;
import org.calminfotech.consultation.models.ExaminationView;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExaminationViewDaoImpl implements ExaminationViewDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ExaminationView getExamViewById(int id) {
		@SuppressWarnings("rawtypes")
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM ExaminationView WHERE examId = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (ExaminationView) list.get(0);

		return null;
	}

	@Override
	public List<ExaminationView> fetchAllByOrganisation(int organisationid) {
		@SuppressWarnings("unchecked")
		List<ExaminationView> list = this.sessionFactory.getCurrentSession()
				   .createQuery("from ExaminationView where organisationId = ?").setParameter(0, organisationid).list();
		return list;
	}

}
