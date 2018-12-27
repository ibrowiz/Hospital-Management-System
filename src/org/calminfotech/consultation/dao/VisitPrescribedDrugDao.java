package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitPrescribedDrug;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;

public interface VisitPrescribedDrugDao {
	
	public List<VisitPrescribedDrug> fetchAll();

	public List<VisitPrescribedDrug> fetchAllByOrgainsation(
			Organisation organisation);

	public List<VisitPrescribedDrug> fetchAllByPatient(Patient patient);
	
	public List<VisitPrescribedDrug> fetchAllByVisit(Visit visit);

	public void save(VisitPrescribedDrug visitPrescribedDrug);

	public VisitPrescribedDrug getVisitPrescribedDrugById(int id);

	public void delete(VisitPrescribedDrug visitPrescribedDrug);

}
