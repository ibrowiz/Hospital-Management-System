package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.UserAssgnment;

public interface UserAssgnmentBo {

	public UserAssgnment getUserAssgnmentById(int id);
	
	public void save(UserAssgnment userAssignment);
	
	List<UserAssgnment> deleteAllUserCheckedValues(Integer userId);
}
