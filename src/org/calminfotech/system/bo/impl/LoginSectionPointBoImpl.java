package org.calminfotech.system.bo.impl;

import org.calminfotech.system.boInterface.LoginSectionPointBo;
import org.calminfotech.system.daoInterface.BillingItemPriceDetailDao;
import org.calminfotech.system.daoInterface.LoginSectionPointDao;
import org.calminfotech.system.models.LoginSectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class LoginSectionPointBoImpl implements LoginSectionPointBo{

	
	
	@Autowired
	private LoginSectionPointDao loginSectionPointDao;
	
	
	@Override
	public void save(LoginSectionPoint loginSectionPoint) {
		// TODO Auto-generated method stub
		this.loginSectionPointDao.save(loginSectionPoint);
	}

	@Override
	public LoginSectionPoint getLoginSectionPointById(int id) {
		// TODO Auto-generated method stub
		return this.loginSectionPointDao.getLoginSectionPointById(id);
	}

	@Override
	public void delete(LoginSectionPoint loginSectionPoint) {
		// TODO Auto-generated method stub
		this.loginSectionPointDao.delete(loginSectionPoint);	
	}
	
	

}
