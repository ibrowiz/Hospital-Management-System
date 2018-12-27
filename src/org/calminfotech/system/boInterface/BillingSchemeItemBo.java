package org.calminfotech.system.boInterface;

import java.util.List;



import org.calminfotech.system.forms.BillingSchemeItemForm;

import org.calminfotech.system.models.BillingSchemeItem;

public interface BillingSchemeItemBo {
	
	public List<BillingSchemeItem> fetchAllByOrganisation();

	public BillingSchemeItem getBillingSchemeitemById(int id);

	public BillingSchemeItem save(BillingSchemeItemForm billingSchemeItemForm);

	public void delete(BillingSchemeItem billingSchemeitem);

	public void update(BillingSchemeItemForm billingSchemeitemForm);
		
	public List<BillingSchemeItem> fetchAll();
	

}
