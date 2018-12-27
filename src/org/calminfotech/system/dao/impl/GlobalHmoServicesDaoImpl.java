package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.setup.models.Allergy;
import org.calminfotech.system.daoInterface.GlobalHmoServicesDao;
import org.calminfotech.system.models.GlobalHmoServices;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class GlobalHmoServicesDaoImpl implements GlobalHmoServicesDao {
	
	@Autowired
	private SessionFactory sessionFactory; 

	@Override
	public void save(GlobalHmoServices globalHmoServices) {
		this.sessionFactory.getCurrentSession().save(globalHmoServices);
		
	}

	@Override
	public void update(GlobalHmoServices globalHmoServices) {
		this.sessionFactory.getCurrentSession().update(globalHmoServices);
		
	}

	@Override
	public void delete(GlobalHmoServices globalHmoServices) {
		this.sessionFactory.getCurrentSession().delete(globalHmoServices);
		
	}

	public GlobalHmoServices getGlobalHmoServiesById(Integer serviceId){
		List<GlobalHmoServices> list = this.sessionFactory.getCurrentSession()
				.createQuery("from GlobalHmoServices where hmoServiceId = ?").setParameter(0, serviceId)
				.list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
	
	@Override
	public List<GlobalHmoServices> fetchAllByOrganisation(int organisationId) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from GlobalHmoServices where organisationId = ?")
				.setParameter(0,organisationId).list();
		
			return list;
	}

}
