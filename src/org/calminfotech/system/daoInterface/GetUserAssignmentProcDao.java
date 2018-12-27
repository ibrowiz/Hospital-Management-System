package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.GetUserAssignmentProc;

public interface GetUserAssignmentProcDao {

	List<GetUserAssignmentProc> fetchUserPermission(Integer userId);
}
