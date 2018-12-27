package org.calminfotech.system.bo.impl;

import java.util.List;


import org.calminfotech.system.boInterface.BillingSchemeBo;

import org.calminfotech.system.daoInterface.BillingSchemeDao;
import org.calminfotech.system.forms.BillingSchemeForm;
import org.calminfotech.system.models.BillingScheme;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BillingSchemeBoImpl implements BillingSchemeBo{

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private BillingSchemeDao billingSchemeDao;

	@Override
	public List<BillingScheme> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
	
		return this.billingSchemeDao.fetchAllByOrganisation(userIdentity
				.getOrganisation());
	}

	@Override
	public BillingScheme getBillingSchemeById(int id) {
		// TODO Auto-generated method stub
		return billingSchemeDao.getBillingSchemeById(id);
	}

	@Override
	public void save(BillingSchemeForm billingSchemeform) {
		// TODO Auto-generated method stub
		BillingScheme billingScheme = new BillingScheme();
		billingScheme.setName(billingSchemeform.getName());
		billingScheme.setDescription(billingSchemeform.getDescription());
		billingScheme.setCreated_by(userIdentity.getUsername());
		billingScheme.setOrganisation(userIdentity.getOrganisation());
		billingSchemeDao.save(billingScheme);
		
	}

	@Override
	public void delete(BillingScheme billingScheme) {
		// TODO Auto-generated method stub
		billingSchemeDao.delete(billingScheme);
	}

	@Override
	public void update(BillingSchemeForm billingSchemeform) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BillingScheme> fetchAll() {
		// TODO Auto-generated method stub
		return billingSchemeDao.fetchAll();
	}

	@Override
	public List<BillingScheme> fetchAllByType() {
		return this.billingSchemeDao.fetchAllByType(userIdentity.getOrganisation());
}
}