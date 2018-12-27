package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.admin.daoInterface.HmoDao;
import org.calminfotech.hmo.daoInterface.EhmoDao;
import org.calminfotech.hmo.models.Ehmo;
import org.calminfotech.system.boInterface.EHmoPackagesBo;
import org.calminfotech.system.daoInterface.EHmoPackagesDao;
import org.calminfotech.system.forms.EHmoPackagesForm;
import org.calminfotech.system.models.Hmo;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EHmoPackagesBoImpl implements EHmoPackagesBo {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private EHmoPackagesDao organisationHmoPackageDao;

	@Autowired
	private EhmoDao ehmoDao;
	
	@Autowired
	private HmoDao hmoDao;

	@Override
	public List<EhmoPackages> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return this.organisationHmoPackageDao.fetchAllByOrganisation();
	}

	@Override
	public EhmoPackages getPackageById(int id) {
		// TODO Auto-generated method stub
		return this.organisationHmoPackageDao.getPackageById(id);
	}

	@Override
	public EhmoPackages save(EHmoPackagesForm form) {
		// TODO Auto-generated method stub
		Ehmo ehmo = this.ehmoDao.getEhmoById(form.getHmoId());
		EhmoPackages hmoPackage = new EhmoPackages();

		hmoPackage.setName(form.getName());
		hmoPackage.setEhmo(ehmo);
		hmoPackage.setCreatedBy(this.userIdentity.getUsername());
		hmoPackage.setOrganisation(this.userIdentity.getOrganisation());

		this.organisationHmoPackageDao.save(hmoPackage);

		return hmoPackage;
	}

	@Override
	public void delete(EhmoPackages hmoPackage) {
		// TODO Auto-generated method stub
		this.organisationHmoPackageDao.delete(hmoPackage);
	}
	

	@Override
	public void update(EHmoPackagesForm form) {
		// TODO Auto-generated method stub
		EhmoPackages hmoPackage = this.organisationHmoPackageDao
				.getPackageById(form.getId());

		Ehmo ehmo = this.ehmoDao.getEhmoById(form.getHmoId());
		hmoPackage.setName(form.getName());
		hmoPackage.setEhmo(ehmo);
		this.organisationHmoPackageDao.update(hmoPackage);
	}

	@Override
	public List<EhmoPackages> fetchAllByHMO(Integer hmoId) {
		// TODO Auto-generated method stub
		Ehmo hmo = this.ehmoDao.getEhmoById(hmoId);
		return organisationHmoPackageDao.fetchAllByHMO(hmo);
	}

}
