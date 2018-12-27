package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.HrUserDepartmentBo;
import org.calminfotech.system.daoInterface.HrUserDepartmentDao;
import org.calminfotech.system.models.HrUserDepartment;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HrUserDepartmentBoImpl implements HrUserDepartmentBo {
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private HrUserDepartmentDao hrUserDepartmentDao;
	
	@Autowired
	private UserBo userBo;

	@Override
	public List<HrUserDepartment> fetchAll() {
		return this.hrUserDepartmentDao.fetchAll();
	}

	@Override
	public List<HrUserDepartment> fetchAllByOrgainsation(
			Organisation organisation) {
		return this.hrUserDepartmentDao.fetchAllByOrgainsation(userIdentity
				.getOrganisation());
	}

	@Override
	public List<HrUserDepartment> getHrUserDepartmentByUserId(Integer userId) {
		User user = this.userBo.getUserById(userId);
		return this.hrUserDepartmentDao.getHrUserDeptByUserId(user);
	}

	@Override
	public void save(HrUserDepartment hrUserDepartment) {
		this.hrUserDepartmentDao.save(hrUserDepartment);
	}

	@Override
	public HrUserDepartment getHrUserDepartmentById(int id) {
		return this.hrUserDepartmentDao.getHrUserDepartmentlById(id);
	}

	@Override
	public void delete(HrUserDepartment hrUserDepartment) {
		this.hrUserDepartmentDao.delete(hrUserDepartment);
	}

	@Override
	public void update(HrUserDepartment hrUserDepartment) {
		this.hrUserDepartmentDao.update(hrUserDepartment);
		
	}

}
