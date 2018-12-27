package org.calminfotech.lab.bo.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.lab.bo.LabTestBo;
import org.calminfotech.lab.dao.LabTestCategoryDaoInter;
import org.calminfotech.lab.dao.LabTestDao;
import org.calminfotech.lab.forms.LabTestForm;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.lab.models.LabTestCategory;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LabTestBoImpl implements LabTestBo {
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private LabTestDao labTestDao;
	
	@Autowired
	private LabTestCategoryDaoInter labTestCategoryDao;
	


	@Override
	public List<LabTest> fetchAllByOrganisationId(Integer organisationId) {
		return this.labTestDao.fetchAllByOrganisationId(userIdentity.getOrganisation().getId());
	}

	@Override
	public LabTest getLabtestById(Integer id) {
		return labTestDao.getLabtestById(id);
	}

	@Override
	public LabTest save(LabTestForm labTestForm) {
		
		LabTestCategory labtestcat =  this.labTestCategoryDao.getLabtestCatById(labTestForm.getCatId());
		//List<LabTestCategory> labtestcategory = this.labTestCatBo.fetchAllCatByOrganisation(userIdentity.getOrganisation().getId())
		LabTest labTest = new LabTest();
		labTest.setlCategory(labtestcat);
		labTest.setName(labTestForm.getName());
		labTest.setDescription(labTestForm.getDescription());	
		labTest.setCreatedBy(userIdentity.getUsername());
		labTest.setCreatedDate(new GregorianCalendar().getTime());
		labTest.setOrganisationId(userIdentity.getOrganisation().getId());
		labTest.setDeleted(false);
		labTest.setStatus("active");	
		labTestDao.save(labTest);
		return labTest;
		
	}

	@Override
	public void update(LabTestForm labTestForm) {
		LabTestCategory labtestcat =  this.labTestCategoryDao.getLabtestCatById(labTestForm.getCatId());
		//LaboratoryTest labTest = new LaboratoryTest();
		LabTest labTest = this.labTestDao.getLabtestById(labTestForm.getId());
		
		
		//labTest.setId(labTestForm.getId());
		labTest.setlCategory(labtestcat);
		labTest.setName(labTestForm.getName());
		labTest.setDescription(labTestForm.getDescription());	
		labTest.setModifiedBy(userIdentity.getUsername());
		labTest.setLastModifiedDate(new GregorianCalendar().getTime());
		labTestDao.update(labTest);
		
	}

	@Override
	public void delete(LabTest labTest) {
		labTestDao.delete(labTest);
	}

	@Override
	public List<LabTest> getLabtestByCatId(Integer catId) {
		LabTestCategory laboratoryTestCategory = labTestCategoryDao.getLabtestCatById(catId);
		return labTestDao.getLaboratoryTestByCatId(laboratoryTestCategory);
}
	
}
