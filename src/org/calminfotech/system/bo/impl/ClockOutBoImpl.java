package org.calminfotech.system.bo.impl;

import org.calminfotech.system.boInterface.ClockOutBo;
import org.calminfotech.system.daoInterface.ClockOutDao;

import org.calminfotech.system.models.ClockOut;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ClockOutBoImpl implements ClockOutBo{

	
	@Autowired
	private UserIdentity userIdentity;
	
	
	@Autowired
	private ClockOutDao clockOutDao;
	
	
	
	@Override
	public void save(ClockOut clockOut) {
		// TODO Auto-generated method stub
		this.clockOutDao.save(clockOut);
	}

	@Override
	public void delete(ClockOut clockOut) {
		// TODO Auto-generated method stub
		clockOutDao.delete(clockOut);
	}

	@Override
	public void update(ClockOut clockOut) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClockOut getClockOutById(int id) {
		// TODO Auto-generated method stub
		return clockOutDao.getClockOutById(id);
	}

}
