package org.calminfotech.consultation.dao.impl;

import java.util.List;

import org.calminfotech.consultation.dao.DoctorDao;
import org.calminfotech.consultation.models.Doctor;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.lab.models.LabTest;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DoctorDaoImpl implements DoctorDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Doctor doctor) {
		this.sessionFactory.getCurrentSession().save(doctor);
	}

	@Override
	public List<Doctor> fetchAllByUIdAndOrg(int userId, Integer organisationId) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Doctor.class);
		criteria.add(Restrictions.eq("createdBy", userId));
		criteria.add(Restrictions.eq("organisationId", organisationId));

		List list = criteria.list();
		return list;
	}

	@Override
	public Doctor getDoctorById(Integer id) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Doctor.class).add(Restrictions.eq("summaryId", id));

		List list = criteria.list();

		if (list.size() > 0)
			return (Doctor) list.get(0);

		return null;
	}

	@Override
	public void update(Doctor doctor) {
		this.sessionFactory.getCurrentSession().update(doctor);
		
	}

}
