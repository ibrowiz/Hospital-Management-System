package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.models.VisitPharmacy;

public interface VisitPharmacyBo {

	List<VisitPharmacy> fetchAllWithOpenStatusForUser();
}
