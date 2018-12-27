package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.BillingSchemeItemBo;
import org.calminfotech.system.daoInterface.BillingSchemeDao;
import org.calminfotech.system.daoInterface.BillingSchemeItemDao;
import org.calminfotech.system.forms.BillingSchemeItemForm;
import org.calminfotech.system.models.HmoPckService;
import org.calminfotech.system.models.HmoPckSubService;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalItemType;
import org.calminfotech.system.models.BillingScheme;
import org.calminfotech.system.models.BillingSchemeItem;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.GlobalItemList;
import org.calminfotech.utils.GlobalItemTypeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BillingSchemeItemBoImpl implements BillingSchemeItemBo{

	
	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private BillingSchemeItemDao billingSchemeItemDao;
	

	@Autowired
	private BillingSchemeDao billingSchemeDao;
	
	
	
	
	@Autowired
	private GlobalItemTypeList itemTypeList;
	
	
	
	@Autowired
	private GlobalItemList itemList;
	
	
	@Override
	public List<BillingSchemeItem> fetchAllByOrganisation() {
		// TODO Auto-generated method stub

		return this.billingSchemeItemDao.fetchAllByOrganisation(userIdentity
				.getOrganisation());
	}

	@Override
	public BillingSchemeItem getBillingSchemeitemById(int id) {
		// TODO Auto-generated method stub
		return billingSchemeItemDao.getBillingSchemeItemById(id);
	}

	@Override
	public void delete(BillingSchemeItem billingSchemeItem) {
		// TODO Auto-generated method stub
		billingSchemeItemDao.delete(billingSchemeItem);
	}

	@Override
	public void update(BillingSchemeItemForm billingSchemeItemForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BillingSchemeItem> fetchAll() {
		// TODO Auto-generated method stub
		return billingSchemeItemDao.fetchAll();
	}

	@Override
	public BillingSchemeItem save(BillingSchemeItemForm billingSchemeItemForm) {
		// TODO Auto-generated method stub
		BillingSchemeItem billingSchemeItem = new BillingSchemeItem();
		
	//	paymentSchemeitem.setName(paymentSchemeitemform.getName());
		billingSchemeItem.setPrice(billingSchemeItemForm.getPrice());
		
		BillingScheme paymentScheme = billingSchemeDao.getBillingSchemeById(billingSchemeItemForm.getSchemeId());
	 billingSchemeItem.setBillingScheme(paymentScheme);
		
	 GlobalItemType itemType = itemTypeList.getItemTypeById(billingSchemeItemForm.getItemtypeId());
	 billingSchemeItem.setGlobalItemType(itemType);
	 
	 GlobalItem globalItem = itemList.getGlobalItemById(billingSchemeItemForm.getItemId());
	 billingSchemeItem.setGlobalItem(globalItem);
		
		billingSchemeItem.setCreatedBy(userIdentity.getUsername());
		billingSchemeItem.setOrganisation(userIdentity.getOrganisation());
		billingSchemeItemDao.save(billingSchemeItem);
		
		return billingSchemeItem;
	}

}
