package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.GetRoleAssignmentProcBo;
import org.calminfotech.system.daoInterface.GetRoleAssignmentProcDao;
import org.calminfotech.system.models.GetRoleAssignmentProc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetRoleAssignmentProcBoImpl implements GetRoleAssignmentProcBo {

	@Autowired
	private GetRoleAssignmentProcDao roleAssignmentProcDao;

	@Override
	public List<GetRoleAssignmentProc> fetchRolePermission(Integer roleId) {
		// TODO Auto-generated method stub
		return this.roleAssignmentProcDao.fetchRolePermission(roleId);
	}
}
