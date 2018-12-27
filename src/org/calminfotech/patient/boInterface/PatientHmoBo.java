package org.calminfotech.patient.boInterface;

import java.util.List;
import org.calminfotech.patient.models.PatientHmoBillSchemeView;

public interface PatientHmoBo {
	
public List<PatientHmoBillSchemeView> fetchPatientHmoByPatient(Integer patientId);

}
