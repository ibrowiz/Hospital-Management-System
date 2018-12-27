package org.calminfotech.patient.dao.impl;

import java.util.List;

import org.calminfotech.patient.daoInterface.PatientHmoPackageDao;
import org.calminfotech.patient.models.PatientHmoPackage;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PatientHmoPackageDaoImpl implements PatientHmoPackageDao {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PatientHmoPackage> fetchAll() {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(PatientHmoPackage.class);

		List list = criteria.list();

		return list;
	}

	@Override
	public List<PatientHmoPackage> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(PatientHmoPackage.class)
				.createAlias("pk.patient", "patient")
				.add(Restrictions.eq("pk.patient.patientId", patient.getPatientId()));
		List list = criteria.list();

		return list;
	}

	@Override
	public List<PatientHmoPackage> fetchAllByHmoPackage(
			EhmoPackages hmoPackage) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(PatientHmoPackage.class)
				.createAlias("pk.hmoPackage", "hmoPackage")
				.add(Restrictions.eq("pk.hmoPackage.id", hmoPackage.getId()));

		List list = criteria.list();

		return list;
	}

	@Override
	public PatientHmoPackage getHmoPackageByPatientAndPackage(Patient patient,
			EhmoPackages hmoPackage) {
		// TODO Auto-generated method stub

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(PatientHmoPackage.class)
				.createAlias("pk.patient", "patient")
				.add(Restrictions.eq("pk.patient.patientId", patient.getPatientId()))
				.createAlias("pk.hmoPackage", "hmoPackage")
				.add(Restrictions.eq("pk.hmoPackage.id", hmoPackage.getId()));

		List list = criteria.list();
		if (list.size() > 0)
			return (PatientHmoPackage) list.get(0);

		return null;
	}

	@Override
	public void save(PatientHmoPackage hmoPackage) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(hmoPackage);
	}

	@Override
	public void delete(PatientHmoPackage hmoPackage) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(hmoPackage);
	}

	@Override
	public void update(PatientHmoPackage hmoPackage) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(hmoPackage);
	}

}
