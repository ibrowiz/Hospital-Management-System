package org.calminfotech.patient.daoInterface;

import java.util.List;

import org.calminfotech.patient.forms.PatientSearchForm;

public interface PatientSearchDao {
	
	public List searchPatient(PatientSearchForm patientForm);

}
