package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.HmoPackageItemsDao;
import org.calminfotech.system.models.HmoPackageItems;
import org.calminfotech.system.models.HmoPckSubService;
import org.calminfotech.system.models.EhmoPackages;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("rawtypes")
@Repository
@Transactional
public class HmoPackageItemsDaoImpl implements HmoPackageItemsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<HmoPackageItems> fetchAll() {
		@SuppressWarnings("unchecked")
		List<HmoPackageItems> list = sessionFactory.getCurrentSession()
				.createQuery("from HmoPackageItems").list();
		return list;

	}

	@Override
	public void save(HmoPackageItems hmoAddPackageServices) {
		sessionFactory.getCurrentSession().save(hmoAddPackageServices);
	}

	@Override
	public void update(HmoPackageItems hmoAddPackageServices) {
		sessionFactory.getCurrentSession().update(hmoAddPackageServices);
	}

	@Override
	public void delete(HmoPackageItems hmoAddPackageServices) {
		sessionFactory.getCurrentSession().delete(hmoAddPackageServices);
	}

	@Override
	public HmoPackageItems getHmoAddPackageServicesById(int id) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(HmoPackageItems.class)
				.add(Restrictions.eq("id", id));

		List list = criteria.list();
		if (list.size() > 0)
			return (HmoPackageItems) list.get(0);
		return null;
	}

	@Override
	public HmoPackageItems getSpendingLimit(
			EhmoPackages organisationHmoPackage,
			HmoPckSubService hmoPckSubservice) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from HmoPackageItems where organisationHmoPackage = ? and hmoPckSubservice = ? ")
				.setParameter(0, organisationHmoPackage)
				.setParameter(1, hmoPckSubservice).list();
		
		if (list.size() > 0)
			return (HmoPackageItems) list.get(0);
		return null;
	}
}