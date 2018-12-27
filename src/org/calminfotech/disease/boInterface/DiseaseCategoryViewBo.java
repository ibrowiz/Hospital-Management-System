package org.calminfotech.disease.boInterface;

import java.util.List;

import org.calminfotech.disease.models.DiseaseCategoryView;

public interface DiseaseCategoryViewBo {
	
public DiseaseCategoryView getDiseaseCatViewById(int id);
	
	public List<DiseaseCategoryView> fetchAllByOrganisation(int organisationid);

}
