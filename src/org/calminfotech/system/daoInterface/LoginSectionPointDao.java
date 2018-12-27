package org.calminfotech.system.daoInterface;

import java.util.List;


import org.calminfotech.system.models.LoginSection;
import org.calminfotech.system.models.LoginSectionPoint;

import org.calminfotech.utils.models.Organisation;

public interface LoginSectionPointDao {
	
	
	public List<LoginSectionPoint> fetchAll();

	public List<LoginSectionPoint> fetchAllByOrgainsation(
			Organisation organisation);

	public List<LoginSectionPoint> fetchAllByLoginSectionPoint(LoginSectionPoint LoginSectionPoint);

	public void save(LoginSectionPoint LoginSectionPoint);

	public LoginSectionPoint getLoginSectionPointById(int id);

	public void delete(LoginSectionPoint LoginSectionPoint);

}
