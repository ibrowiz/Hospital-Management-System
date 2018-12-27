package org.calminfotech.admin.bo.impl;

import java.util.List;

import org.calminfotech.admin.boInterface.HmoBo;
import org.calminfotech.admin.daoInterface.HmoDao;
import org.calminfotech.admin.forms.DataTableForm;
import org.calminfotech.system.forms.HmoForm;
import org.calminfotech.system.models.Hmo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.BankList;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.StatesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HmoBoImpl implements HmoBo {

	@Autowired
	private HmoDao hmoDao;

	@Autowired
	private StatesList stateList;

	@Autowired
	private LocalGovernmentAreaList lgaList;

	@Autowired
	private BankList bankList;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<Hmo> fetchAll() {
		return this.hmoDao.fetchAll();
	}

	@Override
	public Hmo getHmoById(int id) {
		return this.hmoDao.getHmoById(id);
	}

	@Override
	public Hmo save(HmoForm hmoForm) {
		Hmo hmo = new Hmo();
		hmo.setName(hmoForm.getName());
		hmo.setAddress(hmoForm.getAddress());
		hmo.setEmail(hmoForm.getEmail());

		hmo.setLga(this.lgaList.getLgaById(hmoForm.getLgaId()));
		hmo.setState(this.stateList.getStateById(hmoForm.getStateId()));
		hmo.setBank(this.bankList.getBankById(hmoForm.getBankId()));

		hmo.setBankAccount(hmoForm.getBankAccount());

		hmo.setPhoneNumber(hmoForm.getPhoneNumber());
		hmo.setPostalNumber(hmoForm.getPostalNumber());

		hmo.setAdminName(hmoForm.getAdminName());
		hmo.setAdminEmail(hmoForm.getAdminEmail());
		hmo.setAdminPhone(hmoForm.getAdminPhone());
		hmo.setCreatedBy(userIdentity.getUsername());

		this.hmoDao.save(hmo);
		return hmo;
	}

	@Override
	public void delete(Hmo hmo) {
		this.hmoDao.delete(hmo);
	}

	@Override
	public void update(HmoForm hmoForm) {
		Hmo hmo = this.hmoDao.getHmoById(hmoForm.getId());
		hmo.setName(hmoForm.getName());
		hmo.setAddress(hmoForm.getAddress());
		hmo.setEmail(hmoForm.getEmail());

		hmo.setLga(this.lgaList.getLgaById(hmoForm.getLgaId()));
		hmo.setState(this.stateList.getStateById(hmoForm.getStateId()));
		hmo.setBank(this.bankList.getBankById(hmoForm.getBankId()));

		hmo.setBankAccount(hmoForm.getBankAccount());

		hmo.setPhoneNumber(hmoForm.getPhoneNumber());
		hmo.setPostalNumber(hmoForm.getPostalNumber());

		hmo.setAdminName(hmoForm.getAdminName());
		hmo.setAdminEmail(hmoForm.getAdminEmail());
		hmo.setAdminPhone(hmoForm.getAdminPhone());
		hmo.setCreatedBy(userIdentity.getUsername());

		this.hmoDao.update(hmo);
	}

	@Override
	public String dataTableQuery(DataTableForm dataTableForm) {
		return this.hmoDao.dataTableQuery(dataTableForm);
	}

}
