package org.calminfotech.setup.boInterface;

import java.util.List;

import org.calminfotech.setup.models.AllergyCategoryView;

public interface AllergyCategoryViewBo {
	
	public List<AllergyCategoryView> fetchAll();
	
	public List<AllergyCategoryView> fetchAllByOrganisation(int organisationid);

	
	public AllergyCategoryView getAllergyCatViewById(int id) ;

}
