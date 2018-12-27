package org.calminfotech.consultation.dao;

import java.util.Date;
import java.util.List;

import org.calminfotech.consultation.models.Consultation;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.VisitStatus;
import org.calminfotech.utils.models.Organisation;

public interface VisitDao {

	public List<Visit> fetchAll();
	
	public List<Visit> fetchAllByUId(int userId);
	
	public List<Visit> fetchAllByThese(int userId,Date from, Date to,int mstatus, String chkothers );
	
	public List<Visit> fetchAllLabByThese(int userId,Patient patient, Date from, Date to,int mstatus, String chkothers );

	public List<Visit> fetchAllByWorkflowPoint(String point);

	public List<Visit> fetchAllByWorkflowPoint(String point, VisitStatus status);

	public List<Visit> fetchAll(Consultation consultation);

	public List<Visit> fetchAll(Organisation organisation);
	
	public Visit getOnGoingConsultationByPatient(Patient patient);
	
	public List<Visit> fetchByPatientId(int patientId);

	public List<Visit> fetchAll(Patient patient);

	public Visit getVisitationById(int id);

	public void save(Visit visit);

	public void delete(Visit visit);

	public void update(Visit visit);

	public Visit getLastVisit1(Patient patient);
	
	public Visit getLastVisit2(Patient patient);
	
	public Visit getVisitByPatient(Patient patient);
	
	public Visit getVisitByCodvisie(int visitcode);

	public Visit getLastVisit(Consultation consultation);
	
	public Visit getLastVisit(Visit visit);

	public void clearLabTests(Visit visit);

	public void clearPrescribedDrugs(Visit visit);

	Visit getVisitByCode(int visitcode);
	
	/*public List<Visit> fetchByPatientId(int id);*/
	
}
