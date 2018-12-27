package org.calminfotech.consultation.bo;

import java.util.Date;
import java.util.List;

import org.calminfotech.consultation.forms.VisitDoctorForm;
import org.calminfotech.consultation.forms.VisitLaboratoryForm;
import org.calminfotech.consultation.forms.VisitPharmacyForm;
import org.calminfotech.consultation.forms.VisitVitalsForm;
import org.calminfotech.consultation.forms.VisitWorkflowUserConfigurationForm;
import org.calminfotech.consultation.forms.VisitationForm;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.hr.forms.AssignForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.VisitStatus;

public interface VisitBo {

	public List<Visit> fetchAll();
	
	public List<Visit> fetchAllByUId(int userId);
	
	public List<Visit> fetchAllByThese(int userId,Date from, Date to,int mstatus, String chkothers);
	
	public List<Visit> fetchAllLabByThese(int userId,Integer patientId, Date from, Date to,int mstatus, String chkothers );

	public List<Visit> fetchAllByWorkflowPoint(String point);

	public List<Visit> fetchAllByWorkflowPoint(String point, VisitStatus status);

	//public List<Visit> fetchAll(Consultation consultation);

	public List<Visit> fetchAllByOrganisation();
	
	public List<Visit> fetchByPatientId(int patientId);

	public List<Visit> fetchAll(Patient patient);

	public Visit getVisitationById(int id);
	
	public Visit save(Visit visit);

	//public void save(VisitationForm form);

	//public void save(VisitDoctorForm form);

	public void delete(Visit visit);

	public void update(VisitationForm form);
	
	public void update(AssignForm asignForm);

	public void update(Visit visit);
	
	public Visit getLastVisit2(Patient patient);

	public Visit getLastVisit1(Visit onGoingConsultation);
	//public Visit getLastVisit(Consultation consultation);
	
	public Visit getLastVisit(Visit visit);

	//public Visit createVisit(Consultation consultation);

	public void assignUserToPoint(VisitWorkflowUserConfigurationForm form);

	public void updateVitalsVisit(VisitVitalsForm form);

	public void updateDoctorVisit(VisitDoctorForm form);

	public void updatePharmacyVisit(VisitPharmacyForm form);

	void updateLaboratoryVisit(VisitLaboratoryForm form);

	public void clearLabTests(Visit visit);
	
	public void clearPrescribedDrugs(Visit visit);
	
	public Visit getOnGoingConsultationByPatient(Patient patient);
	
	public boolean hasConsultation(Patient patient);
	
	public Visit getVisitByPatient(Integer patientId);
	
	public Visit getVisitByCode(int visitcode);
	
}
