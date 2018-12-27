package org.calminfotech.setup.boInterface;

import java.util.List;

import org.calminfotech.setup.forms.UnitCategoryForm;
import org.calminfotech.setup.models.HrUnitCategory;
import org.calminfotech.setup.models.UnitList;

public interface UnitListBo {
	
	public List<UnitList> fetchAllByOrganisationId(Integer organisationId);
	
public UnitList getUnitListById(int id);
	


}
