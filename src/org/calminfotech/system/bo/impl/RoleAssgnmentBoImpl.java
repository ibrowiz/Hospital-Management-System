package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.RoleAssgnmentBo;
import org.calminfotech.system.daoInterface.RoleAssgnmentDao;
import org.calminfotech.system.models.RoleAssgnment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleAssgnmentBoImpl implements RoleAssgnmentBo {

	@Autowired
	private RoleAssgnmentDao roleAssgnmentDao;
	
	@Override
	public RoleAssgnment getRoleAssgnmentById(int id) {
		return this.roleAssgnmentDao.getRoleAssgnmentById(id);
	}
	
	@Override
	public void save(RoleAssgnment roleAssignment) {
		this.roleAssgnmentDao.save(roleAssignment);
	}
	
	@Override
	public List<RoleAssgnment> deleteAllCheckedValues(Integer roleId) {
		return this.roleAssgnmentDao.deleteAllCheckedValues(roleId);
	}

}
