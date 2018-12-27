package org.calminfotech.patient.daoInterface;

import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.RelatedPatient;
import org.calminfotech.utils.models.Organisation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface RelatedPatientDao {
	
	public List<RelatedPatient> fetchAll(Organisation organisation);

	public List<RelatedPatient> fetchAllByOrganisation(Organisation organisation);
	
	public RelatedPatient getRelatedPatientByPatientId(Patient patient);
	
	public List<RelatedPatient> fetchRelatedPatientByPatientId(Patient patient);

	public RelatedPatient getRelPatientById(int id);
	
	public RelatedPatient getRelPatientByPatientAndRel(Patient patient, int RelPatId);

	public void save(RelatedPatient relPatient);

	public void delete(RelatedPatient relPatient);

	public void update(RelatedPatient relPatient);
	
	RelatedPatient findByBirthDay(String subscriberDob);

	RelatedPatient checkIfPatientIdExist(String patientId);

}
