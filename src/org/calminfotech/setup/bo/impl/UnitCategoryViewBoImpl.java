package org.calminfotech.setup.bo.impl;

import java.util.List;

import org.calminfotech.setup.boInterface.UnitCategoryViewBo;
import org.calminfotech.setup.daoInterface.AllergyCategoryViewDao;
import org.calminfotech.setup.daoInterface.UnitCategoryViewDao;
import org.calminfotech.setup.models.UnitCategoryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class UnitCategoryViewBoImpl implements UnitCategoryViewBo {
	
	@Autowired
	private UnitCategoryViewDao unitCatViewDao;

	@Override
	public List<UnitCategoryView> fetchAll() {
		return this.unitCatViewDao.fetchAll();
	}

	@Override
	public UnitCategoryView getUnitCategoryViewById(int id) {
		return this.unitCatViewDao.getUnitCategoryViewById(id);
	}

	@Override
	public List<UnitCategoryView> fetchAllByOrganisation(int organisationid) {
		return this.unitCatViewDao.fetchAllByOrganisation(organisationid);
	}

}
