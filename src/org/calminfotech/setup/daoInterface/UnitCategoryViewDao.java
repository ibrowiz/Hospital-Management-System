package org.calminfotech.setup.daoInterface;

import java.util.List;

import org.calminfotech.setup.models.AllergyCategoryView;
import org.calminfotech.setup.models.UnitCategoryView;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface UnitCategoryViewDao {
	
	public List<UnitCategoryView> fetchAll();

	public UnitCategoryView getUnitCategoryViewById(int id);
	
	public List<UnitCategoryView> fetchAllByOrganisation(int organisationid);

}
