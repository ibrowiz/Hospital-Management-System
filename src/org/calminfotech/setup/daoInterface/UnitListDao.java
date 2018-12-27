package org.calminfotech.setup.daoInterface;

import java.util.List;

import org.calminfotech.setup.models.AllergyCategoryList;
import org.calminfotech.setup.models.UnitList;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface UnitListDao {
	
	//public List<UnitList> fetchAll();
	
	public List<UnitList> fetchAllByOrganisationId(Integer OrganisationId);
	
	public UnitList getUnitListById(int id);

}
