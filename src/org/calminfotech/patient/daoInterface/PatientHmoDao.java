package org.calminfotech.patient.daoInterface;

import java.util.List;

import org.calminfotech.patient.models.Patient;

import org.calminfotech.patient.models.PatientHmoBillSchemeView;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface PatientHmoDao {
	public List<PatientHmoBillSchemeView> fetchPatientHmoByPatient(Patient patient);
}
