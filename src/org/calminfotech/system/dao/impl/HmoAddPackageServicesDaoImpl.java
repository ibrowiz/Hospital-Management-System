package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.HmoAddPackageServicesDao;
import org.calminfotech.system.models.HmoAddPackageServices;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HmoAddPackageServicesDaoImpl implements HmoAddPackageServicesDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<HmoAddPackageServices> fetchAll() {	
		
		Criteria criteria = sessionFactory.getCurrentSession()
							.createCriteria(HmoAddPackageServices.class);		
		return criteria.list();
	}

	@Override
	public void save(HmoAddPackageServices hmoAddPackageServices) {
		sessionFactory.getCurrentSession().save(hmoAddPackageServices);
	}

	@Override
	public void update(HmoAddPackageServices hmoAddPackageServices) {
		sessionFactory.getCurrentSession().update(hmoAddPackageServices);
	}

	@Override
	public void delete(HmoAddPackageServices hmoAddPackageServices) {
		sessionFactory.getCurrentSession().delete(hmoAddPackageServices);
	}

	@Override
	public HmoAddPackageServices getHmoAddPackageServicesById(int id) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(HmoAddPackageServices.class)
				.add(Restrictions.eq("id", id));
		
		List list = criteria.list();
		if(list.size() > 0)
			return (HmoAddPackageServices) list.get(0);		
		return null;
	}

}
