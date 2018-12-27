package org.calminfotech.hmo.dao.impl;

import java.util.List;

import org.calminfotech.disease.models.DiseaseView;
import org.calminfotech.hmo.daoInterface.EhmoPackageServicesViewDao;
import org.calminfotech.hmo.models.EhmoPackageServicesView;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class EhmoPackageServicesViewDaoImpl implements EhmoPackageServicesViewDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public EhmoPackageServicesView getPackageServicesViewById(Integer id) {
		List<EhmoPackageServicesView> list = this.sessionFactory.getCurrentSession()
				.createQuery("from EhmoPackageServicesView where id = ?").setParameter(0, id)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public List<EhmoPackageServicesView> fetchAllByOrganisation(
			Integer organisationid) {
		List<EhmoPackageServicesView> list = this.sessionFactory.getCurrentSession()
				   .createQuery("from EhmoPackageServicesView where organisationId = ?").setParameter(0, organisationid).list();
		return list;
	}

}
