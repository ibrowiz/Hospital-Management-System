package org.calminfotech.disease.boInterface;

import java.util.List;

import org.calminfotech.disease.models.DiseaseCategoryList;

public interface DiseaseCategoryListBo {
	
	public List<DiseaseCategoryList> fetchAllByOrganisation(
			Integer organisationId);

}
