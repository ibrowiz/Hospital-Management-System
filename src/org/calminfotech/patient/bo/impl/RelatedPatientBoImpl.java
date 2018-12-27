package org.calminfotech.patient.bo.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.admin.boInterface.OrganisationBo;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.boInterface.RelatedPatientBo;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.daoInterface.PatientDocumentDao;
import org.calminfotech.patient.daoInterface.RelatedPatientDao;
import org.calminfotech.patient.forms.RelatedPatientForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.RelatedPatient;
import org.calminfotech.patient.utils.PatientCodeGenerator;
import org.calminfotech.system.models.Gender;
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
import org.calminfotech.utils.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RelatedPatientBoImpl implements RelatedPatientBo {
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private RelatedPatientDao relPatientDao;
	
	@Autowired
	private PatientBo patientBo;
	
	@Autowired
	private OrganisationBo organisationBo;
	
	@Autowired
	private TitleDao titleDao;

	@Autowired
	private GenderDao genderDao;
	
	@Autowired
	private PatientDao patientDao;

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
	public List<RelatedPatient> fetchAll(int organisationId) {
		Organisation organisation = organisationBo.getOrganisationById(organisationId);
		return this.relPatientDao.fetchAll(organisation);
	}

	@Override
	public List<RelatedPatient> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return this.relPatientDao.fetchAllByOrganisation(this.userIdentity
				.getOrganisation());
	}

	@Override
	public RelatedPatient getRelatedPatientByPatientId(int patientId) {
		// TODO Auto-generated method stub
		Patient patient = this.patientBo.getPatientById(patientId);
		return this.relPatientDao.getRelatedPatientByPatientId(patient);
	}
	
	@Override
	public RelatedPatient getRelPatientByPatientAndRel(int patientId,
			int RelPatId) {
		// TODO Auto-generated method stub
		Patient patient = this.patientBo.getPatientById(patientId);
		return this.relPatientDao.getRelPatientByPatientAndRel(patient, RelPatId);
	}

	@Override
	public List<RelatedPatient> fetchRelatedPatientByPatientId(int patientId) {
		Patient patient = this.patientBo.getPatientById(patientId);
		return this.relPatientDao.fetchRelatedPatientByPatientId(patient);
	}

	@Override
	public RelatedPatient getRelPatientById(int id) {
		// TODO Auto-generated method stub
		return this.relPatientDao.getRelPatientById(id);
	}

	@Override
	public RelatedPatient save(RelatedPatientForm relPatientForm) {
		RelatedPatient relPatient = new RelatedPatient();
		Patient patient = this.patientBo.getPatientById(relPatientForm.getPatientId());
		
		
		System.out.println("IDD"+relPatientForm.getPatientId());
		
		System.out.println("rel id" + relPatientForm.getRelPatientId());
		// generate id fromthe gennum class
		//String randomNumber = subscriberCodeGenerator.processNumber(subscriberForm.getSubscriberDob());
		//subscriber.setSubscriberId(randomNumber);
		
		//String randomNumber = patientCodeGenerator.processNumber(patientForm.getDob());
		
		//patient.setPatient_id(new AutoGen().genNum());
		relPatient.setPatient(patient);
		relPatient.setRelPatientId(relPatientForm.getRelPatientId());
		relPatient.setRelationship(relPatientForm.getRelationship());
		relPatient.setCreatedBy(userIdentity.getUserId());
		relPatient.setCreateDate(new GregorianCalendar().getTime());
		relPatient.setStatus("active");
		relPatient.setDeleted(false);
		relPatient.setOrganisationId(userIdentity.getOrganisation().getId());
		this.relPatientDao.save(relPatient);

		return relPatient;
	}
	
	@Override
	public RelatedPatient save(RelatedPatient relPatient) {
		this.relPatientDao.save(relPatient);
		return relPatient;
		
	}

	@Override
	public void delete(RelatedPatient relPatient) {
		this.relPatientDao.delete(relPatient);
	}

	@Override
	public void update(RelatedPatient relPatient) {
		this.relPatientDao.update(relPatient);
		
	}

	@Override
	public RelatedPatient findByBirthDay(String subscriberDob) {
		// TODO Auto-generated method stub
		return this.relPatientDao.findByBirthDay(subscriberDob);
	}

	@Override
	public RelatedPatient checkIfPatientIdExist(String relPatientId) {
		// TODO Auto-generated method stub
		return this.relPatientDao.checkIfPatientIdExist(relPatientId);
	}



	@Override
	public void update(RelatedPatientForm relPatientForm) {
		RelatedPatient relPatient = this.relPatientDao.getRelPatientById(relPatientForm.getRelPatientId());
		Patient patient = this.patientDao.getPatientById(relPatientForm.getPatientId());
		relPatient.setPatient(patient);
		relPatient.setRelPatientId(relPatientForm.getRelPatientId());
		relPatient.setRelationship(relPatientForm.getRelationship());
		relPatient.setModifiedBy(userIdentity.getUserId());
		relPatient.setModifiedDate(new GregorianCalendar().getTime());
		System.out.println("Information "+relPatientForm.getPatientId()+" "+relPatientForm.getRelationship()+" "+relPatientForm.getRelPatientId()+" "+userIdentity.getUserId()+" "+new GregorianCalendar().getTime());
		this.relPatientDao.update(relPatient);
	}

	

}
