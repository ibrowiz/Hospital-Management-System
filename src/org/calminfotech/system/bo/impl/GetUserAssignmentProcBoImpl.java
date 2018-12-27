package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.GetUserAssignmentProcBo;
import org.calminfotech.system.daoInterface.GetUserAssignmentProcDao;
import org.calminfotech.system.models.GetUserAssignmentProc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class GetUserAssignmentProcBoImpl implements GetUserAssignmentProcBo {
	
	@Autowired
	private GetUserAssignmentProcDao getUserAssignmentProcDao;

	@Override
	public List<GetUserAssignmentProc> fetchUserPermission(Integer userId) {
		// TODO Auto-generated method stub
		return this.getUserAssignmentProcDao.fetchUserPermission(userId);
	}

}
