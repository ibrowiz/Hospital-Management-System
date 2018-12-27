package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.VisitPharmacy;
import org.calminfotech.consultation.models.VisitVital;
import org.calminfotech.system.models.VisitStatus;
import org.calminfotech.system.models.VisitWorkflowPoint;

public interface VisitPharmacyDao {

	public List<VisitPharmacy> fetchAll();

	public List<VisitPharmacy> fetchAllForUser();

	List<VisitPharmacy> fetchAllWithOpenStatusForUser(VisitStatus endStatus, VisitWorkflowPoint visitWorkflowPoint);

}
