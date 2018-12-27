package org.calminfotech.setup.boInterface;

import java.util.List;

import org.calminfotech.setup.models.AllergyCategoryView;
import org.calminfotech.setup.models.UnitCategoryView;

public interface UnitCategoryViewBo {
	
	public List<UnitCategoryView> fetchAll();

	public UnitCategoryView getUnitCategoryViewById(int id);
	
	public List<UnitCategoryView> fetchAllByOrganisation(int organisationid);

}
