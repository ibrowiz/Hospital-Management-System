package org.calminfotech.disease.daoInterface;

import java.util.List;

import org.calminfotech.disease.models.DiseaseCategory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface DiseaseCategoryDao {
	
public DiseaseCategory getDiseaseCategoryById(int categoryId);
	
	public void save(DiseaseCategory diseaseCategory);
	
	public void update(DiseaseCategory diseaseCategory);
	
	public void delete(DiseaseCategory diseaseCategory);
	
	public List<DiseaseCategory> fetchAllByOrganisation(int organisationId);	

}
