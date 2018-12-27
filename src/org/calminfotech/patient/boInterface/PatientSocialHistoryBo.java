package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientSocialHistory;


public interface PatientSocialHistoryBo {
	
	public List<PatientSocialHistory> fetchAllByPatient(Integer patientId);
	
	public void save(PatientSocialHistory patientSocialHistory);

	public void delete(PatientSocialHistory patientSocialHistory);

	public PatientSocialHistory getPatientSocialHistoryById(int id);

}
