package org.calminfotech.system.bo.impl;

import java.util.Date;
import java.util.List;

import org.calminfotech.system.boInterface.GlobalHmoServicesBo;
import org.calminfotech.system.daoInterface.GlobalHmoServicesDao;
import org.calminfotech.system.forms.GlobalHmoServicesForm;
import org.calminfotech.system.models.GlobalHmoServices;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalHmoServicesBoImpl implements GlobalHmoServicesBo {
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private GlobalHmoServicesDao globalHmoServicesDao;

	@Override
	public GlobalHmoServices save(GlobalHmoServicesForm globalHmoServicesForm) {
		GlobalHmoServices globalHmoServices = new GlobalHmoServices();
		globalHmoServices.setServiceName(globalHmoServicesForm.getServiceName());
		globalHmoServices.setDescription(globalHmoServicesForm.getDescription());
		globalHmoServices.setCreateDate(new Date(System.currentTimeMillis()));
		globalHmoServices.setCreatedBy(userIdentity.getUserId());
		globalHmoServices.setOrganisationId(userIdentity.getOrganisation().getId());
		globalHmoServices.setDeleted(false);
		this.globalHmoServicesDao.save(globalHmoServices);
		return globalHmoServices;
		
	}

	@Override
	public void update(GlobalHmoServicesForm globalHmoServicesForm) {
		GlobalHmoServices globalHmoServices = this.globalHmoServicesDao.getGlobalHmoServiesById(globalHmoServicesForm.getHmoServiceId());
		globalHmoServices.setServiceName(globalHmoServicesForm.getServiceName());
		globalHmoServices.setDescription(globalHmoServicesForm.getDescription());
		globalHmoServices.setModifiedBy(userIdentity.getUserId());
		globalHmoServices.setModifiedDate(new Date(System.currentTimeMillis()));
		this.globalHmoServicesDao.update(globalHmoServices);
	}

	@Override
	public void delete(GlobalHmoServices globalHmoServices) {
		this.globalHmoServicesDao.delete(globalHmoServices);
	}

	@Override
	public List<GlobalHmoServices> fetchAllByOrganisation(int organisationId) {
		
		return this.globalHmoServicesDao.fetchAllByOrganisation(organisationId);
	}

	@Override
	public GlobalHmoServices getGlobalHmoServiesById(Integer serviceId) {
		return this.globalHmoServicesDao.getGlobalHmoServiesById(serviceId);
	}

}
