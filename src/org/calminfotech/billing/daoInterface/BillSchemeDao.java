package org.calminfotech.billing.daoInterface;

import java.util.List;

import org.calminfotech.billing.models.BillScheme;


public interface BillSchemeDao {
	
	public List<BillScheme> fetchAllByOrganisationBytype(int organisationId,int billtype);
	
	
	public List<BillScheme> fetchAllByOrganisation(int organisationId);
	
	public BillScheme getBillSchemeById(int id);
	
	
	public void save(BillScheme billScheme);

	public void delete(BillScheme billScheme);

	public void update(BillScheme billScheme);


	
}
