package org.calminfotech.system.boInterface;


import java.util.List;

import org.calminfotech.system.forms.LoginSectionForm;
import org.calminfotech.system.models.LoginSection;

public interface LoginSectionBo {
		
	public LoginSection save(LoginSectionForm loginSectionform);

	public LoginSection getLoginSectionById(int id);

	public void delete(LoginSection loginSection);
	
	public List<LoginSection> fetchAllByOrganisation();

	/*public List<LoginSection> getLoginSectionByUserId(Integer userId);*/
	
	public List<LoginSection> fetchAll();
}
