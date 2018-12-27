package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.GetRoleAssignmentProc;

public interface GetRoleAssignmentProcBo {
	
	List<GetRoleAssignmentProc> fetchRolePermission(Integer roleId);
}
