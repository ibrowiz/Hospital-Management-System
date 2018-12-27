package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.GetRoleAssignmentProc;

public interface GetRoleAssignmentProcDao {
	
	List<GetRoleAssignmentProc> fetchRolePermission(Integer roleId);
}
