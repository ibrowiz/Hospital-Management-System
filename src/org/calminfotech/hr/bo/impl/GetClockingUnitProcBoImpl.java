package org.calminfotech.hr.bo.impl;

import java.util.List;

import org.calminfotech.hr.boInterface.GetClockingUnitProcBoInterface;
import org.calminfotech.hr.daoInterface.GetClockingUnitProcDao;
import org.calminfotech.hr.models.GetClockingUnitProc;
import org.calminfotech.system.daoInterface.GetUserAssignmentProcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class GetClockingUnitProcBoImpl implements GetClockingUnitProcBoInterface  {
	
	@Autowired
	private GetClockingUnitProcDao getClockingUnitProcDao;

	@Override
	public List<GetClockingUnitProc> fetchClockinUnit(Integer userId) {
		// TODO Auto-generated method stub
		return this.getClockingUnitProcDao.fetchClockinUnit(userId);
	}

}
