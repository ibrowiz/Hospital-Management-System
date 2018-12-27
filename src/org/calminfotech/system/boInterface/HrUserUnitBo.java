package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.GetUnitDetails;
import org.calminfotech.system.models.HrUserDepartment;
import org.calminfotech.system.models.HrUserUnit;
import org.calminfotech.utils.models.Organisation;

public interface HrUserUnitBo {
	
	public List<HrUserUnit> fetchAll();

	public List<HrUserUnit> fetchAllByOrgainsation(
			Organisation organisation);

/*	public List<HrUserSection> fetchAllByloginsectn(LoginSection loginSection);
*/	
	public List<HrUserUnit> getHrUserUnitByUserId(Integer userId);

	public void save(HrUserUnit hrUserUnit);

	public HrUserUnit getHrUserUnitById(int id);

	public void delete(HrUserUnit hrUserUnit);
	
	public void update(HrUserUnit hrUserUnit);

	public List <GetUnitDetails> getHrUserUnitForDep(int deptId, int userId);
}
