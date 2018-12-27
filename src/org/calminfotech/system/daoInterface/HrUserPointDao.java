package org.calminfotech.system.daoInterface;

import java.util.List;

import org.calminfotech.system.models.GetPointDetails;
import org.calminfotech.system.models.GetUnitDetails;
import org.calminfotech.system.models.HrUserDepartment;
import org.calminfotech.system.models.HrUserPoint;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.user.models.User;
import org.calminfotech.utils.models.Organisation;

public interface HrUserPointDao {
	
	public List<HrUserPoint> fetchAll();

	public List<HrUserPoint> fetchAllByOrgainsation(
			Organisation organisation);

	public List<HrUserPoint> fetchAllByloginsectn(LoginSection loginSection);
	
	public List<HrUserPoint> getHrUserPointByUserId(User user);

	public void save(HrUserPoint hrUserPoint);
	
	public void update(HrUserPoint hrUserPoint);
	
	public HrUserPoint getHrUserPointById(int id);

	public void delete(HrUserPoint hrUserPoint);
	
	public List <GetPointDetails> getHrUserPointForUnit(int unitId, int userId);

}
