package org.calminfotech.setup.daoInterface;

import java.util.List;

import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.setup.models.HrUnitCategory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface UnitCategoryDao {
	
	public List<HrUnitCategory> fetchAll();	

	public HrUnitCategory getCategoryById(Integer categoryId);
	
	//public List<OuterRecursive> fetchAllTypes();
	
	public void save(HrUnitCategory unitcategory);
	
	public void update(HrUnitCategory unitcategory);
	
	public void delete(HrUnitCategory unitcategory);
	
	public List<HrUnitCategory> fetchAllByOrganisation(int organisationId);

}
