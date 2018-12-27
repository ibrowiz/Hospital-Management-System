package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.VisitLaboratory;
import org.calminfotech.system.models.VisitStatus;
import org.calminfotech.system.models.VisitWorkflowPoint;

public interface VisitLaboratoryDao {

	public List<VisitLaboratory> fetchAll();

	public List<VisitLaboratory> fetchAllForUser();

	List<VisitLaboratory> fetchAllWithOpenStatusForUser(VisitStatus endStatus, VisitWorkflowPoint visitWorkflowPoint);

}
