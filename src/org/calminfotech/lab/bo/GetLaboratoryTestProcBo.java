package org.calminfotech.lab.bo;

import java.util.List;

import org.calminfotech.lab.models.GetLaboratoryTestProc;

public interface GetLaboratoryTestProcBo {
	
	List<GetLaboratoryTestProc> fetchResult(Integer testId);

}
