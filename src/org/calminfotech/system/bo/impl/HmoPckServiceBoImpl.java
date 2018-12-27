package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.HmoPckServiceBo;
import org.calminfotech.system.daoInterface.HmoPckServiceDao;
import org.calminfotech.system.forms.HmoPckServiceForm;
import org.calminfotech.system.models.HmoPckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HmoPckServiceBoImpl implements HmoPckServiceBo {

	@Autowired
	private HmoPckServiceDao hmoPckServiceDao;
	
	@Override
	public List<HmoPckService> fetchAll() {
		return hmoPckServiceDao.fetchAll();
	}

	@Override
	public HmoPckService save(HmoPckServiceForm hmoPckServiceForm) {
		 	
		HmoPckService hmoPckService = new HmoPckService();
		
		hmoPckService.setServiceName(hmoPckServiceForm.getServiceName());
		hmoPckServiceDao.save(hmoPckService);
		return hmoPckService;		
		//hmo.setCreatedBy(userIdentity.getUsername());
	}

	@Override
	public void update(HmoPckServiceForm hmoPckServiceForm) {
		
		HmoPckService hmoPckService = hmoPckServiceDao.getHmoPckServiceById(hmoPckServiceForm.getServiceId());
		
		hmoPckService.setServiceName(hmoPckServiceForm.getServiceName());
		hmoPckServiceDao.update(hmoPckService);		
		
		//hmoPckService.setCreatedBy(userIdentity.getUsername());
		//hmo.setCreatedBy(userIdentity.getUsername());
		//this.hmoDao.update(hmo);
	}

	@Override
	public void delete(HmoPckService hmoPckService) {
		hmoPckServiceDao.delete(hmoPckService);		
	}

	@Override
	public HmoPckService getHmoPckServiceById(int serviceId) {
		return hmoPckServiceDao.getHmoPckServiceById(serviceId);
	}

	

}
