package org.calminfotech.system.bo.impl;

import java.util.Date;
import java.util.List;

import org.calminfotech.system.boInterface.GlobalItemCategoryBo;
import org.calminfotech.system.boInterface.GlobalItemTypeBo;
import org.calminfotech.system.daoInterface.GlobalItemCategoryDao;
import org.calminfotech.system.forms.GlobalItemCategoryForm;
import org.calminfotech.system.models.GlobalItemCategory;
import org.calminfotech.system.models.GlobalItemType;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalItemCategoryBoImpl implements GlobalItemCategoryBo {

	@Autowired
	private GlobalItemCategoryDao globalItemCategoryDao;
	
	@Autowired
	private GlobalItemTypeBo globalItemTypeBo;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Override
	public GlobalItemCategory getGlobalCategoryItemById(int id) {
		// TODO Auto-generated method stub
		return globalItemCategoryDao.getGlobalCategoryItemById(id);
	}

	@Override
	public void save(GlobalItemCategory globalCategoryItem) {
		// TODO Auto-generated method stub
		globalItemCategoryDao.save(globalCategoryItem);
	}

	@Override
	public void delete(GlobalItemCategory globalCategoryItem) {
		// TODO Auto-generated method stub
		globalItemCategoryDao.delete(globalCategoryItem);
	}

	@Override
	public void update(GlobalItemCategory globalCategoryItem) {
		// TODO Auto-generated method stub
		globalItemCategoryDao.update(globalCategoryItem);
	}

	@Override
	public List<GlobalItemCategory> fetchAll() {
		// TODO Auto-generated method stub
		return globalItemCategoryDao.fetchAll();
	}

	@Override
	public List<GlobalItemCategory> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return globalItemCategoryDao.fetchAllByOrganisation(userIdentity.getOrganisation());
	}

	@Override
	public void saveCategoryForm(GlobalItemCategoryForm cForm) {
		// TODO Auto-generated method stub
		GlobalItemCategory gCategory = new GlobalItemCategory();
		gCategory.setName(cForm.getCategoryName());
		gCategory.setDescription(cForm.getCategoryDescription());
		GlobalItemType type = globalItemTypeBo.getGlobalItemTypeById(cForm.getItemTypeId());
		gCategory.setItemTypeId(type);
		gCategory.setOrganisation(userIdentity.getOrganisation());
		gCategory.setCreatedBy(userIdentity.getUsername());
		gCategory.setCreateDate(new Date(System.currentTimeMillis()));
		gCategory.setDeleted(false);
		globalItemCategoryDao.save(gCategory);
	}

	@Override
	public void updateForm(GlobalItemCategoryForm cForm) {
		// TODO Auto-generated method stub
		GlobalItemCategory gICategory = globalItemCategoryDao.getGlobalCategoryItemById(cForm.getCategoryId());
		gICategory.setName(cForm.getCategoryName());
		gICategory.setDescription(cForm.getCategoryDescription());
		GlobalItemType type = globalItemTypeBo.getGlobalItemTypeById(cForm.getItemTypeId());
		gICategory.setItemTypeId(type);
		gICategory.setModifiedBy(userIdentity.getUsername());
		gICategory.setModifyDate(new Date(System.currentTimeMillis()));
	}
	@Override
	public List<GlobalItemCategory> fetchGlobalItemCategoryByItemTypeId(
			GlobalItemType globalItemTypId) {
		// TODO Auto-generated method stub
		return globalItemCategoryDao.fetchGlobalItemCategoryByItemTypeId(globalItemTypId);
	}

}
