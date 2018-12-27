package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.HrUserSection;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.user.models.User;
import org.calminfotech.utils.models.Organisation;

public interface HrUserSectionDao {
	
	public List<HrUserSection> fetchAll();

	public List<HrUserSection> fetchAllByOrgainsation(
			Organisation organisation);

	public List<HrUserSection> fetchAllByloginsectn(LoginSection loginSection);
	
	public List<HrUserSection> getHrUserSectionByUserId(User user);

	public void save(HrUserSection hrUserSection);
	
	public void update(HrUserSection hrUserSection);

	public HrUserSection getHrUserSectionlById(int id);

	public void delete(HrUserSection hrUserSection);


}
