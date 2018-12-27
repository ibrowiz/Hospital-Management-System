package org.calminfotech.lab.bo;

import java.util.List;

import org.calminfotech.lab.forms.LabTestForm;
import org.calminfotech.lab.models.LabTest;

public interface LabTestBo {
	public List<LabTest> fetchAllByOrganisationId(Integer organisationId);	

	public LabTest getLabtestById(Integer id);
	
	public List<LabTest> getLabtestByCatId(Integer catId);
	
	public LabTest save(LabTestForm labTestForm);
	
	public void update(LabTestForm labTestForm);
	
	public void delete(LabTest labTest);
}
