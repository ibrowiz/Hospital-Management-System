package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.VisitVital;
import org.calminfotech.system.models.VisitStatus;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.models.User;

public interface VisitVitalsDao {

	public List<VisitVital> fetchAll();

	public List<VisitVital> fetchAllForUser();
	
	
	public List<VisitVital> fetchAllByUser(User user);

	List<VisitVital> fetchAllWithOpenStatusForUser(VisitStatus endStatus, VisitWorkflowPoint visitWorkflowPoint);

}
