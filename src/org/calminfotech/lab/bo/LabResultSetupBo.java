package org.calminfotech.lab.bo;

import java.util.List;

import org.calminfotech.lab.forms.LabResultSetupForm;
import org.calminfotech.lab.models.LabResultSetup;


	public interface LabResultSetupBo {
	
		public List<LabResultSetup> fetchAll();
		
		public LabResultSetup getLabResultSetupById(Integer resultId);
		
		public List<LabResultSetup> getLabResultSetupByTestId(Integer TestId);
		
		public LabResultSetup getLabRSetupByTestId(Integer testId);
		
		public LabResultSetup save(LabResultSetupForm labResultSetForm);
		
		public void update(LabResultSetupForm labRSetupForm);
		
		public void delete(LabResultSetup labResult);

}
