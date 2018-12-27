package org.calminfotech.user.bo.impl;

import org.calminfotech.user.boInterface.GroupBo;
import org.calminfotech.user.boInterface.TitleBo;
import org.calminfotech.user.boInterface.UserClockOutBo;
import org.calminfotech.user.daoInterface.UserClockOutDao;
import org.calminfotech.user.daoInterface.UserDao;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserClockOut;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.Encryptor;
import org.calminfotech.utils.NotificationCentre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class UserClockOutBoImpl implements UserClockOutBo{
	
	@Autowired
	private UserClockOutDao userClockOutDao;
	

	@Override
	public void save(UserClockOut userClockOut) {
		// TODO Auto-generated method stub
		userClockOutDao.save(userClockOut);
	}

	@Override
	public void delete(UserClockOut userClockOut) {
		// TODO Auto-generated method stub
		userClockOutDao.delete(userClockOut);
	}

	@Override
	public void update(UserClockOut userClockOut) {
		// TODO Auto-generated method stub
		userClockOutDao.update(userClockOut);
	}

	@Override
	public UserClockOut getUserClockOutById(int userId) {
		// TODO Auto-generated method stub
		UserClockOut userClockOut = userClockOutDao.getUserById(userId);
		return userClockOut;
	}

}
