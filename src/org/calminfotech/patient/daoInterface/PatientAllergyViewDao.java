package org.calminfotech.patient.daoInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.patient.models.PatientAllergyView;
import org.calminfotech.setup.models.AllergyCategoryView;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface PatientAllergyViewDao {
	
	public List<PatientAllergyView> fetchAllByOrganisation(int organisationId);
	
	public List<PatientAllergyView> getPatientAllergyByPatientId(Patient patient);
	
	public PatientAllergyView fetchPatientAllergyByPatientId(Patient patient);

}
