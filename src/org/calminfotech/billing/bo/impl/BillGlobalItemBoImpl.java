package org.calminfotech.billing.bo.impl;

import java.util.GregorianCalendar;
import java.util.List;
import org.calminfotech.billing.boInterface.BillGlobalItemBo;
import org.calminfotech.billing.daoInterface.BillGlobalItemDao;
import org.calminfotech.billing.models.BillGlobalItem;
import org.calminfotech.billing.models.BillScheme;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BillGlobalItemBoImpl implements BillGlobalItemBo{

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private BillGlobalItemDao globalItemDao;
	
	
	

	@Override
	public List<BillGlobalItem> fetchAll() {
		return this.globalItemDao.fetchAll();
	}

	@Override
	public List<BillGlobalItem> fetchAllByOrganisation(int organisationId) {
		return this.globalItemDao.fetchAllByOrganisation(organisationId);
	}
	
	
	
	
	@Override
	public BillGlobalItem getGlobalItemById(int id) {
		return this.globalItemDao.getGlobalItemById(id);
	}


	
	@Override
	public void update(BillGlobalItem globalItem) {
		BillGlobalItem glob = this.globalItemDao.getGlobalItemById(globalItem.getGlobalItemId());
		glob.setGlobalName(globalItem.getGlobalName());
		glob.setGlobalDescription(globalItem.getGlobalDescription());
	
	//	ehmo.setOrganisationId(userIdentity.getOrganisation().getId());
		glob.setModifiedBy(userIdentity.getUsername());
		glob.setModifiedDate(new GregorianCalendar().getTime());
		this.globalItemDao.update(globalItem);
	}
	@Override
	public List<BillGlobalItem> fetchAllByOrganisationByType(int organisationId, int itemType) {
		return this.globalItemDao.fetchAllByOrganisationByType(organisationId, itemType);
	}

	@Override
	public void save(BillGlobalItem globalItemItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BillGlobalItem globalItemItem) {
		// TODO Auto-generated method stub
		
	}

	
}