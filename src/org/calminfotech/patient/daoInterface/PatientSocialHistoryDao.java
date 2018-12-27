package org.calminfotech.patient.daoInterface;

import java.util.List;

import org.calminfotech.patient.forms.PatientSocialHistoryForm;
import org.calminfotech.patient.models.PatientSocialHistory;
import org.calminfotech.patient.models.PatientSurgicalHistory;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;

public interface PatientSocialHistoryDao {
	
	public List<PatientSocialHistory> fetchAll();

	public List<PatientSocialHistory> fetchAllByOrgainsation(
			Organisation organisation);

	public List<PatientSocialHistory> fetchAllByPatient(Patient patient);

	public void save(PatientSocialHistory patientSocialHistory);

	public PatientSocialHistory getPatientSocialHistoryById(int id);

	public void delete(PatientSocialHistory patientSocialHistory);


}
