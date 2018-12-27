package org.calminfotech.system.bo.impl;

import java.util.Date;
import java.util.List;





import org.calminfotech.system.boInterface.GlobalItemBo;
import org.calminfotech.system.boInterface.GlobalItemCategoryBo;
import org.calminfotech.system.daoInterface.GlobalItemDao;
import org.calminfotech.system.forms.GlobalItemForm;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemCategory;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class GlobalItemBoImpl implements GlobalItemBo{
	
	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private GlobalItemDao globalItemDao;
	
	@Autowired
	private GlobalItemCategoryBo itemCategoryBo;

	@Override
	public List<GlobalItem> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return this.globalItemDao.fetchAllByOrganisation(userIdentity
				.getOrganisation());
	}

	@Override
	public GlobalItem getGlobalItemById(int id) {
		// TODO Auto-generated method stub
		return globalItemDao.getglobalItemById(id);
	}

	@Override
	public void save(GlobalItemForm iForm) {
		// TODO Auto-generated method stub
		GlobalItem globalItem = new GlobalItem();
		
		globalItem.setName(iForm.getItem_name());
		globalItem.setDescription(iForm.getDescription());
		GlobalItemCategory itemCategory = itemCategoryBo.getGlobalCategoryItemById(iForm.getCategory());
		globalItem.setCategory(itemCategory);
		globalItem.setCreatedBy(userIdentity.getUsername());
		globalItem.setCreateDate(new Date(System.currentTimeMillis()));
		globalItem.setOrganisation(userIdentity.getOrganisation());
		globalItemDao.save(globalItem);
	}

	@Override
	public void delete(GlobalItem itemDistribution) {
		// TODO Auto-generated method stub
		globalItemDao.delete(itemDistribution);
	}

	@Override
	public void update(GlobalItemForm iForm) {
		// TODO Auto-generated method stub
		GlobalItem item = globalItemDao.getglobalItemById(iForm.getId());
		item.setName(iForm.getItem_name());
		item.setDescription(iForm.getDescription());
		item.setModifiedBy(userIdentity.getUsername());
		item.setModifyDate(new Date(System.currentTimeMillis()));
		globalItemDao.save(item);
	}

	@Override
	public List<GlobalItem> fetchAll() {
		// TODO Auto-generated method stub
		return globalItemDao.fetchAll();
	}
}
