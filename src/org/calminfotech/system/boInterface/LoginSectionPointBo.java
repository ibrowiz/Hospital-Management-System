package org.calminfotech.system.boInterface;


import org.calminfotech.system.models.LoginSectionPoint;



public interface LoginSectionPointBo {
	
	public void save(LoginSectionPoint loginSectionPoint);

	public LoginSectionPoint getLoginSectionPointById(int id);

	public void delete(LoginSectionPoint loginSectionPoint);
	
	

}
