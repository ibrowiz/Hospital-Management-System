package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.HmoSubserviceItemBo;
import org.calminfotech.system.daoInterface.HmoSubserviceItemDao;
import org.calminfotech.system.models.HmoSubserviceItem;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HmoSubserviceItemBoImpl implements HmoSubserviceItemBo {
	
	@Autowired
	private HmoSubserviceItemDao hmoSubserviceItemDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public HmoSubserviceItem getHmoSubserviceItemById(int id) {
		// TODO Auto-generated method stub
		return this.hmoSubserviceItemDao.getHmoSubserviceItemById(id);
	}

	@Override
	public void save(HmoSubserviceItem hmoSubserviceItem) {
		// TODO Auto-generated method stub
		this.hmoSubserviceItemDao.save(hmoSubserviceItem);
	}

	@Override
	public List<HmoSubserviceItem> deleteAll(HmoSubserviceItem hmoSubserviceItem) {
		// TODO Auto-generated method stub
		return this.hmoSubserviceItemDao.deleteAll(hmoSubserviceItem, userIdentity.getOrganisation().getId());
	}

	@Override
	public HmoSubserviceItem getHmoSubserviceItemByCategoryItem(int itemId) {
		// TODO Auto-generated method stub
		return this.hmoSubserviceItemDao.getHmoSubserviceItemByCategoryItem(itemId, userIdentity.getOrganisation().getId());
	}

	@Override
	public HmoSubserviceItem checksubservice(int itemid, int packid) {
		// TODO Auto-generated method stub
		return hmoSubserviceItemDao.checksubservice(itemid, packid);
	}
}
