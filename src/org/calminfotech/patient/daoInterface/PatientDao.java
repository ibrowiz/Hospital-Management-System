package org.calminfotech.patient.daoInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;

public interface PatientDao {

	public List<Patient> fetchAll();

	public List<Patient> fetchAllByOrganisation(Organisation organisation);

	public Patient getPatientById(int id);

	public void save(Patient patient);

	public void delete(Patient patient);

	public void update(Patient patient);
	
	Patient findByBirthDay(String subscriberDob);

	Patient checkIfPatientIdExist(String PatientId);
	
}
