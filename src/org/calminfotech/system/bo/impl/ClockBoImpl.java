package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.ClockBo;
import org.calminfotech.system.daoInterface.ClockDao;
import org.calminfotech.system.models.Clock;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClockBoImpl implements ClockBo {

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private ClockDao clockDao;
	
	@Override
	public List<Clock> fetchAllClock() {
		return this.clockDao.fetchAllClock();
	}

	@Override
	public List<Clock> fetchAllByOrganisation(Organisation organisation) {
		return this.clockDao.fetchAllByOrganisation(organisation);
	}

	@Override
	public Clock getClockById(Integer id) {
		return this.clockDao.getClockById(id);
	}

	@Override
	public void save(Clock clock) {
		this.clockDao.save(clock);
	}

	@Override
	public void update(Clock clock) {
		this.clockDao.update(clock);
	}

	@Override
	public void delete(Clock clock) {
		this.clockDao.delete(clock);
	}

}
