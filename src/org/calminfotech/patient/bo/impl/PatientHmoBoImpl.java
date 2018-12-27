package org.calminfotech.patient.bo.impl;

import java.util.List;

import org.calminfotech.patient.boInterface.PatientHmoBo;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.daoInterface.PatientHmoDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientHmoBillSchemeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PatientHmoBoImpl implements PatientHmoBo {
	
	@Autowired
	private PatientHmoDao patientHmoDao;
	
	@Autowired
	private PatientDao patientDao;

	@Override
	public List<PatientHmoBillSchemeView> fetchPatientHmoByPatient(Integer patientId) {
		// TODO Auto-generated method stub
		Patient patient = this.patientDao.getPatientById(patientId);
		return this.patientHmoDao.fetchPatientHmoByPatient(patient);
	}

}
