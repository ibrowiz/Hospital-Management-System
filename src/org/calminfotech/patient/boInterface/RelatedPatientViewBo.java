package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.models.RelatedPatientView;
import org.calminfotech.utils.models.Organisation;

public interface RelatedPatientViewBo {

	public List<RelatedPatientView> fetchAll();
	
	public RelatedPatientView getRelPatViewById(int id);
	
	public List<RelatedPatientView> fetchAllByOrganisation(
			Integer organisationId,Integer PatientId);
}
