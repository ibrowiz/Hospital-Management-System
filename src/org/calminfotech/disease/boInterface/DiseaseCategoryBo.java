package org.calminfotech.disease.boInterface;

import java.util.List;
import org.calminfotech.disease.forms.DiseaseCategoryForm;
import org.calminfotech.disease.models.DiseaseCategory;

public interface DiseaseCategoryBo {
public DiseaseCategory getDiseaseCategoryById(int categoryId);
	
	public DiseaseCategory save(DiseaseCategoryForm diseaseCategoryForm);
	
	public void update(DiseaseCategoryForm diseaseCategoryForm);
	
	public void delete(DiseaseCategory diseaseCategory);
	
	public List<DiseaseCategory> fetchAllByOrganisation(int organisationId);
}
