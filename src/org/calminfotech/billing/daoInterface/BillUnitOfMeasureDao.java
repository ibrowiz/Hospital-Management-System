package org.calminfotech.billing.daoInterface;

import java.util.List;

import org.calminfotech.billing.models.BillUnitOfMeasure;

public interface BillUnitOfMeasureDao {
	
	public List<BillUnitOfMeasure> fetchAll();
	public List<BillUnitOfMeasure> fetchAllByOrganisation(int organisationId);
	
	//public List<BillScheme> fetchAllByOrganisation();

	public BillUnitOfMeasure getBillUnitOfMeasureById(int id);

	public void save(BillUnitOfMeasure billUnitOfMeasure);

	public void delete(BillUnitOfMeasure billUnitOfMeasure);

	public void update(BillUnitOfMeasure billUnitOfMeasure);
		
	



	
	

}
