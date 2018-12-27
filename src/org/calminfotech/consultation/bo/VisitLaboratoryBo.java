package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.models.VisitLaboratory;

public interface VisitLaboratoryBo {

	List<VisitLaboratory> fetchAllWithOpenStatusForUser();
}
