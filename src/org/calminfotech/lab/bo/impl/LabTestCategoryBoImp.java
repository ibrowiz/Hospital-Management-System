package org.calminfotech.lab.bo.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.lab.bo.LabTestCategoryBo;
import org.calminfotech.lab.dao.LabTestCategoryDaoInter;
import org.calminfotech.lab.forms.LabTestCategoryForm;
import org.calminfotech.lab.models.LabTestCategory;
import org.calminfotech.lab.models.LabTestCategoryView;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabTestCategoryBoImp implements LabTestCategoryBo {

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private LabTestCategoryDaoInter labTestCatDao;

	@Override
	public List<LabTestCategory> fetchAllCatByOrganisation(Integer organisationId) {
		return this.labTestCatDao.fetchAllCatByOrganisation(userIdentity.getOrganisation().getId());
	}

	@Override
	public LabTestCategory getLabtestCatById(Integer id) {
		return labTestCatDao.getLabtestCatById(id);
	}

	@Override
	public LabTestCategory save(LabTestCategoryForm labTestCatForm) {
		LabTestCategory labTest = new LabTestCategory();
		labTest.setParentId(labTestCatForm.getParentId());
		labTest.setCategoryName(labTestCatForm.getCategoryName());
		labTest.setDescription(labTestCatForm.getDescription());	
		labTest.setCreatedBy(userIdentity.getUsername());
		labTest.setCreatedDate(new GregorianCalendar().getTime());
		labTest.setModifiedBy(labTestCatForm.getModifiedBy());
		labTest.setLastModifiedDate(labTestCatForm.getLastModifiedDate());
		labTest.setStatus("active");
		labTest.setDeleted(false);
		labTest.setOrganisationId(userIdentity.getOrganisation().getId());
		//labTestCatBo.save(labTest);
		labTestCatDao.save(labTest);
		return labTest;
	}

	@Override
	public void update(LabTestCategoryForm labTestCatForm) {
		LabTestCategory labTestCat = this.labTestCatDao.getLabtestCatById(labTestCatForm.getId());
		//labTestCat.setId(labTestCatForm.getId());
		labTestCat.setParentId(labTestCatForm.getParentId());
		labTestCat.setCategoryName(labTestCatForm.getCategoryName());
		labTestCat.setDescription(labTestCatForm.getDescription());	
		//labTestCat.setCreatedBy(labTestCatForm.getCreatedBy());
		//labTestCat.setCreatedDate(labTestCatForm.getCreatedDate());
		labTestCat.setModifiedBy(userIdentity.getUsername());
		labTestCat.setLastModifiedDate(new GregorianCalendar().getTime());
		//labTestCat.setStatus(labTestCatForm.getStatus());
		//this.labTestCatBo.update(labTestCat);
		labTestCatDao.update(labTestCat);
	}

	@Override
	public void delete(LabTestCategory labTestCat) {
		labTestCatDao.delete(labTestCat);
	}

	@Override
	public List<LabTestCategoryView> fetchAllCatByOrg(Integer organisationId) {
		// TODO Auto-generated method stub
		return this.labTestCatDao.fetchAllCatByOrg(organisationId);
	}
	
}
