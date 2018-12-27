package org.calminfotech.lab.bo;

import java.util.List;

import org.calminfotech.lab.models.LabDeleteResult;

public interface LabDeleteResultBo {
	
	List<LabDeleteResult> deleteResult(Integer testId);

}
