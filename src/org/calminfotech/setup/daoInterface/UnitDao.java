package org.calminfotech.setup.daoInterface;

import java.util.List;

import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.setup.models.HrUnit;
import org.calminfotech.setup.models.HrUnitCategory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface UnitDao {
	
	public List<HrUnit> fetchAll();	

	public HrUnit getUnitById(int unitId);
		
	//public List<OuterRecursive> fetchAllTypes();
	
	public void save(HrUnit hrUnit);
	
	public void update(HrUnit hrUnit);
	
	public void delete(HrUnit hrUnit);
	
	public List<HrUnit> fetchAllByOrganisation(int organisationId);

}
