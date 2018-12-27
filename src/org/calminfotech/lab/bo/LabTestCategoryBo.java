package org.calminfotech.lab.bo;

import java.util.List;

import org.calminfotech.lab.forms.LabTestCategoryForm;
import org.calminfotech.lab.models.LabTestCategory;
import org.calminfotech.lab.models.LabTestCategoryView;

public interface LabTestCategoryBo {
	public List<LabTestCategory> fetchAllCatByOrganisation(Integer organisation);	

	public LabTestCategory getLabtestCatById(Integer id);
	
	public LabTestCategory save(LabTestCategoryForm labTestCatForm);
	
	public void update(LabTestCategoryForm labTestCatForm);
	
	public List<LabTestCategoryView> fetchAllCatByOrg(Integer organisationId);
	
	public void delete(LabTestCategory labTestCat);

}
