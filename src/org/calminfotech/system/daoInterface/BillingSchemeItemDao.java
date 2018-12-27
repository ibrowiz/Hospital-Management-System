package org.calminfotech.system.daoInterface;

import java.util.List;


import org.calminfotech.system.models.BillingSchemeItem;
import org.calminfotech.utils.models.Organisation;

public interface BillingSchemeItemDao {
	

	public BillingSchemeItem getBillingSchemeItemById(int id);

	public void save(BillingSchemeItem billingSchemeItem);

	public void delete(BillingSchemeItem billingSchemeItem);

	public void update(BillingSchemeItem billingSchemeItem);
		
	public List<BillingSchemeItem> fetchAll();

	public List<BillingSchemeItem> fetchAllByOrganisation(Organisation organisation);
	

}
