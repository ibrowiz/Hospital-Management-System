package org.calminfotech.patient.bo.impl;



import java.util.List;

import org.calminfotech.patient.boInterface.PatientFamilyHistoryBo;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.daoInterface.PatientFamilyHistoryDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientFamilyHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PatientFamilyHistoryBoImpl implements PatientFamilyHistoryBo {

	
	@Autowired
	private PatientFamilyHistoryDao patientFamilyHistoryDao;
	
	@Autowired
	private PatientDao patientDao;
	
	@Override
	public void save(PatientFamilyHistory patientFamilyHistory) {
		this.patientFamilyHistoryDao.save(patientFamilyHistory);
	}

	@Override
	public void delete(PatientFamilyHistory patientFamilyHistory) {
		this.patientFamilyHistoryDao.delete(patientFamilyHistory);
	}

	@Override
	public PatientFamilyHistory getPatientFamilyHistoryById(int id) {
		return this.patientFamilyHistoryDao.getPatientFamilyHistoryById(id);
	}

	@Override
	public void update(PatientFamilyHistory patientFamilyHistory) {
		this.patientFamilyHistoryDao.update(patientFamilyHistory);		
	}

	@Override
	public List<PatientFamilyHistory> fetchAllByPatient(Integer patientId) {
		Patient patient = this.patientDao.getPatientById(patientId);
		return this.patientFamilyHistoryDao.fetchAllByPatient(patient);
	}

}
