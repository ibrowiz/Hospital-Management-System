package org.calminfotech.patient.daoInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientDocument;
import org.calminfotech.patient.models.PatientDocument;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;

public interface PatientDocumentDao {
	
	public List<PatientDocument> fetchAll();

	public List<PatientDocument> fetchAllByOrgainsation(
			Organisation organisation);

	public List<PatientDocument> fetchAllByPatient(Patient patient);

	public void save(PatientDocument patientDocument);

	public PatientDocument getPatientDocumentById(int id);

	public void delete(PatientDocument patientDocument);

}
