package org.calminfotech.system.bo.impl;

import java.util.Date;
import java.util.List;

import org.calminfotech.system.boInterface.ClockingBo;
import org.calminfotech.system.daoInterface.ClockingDao;


import org.calminfotech.system.forms.ClockingForm;
import org.calminfotech.system.models.Clocking;
import org.calminfotech.system.models.Gender;
import org.calminfotech.hr.forms.GetClockingUnitProcForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.user.boInterface.UserLoginSessionBo;
import org.calminfotech.user.models.Language;
import org.calminfotech.user.models.Title;
import org.calminfotech.user.models.UserLoginSession;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.AutoGen;
import org.calminfotech.utils.LoginSectionPointList;
import org.calminfotech.utils.SectionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ClockingBoImpl implements ClockingBo{
	
	
	@Autowired
	private UserIdentity userIdentity;
	
	
	@Autowired
	private ClockingDao clockingDao;
	
	
	
/*	@Autowired
	private UserLoginSessionBo userLoginSessionBo;*/
	
	
	
	
	/*@Override
	public Clocking save(ClockingForm clockingform) {
		// TODO Auto-generated method stub
		Clocking clocking = new Clocking();

		clocking.setUsername(clockingform.getUsername());
		clocking.setClock_out_time(clockingform.getClock_out_time());
		clocking.setLoginSection(this.sectionList.getLoginSectionById(clockingform.getLoginSectionId()));
		clocking.setLoginSectionPoint(this.loginSectionPointList.getLoginSectionPointById(clockingform.getLoginSectionPointId()));
	

		clocking.setOrganisation(userIdentity.getOrganisation());

		this.clockingDao.save(clocking);

		return clocking;
	}
	*/
	
	

	@Override
	public Clocking getClockingById(int id) {
		// TODO Auto-generated method stub
		return clockingDao.getClockingById(id);
	}

	@Override
	public void delete(Clocking clocking) {
		// TODO Auto-generated method stub
		clockingDao.delete(clocking);
	}

	@Override
	public void save(Clocking clocking) {
		this.clockingDao.save(clocking);
		/*// TODO Auto-generated method stub
		Clocking clocking = new Clocking();
		if(getClockingUnitProcForm.getSaveButton()=="clocking"){
		clocking.setUserId(userIdentity.getUserId());
		clocking.setUsername(userIdentity.getUsername());
		clocking.setClockTime(new Date(System.currentTimeMillis()));
		clocking.setClockingType("in");
		clocking.setOrganisationId(userIdentity.getOrganisation().getId());
		this.clockingDao.save(clocking);
		
		}else{
			clocking.setUserId(userIdentity.getUserId());
			clocking.setUsername(userIdentity.getUsername());
			clocking.setClockTime(new Date(System.currentTimeMillis()));
			clocking.setClockingType("out");
			clocking.setOrganisationId(userIdentity.getOrganisation().getId());
			this.clockingDao.save(clocking);	

			
		}
		return clocking;*/
	}

	@Override
	public void update(Clocking clocking) {
		// TODO Auto-generated method stub
	
	}

	

	
}
