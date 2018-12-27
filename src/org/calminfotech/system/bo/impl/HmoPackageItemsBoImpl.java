package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.HmoPackageItemsBo;
import org.calminfotech.system.boInterface.HmoPckServiceBo;
import org.calminfotech.system.boInterface.HmoPckSubServiceBo;
import org.calminfotech.system.boInterface.EHmoPackagesBo;
import org.calminfotech.system.daoInterface.HmoPackageItemsDao;
import org.calminfotech.system.forms.HmoPackageItemsForm;
import org.calminfotech.system.models.HmoPackageItems;
import org.calminfotech.system.models.HmoPckSubService;
import org.calminfotech.system.models.EhmoPackages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HmoPackageItemsBoImpl implements HmoPackageItemsBo {

	@Autowired
	private HmoPackageItemsDao packageItemsDao;
	
	@Autowired
	private EHmoPackagesBo organisationHmoBo;
	
	@Autowired
	private HmoPckServiceBo hmoPckServiceBo;
	
	@Autowired
	private HmoPckSubServiceBo hmoPckSubServiceBo;
	
	@Override
	public List<HmoPackageItems> fetchAll() {
		return packageItemsDao.fetchAll();
	}

	@Override
	public HmoPackageItems save(HmoPackageItemsForm hmoAddPackageServicesForm) {
		 	
		HmoPackageItems packageItems = new HmoPackageItems();
		
		packageItems.setSpendingLimit(hmoAddPackageServicesForm.getSpendingLimit());
		packageItems.setPeriod(hmoAddPackageServicesForm.getPeriod());
		packageItems.setCycle(hmoAddPackageServicesForm.getCycle());
			
		packageItems.setHmoPckService(hmoPckServiceBo.getHmoPckServiceById(hmoAddPackageServicesForm.getHmoPckService()));
		packageItems.setHmoPckSubservice(hmoPckSubServiceBo.getHmoPckSubServiceById(hmoAddPackageServicesForm.getHmoPckSubservice()));	
		
		packageItems.setOrganisationHmoPackage(organisationHmoBo.getPackageById(hmoAddPackageServicesForm.getOrganisationHmoPackage()));
		
		packageItemsDao.save(packageItems);		
		
		return packageItems;		
	}

	@Override
	public void update(HmoPackageItemsForm hmoPckServiceForm) {
		
		//HmoPckService hmoPckService = hmoPckServiceDao.getHmoPckServiceById(hmoPckServiceForm.getServiceId());
		
		//hmoPckService.setServiceName(hmoPckServiceForm.getServiceName());
		//hmoPckServiceDao.update(hmoPckService);		
		
		//hmoPckService.setCreatedBy(userIdentity.getUsername());
		//hmo.setCreatedBy(userIdentity.getUsername());
		//this.hmoDao.update(hmo);
	}

	@Override
	public void delete(HmoPackageItems hmoAddPackageServices) {
		packageItemsDao.delete(hmoAddPackageServices);		
	}

	@Override
	public HmoPackageItems getHmoAddPackageServicesById(int id) {
		return packageItemsDao.getHmoAddPackageServicesById(id);
	}

	@Override
	public HmoPackageItems getSpendingLimit(int HmoPackage, int hmoPckSubservice) {
		// TODO Auto-generated method stub
		EhmoPackages hmoPackage = this.organisationHmoBo.getPackageById(HmoPackage);
		HmoPckSubService hmoPckSubService = this.hmoPckSubServiceBo.getHmoPckSubServiceById(hmoPckSubservice);
		return packageItemsDao.getSpendingLimit(hmoPackage, hmoPckSubService);
	}

	/*@Override
	public HmoPackageItems getHmoPackageItemsBySubservice(HmoPckSubService subserviceId) {
		// TODO Auto-generated method stub
		return packageItemsDao.getHmoPackageItemsBySubservice(subserviceId);
	}*/
}
