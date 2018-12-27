package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.GetPointDetails;
import org.calminfotech.system.models.HrUserDepartment;
import org.calminfotech.system.models.HrUserPoint;
import org.calminfotech.utils.models.Organisation;

public interface HrUserPointBo {
	
	public List<HrUserPoint> fetchAll();

	public List<HrUserPoint> fetchAllByOrgainsation(
			Organisation organisation);

/*	public List<HrUserSection> fetchAllByloginsectn(LoginSection loginSection);
*/	
	public List<HrUserPoint> getHrUserPointByUserId(Integer userId);

	public void save(HrUserPoint hrUserPoint);
	
	public void update(HrUserPoint hrUserPoint);

	public HrUserPoint getHrUserPointById(int id);

	public void delete(HrUserPoint hrUserPoint);
	
	public List <GetPointDetails> getHrUserPointForUnit(int unitId, int userId);

}
