package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.forms.PatientForm;
import org.calminfotech.patient.forms.PatientForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientDocument;
import org.calminfotech.patient.models.PatientDocument;
import org.calminfotech.patient.models.Patient;

public interface PatientBo {

	public List<Patient> fetchAll();

	public List<Patient> fetchAllByOrganisation();

	public Patient getPatientById(int id);

	public Patient save(PatientForm patientForm);
	
	
	
	//just added for transfer sake
	public Patient save(Patient patient);
	//ends
	

	public void delete(Patient patient);

	public void update(PatientForm patientForm);

	public void update(Patient patient);

	public List<PatientDocument> getPatientDocumentByPatient(Patient patient);
	
	
	Patient findByBirthDay(String patientDob);

	Patient checkIfPatientIdExist(String patientId);

	
}
