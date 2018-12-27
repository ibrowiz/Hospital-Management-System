package org.calminfotech.patient.bo.impl;



import java.util.List;

import org.calminfotech.patient.boInterface.PatientMedicalHistoryBo;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.daoInterface.PatientMedicalHistoryDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientMedicalHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
	public class PatientMedicalHistoryBoImpl implements PatientMedicalHistoryBo{

	@Autowired
	private PatientDao patientDao;
	@Autowired
	private PatientMedicalHistoryDao patientMedicalHistoryDao;

	@Override
	public void save(PatientMedicalHistory patientMedicalHistory) {
		// TODO Auto-generated method stub
		this.patientMedicalHistoryDao.save(patientMedicalHistory);
	}

	@Override
	public void delete(PatientMedicalHistory patientMedicalHistory) {
		// TODO Auto-generated method stub
		this.patientMedicalHistoryDao.delete(patientMedicalHistory);
	}

	@Override
	public PatientMedicalHistory getPatientMedicalHistoryById(int id) {
		// TODO Auto-generated method stub
		return this.patientMedicalHistoryDao.getPatientMedicalHistoryById(id);
	}

	@Override
	public void update(PatientMedicalHistory patientMedicalHistory) {
		this.patientMedicalHistoryDao.update(patientMedicalHistory);		
	}

	@Override
	public List<PatientMedicalHistory> fetchAllByPatient(Integer patientId) {
		// TODO Auto-generated method stub
		Patient patient = this.patientDao.getPatientById(patientId);
		return this.patientMedicalHistoryDao.fetchAllByPatient(patient);
	}
	
	
	
	
}
