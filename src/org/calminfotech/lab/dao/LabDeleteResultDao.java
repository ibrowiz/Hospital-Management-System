package org.calminfotech.lab.dao;

import java.util.List;

import org.calminfotech.lab.models.LabDeleteResult;


public interface  LabDeleteResultDao {
	
	List<LabDeleteResult> deleteResult(Integer testId);	
	
}
