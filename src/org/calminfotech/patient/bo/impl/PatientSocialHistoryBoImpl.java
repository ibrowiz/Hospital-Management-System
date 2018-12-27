package org.calminfotech.patient.bo.impl;

import java.util.List;

import org.calminfotech.patient.boInterface.PatientSocialHistoryBo;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.daoInterface.PatientSocialHistoryDao;
import org.calminfotech.patient.daoInterface.PatientSurgicalHistoryDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientSocialHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PatientSocialHistoryBoImpl implements PatientSocialHistoryBo{
	
	@Autowired
	private PatientSocialHistoryDao patientSocialHistoryDao;
	
	@Autowired
	private PatientDao patientDao;

	@Override
	public void save(PatientSocialHistory patientSocialHistory) {
		// TODO Auto-generated method stub
		this.patientSocialHistoryDao.save(patientSocialHistory);
	}

	@Override
	public void delete(PatientSocialHistory patientSocialHistory) {
		// TODO Auto-generated method stub
		this.patientSocialHistoryDao.delete(patientSocialHistory);
	}

	@Override
	public PatientSocialHistory getPatientSocialHistoryById(int id) {
		// TODO Auto-generated method stub
		return this.patientSocialHistoryDao.getPatientSocialHistoryById(id);
	}

	@Override
	public List<PatientSocialHistory> fetchAllByPatient(Integer patientId) {
		Patient patient = this.patientDao.getPatientById(patientId);
		return this.patientSocialHistoryDao.fetchAllByPatient(patient);
	}

}
