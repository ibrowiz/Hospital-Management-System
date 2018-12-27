package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.BillingItemPriceDetailDao;
import org.calminfotech.system.models.BillingItemPriceDetail;
import org.calminfotech.system.models.BillingSchemeItem;
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
public class BillingItemPriceDetailDaoImpl implements BillingItemPriceDetailDao{
	
	
	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public List<BillingItemPriceDetail> fetchAll() {
		// TODO Auto-generated method stub
		List<BillingItemPriceDetail> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingItemPriceDetail").list();
		return list;
	}

	@Override
	public List<BillingItemPriceDetail> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(BillingItemPriceDetail.class)
		.createAlias("organisation", "organisation")
		 .add(Restrictions.eq("organisation.id", organisation.getId()));
	
		List list = criteria.list();
		return list;
	}

	@Override
	public List<BillingItemPriceDetail> fetchAllByBilleditem(
			BillingSchemeItem billingSchemeItem) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createCriteria(BillingItemPriceDetail.class)
				.createAlias("billingSchemeItem", "billingSchemeItem")
				.add(Restrictions.eq("billingSchemeItem.id", billingSchemeItem.getId())).list();
	}

	@Override
	public void save(BillingItemPriceDetail billingItemPriceDetail) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(billingItemPriceDetail);
	}

	@Override
	public BillingItemPriceDetail getBillingItemPriceDetailById(int id) {
		// TODO Auto-generated method stub
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("FROM BillingItemPriceDetail WHERE id = ?")
				.setParameter(0, id).list();

		if (list.size() > 0)
			return (BillingItemPriceDetail) list.get(0);

		return null;
	}

	@Override
	public void delete(BillingItemPriceDetail billingItemPriceDetail) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(billingItemPriceDetail);
	}

}
