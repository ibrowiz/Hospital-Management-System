package org.calminfotech.system.boInterface;

import java.util.List;

import org.calminfotech.system.forms.VisitWorkflowPointForm;

import org.calminfotech.system.models.VisitWorkflowPoint;

public interface VisitWorkflowPointBo {

	public List<VisitWorkflowPoint> fetchAllByOrganisation(Integer organisationId);

	public VisitWorkflowPoint getWorkflowPointById(int id);
	
	List<VisitWorkflowPoint> fetchFontDeskPoint(int section);
	
	public List<VisitWorkflowPoint> getvWorkflowPointByUnitId(int unit_id);

	public VisitWorkflowPoint save(VisitWorkflowPointForm workflowPoint);

	public void delete(VisitWorkflowPoint workflowPoint);

	public void update(VisitWorkflowPointForm workflowPoint);

	public VisitWorkflowPoint getWorkflowStartPoint(Integer organisationId);

	public VisitWorkflowPoint getWorkflowEndPoint();

	public VisitWorkflowPoint getPointByName(String string);

}
