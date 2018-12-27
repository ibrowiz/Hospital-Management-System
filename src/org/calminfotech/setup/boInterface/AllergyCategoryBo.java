package org.calminfotech.setup.boInterface;

import java.util.List;

import org.calminfotech.setup.forms.AllergyCategoryForm;
import org.calminfotech.setup.models.AllergyCategory;

public interface AllergyCategoryBo {

	public List<AllergyCategory> fetchAll();	

	public AllergyCategory getCategoryById(int categoryId);
	
	public AllergyCategory save(AllergyCategoryForm allergyCategoryForm);
	
	public void update(AllergyCategoryForm allergyCategoryForm);
	
	public void delete(AllergyCategory category);
	
	public List<AllergyCategory> fetchAllByOrganisation(int organisationId);
	
	//public List<OuterRecursive> fetchAllTypes();
	
}
