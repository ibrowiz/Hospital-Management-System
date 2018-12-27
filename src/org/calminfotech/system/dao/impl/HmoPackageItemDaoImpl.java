package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.HmoPackageItemDao;
import org.calminfotech.system.models.Drug;
import org.calminfotech.system.models.HmoPackageDrug;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HmoPackageItemDaoImpl implements HmoPackageItemDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<HmoPackageDrug> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HmoPackageDrug> fetchAll(EhmoPackages oHmoPackage) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(HmoPackageDrug.class)
				.createAlias("pk.hmoPackage", "hmoPackage")
				.add(Restrictions.eq("pk.hmoPackage.id", oHmoPackage.getId()));

		return criteria.list();
	}

	@Override
	public List<HmoPackageDrug> fetchAll(Drug drug) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(HmoPackageDrug.class)
				.createAlias("pk.drug", "drug")
				.add(Restrictions.eq("pk.drug.id", drug.getId()));

		return criteria.list();
	}

	@Override
	public HmoPackageDrug getItem(Integer itemId, Integer packageId) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(HmoPackageDrug.class)
				.createAlias("pk.drug", "drug")
				.add(Restrictions.eq("pk.drug.id", itemId))
				.createAlias("pk.hmoPackage", "hmoPackage")
				.add(Restrictions.eq("pk.hmoPackage.id", packageId));

		List list = criteria.list();
		if (list.size() > 0)
			return (HmoPackageDrug) list.get(0);
		return null;
	}

	@Override
	public void save(HmoPackageDrug packageDrug) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(packageDrug);
	}

	@Override
	public void delete(HmoPackageDrug packageDrug) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(packageDrug);
	}

	@Override
	public void update(HmoPackageDrug packageDrug) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(packageDrug);
	}

}
