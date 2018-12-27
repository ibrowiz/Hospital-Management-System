package org.calminfotech.disease.daoInterface;

import java.util.List;

import org.calminfotech.disease.models.DiseaseView;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface DiseaseViewDao {

public DiseaseView getDiseaseViewById(int id);
	
	public List<DiseaseView> fetchAllByOrganisation(int organisationid);
	
}
