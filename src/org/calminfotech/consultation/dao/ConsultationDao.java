package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.Consultation;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;

public interface ConsultationDao {

	public List<Consultation> fetchAll();

	public List<Consultation> fetchAllByOrganisation(Organisation organisation);

	public Consultation getConsultationById(int id);

	public void save(Consultation consultation);

	public void delete(Consultation consultation);

	public void update(Consultation consultation);

	public Consultation getOnGoingConsultationByPatient(Patient patient);

	public Consultation getLastConsultation();
	
}
