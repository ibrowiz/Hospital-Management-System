package org.calminfotech.patient.boInterface;


import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientSocialHistory;
import org.calminfotech.patient.models.PatientSurgicalHistory;

public interface PatientSurgicalHistoryBo {
	
	public void save(PatientSurgicalHistory patientSurgicalHistoryy);

	public void delete(PatientSurgicalHistory patientSurgicalHistoryy);

	public PatientSurgicalHistory getPatientSurgicalHistoryById(int id);
	
	public List<PatientSurgicalHistory> fetchAllByPatient(Integer patientId);

}
