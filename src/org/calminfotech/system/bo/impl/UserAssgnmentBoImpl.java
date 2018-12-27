package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.UserAssgnmentBo;
import org.calminfotech.system.daoInterface.UserAssgnmentDao;
import org.calminfotech.system.models.UserAssgnment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAssgnmentBoImpl implements UserAssgnmentBo {
	
	@Autowired
	private UserAssgnmentDao userAssigned;

	@Override
	public void save(UserAssgnment userAssignment) {
		userAssigned.save(userAssignment);
	}

	@Override
	public List<UserAssgnment> deleteAllUserCheckedValues(Integer userId) {
		// TODO Auto-generated method stub
		return this.userAssigned.deleteAllUserCheckedValues(userId);
	}

	@Override
	public UserAssgnment getUserAssgnmentById(int id) {
		// TODO Auto-generated method stub
		return this.userAssigned.getUserAssgnmentById(id);
	}

}
