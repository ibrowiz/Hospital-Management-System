package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.patient.models.PatientAllergyView;

public interface PatientAllergyViewBo {
	
	public List<PatientAllergyView> fetchAllByOrganisation(int organisationId);
	
	public List<PatientAllergyView> getPatientAllergyByPatientId(Integer patientId);
	
	public PatientAllergyView fetchPatientAllergyByPatientId(Integer patientId);

}
