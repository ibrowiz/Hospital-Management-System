package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitLaboratoryTest;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;

public interface VisitLaboratoryTestDao {
	
	public List<VisitLaboratoryTest> fetchAll();

	public List<VisitLaboratoryTest> fetchAllByOrgainsation(
			Organisation organisation);

	public List<VisitLaboratoryTest> fetchAllByPatient(Patient patient);
	
	public List<VisitLaboratoryTest> fetchAllByVisit(Visit visit);

	public void save(VisitLaboratoryTest visitLaboratoryTest);

	public VisitLaboratoryTest getVisitLaboratoryTestById(int id);

	public void delete(VisitLaboratoryTest visitLaboratoryTest);


}
