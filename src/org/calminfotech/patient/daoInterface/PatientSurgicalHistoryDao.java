package org.calminfotech.patient.daoInterface;

import java.util.List;

import org.calminfotech.patient.models.PatientFamilyHistory;
import org.calminfotech.patient.models.PatientSurgicalHistory;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;

public interface PatientSurgicalHistoryDao {
	
	public List<PatientSurgicalHistory> fetchAll();

	public List<PatientSurgicalHistory> fetchAllByOrgainsation(
			Organisation organisation);

	public List<PatientSurgicalHistory> fetchAllByPatient(Patient patient);

	public void save(PatientSurgicalHistory patientSurgicalHistory);

	public PatientSurgicalHistory getPatientSurgicalHistoryById(int id);

	public void delete(PatientSurgicalHistory patientSurgicalHistory);

}
