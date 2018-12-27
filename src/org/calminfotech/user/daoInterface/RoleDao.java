package org.calminfotech.user.daoInterface;

import java.util.List;

import org.calminfotech.user.models.Role;
import org.calminfotech.utils.models.Organisation;

public interface RoleDao {
	
	void save(Role role);
	
	void delete(Role role);
	
	void update(Role role);
	
	Role getRoleById(int id);
	
	Role getRoleByIdJson(int id);
	
	List<Role> fetchAll();
	
	List<Role> fetchAllRoleByOrganisation(Organisation organisation);
	
	Role getRoleByAdmin(Organisation organisation);
}
