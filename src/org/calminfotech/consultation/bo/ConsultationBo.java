package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.forms.VisitDoctorForm;
import org.calminfotech.consultation.models.Consultation;
import org.calminfotech.patient.models.Patient;

public interface ConsultationBo {

	public List<Consultation> fetchAll();

	public List<Consultation> fetchAllByOrganisation();

	public Consultation getConsultationById(int id);

	public void save(VisitDoctorForm visitDoctorForm);

	public void delete(Consultation consultation);

	public void update(VisitDoctorForm visitDoctorForm);

	public Consultation create(Patient patient);
	
	public boolean hasConsultation(Patient patient);

	Consultation getOnGoingConsultationByPatient(Patient patient);
}
