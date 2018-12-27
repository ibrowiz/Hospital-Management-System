package org.calminfotech.patient.daoInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.patient.models.Patient;

public interface PatientAllergyDao {

	//public List<PatientAllergy> fetchAll();

	//public List<PatientAllergy> fetchAllPatientsByAllergy(int allergyId);

	/*public PatientAllergy fetchAllAlleryByPatient(Patient patient);*/
	public List<PatientAllergy> fetchAllpAllergiesByPatient(Patient patient);
	public PatientAllergy fetchAllAllergiesByPatient(Patient patient);
	
	public PatientAllergy getPatientAllergyById(int id);
	
	public PatientAllergy getByIdAndPallergyId(
			Patient patient, int id);
	
	
	public void save(PatientAllergy patientAllergy);

	public void delete(PatientAllergy patientAllergy);

	public void update(PatientAllergy patientAllergy);

}
