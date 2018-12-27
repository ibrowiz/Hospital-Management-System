package org.calminfotech.patient.boInterface;

import org.calminfotech.patient.models.PatientDocument;
import org.calminfotech.patient.models.PatientDocument;

public interface PatientDocumentBo {

	public void save(PatientDocument patientDocument);

	public void delete(PatientDocument patientDocument);

	public PatientDocument getPatientDocumentById(int id);
	
}
