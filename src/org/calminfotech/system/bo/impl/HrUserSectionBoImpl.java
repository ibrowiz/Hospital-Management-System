package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.HrUserSectionBo;
import org.calminfotech.system.daoInterface.HrUserSectionDao;
import org.calminfotech.system.models.HrUserSection;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HrUserSectionBoImpl implements HrUserSectionBo {
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private HrUserSectionDao hrUserSectionDao;
	
	@Autowired
	private UserBo userBo;

	@Override
	public List<HrUserSection> fetchAll() {
		return this.hrUserSectionDao.fetchAll();
	}

	@Override
	public List<HrUserSection> fetchAllByOrgainsation(Organisation organisation) {
		return this.hrUserSectionDao.fetchAllByOrgainsation(userIdentity
				.getOrganisation());
	}

	/*@Override
	public List<HrUserSection> fetchAllByloginsectn(LoginSection loginSection) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<HrUserSection> getHrUserSectionByUserId(Integer userId) {
		User user = this.userBo.getUserById(userId);
		return this.hrUserSectionDao.getHrUserSectionByUserId(user);
	}

	@Override
	public void save(HrUserSection hrUserSection) {
		// TODO Auto-generated method stub
		this.hrUserSectionDao.save(hrUserSection);
	}
	
	@Override
	public void update(HrUserSection hrUserSection) {
		// TODO Auto-generated method stub
		this.hrUserSectionDao.update(hrUserSection);
	}

	@Override
	public HrUserSection getHrUserSectionlById(int id) {
		return this.hrUserSectionDao.getHrUserSectionlById(id);
	}

	@Override
	public void delete(HrUserSection hrUserSection) {
		this.hrUserSectionDao.delete(hrUserSection);
	}

}
