package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.BillingItemPriceDetail;

import org.calminfotech.system.models.BillingSchemeItem;
import org.calminfotech.utils.models.Organisation;

public interface BillingItemPriceDetailDao {
		
	
	public List<BillingItemPriceDetail> fetchAll();

	public List<BillingItemPriceDetail> fetchAllByOrgainsation(
			Organisation organisation);

	public List<BillingItemPriceDetail> fetchAllByBilleditem(BillingSchemeItem billingSchemeItem);

	public void save(BillingItemPriceDetail billingItemPriceDetail);

	public BillingItemPriceDetail getBillingItemPriceDetailById(int id);

	public void delete(BillingItemPriceDetail billingItemPriceDetail);

}
