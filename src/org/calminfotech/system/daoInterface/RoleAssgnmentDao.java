package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.RoleAssgnment;

public interface RoleAssgnmentDao {
	
	public RoleAssgnment getRoleAssgnmentById(int id);
	
	public void save(RoleAssgnment roleAssignment);
	
	List<RoleAssgnment> deleteAllCheckedValues(Integer roleId);
}