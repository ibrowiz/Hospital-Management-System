package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.models.VisitVital;

public interface VisitVitalsBo {

	List<VisitVital> fetchAllWithOpenStatusForUser();
	
	//List<VisitVital> fetchAllWithOpenStatusperUser();
	
	// List<VisitVital> fetchAllByuser();
}
