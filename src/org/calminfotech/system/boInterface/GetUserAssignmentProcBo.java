package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.GetUserAssignmentProc;

public interface GetUserAssignmentProcBo {

	List<GetUserAssignmentProc> fetchUserPermission(Integer userId);
}
