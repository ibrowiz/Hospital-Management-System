package org.calminfotech.hmo.dao.impl;

import java.util.List;

import org.calminfotech.hmo.daoInterface.EhmoPackageServicesDao;
import org.calminfotech.hmo.models.EhmoPackageServices;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class EhmoPackageServicesDaoImpl implements EhmoPackageServicesDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(EhmoPackageServices eHmoPackageServices) {
		this.sessionFactory.getCurrentSession().save(eHmoPackageServices);
		
	}

	@Override
	public void update(EhmoPackageServices eHmoPackageServices) {
		this.sessionFactory.getCurrentSession().update(eHmoPackageServices);
		
	}

	@Override
	public void delete(EhmoPackageServices eHmoPackageServices) {
		this.sessionFactory.getCurrentSession().delete(eHmoPackageServices);
		
	}

	@Override
	public EhmoPackageServices getEhmoPackageServicesById(Integer Id) {
		List<EhmoPackageServices> list = this.sessionFactory.getCurrentSession()
				.createQuery("from EhmoPackageServices where hmoServiceId = ?").setParameter(0, Id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<EhmoPackageServices> fetchAllByOrganisation(int organisationId) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from EhmoPackageServices where organisationId = ?")
				.setParameter(0,organisationId).list();
		
			return list;
	}

}
