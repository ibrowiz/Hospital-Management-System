package org.calminfotech.hr.daoInterface;

import java.util.List;

import org.calminfotech.hr.models.Clockin;
import org.calminfotech.setup.models.UnitCategoryView;
import org.calminfotech.system.models.UserAssgnment;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface ClockinDao {
	
public Clockin getClockinAssgnmentById(int id);
	
	public void save(Clockin clockin);
	
	List<Clockin> deleteAllCheckedValues(Integer userId);
	
	/*public Clockin getClockinById(int id);
	
	public Clockin getClockinByUnitId(int unitId);
	public List<Clockin> fetchAllByOrganisation(int organisationId);
	*/
	public List<Clockin> fetchAllByUnitId(int unitId);
	
	

}
