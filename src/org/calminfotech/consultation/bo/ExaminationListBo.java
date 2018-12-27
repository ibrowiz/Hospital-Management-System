package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.models.ExaminationList;

public interface ExaminationListBo  {

public List<ExaminationList> fetchAll();	
	
	public List<ExaminationList> fetchAllByOrganisation(Integer organisationId);
	
}
