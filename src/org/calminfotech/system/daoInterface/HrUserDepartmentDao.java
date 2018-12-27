package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.HrUserDepartment;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.user.models.User;
import org.calminfotech.utils.models.Organisation;

public interface HrUserDepartmentDao {
	
	public List<HrUserDepartment> fetchAll();

	public List<HrUserDepartment> fetchAllByOrgainsation(
			Organisation organisation);

	public List<HrUserDepartment> fetchAllByloginsectn(LoginSection loginSection);
	
	public List<HrUserDepartment> getHrUserDeptByUserId(User user);

	public void save(HrUserDepartment hrUserDepartment);
	
	public void update(HrUserDepartment hrUserDepartment);

	public HrUserDepartment getHrUserDepartmentlById(int id);

	public void delete(HrUserDepartment hrUserDepartment);

}
