package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitPresentingComplaint;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;

public interface VisitPresentingCompliantDao {
	
	
	public List<VisitPresentingComplaint> fetchAll();

	public List<VisitPresentingComplaint> fetchAllByOrgainsation(
			Organisation organisation);

	public List<VisitPresentingComplaint> fetchAllByPatient(Patient patient);
	
	public List<VisitPresentingComplaint> fetchAllByVisit(Visit visit);

	public void save(VisitPresentingComplaint visitPresentingComplaint);

	public VisitPresentingComplaint getVisitPresentingComplaintById(int id);

	public void delete(VisitPresentingComplaint visitPresentingComplaint);

}
