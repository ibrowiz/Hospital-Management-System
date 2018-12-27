package org.calminfotech.lab.dao;

import java.util.List;

import org.calminfotech.lab.models.LabTest;
import org.calminfotech.lab.models.LabTestCategory;
import org.calminfotech.utils.models.Organisation;



public interface LabTestDao {
	
	public List<LabTest> fetchAllByOrganisationId(Integer organisationId);	

	public LabTest getLabtestById(Integer id);
	
	public List<LabTest> getLaboratoryTestByCatId(LabTestCategory lCategory);
	
	public void save(LabTest labTest);
	
	public void update(LabTest labTest);
	
	public void delete(LabTest labTest);

}
