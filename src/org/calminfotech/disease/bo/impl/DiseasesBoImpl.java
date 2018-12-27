package org.calminfotech.disease.bo.impl;

import java.util.Date;
import java.util.List;

import org.calminfotech.disease.boInterface.DiseasesBo;
import org.calminfotech.disease.daoInterface.DiseaseCategoryDao;
import org.calminfotech.disease.daoInterface.DiseasesDao;
import org.calminfotech.disease.forms.DiseaseForm;
import org.calminfotech.disease.models.DiseaseCategory;
import org.calminfotech.disease.models.Diseases;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseasesBoImpl implements DiseasesBo {
	
	@Autowired
	private DiseasesDao diseasesDao;
	
	@Autowired
	private DiseaseCategoryDao diseasesCategoryDao;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Override
	public Diseases getDiseaseById(int id) {
		return this.diseasesDao.getDiseaseById(id);
	}

	@Override
	public List<Diseases> fetchDiseaseById(Integer id) {
		// TODO Auto-generated method stub
		return this.diseasesDao.fetchDiseaseById(id);
	}

	@Override
	public List<Diseases> fetchAllByOrganisation(int organisationId) {
		// TODO Auto-generated method stub
		return this.diseasesDao.fetchAllByOrganisation(organisationId);
	}

	@Override
	public Diseases save(DiseaseForm diseasesForm) {
		Diseases diseases = new Diseases();
		//allergy.setCategoryId(allergyForm.getCategoryId());
		DiseaseCategory category = this.diseasesCategoryDao.getDiseaseCategoryById(diseasesForm.getCategoryId());
		diseases.setCategory(category);
		diseases.setName(diseasesForm.getName());
		//diseases.setDescription(diseasesForm.getDescription());
		diseases.setCreatedBy(userIdentity.getUsername());
		diseases.setCreateDate(new Date(System.currentTimeMillis()));
		diseases.setOrganisationId(userIdentity.getOrganisation().getId());
		diseases.setStatus("active");
		
		diseasesDao.save(diseases);
		return diseases;
	}

	@Override
	public void delete(Diseases diseases) {
		this.diseasesDao.delete(diseases);
		
	}

	@Override
	public void update(DiseaseForm diseasesForm) {
		Diseases diseases = this.diseasesDao.getDiseaseById(diseasesForm.getDiseaseId());

		//AllergyCategory1 category = this.allergyCategoryDao1.getCategoryById(allergyForm.getCategoryId());
		
		DiseaseCategory category = this.diseasesCategoryDao.getDiseaseCategoryById(diseasesForm.getCategoryId());
		diseases.setCategory(category);
		diseases.setName(diseasesForm.getName());
		//allergy.setDescription(allergyForm.getDescription());
		diseases.setModifiedBy(userIdentity.getUsername());
		diseases.setModifyDate(new Date(System.currentTimeMillis()));
		//allergy.setOrganisationId(userIdentity.getOrganisation().getId());
		diseasesDao.update(diseases);
	}


}
