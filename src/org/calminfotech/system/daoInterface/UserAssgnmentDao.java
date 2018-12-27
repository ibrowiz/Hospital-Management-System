package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.UserAssgnment;

public interface UserAssgnmentDao {
	
	public UserAssgnment getUserAssgnmentById(int id);
	
	public void save(UserAssgnment userAssignment);
	
	List<UserAssgnment> deleteAllUserCheckedValues(Integer userId);
}
