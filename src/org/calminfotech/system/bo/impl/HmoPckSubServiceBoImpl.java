package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.HmoPckSubServiceBo;
import org.calminfotech.system.daoInterface.HmoPckServiceDao;
import org.calminfotech.system.daoInterface.HmoPckSubServiceDao;
import org.calminfotech.system.forms.HmoPckSubServiceForm;
import org.calminfotech.system.models.HmoPckService;
import org.calminfotech.system.models.HmoPckSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HmoPckSubServiceBoImpl implements HmoPckSubServiceBo {

	@Autowired
	private HmoPckSubServiceDao hmoPckSubServiceDao;
	
	@Autowired
	private HmoPckServiceDao hmoPckServiceDao;
	
	@Override
	public List<HmoPckSubService> fetchAll() {
		return hmoPckSubServiceDao.fetchAll();
	}

	@Override
	public HmoPckSubService save(HmoPckSubServiceForm hmoPckSubServiceForm) {	
		
		HmoPckSubService hmoPckSubService = new HmoPckSubService();
		
		hmoPckSubService.setSubserviceName(hmoPckSubServiceForm.getSubserviceName());
		HmoPckService hmoPckService = hmoPckServiceDao.getHmoPckServiceById(hmoPckSubServiceForm.getHmoPckService());
		hmoPckSubService.setHmoPckService(hmoPckService);
		
		hmoPckSubServiceDao.save(hmoPckSubService);
		return hmoPckSubService;		
		//hmo.setCreatedBy(userIdentity.getUsername());
	}

	
	@Override
	public void update(HmoPckSubServiceForm hmoPckSubServiceForm) {
		
		HmoPckSubService hmoPckSubService = hmoPckSubServiceDao.getHmoPckSubServiceById(hmoPckSubServiceForm.getId());
		
		hmoPckSubService.setSubserviceName(hmoPckSubServiceForm.getSubserviceName());
		hmoPckSubServiceDao.update(hmoPckSubService);		
		
		//hmoPckService.setCreatedBy(userIdentity.getUsername());
		//hmo.setCreatedBy(userIdentity.getUsername());
		//this.hmoDao.update(hmo);
	}

	@Override
	public void delete(HmoPckSubService hmoPckSubService) {
		hmoPckSubServiceDao.delete(hmoPckSubService);		
	}

	@Override
	public HmoPckSubService getHmoPckSubServiceById(int subserviceId) {
		return hmoPckSubServiceDao.getHmoPckSubServiceById(subserviceId);
	}

	
	

}
