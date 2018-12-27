package org.calminfotech.lab.dao;

import java.util.List;

import org.calminfotech.lab.models.LabResultSetup;
import org.calminfotech.lab.models.LabTest;
public interface LabResultSetupDao {
	
	public List<LabResultSetup> fetchAll();
	
	public LabResultSetup getLabResultSetupById(Integer resultId);
	
	public List<LabResultSetup> getLabResultSetupByTestId(LabTest labTest);
	
	public LabResultSetup getLabRSetupByTestId(LabTest labTest);
	
	public void save(LabResultSetup labResult);
	
	public void update(LabResultSetup labResult);
	
	public void delete(LabResultSetup labResult);

}
