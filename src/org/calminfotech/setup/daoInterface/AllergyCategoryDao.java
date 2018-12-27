package org.calminfotech.setup.daoInterface;
import java.util.List;

import org.calminfotech.setup.models.Allergy;
import org.calminfotech.setup.models.AllergyCategory;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface AllergyCategoryDao {

	public List<AllergyCategory> fetchAll();	

	public AllergyCategory getCategoryById(int categoryId);
	
	public void save(AllergyCategory category);
	
	public void update(AllergyCategory category);
	
	public void delete(AllergyCategory category);
	
	public List<AllergyCategory> fetchAllByOrganisation(int organisationId);
	
}
