package org.calminfotech.disease.daoInterface;

import java.util.List;

import org.calminfotech.disease.models.DiseaseCategoryList;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface DiseaseCategoryListDao {
	
	public List<DiseaseCategoryList> fetchAllByOrganisation(Integer organisationId);	

}
