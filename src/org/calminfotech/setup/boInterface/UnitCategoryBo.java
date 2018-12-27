package org.calminfotech.setup.boInterface;

import java.util.List;

import org.calminfotech.setup.forms.UnitCategoryForm;
import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.setup.models.HrUnitCategory;

public interface UnitCategoryBo {
	
	public List<HrUnitCategory> fetchAll();	

	public HrUnitCategory getCategoryById(Integer categoryId);
	
	public HrUnitCategory save(UnitCategoryForm unitCategoryForm);
	
	public void update(UnitCategoryForm unitCategoryForm);
	
	public void delete(HrUnitCategory unitCategory);
	
	public List<HrUnitCategory> fetchAllByOrganisation(int organisationId);

}
