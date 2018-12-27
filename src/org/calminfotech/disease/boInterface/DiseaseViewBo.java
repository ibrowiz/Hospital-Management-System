package org.calminfotech.disease.boInterface;

import java.util.List;

import org.calminfotech.disease.models.DiseaseView;
public interface DiseaseViewBo {
	
public DiseaseView getDiseaseViewById(int id);
	
	public List<DiseaseView> fetchAllByOrganisation(int organisationid);

}
