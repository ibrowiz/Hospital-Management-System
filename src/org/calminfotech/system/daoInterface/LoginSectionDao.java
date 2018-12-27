package org.calminfotech.system.daoInterface;

import java.util.List;


import org.calminfotech.system.models.LoginSection;
import org.calminfotech.utils.models.Organisation;

public interface LoginSectionDao {
	
	
	public List<LoginSection> fetchAll();

	public List<LoginSection> fetchAllByOrgainsation(
			Organisation organisation);

	public List<LoginSection> fetchAllByloginsectn(LoginSection loginSection);
	
	/*public List<LoginSection> getLoginSectionByUserId(User user);*/

	public void save(LoginSection loginSection);

	public LoginSection getLoginSectionlById(int id);

	public void delete(LoginSection loginSection);

	
	
}
