package org.calminfotech.billing.daoInterface;

import java.util.List;
import org.calminfotech.billing.models.BillGlobalItem;
import org.calminfotech.system.models.GlobalItem;


public interface BillGlobalItemDao {
	
	public List<BillGlobalItem> fetchAll();
	public List<BillGlobalItem> fetchAllByOrganisation(int organisationId);
	
	//public List<BillScheme> fetchAllByOrganisation();

	public BillGlobalItem getGlobalItemById(int id);

	public void save(BillGlobalItem globalItemItem);

	public void delete(BillGlobalItem globalItemItem);

	public void update(BillGlobalItem globalItemItem);
	
	public List<BillGlobalItem> fetchAllByOrganisationByType(int organisationId,
			int itemType);
		
	



	
	

}
