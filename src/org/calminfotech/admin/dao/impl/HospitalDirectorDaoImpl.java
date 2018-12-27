package org.calminfotech.admin.dao.impl;

import java.util.List;

import org.calminfotech.admin.daoInterface.HospitalDirectorDao;
import org.calminfotech.admin.models.HospitalDirector;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HospitalDirectorDaoImpl implements HospitalDirectorDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<HospitalDirector> fetchAll() {
		//.createQuery("from HospitalDirector ORDER BY directorId DESC")
		List<HospitalDirector> list = this.sessionFactory.getCurrentSession()
				.createQuery("from HospitalDirector")
				.list();

		return list;
	}

	@Override
	public HospitalDirector getHospitalDirectorById(int directorId) {
		// TODO Auto-generated method stub
		List<HospitalDirector> list = this.sessionFactory.getCurrentSession()
				.createQuery("from HospitalDirector where directorId = ?")
				.setParameter(0, directorId)
				.list();

		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public void save(HospitalDirector director) {
		this.sessionFactory.getCurrentSession().save(director);
	}

	@Override
	public void delete(HospitalDirector director) {
		this.sessionFactory.getCurrentSession().delete(director);
	}

	@Override
	public void update(HospitalDirector director) {
		this.sessionFactory.getCurrentSession().update(director);
	}

	/*@Override
	public List<Patient> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createCriteria(Patient.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", organisation.getId()))
				.addOrder(Order.asc("createdDate")).list();
		return list;
	}*/
	
	
	
	@Override
	public List<HospitalDirector> fetchAllDirectorByOrganisation(
			Organisation organisation) {
		List list = sessionFactory.getCurrentSession()
				    .createCriteria(HospitalDirector.class)
				    .createAlias("organisation", "organisation")
				    .add(Restrictions.eq("organisation.id", organisation.getId()))
				    .list();
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	

}
