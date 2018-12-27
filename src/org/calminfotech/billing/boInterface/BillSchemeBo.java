package org.calminfotech.billing.boInterface;

import java.util.List;

import org.calminfotech.billing.models.BillScheme;

public interface BillSchemeBo {
	
	
	public List<BillScheme> fetchAllByOrganisationBytype(int organisationId,int billtype);
	
	
	public List<BillScheme> fetchAllByOrganisation(int organisationId);
	
	//public List<BillScheme> fetchAllByOrganisation();

	public BillScheme getBillSchemeById(int id);

	public void save(BillScheme billScheme);

	public void delete(BillScheme billScheme);

	public void update(BillScheme billScheme);


	
	

}
