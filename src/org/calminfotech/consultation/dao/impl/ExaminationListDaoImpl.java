package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.ExaminationListDao;
import org.calminfotech.consultation.models.ExaminationList;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ExaminationListDaoImpl implements ExaminationListDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ExaminationList> fetchAll() {
		@SuppressWarnings("unchecked")
		List<ExaminationList> list = sessionFactory.getCurrentSession()
				   .createQuery("from ExaminationList").list();
		return list;
	}

	@Override
	public List<ExaminationList> fetchAllByOrganisation(Integer organisationId) {
		@SuppressWarnings("unchecked")
		List<ExaminationList> list = sessionFactory.getCurrentSession()
				   .createQuery("from ExaminationList where organisationId = ?").setParameter(0,organisationId).list();
		return list;
	}

}
