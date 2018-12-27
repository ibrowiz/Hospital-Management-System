package org.calminfotech.system.dao.impl;

import java.util.List;

import org.calminfotech.system.daoInterface.BillingSchemeItemPriceDetailsDao;
import org.calminfotech.system.models.BillingSchemeItemPriceDetails;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class BillingSchemeItemPriceDetailsDaoImpl implements
		BillingSchemeItemPriceDetailsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<BillingSchemeItemPriceDetails> fetchAll() {
		// TODO Auto-generated method stub
		List<BillingSchemeItemPriceDetails> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingSchemeItemPriceDetails").list();
		return list;
	}

	@Override
	public List<BillingSchemeItemPriceDetails> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(BillingSchemeItemPriceDetails.class)
		.createAlias("organisation", "organisation")
		 .add(Restrictions.eq("organisation.id", organisation.getId()));
	
		List list = criteria.list();
		return list;
	}

	@Override
	public void save(BillingSchemeItemPriceDetails billingSchemeItemPriceDetails) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(billingSchemeItemPriceDetails);
	}

	@Override
	public void delete(BillingSchemeItemPriceDetails billingSchemeItemPriceDetails) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(billingSchemeItemPriceDetails);
	}

	@Override
	public void update(BillingSchemeItemPriceDetails billingSchemeItemPriceDetails) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(billingSchemeItemPriceDetails);
	}

	@Override
	public BillingSchemeItemPriceDetails getBillingById(int id) {
		// TODO Auto-generated method stub
		List<BillingSchemeItemPriceDetails> list = this.sessionFactory.getCurrentSession()
				.createQuery("from BillingSchemeItemPriceDetails where id = ? ")
				.setParameter(0, id).list();
		if(list.size()>0)
			return (BillingSchemeItemPriceDetails)list.get(0);
		return null; 
	}

	@Override
	public List<BillingSchemeItemPriceDetails> getBillingInvoice(
			Integer billingSchemeId, Integer unitofmeasureId, Integer globalItemPointId,
			Integer organisationId) {
		// TODO Auto-generated method stub
		try{
			Query query = sessionFactory.getCurrentSession()
					.getNamedQuery("spGetBillingInvoice")
					.setParameter("billingScheme", billingSchemeId)
					.setParameter("unitofMeasure", unitofmeasureId)
					.setParameter("globalItemPoint", globalItemPointId)
					.setParameter("organisation", organisationId);
			List result = query.list();
			System.out.println("Procedure "+result);
			if(result.size()>0)
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error for parameters is: " + ex.getMessage());
		}
		return null;
	}

}
