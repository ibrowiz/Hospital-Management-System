package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.LaboratoryDeleteResult;
import org.calminfotech.system.models.RoleAssgnment;

public interface  LaboratoryDeleteResultDao {
	
	List<LaboratoryDeleteResult> deleteResult(Integer testId);	
	
}
