package org.calminfotech.disease.daoInterface;

import java.util.List;

import org.calminfotech.disease.models.DiseaseCategoryView;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface DiseaseCategoryViewDao {

public DiseaseCategoryView getDiseaseCatViewById(int id);
	
	public List<DiseaseCategoryView> fetchAllByOrganisation(int organisationid);
	
}
