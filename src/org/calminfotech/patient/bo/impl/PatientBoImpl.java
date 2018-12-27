package org.calminfotech.patient.bo.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.daoInterface.PatientDocumentDao;
import org.calminfotech.patient.daoInterface.PatientDocumentDao;
import org.calminfotech.patient.forms.PatientForm;
import org.calminfotech.patient.forms.PatientForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientDocument;
import org.calminfotech.patient.models.PatientDocument;
import org.calminfotech.patient.utils.PatientCodeGenerator;
import org.calminfotech.system.models.Gender;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.user.daoInterface.LanguageDao;
import org.calminfotech.user.daoInterface.TitleDao;
import org.calminfotech.user.models.Language;
import org.calminfotech.user.models.Title;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.AutoGenerate;
import org.calminfotech.utils.GenderDao;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.MaritalStatusList;
import org.calminfotech.utils.StatesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PatientBoImpl implements PatientBo {

	@Autowired
	private PatientDao patientDao;

	@Autowired
	private TitleDao titleDao;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private GenderDao genderDao;

	@Autowired
	private MaritalStatusList maritalStatusList;

	@Autowired
	private StatesList stateList;

	@Autowired
	private LocalGovernmentAreaList lgaList;

	@Autowired
	private LanguageDao languageDao;

	@Autowired
	private PatientDocumentDao patientDocumentDao;
	
	@Autowired 
	PatientCodeGenerator patientCodeGenerator;

	@Override
	public List<Patient> fetchAll() {
		// TODO Auto-generated method stub
		return this.patientDao.fetchAll();
	}

	@Override
	public List<Patient> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return this.patientDao.fetchAllByOrganisation(this.userIdentity
				.getOrganisation());
	}

	@Override
	public Patient getPatientById(int id) {
		// TODO Auto-generated method stub
		return this.patientDao.getPatientById(id);
	}

	@Override
	public Patient save(PatientForm patientForm) {
		// TODO Auto-generated method stub

		Patient patient = new Patient();

		// generate id fromthe gennum class
		//String randomNumber = subscriberCodeGenerator.processNumber(subscriberForm.getSubscriberDob());
		//subscriber.setSubscriberId(randomNumber);
		
		//String randomNumber = patientCodeGenerator.processNumber(patientForm.getDob());
		
		//patient.setPatient_id(new AutoGen().genNum());
		patient.setPatientId(patientForm.getPatientId());

		patient.setSurname(patientForm.getSurname());
		patient.setFirstName(patientForm.getFirstName());
		patient.setOthernames(patientForm.getOthernames());
		patient.setEmail(patientForm.getEmail());
		patient.setAddress(patientForm.getAddress());
		patient.setPatientCode(new AutoGenerate().mygen());
		patient.setPhoneNumber(patientForm.getPhoneNumber());
		patient.setDob(patientForm.getDob());
		patient.setBldgrp(patientForm.getBldgrp());
		patient.setGenotype(patientForm.getGenotype());
		patient.setHeight(patientForm.getHeight());
		/*patient.setHeightFeet(patientForm.getHeightFeet());
		patient.setHeightInch(patientForm.getHeightInch());*/
	
		patient.setLga(this.lgaList.getLgaById(patientForm.getLgaId()));
		patient.setState(this.stateList.getStateById(patientForm.getStateId()));
		patient.setMaritalStatus(this.maritalStatusList
				.getMartialStatusById(patientForm.getStatusId()));

		patient.setCreatedBy(userIdentity.getUserId());
		patient.setCreateDate(new GregorianCalendar().getTime());
		patient.setHomeNumber(patientForm.getHomeNumber());
		patient.setOccupation(patientForm.getOccupation());
		
		patient.setIsDeleted(0);
		Language language = this.languageDao.getLanguageById(patientForm
				.getLanguageId());
		patient.setLanguage(language);

		Title title = this.titleDao.getTitleById(patientForm.getTitleId());
		patient.setTitle(title);

		Gender gender = this.genderDao.getGenderById(patientForm.getGenderId());
		patient.setGender(gender);

		patient.setStatus("active");
		
	
		patient.setOrganisation(userIdentity.getOrganisation());

		this.patientDao.save(patient);

		return patient;
	}

	// just added
	@Override
	public Patient save(Patient patient) {
		// TODO Auto-generated method stub
		this.patientDao.save(patient);
		return patient;
	}

	// ends

	@Override
	public void delete(Patient patient) {
		// TODO Auto-generated method stub
		this.patientDao.delete(patient);
	}

	@Override
	public void update(PatientForm patientForm) {
		// TODO Auto-generated method stub
		Patient patient = this.getPatientById(patientForm.getPatientId());

		patient.setSurname(patientForm.getSurname());
		patient.setFirstName(patientForm.getFirstName());
		patient.setOthernames(patientForm.getOthernames());
		patient.setEmail(patientForm.getEmail());
		//patient.setPatient_code(patientForm.getPatient_code());
		patient.setAddress(patientForm.getAddress());
		patient.setPhoneNumber(patientForm.getPhoneNumber());
		patient.setHomeNumber(patientForm.getHomeNumber());
		patient.setBldgrp(patientForm.getBldgrp());
		patient.setGenotype(patientForm.getGenotype());
		patient.setHeight(patientForm.getHeight());
		/*patient.setHeightFeet(patientForm.getHeightFeet());
		patient.setHeightInch(patientForm.getHeightInch());*/
		patient.setModifiedBy(userIdentity.getUserId());
		patient.setModifiedDate(new GregorianCalendar().getTime());
		Title title = this.titleDao.getTitleById(patientForm.getTitleId());
		patient.setTitle(title);
		
		Gender gender = this.genderDao.getGenderById(patientForm.getGenderId());
		patient.setGender(gender);
		
		patient.setState(this.stateList.getStateById(patientForm.getStateId()));
		
		patient.setLga(this.lgaList.getLgaById(patientForm.getLgaId()));
		
		patient.setDob(patientForm.getDob());
		
		patient.setOccupation(patientForm.getOccupation());
		
		patient.setOrganisation(userIdentity.getOrganisation());
		
		Language language = this.languageDao.getLanguageById(patientForm.getLanguageId());
		patient.setLanguage(language);
		
		patient.setMaritalStatus(this.maritalStatusList.getMartialStatusById(patientForm.getStatusId()));
		
		patient.setStatus(patientForm.getStatus());

		this.patientDao.update(patient);

	}

	@Override
	public void update(Patient patient) {
		// TODO Auto-generated method stub
		this.patientDao.update(patient);
	}

	public List<PatientDocument> getPatientDocumentByPatient(Patient patient) {
		return this.patientDocumentDao.fetchAllByPatient(patient);
	}

	@Override
	public Patient findByBirthDay(String patientDob) {
		return patientDao.findByBirthDay(patientDob);
	}

	@Override
	public Patient checkIfPatientIdExist(String patientId) {
		return patientDao.checkIfPatientIdExist(patientId);
	}


	
}
