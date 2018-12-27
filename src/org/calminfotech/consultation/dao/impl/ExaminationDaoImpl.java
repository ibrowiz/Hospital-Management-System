package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.ExaminationDao;
import org.calminfotech.consultation.models.Examination;
import org.calminfotech.consultation.models.ExaminationCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ExaminationDaoImpl implements ExaminationDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Examination> fetchAllByOrgainsation(Integer organisationId) {
		@SuppressWarnings("unchecked")
		List<Examination> list = this.sessionFactory.getCurrentSession()
				   .createQuery("from Examination where organisationId = ?").setParameter(0, organisationId).list();
		return list;
	}

	@Override
	public Examination getExaminationById(Integer examId) {
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM Examination WHERE examId = ?")
				.setParameter(0, examId).list();

		if (list.size() > 0)
			return (Examination) list.get(0);

		return null;
	}

	@Override
	public void save(Examination examination) {
		this.sessionFactory.getCurrentSession().save(examination);
	}

	@Override
	public void update(Examination examination) {
		this.sessionFactory.getCurrentSession().update(examination);
	}

	@Override
	public void delete(Examination examination) {
		this.sessionFactory.getCurrentSession().delete(examination);
	}

}
