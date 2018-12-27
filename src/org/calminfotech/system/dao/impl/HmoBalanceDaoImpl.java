package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.HmoBalanceDao;
import org.calminfotech.system.models.HmoBalance;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@SuppressWarnings("unchecked")
@Transactional
@Repository
public class HmoBalanceDaoImpl implements HmoBalanceDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<HmoBalance> fetchAll() {
		// TODO Auto-generated method stub
		List<HmoBalance> list = this.sessionFactory.getCurrentSession()
				.createQuery("From HmoBalance ").list();
		return list;
	}

	@Override
	public List<HmoBalance> fetchAllByOrganisation(Organisation organisation) {
		// TODO Auto-generated method stub
		List<HmoBalance> list = sessionFactory.getCurrentSession()
				.createQuery("FROM HmoBalance WHERE organisation = ? ")
				.setParameter(0, organisation).list();
		return list;
	}

	@Override
	public void save(HmoBalance hmoBalance) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(hmoBalance);
	}

	@Override
	public void delete(HmoBalance hmoBalance) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(hmoBalance);
	}

	@Override
	public void update(HmoBalance hmoBalance) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(hmoBalance);
	}

	@Override
	public HmoBalance getHmoBalanceById(int id) {
		// TODO Auto-generated method stub
		List<HmoBalance> list = sessionFactory.getCurrentSession()
				.createQuery("From HmoBalance where id = ? ")
				.setParameter(0, id).list();
		if(list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public HmoBalance getHmoStatus(Patient patientId,
			EhmoPackages packageId, int subservice,
			Organisation organisation) {
		// TODO Auto-generated method stub
		List<HmoBalance> list = sessionFactory.getCurrentSession()
				.createQuery("From HmoBalance where patientId = ? and packageId = ? and subservice = ? and organisation = ? ")
				.setParameter(0, patientId)
				.setParameter(1, packageId)
				.setParameter(2, subservice)
				.setParameter(3, organisation).list();
		if(list.size()>0)
			return list.get(0);
		return null;
	}

}
