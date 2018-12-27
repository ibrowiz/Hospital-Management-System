package org.calminfotech.lab.bo;

import java.util.List;

import org.calminfotech.lab.models.LabTestList;

public interface LabTestListBo {
	
	public List<LabTestList> fetchAllByOrganisation(Integer organisationId);

}
