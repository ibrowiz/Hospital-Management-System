package org.calminfotech.patient.daoInterface;

import java.util.List;



import org.calminfotech.patient.models.PatientFamilyHistory;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;

public interface PatientFamilyHistoryDao {
	
	public List<PatientFamilyHistory> fetchAll();

	public List<PatientFamilyHistory> fetchAllByOrgainsation(
			Organisation organisation);

	public List<PatientFamilyHistory> fetchAllByPatient(Patient patient);

	public void save(PatientFamilyHistory patientFamilyHistory);

	public PatientFamilyHistory getPatientFamilyHistoryById(int id);

	public void delete(PatientFamilyHistory patientFamilyHistory);
	
	public void update(PatientFamilyHistory patientFamilyHistory);
	

}
