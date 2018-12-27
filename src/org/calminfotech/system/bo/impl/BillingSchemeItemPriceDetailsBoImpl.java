package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.BillingSchemeItemPriceDetailsBo;
import org.calminfotech.system.daoInterface.BillingSchemeItemPriceDetailsDao;
import org.calminfotech.system.models.BillingSchemeItemPriceDetails;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BillingSchemeItemPriceDetailsBoImpl implements
		BillingSchemeItemPriceDetailsBo {
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private BillingSchemeItemPriceDetailsDao billingSchemePriceDetails;

	@Override
	public List<BillingSchemeItemPriceDetails> fetchAll() {
		// TODO Auto-generated method stub
		return this.billingSchemePriceDetails.fetchAll();
	}

	@Override
	public List<BillingSchemeItemPriceDetails> fetchAllByOrgainsation() {
		// TODO Auto-generated method stub
		return this.billingSchemePriceDetails.fetchAllByOrgainsation(this.userIdentity.getOrganisation());
	}

	@Override
	public void save(BillingSchemeItemPriceDetails billing) {
		// TODO Auto-generated method stub
		this.billingSchemePriceDetails.save(billing);
	}

	@Override
	public void delete(BillingSchemeItemPriceDetails billing) {
		// TODO Auto-generated method stub
		this.billingSchemePriceDetails.delete(billing);
	}

	@Override
	public void update(BillingSchemeItemPriceDetails billing) {
		// TODO Auto-generated method stub
		this.billingSchemePriceDetails.update(billing);
	}

	@Override
	public BillingSchemeItemPriceDetails getBillingById(int id) {
		// TODO Auto-generated method stub
		return this.billingSchemePriceDetails.getBillingById(id);
	}

	@Override
	public List<BillingSchemeItemPriceDetails> getBillingInvoice(Integer billingSchemeId
			, Integer unitofmeasureId, Integer globalItemPointId) {
		// TODO Auto-generated method stub
		Integer OrganisationId = userIdentity.getOrganisation().getId();
		System.out.println("++++++ organisation "+OrganisationId);
		return this.billingSchemePriceDetails.getBillingInvoice(billingSchemeId,unitofmeasureId, globalItemPointId, OrganisationId);
	}

}
