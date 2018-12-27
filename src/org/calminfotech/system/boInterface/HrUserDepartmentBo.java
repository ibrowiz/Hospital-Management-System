package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.HrUserDepartment;
import org.calminfotech.utils.models.Organisation;

public interface HrUserDepartmentBo {
	
	public List<HrUserDepartment> fetchAll();

	public List<HrUserDepartment> fetchAllByOrgainsation(
			Organisation organisation);

/*	public List<HrUserSection> fetchAllByloginsectn(LoginSection loginSection);
*/	
	public List<HrUserDepartment> getHrUserDepartmentByUserId(Integer userId);

	public void save(HrUserDepartment hrUserDepartment);
	
	public void update(HrUserDepartment hrUserDepartment);

	public HrUserDepartment getHrUserDepartmentById(int id);

	public void delete(HrUserDepartment hrUserDepartment);

}
