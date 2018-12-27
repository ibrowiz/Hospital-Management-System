package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.BillingSchemeItemPriceDetails;
import org.calminfotech.utils.models.Organisation;

public interface BillingSchemeItemPriceDetailsDao {

	public List<BillingSchemeItemPriceDetails> fetchAll();
	public List<BillingSchemeItemPriceDetails> fetchAllByOrgainsation(Organisation organisation);
	public void save(BillingSchemeItemPriceDetails billingSchemeItemPriceDetails);
	public void delete(BillingSchemeItemPriceDetails billingSchemeItemPriceDetails);
	public void update(BillingSchemeItemPriceDetails billingSchemeItemPriceDetails);
	public BillingSchemeItemPriceDetails getBillingById(int id);
	public List<BillingSchemeItemPriceDetails> getBillingInvoice(Integer billingSchemeId, Integer unitofmeasureId, Integer globalItemPointId, Integer organisationId);
}
