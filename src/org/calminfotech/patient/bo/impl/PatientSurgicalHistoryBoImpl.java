package org.calminfotech.patient.bo.impl;


import java.util.List;

import org.calminfotech.patient.boInterface.PatientSurgicalHistoryBo;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.daoInterface.PatientSurgicalHistoryDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientSocialHistory;
import org.calminfotech.patient.models.PatientSurgicalHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PatientSurgicalHistoryBoImpl implements PatientSurgicalHistoryBo {
	
	@Autowired
	private PatientSurgicalHistoryDao patientSurgicalHistoryDao;
	
	@Autowired
	private PatientDao patientDao;

	@Override
	public void save(PatientSurgicalHistory patientSurgicalHistoryy) {
		// TODO Auto-generated method stub
		this.patientSurgicalHistoryDao.save(patientSurgicalHistoryy);
	}

	@Override
	public void delete(PatientSurgicalHistory patientSurgicalHistoryy) {
		// TODO Auto-generated method stub
		this.patientSurgicalHistoryDao.delete(patientSurgicalHistoryy);
	}

	@Override
	public PatientSurgicalHistory getPatientSurgicalHistoryById(int id) {
		// TODO Auto-generated method stub
		return this.patientSurgicalHistoryDao.getPatientSurgicalHistoryById(id);
	}

	@Override
	public List<PatientSurgicalHistory> fetchAllByPatient(Integer patientId) {
		Patient patient = this.patientDao.getPatientById(patientId);
		return this.patientSurgicalHistoryDao.fetchAllByPatient(patient);
	}

}
