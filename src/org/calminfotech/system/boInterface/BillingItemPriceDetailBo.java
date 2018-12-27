package org.calminfotech.system.boInterface;

import org.calminfotech.system.models.BillingItemPriceDetail;

public interface BillingItemPriceDetailBo {
	
	public void save(BillingItemPriceDetail billingItemPriceDetail);

	public BillingItemPriceDetail getBillingItemPriceDetailById(int id);

	public void delete(BillingItemPriceDetail billingItemPriceDetail);

}
