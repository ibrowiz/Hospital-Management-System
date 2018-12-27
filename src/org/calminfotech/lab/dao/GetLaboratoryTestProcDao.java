package org.calminfotech.lab.dao;

import java.util.List;

import org.calminfotech.lab.models.GetLaboratoryTestProc;

public interface GetLaboratoryTestProcDao {
	
	List<GetLaboratoryTestProc> fetchResult(Integer testId);

}
