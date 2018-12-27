package org.calminfotech.setup.bo.impl;

import java.util.List;

import org.calminfotech.setup.boInterface.UnitListBo;
import org.calminfotech.setup.daoInterface.CategoryListDao;
import org.calminfotech.setup.daoInterface.UnitListDao;
import org.calminfotech.setup.models.UnitList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UnitListBoImpl implements UnitListBo {
	
	@Autowired
	private UnitListDao unitListDao;

	@Override
	public List<UnitList> fetchAllByOrganisationId(Integer organisationId) {
		return this.unitListDao.fetchAllByOrganisationId(organisationId);
	}

	@Override
	public UnitList getUnitListById(int id) {
		return this.getUnitListById(id);
	}

}
