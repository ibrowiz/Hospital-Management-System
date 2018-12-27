package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.HrUserDepartmentBo;
import org.calminfotech.system.boInterface.HrUserPointBo;
import org.calminfotech.system.daoInterface.HrUserDepartmentDao;
import org.calminfotech.system.daoInterface.HrUserPointDao;
import org.calminfotech.system.models.GetPointDetails;
import org.calminfotech.system.models.HrUserDepartment;
import org.calminfotech.system.models.HrUserPoint;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HrUserPointBoImpl implements HrUserPointBo {
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private HrUserPointDao hrUserPointDao;
	
	@Autowired
	private UserBo userBo;

	@Override
	public List<HrUserPoint> fetchAll() {
		return this.hrUserPointDao.fetchAll();
	}

	@Override
	public List<HrUserPoint> fetchAllByOrgainsation(
			Organisation organisation) {
		return this.hrUserPointDao.fetchAllByOrgainsation(userIdentity
				.getOrganisation());
	}

	@Override
	public List<HrUserPoint> getHrUserPointByUserId(Integer userId) {
		User user = this.userBo.getUserById(userId);
		return this.hrUserPointDao.getHrUserPointByUserId(user);
	}

	@Override
	public void save(HrUserPoint hrUserPoint) {
		this.hrUserPointDao.save(hrUserPoint);
	}

	@Override
	public HrUserPoint getHrUserPointById(int id) {
		return this.hrUserPointDao.getHrUserPointById(id);
	}

	@Override
	public void delete(HrUserPoint hrUserPoint) {
		this.hrUserPointDao.delete(hrUserPoint);
	}

	@Override
	public void update(HrUserPoint hrUserPoint) {
		this.hrUserPointDao.update(hrUserPoint);
	}

	@Override
	public List<GetPointDetails> getHrUserPointForUnit(int unitId, int userId) {
		return this.hrUserPointDao.getHrUserPointForUnit(unitId, userId);
	}

}
