package org.calminfotech.system.daoInterface;

import java.util.List;


import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.utils.models.Organisation;

public interface VisitWorkflowPointDao {

	public List<VisitWorkflowPoint> fetchAllByOrganisation(Organisation organisation);

	public VisitWorkflowPoint getWorkflowPointById(int id);
	
	public List<VisitWorkflowPoint> fetchFontDeskPoint(int id ,int section);
	
	public void save(VisitWorkflowPoint workflowPoint);

	public void delete(VisitWorkflowPoint workflowPoint);

	public void update(VisitWorkflowPoint workflowPoint);

	public VisitWorkflowPoint getWorkflowStartPoint(Organisation organisation);
	
	//public List<VisitWorkflowPoint> getvWorkflowPointByUnitId(Unit unit);

	public VisitWorkflowPoint getWorkflowEndPoint();

	public VisitWorkflowPoint getPointByName(String string);

}
