package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.forms.PatientAllergyForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.patient.models.Patient;

public interface PatientAllergyBo {

//	public List<PatientAllergy> fetchAllPatientsByAllergy(Allergy allergy);
//
public PatientAllergy fetchAllAllergiesByPatient(Integer patientId);

public List<PatientAllergy> fetchAllpAllergiesByPatient(Integer patientid);
	
	public PatientAllergy getByIdAndPallergyId(
			Integer patientId, int id);

	public void save(PatientAllergyForm form);

	public void delete(PatientAllergy patientAllergy);

	public void update(PatientAllergyForm form);

	public PatientAllergy getPatientAllergyById(int id);

}
