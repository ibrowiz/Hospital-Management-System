package org.calminfotech.patient.bo.impl;

import java.util.List;

import org.calminfotech.patient.boInterface.PatientSearchBo;
import org.calminfotech.patient.daoInterface.PatientSearchDao;
import org.calminfotech.patient.forms.PatientSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientSearchBoImpl implements PatientSearchBo {

	@Autowired
	private PatientSearchDao patientSearchDao;
	
	@Override
	public List searchPatient(PatientSearchForm patientForm) {		
		return patientSearchDao.searchPatient(patientForm);
	}

}
