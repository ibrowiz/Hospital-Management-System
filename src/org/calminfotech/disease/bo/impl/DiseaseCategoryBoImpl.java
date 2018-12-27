package org.calminfotech.disease.bo.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.disease.boInterface.DiseaseCategoryBo;
import org.calminfotech.disease.daoInterface.DiseaseCategoryDao;
import org.calminfotech.disease.forms.DiseaseCategoryForm;
import org.calminfotech.disease.models.DiseaseCategory;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseCategoryBoImpl implements DiseaseCategoryBo{
	
	@Autowired
	private DiseaseCategoryDao diseaseCategoryDao;
	
	@Autowired
	private UserIdentity userIdentity;
	

	@Override
	public DiseaseCategory getDiseaseCategoryById(int categoryId) {
		return this.diseaseCategoryDao.getDiseaseCategoryById(categoryId);
	}

	@Override
	public DiseaseCategory save(DiseaseCategoryForm diseaseCategoryForm) {
		DiseaseCategory diseaseCategory = new DiseaseCategory();
		diseaseCategory.setParentId(diseaseCategoryForm.getParentId());
		diseaseCategory.setName(diseaseCategoryForm.getName());
		diseaseCategory.setStatus("active");
		diseaseCategory.setCreatedBy(userIdentity.getUserId());
		diseaseCategory.setOrganisationId(userIdentity.getOrganisation().getId());
		diseaseCategory.setCreatedDate(new GregorianCalendar().getTime());
		diseaseCategory.setIsDeleted(false);
		diseaseCategoryDao.save(diseaseCategory);
		return diseaseCategory;
	}

	@Override
	public void update(DiseaseCategoryForm diseaseCategoryForm) {
		// TODO Auto-generated method stub
		DiseaseCategory diseaseCategory = this.diseaseCategoryDao.getDiseaseCategoryById(diseaseCategoryForm.getDiseaseCategoryId());
		diseaseCategory.setParentId(diseaseCategoryForm.getParentId());
		diseaseCategory.setName(diseaseCategoryForm.getName());
		diseaseCategory.setModifiedBy(userIdentity.getUserId());
		diseaseCategory.setModifiedDate(new GregorianCalendar().getTime());
		diseaseCategoryDao.update(diseaseCategory);
	}

	@Override
	public void delete(DiseaseCategory diseaseCategory) {
		this.diseaseCategoryDao.delete(diseaseCategory);
	}

	@Override
	public List<DiseaseCategory> fetchAllByOrganisation(int organisationId) {
		// TODO Auto-generated method stub
		return this.diseaseCategoryDao.fetchAllByOrganisation(organisationId);
	}

}
