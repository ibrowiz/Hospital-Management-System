package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.HmoPackageServiceDao;
import org.calminfotech.system.models.HmoPackageService;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HmoPackageServiceDaoImpl implements HmoPackageServiceDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override  
	public List<HmoPackageService> fetchAll() {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(HmoPackageService.class);

		return criteria.list();
	}

	@Override
	public List<HmoPackageService> fetchAllByPackageId(int id) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(HmoPackageService.class)
				.createAlias("hmoPackage", "hmoPackage")
				.add(Restrictions.eq("hmoPackage.id", id));

		return criteria.list();
	}

	@Override
	public HmoPackageService getServiceById(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(HmoPackageService.class)
				.add(Restrictions.eq("id", id));

		List list = criteria.list();
		if (list.size() > 0)
			return (HmoPackageService) list.get(0);
		return null;
	}

	@Override
	public void save(HmoPackageService service) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(service);
	}

	@Override
	public void delete(HmoPackageService service) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(service);
	}

	@Override
	public void update(HmoPackageService service) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(service);
	}

}
