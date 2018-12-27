package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.HrUserDepartmentBo;
import org.calminfotech.system.boInterface.HrUserUnitBo;
import org.calminfotech.system.daoInterface.HrUserDepartmentDao;
import org.calminfotech.system.daoInterface.HrUserUnitDao;
import org.calminfotech.system.models.GetUnitDetails;
import org.calminfotech.system.models.HrUserDepartment;
import org.calminfotech.system.models.HrUserUnit;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HrUserUnitBoImpl implements HrUserUnitBo {
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private HrUserUnitDao hrUserUnitDao;
	
	@Autowired
	private UserBo userBo;

	@Override
	public List<HrUserUnit> fetchAll() {
		return this.hrUserUnitDao.fetchAll();
	}

	@Override
	public List<HrUserUnit> fetchAllByOrgainsation(
			Organisation organisation) {
		return this.hrUserUnitDao.fetchAllByOrgainsation(userIdentity
				.getOrganisation());
	}

	@Override
	public List<HrUserUnit> getHrUserUnitByUserId(Integer userId) {
		User user = this.userBo.getUserById(userId);
		return this.hrUserUnitDao.getHrUserUnitByUserId(user);
	}

	@Override
	public void save(HrUserUnit hrUserUnit) {
		this.hrUserUnitDao.save(hrUserUnit);
	}

	@Override
	public HrUserUnit getHrUserUnitById(int id) {
		return this.hrUserUnitDao.getHrUserUnitById(id);
	}

	@Override
	public void delete(HrUserUnit hrUserUnit) {
		this.hrUserUnitDao.delete(hrUserUnit);
	}

	@Override
	public void update(HrUserUnit hrUserUnit) {
		this.hrUserUnitDao.update(hrUserUnit);
	}

	@Override
	public List<GetUnitDetails> getHrUserUnitForDep(int deptId, int userId) {
		// TODO Auto-generated method stub
		return this.hrUserUnitDao.getHrUserUnitForDep(deptId, userId);
	}

}
