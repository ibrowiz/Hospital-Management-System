package org.calminfotech.lab.dao;

import java.util.List;

import org.calminfotech.lab.models.LabTestList;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface LabTestListDao {

	
	
	public List<LabTestList> fetchAllByOrganisation(Integer organisationId);
}
