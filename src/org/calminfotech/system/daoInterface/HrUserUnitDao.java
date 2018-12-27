package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.GetUnitDetails;
import org.calminfotech.system.models.HrUserDepartment;
import org.calminfotech.system.models.HrUserUnit;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.user.models.User;
import org.calminfotech.utils.models.Organisation;

public interface HrUserUnitDao {
	
	public List<HrUserUnit> fetchAll();

	public List<HrUserUnit> fetchAllByOrgainsation(
			Organisation organisation);

	public List<HrUserUnit> fetchAllByloginsectn(LoginSection loginSection);
	
	public List<HrUserUnit> getHrUserUnitByUserId(User user);
	
	//public List<HrUserUnit> getHrUserUnitForDep();
	
	public void save(HrUserUnit hrUserUnit);
	
	public void update(HrUserUnit hrUserUnit);

	public HrUserUnit getHrUserUnitById(int id);

	public void delete(HrUserUnit hrUserUnit);

	public List <GetUnitDetails> getHrUserUnitForDep(int deptId, int userId);
}
