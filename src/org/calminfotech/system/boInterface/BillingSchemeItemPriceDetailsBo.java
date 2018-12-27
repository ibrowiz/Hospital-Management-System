package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.BillingSchemeItemPriceDetails;

public interface BillingSchemeItemPriceDetailsBo {

	public List<BillingSchemeItemPriceDetails> fetchAll();
	public List<BillingSchemeItemPriceDetails> fetchAllByOrgainsation();
	public void save(BillingSchemeItemPriceDetails billing);
	public void delete(BillingSchemeItemPriceDetails billing);
	public void update(BillingSchemeItemPriceDetails billing);
	public BillingSchemeItemPriceDetails getBillingById(int id);
	public List<BillingSchemeItemPriceDetails> getBillingInvoice(Integer billingSchemeId, Integer unitofmeasureId, Integer globalItemPointId);
}
