package org.calminfotech.user.boInterface;

import java.util.List;

import org.calminfotech.user.models.Role;
import org.calminfotech.utils.models.Organisation;

public interface RoleBo {
	
	void save(Role role);
	
	void delete(Role role);
	
	void update(Role role);
	
	Role getRoleById(int id);
	
	List<Role> fetchAll();
	
	List<Role> fetchAllRoleByOrganisation();
	
	Role getRoleByAdmin(Organisation organisation);
}
