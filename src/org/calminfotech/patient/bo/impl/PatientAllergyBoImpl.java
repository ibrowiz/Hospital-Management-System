package org.calminfotech.patient.bo.impl;

import java.util.Date;
import java.util.List;

import org.calminfotech.patient.boInterface.PatientAllergyBo;
import org.calminfotech.patient.daoInterface.PatientAllergyDao;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.forms.PatientAllergyForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.setup.daoInterface.AllergyDao;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientAllergyBoImpl implements PatientAllergyBo {

	@Autowired
	private PatientAllergyDao patientAllergyDao;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private PatientDao patientDao;

	@Autowired
	private AllergyDao allergyDao;

	/*@Override
	public List<PatientAllergy> fetchAllPatientsByAllergy(Allergy allergy) {
		// TODO Auto-generated method stub
		return this.patientAllergyDao.fetchAllPatientsByAllergy(allergy);
	}
*/
	/*@Override
	public List<PatientAllergy> fetchAllAllergiesByPatient(Patient patient) {
		// TODO Auto-generated method stub
		return this.patientAllergyDao.fetchAllAllergiesByPatient(patient);
	}*/

	@Override
	public void save(PatientAllergyForm form) {
		// TODO Auto-generated method stub
		Patient patient = this.patientDao.getPatientById(form.getPatientId());
		//Allergy allergy = this.allergyDao.getAllergyById(form.getAllergyId());

		PatientAllergy patientAllergy = new PatientAllergy();
		patientAllergy.setPatient(patient);
		patientAllergy.setAllergyId(form.getAllergyId());
		patientAllergy.setAllergyName(form.getAllergyName());
		patientAllergy.setReactions(form.getReactions());
		patientAllergy.setDescription(form.getDescription());
		patientAllergy.setOrganisationId(this.userIdentity.getOrganisation().getId());
		patientAllergy.setDeleted(false);
		patientAllergy.setStatus("active");
		patientAllergy.setCreatedBy(this.userIdentity.getUsername()); 
		patientAllergy.setCreateDate(new Date(System.currentTimeMillis()));
		
		System.out.println("alergy id"+ form.getAllergyId());

		this.patientAllergyDao.save(patientAllergy);
	}
  
	
	
	@Override
	public void delete(PatientAllergy patientAllergy) {
		// TODO Auto-generated method stub 
		this.patientAllergyDao.delete(patientAllergy);
	}

	@Override
	public void update(PatientAllergyForm form) {
		System.out.println("PAllergyID"+form.getPatientId());
		System.out.println("pallId"+form.getId());
		Patient patient = this.patientDao.getPatientById(form.getPatientId());
		PatientAllergy patAllergy = this.patientAllergyDao.getByIdAndPallergyId(patient, form.getId());
		
		
		//return this.transactionDao.getTransactionByAccId(account);
		patAllergy.setAllergyId(form.getAllergyId());
		patAllergy.setDescription(form.getDescription());
		patAllergy.setReactions(form.getReactions());
		patAllergy.setModifiedBy(userIdentity.getUsername());
		patAllergy.setModifiedDate(new Date(System.currentTimeMillis()));
		
		this.patientAllergyDao.update(patAllergy);

	}

	@Override
	public PatientAllergy getPatientAllergyById(int id) {

		return this.patientAllergyDao.getPatientAllergyById(id);

	}



	@Override
	public PatientAllergy getByIdAndPallergyId(Integer patientId, int id) {
		
		Patient patient = this.patientDao.getPatientById(patientId);
		
			return this.patientAllergyDao.getByIdAndPallergyId(patient, id);
		

	}



	@Override
	public PatientAllergy fetchAllAllergiesByPatient(Integer patientId) {
		// TODO Auto-generated method stub
		Patient patient = this.patientDao.getPatientById(patientId);
		return this.patientAllergyDao.fetchAllAllergiesByPatient(patient) ;
	}



	@Override
	public List<PatientAllergy> fetchAllpAllergiesByPatient(Integer patientid) {
		Patient patient = this.patientDao.getPatientById(patientid);
		return this.patientAllergyDao.fetchAllpAllergiesByPatient(patient);
	}



	
}
