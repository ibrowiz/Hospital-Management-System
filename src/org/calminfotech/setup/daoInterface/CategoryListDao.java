package org.calminfotech.setup.daoInterface;

import java.util.List;

import org.calminfotech.setup.models.AllergyCategoryList;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface CategoryListDao {
	
	public List<AllergyCategoryList> fetchAll();	
	
	public List<AllergyCategoryList> fetchAllByOrganisation(Integer organisationId);

}
