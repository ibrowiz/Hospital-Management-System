package org.calminfotech.lab.dao;

import java.util.List;

import org.calminfotech.lab.models.LabTestCategory;
import org.calminfotech.lab.models.LabTestCategoryView;

public interface LabTestCategoryDaoInter {
	
	public List<LabTestCategory> fetchAllCatByOrganisation(Integer OrganisationId);	
	
	public List<LabTestCategoryView> fetchAllCatByOrg(Integer organisationId);

	public LabTestCategory getLabtestCatById(Integer id);
	
	public void save(LabTestCategory labTestCat);
	
	public void update(LabTestCategory labTestCat);
	
	public void delete(LabTestCategory labTestCat);

}
