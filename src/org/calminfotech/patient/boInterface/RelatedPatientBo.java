package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.forms.RelatedPatientForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.RelatedPatient;
import org.calminfotech.utils.models.Organisation;

public interface RelatedPatientBo {
	
	public List<RelatedPatient> fetchAll(int organisationId);

	public List<RelatedPatient> fetchAllByOrganisation();
	
	public RelatedPatient getRelatedPatientByPatientId(int patientId);
	
	public RelatedPatient getRelPatientByPatientAndRel(int patientId, int RelPatId);
	
	public List<RelatedPatient> fetchRelatedPatientByPatientId(int patientId);

	public RelatedPatient getRelPatientById(int id);

	public RelatedPatient save(RelatedPatient relPatient);
	
	public RelatedPatient save(RelatedPatientForm relPatientForm);
	
	public void update(RelatedPatientForm relPatientForm);

	public void delete(RelatedPatient relPatient);

	public void update(RelatedPatient relPatient);
	
	RelatedPatient findByBirthDay(String subscriberDob);

	RelatedPatient checkIfPatientIdExist(String relPatientId);


}
