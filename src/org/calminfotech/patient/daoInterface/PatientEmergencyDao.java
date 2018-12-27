package org.calminfotech.patient.daoInterface;

import java.util.List;


import org.calminfotech.patient.models.PatientEmergency;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;

public interface PatientEmergencyDao {

	
	public List<PatientEmergency> fetchAll();

	public List<PatientEmergency> fetchAllByOrgainsation(
			Organisation organisation);

	public List<PatientEmergency> fetchAllByPatient(Patient patient);

	public void save(PatientEmergency patientEmergency);

	public PatientEmergency getPatientEmergencyById(int id);

	public void delete(PatientEmergency patientEmergency);
	
}
