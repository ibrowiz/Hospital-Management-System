package org.calminfotech.setup.bo.impl;

import java.util.List;

import org.calminfotech.setup.boInterface.HrUnitViewBo;
import org.calminfotech.setup.daoInterface.HrUnitViewDao;
import org.calminfotech.setup.models.HrUnitView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class HrUnitViewBoImpl implements HrUnitViewBo {
	
	@Autowired
	private HrUnitViewDao hrUnitViewDao;

	@Override
	public List<HrUnitView> fetchAll() {
		// TODO Auto-generated method stub
		return this.hrUnitViewDao.fetchAll();
	}

	@Override
	public HrUnitView getUnitViewById(int id) {
		// TODO Auto-generated method stub
		return this.hrUnitViewDao.getUnitViewById(id);
	}

	@Override
	public List<HrUnitView> fetchAllByOrganisation(Integer organisationid) {
		// TODO Auto-generated method stub
		return this.hrUnitViewDao.fetchAllByOrganisation(organisationid);
	}

}
