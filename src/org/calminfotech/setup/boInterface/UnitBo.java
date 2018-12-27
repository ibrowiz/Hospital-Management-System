package org.calminfotech.setup.boInterface;

import java.util.List;

import org.calminfotech.setup.forms.UnitCategoryForm;
import org.calminfotech.setup.forms.UnitForm;
import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.setup.models.HrUnit;
import org.calminfotech.setup.models.HrUnitCategory;

public interface UnitBo {
	
	public List<HrUnit> fetchAll();	

	public HrUnit getUnitById(int categoryId);
	
	public HrUnit save(UnitForm unitForm);
	
	public void update(UnitForm unitForm);
	
	public void delete(HrUnit hrUnit);
	
	public List<HrUnit> fetchAllByOrganisation(int organisationId);

}
