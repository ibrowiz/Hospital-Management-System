package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.HmoAddPackageServicesBo;
import org.calminfotech.system.boInterface.HmoPckServiceBo;
import org.calminfotech.system.boInterface.HmoPckSubServiceBo;
import org.calminfotech.system.boInterface.EHmoPackagesBo;
import org.calminfotech.system.daoInterface.HmoAddPackageServicesDao;
import org.calminfotech.system.daoInterface.HmoPckServiceDao;
import org.calminfotech.system.forms.HmoAddPackageServicesForm;
import org.calminfotech.system.forms.HmoPckServiceForm;
import org.calminfotech.system.models.HmoAddPackageServices;
import org.calminfotech.system.models.HmoPckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HmoAddPackageServicesBoImpl implements HmoAddPackageServicesBo {

	@Autowired
	private HmoAddPackageServicesDao hmoAddPackageServicesDao;
	
	@Autowired
	private EHmoPackagesBo organisationHmoBo;
	
	@Autowired
	private HmoPckServiceBo hmoPckServiceBo;
	
	@Autowired
	private HmoPckSubServiceBo hmoPckSubServiceBo;
	
	@Override
	public List<HmoAddPackageServices> fetchAll() {
		return hmoAddPackageServicesDao.fetchAll();
	}

	@Override
	public HmoAddPackageServices save(HmoAddPackageServicesForm hmoAddPackageServicesForm) {
		 	
		HmoAddPackageServices hmoAddPackageServices = new HmoAddPackageServices();
		
		hmoAddPackageServices.setSpendingLimit(hmoAddPackageServicesForm.getSpendingLimit());
		hmoAddPackageServices.setPeriod(hmoAddPackageServicesForm.getPeriod());
		hmoAddPackageServices.setCycle(hmoAddPackageServicesForm.getCycle());
		
		hmoAddPackageServices.setOrganisationHmoPackage(organisationHmoBo.getPackageById(hmoAddPackageServicesForm.getOrganisationHmoPackage()));
		hmoAddPackageServices.setHmoPckService(hmoPckServiceBo.getHmoPckServiceById(hmoAddPackageServicesForm.getHmoPckService()));
		hmoAddPackageServices.setHmoPckSubservice(hmoPckSubServiceBo.getHmoPckSubServiceById(hmoAddPackageServicesForm.getHmoPckSubservice()));			
		
		hmoAddPackageServicesDao.save(hmoAddPackageServices);		
		
		return hmoAddPackageServices;		
		//hmo.setCreatedBy(userIdentity.getUsername());
	}

	@Override
	public void update(HmoPckServiceForm hmoPckServiceForm) {
		
		//HmoPckService hmoPckService = hmoPckServiceDao.getHmoPckServiceById(hmoPckServiceForm.getServiceId());
		
		//hmoPckService.setServiceName(hmoPckServiceForm.getServiceName());
		//hmoPckServiceDao.update(hmoPckService);		
		
		//hmoPckService.setCreatedBy(userIdentity.getUsername());
		//hmo.setCreatedBy(userIdentity.getUsername());
		//this.hmoDao.update(hmo);
	}

	@Override
	public void delete(HmoAddPackageServices hmoAddPackageServices) {
		hmoAddPackageServicesDao.delete(hmoAddPackageServices);		
	}

	@Override
	public HmoAddPackageServices getHmoAddPackageServicesById(int id) {
		return hmoAddPackageServicesDao.getHmoAddPackageServicesById(id);
	}

	

}
