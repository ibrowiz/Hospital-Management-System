package org.calminfotech.system.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import org.calminfotech.admin.daoInterface.DrugDao;
import org.calminfotech.system.boInterface.HmoPackageItemBo;
import org.calminfotech.system.daoInterface.HmoPackageItemDao;
import org.calminfotech.system.daoInterface.EHmoPackagesDao;
import org.calminfotech.system.forms.HmoPackageItemForm;
import org.calminfotech.system.models.Drug;
import org.calminfotech.system.models.HmoPackageDrug;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HmoPackageItemBoImpl implements HmoPackageItemBo {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private HmoPackageItemDao hmoPackageItemDao;

	@Autowired
	private DrugDao drugBo;

	@Autowired
	private EHmoPackagesDao hmoPackageDao;

	@Override
	public List<HmoPackageDrug> fetchAll() {
		// TODO Auto-generated method stub
		return this.hmoPackageItemDao.fetchAll();
	}

	@Override
	public List<HmoPackageDrug> fetchAll(EhmoPackages oHmoPackage) {
		// TODO Auto-generated method stub
		return this.hmoPackageItemDao.fetchAll(oHmoPackage);
	}

	@Override
	public List<HmoPackageDrug> fetchAll(Drug drug) {
		// TODO Auto-generated method stub
		return this.hmoPackageItemDao.fetchAll(drug);
	}

	@Override
	public void save(HmoPackageItemForm form) {
		// TODO Auto-generated method stub
		HmoPackageDrug packageDrug = new HmoPackageDrug();

		Drug drug = this.drugBo.getDrugById(form.getItemId());
		packageDrug.setDrug(drug);

		EhmoPackages hmoPackage = this.hmoPackageDao
				.getPackageById(form.getPackageId());
		packageDrug.setHmoPackage(hmoPackage);

		packageDrug.setPrice(new BigDecimal(Float.parseFloat(form.getPrice())));
		packageDrug.setCreatedBy(this.userIdentity.getUsername());

		this.hmoPackageItemDao.save(packageDrug);
	}

	@Override
	public void delete(HmoPackageDrug packageDrug) {
		// TODO Auto-generated method stub
		this.hmoPackageItemDao.delete(packageDrug);
	}

	@Override
	public void update(HmoPackageItemForm form) {
		// TODO Auto-generated method stub
		HmoPackageDrug packageDrug = this.hmoPackageItemDao.getItem(
				form.getItemId(), form.getPackageId());

		packageDrug.setPrice(new BigDecimal(Float.parseFloat(form.getPrice())));

		this.hmoPackageItemDao.update(packageDrug);
	}

	@Override
	public HmoPackageDrug getItem(Integer itemId, Integer packageId) {
		// TODO Auto-generated method stub
		return this.hmoPackageItemDao.getItem(itemId, packageId);
	}

}
