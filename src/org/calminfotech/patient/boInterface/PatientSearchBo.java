package org.calminfotech.patient.boInterface;

import java.util.List;

import org.calminfotech.patient.forms.PatientSearchForm;

public interface PatientSearchBo {
	
	public List searchPatient(PatientSearchForm patientForm);

}
