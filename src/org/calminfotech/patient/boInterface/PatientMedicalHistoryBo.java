package org.calminfotech.patient.boInterface;


import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientMedicalHistory;



public interface PatientMedicalHistoryBo {

	public List<PatientMedicalHistory> fetchAllByPatient(Integer patientId);
	
	public void save(PatientMedicalHistory patientMedicalHistory);

	public void delete(PatientMedicalHistory patientMedicalHistory);
	
	public void update(PatientMedicalHistory patientMedicalHistory);

	public PatientMedicalHistory getPatientMedicalHistoryById(int id);
	
}
