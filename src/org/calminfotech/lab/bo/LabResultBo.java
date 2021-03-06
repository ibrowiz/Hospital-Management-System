package org.calminfotech.lab.bo;

import java.util.List;

import org.calminfotech.lab.models.LabDeleteResult;
import org.calminfotech.lab.models.LabResult;
import org.calminfotech.lab.models.LabTest;


public interface LabResultBo {
	
	public LabResult getResultByTestId(LabTest labTest);

	public LabResult getResultById(int id);
		
		public void save(LabResult laboratoryResult);
		
		public void deleteByTestId(LabDeleteResult labDelResult);
		
		List<LabResult> allLabResultById(Integer id);
		
		List<LabResult> allLabResultByTestId(LabTest labTest);
}
