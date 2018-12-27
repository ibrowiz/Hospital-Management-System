package org.calminfotech.patient.bo.impl;



import org.calminfotech.patient.boInterface.PatientEmergencyBo;
import org.calminfotech.patient.daoInterface.PatientEmergencyDao;
import org.calminfotech.patient.models.PatientEmergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PatientEmergencyBoImpl implements PatientEmergencyBo{

	
	
	@Autowired
	private PatientEmergencyDao patientEmergencyDao;
	
	
	@Override
	public void save(PatientEmergency patientEmergency) {
		// TODO Auto-generated method stub
		this.patientEmergencyDao.save(patientEmergency);
	}

	@Override
	public void delete(PatientEmergency patientEmergency) {
		// TODO Auto-generated method stub
		this.patientEmergencyDao.delete(patientEmergency);
	}

	@Override
	public PatientEmergency getPatientEmergencyById(int id) {
		// TODO Auto-generated method stub
		return this.patientEmergencyDao.getPatientEmergencyById(id);
	}

}
