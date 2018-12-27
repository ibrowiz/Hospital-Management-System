package org.calminfotech.hmo.bo.impl;

import java.util.Date;
import java.util.List;

import org.calminfotech.hmo.boInterface.EhmoPackageServicesBo;
import org.calminfotech.hmo.daoInterface.EhmoPackageServicesDao;
import org.calminfotech.hmo.forms.EhmoPackageServicesForm;
import org.calminfotech.hmo.models.EhmoPackageServices;
import org.calminfotech.system.daoInterface.EHmoPackagesDao;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EhmoPackageServicesBoImpl implements EhmoPackageServicesBo {
	
	@Autowired 
	private EhmoPackageServicesDao ehmoPackageServicesDao;
	
	@Autowired 
	private EHmoPackagesDao ehmoPackagesDao;
	
	@Autowired 
	private UserIdentity userIdentity;

	@Override
	public EhmoPackageServices save(
			EhmoPackageServicesForm eHmoPackageServicesForm) {
		EhmoPackageServices ehmoPackageServices = new EhmoPackageServices();
		EhmoPackages ehmoPackages = this.ehmoPackagesDao.getPackageById(eHmoPackageServicesForm.getHmoPackageId()) ;
		ehmoPackageServices.setEhmoPackages(ehmoPackages);
		ehmoPackageServices.setGlobalServiceId(eHmoPackageServicesForm.getGlobalServiceId());
		ehmoPackageServices.setName(eHmoPackageServicesForm.getName());
		ehmoPackageServices.setPrice(eHmoPackageServicesForm.getPrice());
		ehmoPackageServices.setCreatedBy(userIdentity.getUserId());
		ehmoPackageServices.setCreateDate(new Date(System.currentTimeMillis()));
		ehmoPackageServices.setDeleted(false);
		ehmoPackageServices.setOrganisationId(userIdentity.getOrganisation().getId());
		this.ehmoPackageServicesDao.save(ehmoPackageServices);
		return ehmoPackageServices;
		
		
		
	}

	@Override
	public void update(EhmoPackageServicesForm eHmoPackageServicesForm) {
		EhmoPackageServices ehmoPackageServices = this.ehmoPackageServicesDao.getEhmoPackageServicesById(eHmoPackageServicesForm.getHmoPackageId());
		EhmoPackages ehmoPackages = this.ehmoPackagesDao.getPackageById(eHmoPackageServicesForm.getHmoPackageId());
		ehmoPackageServices.setEhmoPackages(ehmoPackages);
		ehmoPackageServices.setGlobalServiceId(eHmoPackageServicesForm.getGlobalServiceId());
		ehmoPackageServices.setName(eHmoPackageServicesForm.getName());
		ehmoPackageServices.setPrice(eHmoPackageServicesForm.getPrice());
		ehmoPackageServices.setModifiedBy(userIdentity.getUserId());
		ehmoPackageServices.setModifiedDate(new Date(System.currentTimeMillis()));
		this.ehmoPackageServicesDao.update(ehmoPackageServices);
	}

	@Override
	public void delete(EhmoPackageServices eHmoPackageServices) {
		 this.ehmoPackageServicesDao.delete(eHmoPackageServices);	
	}

	@Override
	public EhmoPackageServices getEhmoPackageServicesById(Integer Id) {
		return this.ehmoPackageServicesDao.getEhmoPackageServicesById(Id);
	}

	@Override
	public List<EhmoPackageServices> fetchAllByOrganisation(int organisationId) {
		return this.ehmoPackageServicesDao.fetchAllByOrganisation(organisationId);
	}

}
