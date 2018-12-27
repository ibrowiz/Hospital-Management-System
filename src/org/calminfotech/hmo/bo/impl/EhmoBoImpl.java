package org.calminfotech.hmo.bo.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.hmo.boInterface.EhmoBo;
import org.calminfotech.hmo.daoInterface.EhmoDao;
import org.calminfotech.hmo.forms.EhmoForm;
import org.calminfotech.hmo.models.Ehmo;
import org.calminfotech.admin.forms.DataTableForm;


import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EhmoBoImpl implements EhmoBo {

	@Autowired
	private EhmoDao ehmoDao;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<Ehmo> fetchAll() {
		return this.ehmoDao.fetchAll();
	}

	@Override
	public List<Ehmo> fetchAllByOrganisation(int organisationId) {
		return this.ehmoDao.fetchAllByOrganisation(organisationId);
	}
	
	@Override
	public List<Ehmo> fetchAllByOrganisationEdit(int organisationId) {
		return this.ehmoDao.fetchAllByOrganisation(organisationId);
	}
	
	
	@Override
	public Ehmo getEhmoById(int id) {
		return this.ehmoDao.getEhmoById(id);
	}

	@Override
	public Ehmo save(EhmoForm ehmoForm) {
		Ehmo ehmo = new Ehmo();
		ehmo.setHmoId(ehmoForm.getHmoId());
		ehmo.setName(ehmoForm.getName());
		ehmo.setAddress(ehmoForm.getAddress());
		ehmo.setPhoneNumber(ehmoForm.getPhoneNumber());
		ehmo.setAdminName(ehmoForm.getAdminName());
		ehmo.setAdminEmail(ehmoForm.getAdminEmail());
		ehmo.setAdminPhone(ehmoForm.getAdminPhone());
		//ehmo.setOrganisationId(ehmoForm.getOrganisationId());
		ehmo.setCreatedBy(userIdentity.getUsername());
		ehmo.setOrganisationId(userIdentity.getOrganisation().getId());
		ehmo.setCreatedDate(new GregorianCalendar().getTime());
		ehmo.setStatus("Active");
		
		this.ehmoDao.save(ehmo);
		return ehmo;
	}

	@Override
	public void delete(Ehmo ehmo) {
		this.ehmoDao.delete(ehmo);
	}

	@Override
	public void update(EhmoForm ehmoForm) {
		Ehmo ehmo = this.ehmoDao.getEhmoById(ehmoForm.getHmoId());
		ehmo.setName(ehmoForm.getName());
		ehmo.setAddress(ehmoForm.getAddress());
		ehmo.setPhoneNumber(ehmoForm.getPhoneNumber());

		ehmo.setAdminName(ehmoForm.getAdminName());
		ehmo.setAdminEmail(ehmoForm.getAdminEmail());
		ehmo.setAdminPhone(ehmoForm.getAdminPhone());
	//	ehmo.setOrganisationId(userIdentity.getOrganisation().getId());
		ehmo.setModifiedBy(userIdentity.getUsername());
		ehmo.setModifiedDate(new GregorianCalendar().getTime());

		
		this.ehmoDao.update(ehmo);
	}
	
	
	
	
}
