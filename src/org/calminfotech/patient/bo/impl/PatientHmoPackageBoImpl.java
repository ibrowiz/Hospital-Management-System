package org.calminfotech.patient.bo.impl;

import java.util.List;

import org.calminfotech.admin.boInterface.HmoBo;
import org.calminfotech.patient.boInterface.PatientHmoPackageBo;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.daoInterface.PatientHmoPackageDao;
import org.calminfotech.patient.forms.PatientHmoForm;
import org.calminfotech.patient.models.PatientHmoPackage;
import org.calminfotech.system.daoInterface.EHmoPackagesDao;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientHmoPackageBoImpl implements PatientHmoPackageBo {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private PatientHmoPackageDao patientHmoPackageDao;

	@Autowired
	private PatientDao patientDao;
	
	@Autowired
	private HmoBo hmoBo;

	@Autowired
	private EHmoPackagesDao hmoPackageDao;

	@Override
	public List<PatientHmoPackage> fetchAll() {
		// TODO Auto-generated method stub
		return this.patientHmoPackageDao.fetchAll();
	}

	@Override
	public List<PatientHmoPackage> fetchAllByPatient(Patient patient) {
		// TODO Auto-generated method stub
		return this.patientHmoPackageDao.fetchAllByPatient(patient);
	}

	@Override
	public List<PatientHmoPackage> fetchAllByHmoPackage(
			EhmoPackages hmoPackage) {
		// TODO Auto-generated method stub
		return this.patientHmoPackageDao.fetchAllByHmoPackage(hmoPackage);
	}

	@Override
	public PatientHmoPackage getHmoPackageByPatientAndPackage(Patient patient,
			EhmoPackages hmoPackage) {
		// TODO Auto-generated method stub
		return this.patientHmoPackageDao.getHmoPackageByPatientAndPackage(
				patient, hmoPackage);
	}

	@Override
	public void save(PatientHmoForm form) {
		// TODO Auto-generated method stub
		Patient patient = this.patientDao.getPatientById(form.getPatientId());
		EhmoPackages hmoPackage = this.hmoPackageDao
				.getPackageById(form.getPackageId());

		PatientHmoPackage patientHmoPackage = new PatientHmoPackage();
		patientHmoPackage.setPatient(patient);
		patientHmoPackage.setHmoPackage(hmoPackage);
		patientHmoPackage.setHmo(hmoBo.getHmoById(form.getHmoId()));
		patientHmoPackage.setComments(form.getDescription());
		patientHmoPackage.setCreatedBy(this.userIdentity.getUsername());

		this.patientHmoPackageDao.save(patientHmoPackage);
	}

	@Override
	public void delete(PatientHmoPackage hmoPackage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(PatientHmoForm form) {
		// TODO Auto-generated method stub

	}

}
