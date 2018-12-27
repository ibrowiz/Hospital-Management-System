package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.RoleAssgnment;

public interface RoleAssgnmentBo {
	
	public RoleAssgnment getRoleAssgnmentById(int id);
	
	public void save(RoleAssgnment roleAssignment);
	
	List<RoleAssgnment> deleteAllCheckedValues(Integer roleId);
}
