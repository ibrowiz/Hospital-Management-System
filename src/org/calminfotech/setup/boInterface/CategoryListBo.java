package org.calminfotech.setup.boInterface;

import java.util.List;

import org.calminfotech.setup.models.AllergyCategoryList;

public interface CategoryListBo {


	public List<AllergyCategoryList> fetchAll();	
	
	public List<AllergyCategoryList> fetchAllByOrganisation(Integer organisationId);
}
