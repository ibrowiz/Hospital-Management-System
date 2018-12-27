package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.LaboratoryDeleteResult;

public interface LaboratoryDeleteResultBo {
	
	List<LaboratoryDeleteResult> deleteResult(Integer testId);

}
