package org.calminfotech.patient.boInterface;

import org.calminfotech.patient.models.PatientEmergency;


public interface PatientEmergencyBo {
	public void save(PatientEmergency patientEmergency);

	public void delete(PatientEmergency patientEmergency);

	public PatientEmergency getPatientEmergencyById(int id);
	
	
}
