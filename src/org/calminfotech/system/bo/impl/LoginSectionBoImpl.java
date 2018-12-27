package org.calminfotech.system.bo.impl;

import java.util.List;


import org.calminfotech.system.boInterface.LoginSectionBo;

import org.calminfotech.system.daoInterface.LoginSectionDao;
import org.calminfotech.system.daoInterface.BillingSchemeDao;
import org.calminfotech.system.forms.LoginSectionForm;

import org.calminfotech.system.models.LoginSection;
import org.calminfotech.system.models.BillingScheme;

import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginSectionBoImpl implements  LoginSectionBo{
	
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private UserBo userBo;
	
	@Autowired
	private LoginSectionDao loginSectionDao;
	
	@Autowired
	private BillingSchemeDao organisationPaymentSchemeDao;
	

	@Override
	public LoginSection getLoginSectionById(int id) {
		// TODO Auto-generated method stub
		return this.loginSectionDao.getLoginSectionlById(id);
	}

	@Override
	public void delete(LoginSection loginSection) {
		// TODO Auto-generated method stub
		this.loginSectionDao.delete(loginSection);	
	}

	@Override
	public List<LoginSection> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return this.loginSectionDao.fetchAllByOrgainsation(userIdentity
				.getOrganisation());
	}

	@Override
	public List<LoginSection> fetchAll() {
		// TODO Auto-generated method stub
		return loginSectionDao.fetchAll();
	}



	@Override
	public LoginSection save(LoginSectionForm loginSectionform) {
		// TODO Auto-generated method stub
		
		
		LoginSection loginSection = new LoginSection();
		
	
		loginSection.setSession_name(loginSectionform.getSession_name());
		
		

			System.out.println("Kunle Please Unream this part later");
			//PaymentScheme paymentScheme = organisationPaymentSchemeDao.getschemeById(loginSectionform.getPaymentSchemeId());
			//loginSection.setPscheme(paymentScheme);
			
	
			
		 loginSection.setCreatedBy(userIdentity.getUsername());
		 loginSection.setOrganisation(userIdentity.getOrganisation());
		 loginSectionDao.save(loginSection);
			
			return loginSection;	
	}

	/*@Override
	public List<LoginSection> getLoginSectionByUserId(Integer userId) {
		User user = this.userBo.getUserById(userId);
		return loginSectionDao.getLoginSectionByUserId(user);
	}
*/
}
