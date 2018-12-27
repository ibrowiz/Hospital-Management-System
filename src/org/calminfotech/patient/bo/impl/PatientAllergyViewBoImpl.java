package org.calminfotech.patient.bo.impl;

import java.util.List;

import org.calminfotech.patient.boInterface.PatientAllergyViewBo;
import org.calminfotech.patient.daoInterface.PatientAllergyDao;
import org.calminfotech.patient.daoInterface.PatientAllergyViewDao;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.patient.models.PatientAllergyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class PatientAllergyViewBoImpl implements PatientAllergyViewBo {
	
	@Autowired
	private PatientDao patientDao;
	@Autowired
	private PatientAllergyViewDao patientAllergyViewDao;

	@Override
	public List<PatientAllergyView> fetchAllByOrganisation(int organisationId) {
		// TODO Auto-generated method stub
		return this.patientAllergyViewDao.fetchAllByOrganisation(organisationId);
	}

	@Override
	public List<PatientAllergyView> getPatientAllergyByPatientId(Integer patientId) {
		// TODO Auto-generated method stub
		Patient patient = this.patientDao.getPatientById(patientId);
		return this.patientAllergyViewDao.getPatientAllergyByPatientId(patient);
	}

	@Override
	public PatientAllergyView fetchPatientAllergyByPatientId(Integer patientId) {
		Patient patient = this.patientDao.getPatientById(patientId);
		return this.patientAllergyViewDao.fetchPatientAllergyByPatientId(patient);
		
	}

}
