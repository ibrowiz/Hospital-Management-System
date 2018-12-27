package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.models.GlobalItemPoint;
import org.calminfotech.system.models.VisitWorkflowPoint;

public interface GlobalItemPointBo {

	public List<GlobalItemPoint> fetchAll();

	public GlobalItemPoint getGlobalItemPointById(int id);

	public void save(GlobalItemPoint point);

	public void delete(GlobalItemPoint point);

	public void update(GlobalItemPoint point);
	
	public GlobalItemPoint getByPointAndItem(Integer itemId, Integer point); 
	
	//public List<GlobalItemPoint> fetchByPoint(Integer point);
	public VisitWorkflowPoint fetchGlobalItemViaPoint();
	
	//public GlobalItemPoint fetchByFrontDesk();
}
