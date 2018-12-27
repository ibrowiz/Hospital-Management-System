package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.hmo.models.Ehmo;
import org.calminfotech.system.daoInterface.EHmoPackagesDao;
import org.calminfotech.system.models.Hmo;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.user.utils.UserIdentity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EHmoPackagesDaoImpl implements
		EHmoPackagesDao {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<EhmoPackages> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(EhmoPackages.class)
				.createAlias("organisation", "organisation")
				.add(Restrictions.eq("organisation.id", this.userIdentity
						.getOrganisation().getId()));

		List list = criteria.list();
		return list;
	}

	@Override
	public EhmoPackages getPackageById(int id) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(EhmoPackages.class)
				.add(Restrictions.eq("id", id));

		List list = criteria.list();

		if (list.size() > 0)
			return (EhmoPackages) list.get(0);
		return null;
	}

	@Override
	public void save(EhmoPackages hmoPackage) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(hmoPackage);
	}

	@Override
	public void delete(EhmoPackages hmoPackage) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(hmoPackage);
	}

	@Override
	public void update(EhmoPackages hmoPackage) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(hmoPackage);
	}

	@Override
	public List<EhmoPackages> fetchAllByHMO(Ehmo hmo) {
		// TODO Auto-generated method stub
		/*Criteria criteria = this.sessionFactory
				.getCurrentSession()
				.createCriteria(OrganisationHmoPackage.class)
				.createAlias("hmo", "hmo")
				.add(Restrictions.eq("hmo.hmoId", hmo.getHmoId()));

		List list = criteria.list();
		return list;*/
		List list = sessionFactory.getCurrentSession()
				.createQuery("from OrganisationHmoPackage where ehmo = ?")
				.setParameter(0, hmo).list();
		return list;
	}
}
