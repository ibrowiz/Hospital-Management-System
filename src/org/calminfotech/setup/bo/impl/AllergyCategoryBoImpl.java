package org.calminfotech.setup.bo.impl;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.setup.boInterface.AllergyCategoryBo;
import org.calminfotech.setup.boInterface.AllergyCategoryViewBo;
import org.calminfotech.setup.daoInterface.AllergyCategoryDao;
import org.calminfotech.setup.forms.AllergyCategoryForm;
import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class AllergyCategoryBoImpl implements AllergyCategoryBo {
	
	@Autowired
	private AllergyCategoryDao allergyCategoryDao;
	
	@Autowired
	private AllergyCategoryViewBo aCatViewBo;
	
	@Autowired
	UserIdentity userIdentity;

	@Override
	public List<AllergyCategory> fetchAll() {
		// TODO Auto-generated method stub
		
		
		return allergyCategoryDao.fetchAll();
	}

	@Override
	public AllergyCategory getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return allergyCategoryDao.getCategoryById(categoryId);
	}

	@Override
	public AllergyCategory save(AllergyCategoryForm allergyCategoryForm) {
		// TODO Auto-generated method stub
		AllergyCategory allergyCategory = new AllergyCategory();
		allergyCategory.setParentId(allergyCategoryForm.getParentId());
		allergyCategory.setName(allergyCategoryForm.getName());
		allergyCategory.setCreatedBy(userIdentity.getUsername());
		allergyCategory.setOrganisationId(userIdentity.getOrganisation().getId());
		allergyCategory.setStatus("active");
		allergyCategory.setCreateDate(new GregorianCalendar().getTime());
		allergyCategoryDao.save(allergyCategory);
		return allergyCategory;
	}

	@Override
	public void update(AllergyCategoryForm allergyCategoryForm) {
		// TODO Auto-generated method stub
		System.out.println("alergycat ");
		System.out.println("alergycat "+allergyCategoryForm.getAllergyCategoryId());
		System.out.println("alergycat "+allergyCategoryForm.getParentId());
		AllergyCategory category = this.allergyCategoryDao.getCategoryById(allergyCategoryForm.getAllergyCategoryId());
		//AllergyCategoryView ac = aCatViewBo.getAllergyCatViewById(allergyCategoryForm.getAllergyCategoryId());
		
		
		category.setParentId(allergyCategoryForm.getParentId());
		category.setName(allergyCategoryForm.getName());
		category.setModifiedBy(userIdentity.getUsername());
		category.setModifiedDate(new Date(System.currentTimeMillis()));
		
		allergyCategoryDao.update(category);
	}

	@Override
	public void delete(AllergyCategory category) {
		// TODO Auto-generated method stub
		
		allergyCategoryDao.delete(category);
		
	}

	@Override
	public List<AllergyCategory> fetchAllByOrganisation(int organisationId) {
		// TODO Auto-generated method stub
		return this.allergyCategoryDao.fetchAllByOrganisation(organisationId);
	}

	

}
