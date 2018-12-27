package org.calminfotech.patient.daoInterface;

import java.util.List;


import org.calminfotech.patient.models.PatientMedicalHistory;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;




public interface PatientMedicalHistoryDao {
	
	
	public List<PatientMedicalHistory> fetchAll();

	public List<PatientMedicalHistory> fetchAllByOrgainsation(
			Organisation organisation);

	public List<PatientMedicalHistory> fetchAllByPatient(Patient patient);

	public void save(PatientMedicalHistory patientMedicalHistory);

	public PatientMedicalHistory getPatientMedicalHistoryById(int id);

	public void delete(PatientMedicalHistory patientMedicalHistory);
	
	public void update(PatientMedicalHistory patientMedicalHistory);
	

}
