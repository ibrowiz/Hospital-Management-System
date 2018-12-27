package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.BillingScheme;
import org.calminfotech.utils.models.Organisation;

public interface BillingSchemeDao {
	
	public BillingScheme getBillingSchemeById(int id);

	public void save(BillingScheme billingScheme);

	public void delete(BillingScheme billingScheme);

	public void update(BillingScheme billingScheme);
	
	public List<BillingScheme> fetchAll();
	
	public List<BillingScheme> fetchAllByType(Organisation organisation);

	public List<BillingScheme> fetchAllByOrganisation(Organisation organisation);
	
}
