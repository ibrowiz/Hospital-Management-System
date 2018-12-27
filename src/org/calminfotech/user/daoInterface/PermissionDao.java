package org.calminfotech.user.daoInterface;

import java.util.List;

import org.calminfotech.user.models.Permission;
import org.calminfotech.user.models.Role;
import org.calminfotech.utils.models.Organisation;

public interface PermissionDao {

	void save(Permission permission);
	
	void update(Permission permission);
	
	void delete(Permission permission);
	
	Permission getPermissionById(int id);
	
	List<Permission> fetchAll();
	
	List<Permission> getPermissionByRole(Role role);
	
	List<Permission> fetchByOrganisation(Organisation organisation);
	
	List<Permission> fetchMenuPermissionByOrganisation(Organisation organisation, String val);
}
