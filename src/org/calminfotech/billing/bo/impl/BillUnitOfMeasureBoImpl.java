package org.calminfotech.billing.bo.impl;

import java.util.GregorianCalendar;
import java.util.List;


import org.calminfotech.billing.boInterface.BillSchemeBo;
import org.calminfotech.billing.boInterface.BillUnitOfMeasureBo;
import org.calminfotech.billing.daoInterface.BillSchemeDao;
import org.calminfotech.billing.daoInterface.BillUnitOfMeasureDao;
import org.calminfotech.billing.forms.BillSchemeForm;
import org.calminfotech.billing.forms.BillUnitOfMeasureForm;
import org.calminfotech.billing.models.BillScheme;
import org.calminfotech.billing.models.BillUnitOfMeasure;
import org.calminfotech.hmo.forms.EhmoForm;
import org.calminfotech.hmo.models.Ehmo;

import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BillUnitOfMeasureBoImpl implements BillUnitOfMeasureBo{

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private BillUnitOfMeasureDao billUnitOfMeasureDao;

	@Override
	public List<BillUnitOfMeasure> fetchAll() {
		return this.billUnitOfMeasureDao.fetchAll();
	}

	@Override
	public List<BillUnitOfMeasure> fetchAllByOrganisation(int organisationId) {
		return this.billUnitOfMeasureDao.fetchAllByOrganisation(organisationId);
	}
	
	/*@Override
	public List<Ehmo> fetchAllByOrganisationEdit(int organisationId) {
		return this.ehmoDao.fetchAllByOrganisation(organisationId);
	}*/
	
	
	@Override
	public BillUnitOfMeasure getBillUnitOfMeasureById(int id) {
		return this.billUnitOfMeasureDao.getBillUnitOfMeasureById(id);
	}

	@Override
	public void save(BillUnitOfMeasure billUnitOfMeasure) {
		/*BillUnitOfMeasureForm billUnitOfMeasureForm = new BillUnitOfMeasureForm();
		billSchemeForm.setName(billScheme.getName());
		billSchemeForm.setBillId(billScheme.getBillId());
		billSchemeForm.setBillingType(billScheme.getBillingType());
		billSchemeForm.setDescription(billScheme.getDescription());
		billSchemeForm.setName(billScheme.getName());
		billSchemeForm.setOrganisationId(userIdentity.getOrganisation().getId());*/
		this.billUnitOfMeasureDao.save(billUnitOfMeasure);
	}
	@Override
	public void delete(BillUnitOfMeasure billUnitOfMeasure) {
		this.billUnitOfMeasureDao.delete(billUnitOfMeasure);
	}
	@Override
	public void update(BillUnitOfMeasure billUnitOfMeasure) {
		/*BillScheme bill = this.billSchemeDao.getBillSchemeById(billScheme.getBillId());
		bill.setName(billScheme.getName());
		bill.setDescription(billScheme.getDescription());
		bill.setBillingType(billScheme.getBillingType());
		billScheme.setModifiedBy(userIdentity.getUsername());
		billScheme.setModifiedDate(new GregorianCalendar().getTime());*/
       this.billUnitOfMeasureDao.update(billUnitOfMeasure);
	}
}