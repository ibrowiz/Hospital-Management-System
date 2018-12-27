package org.calminfotech.setup.bo.impl;

import java.util.List;

import org.calminfotech.setup.boInterface.AllergyCategoryViewBo;
import org.calminfotech.setup.daoInterface.AllergyCategoryViewDao;
import org.calminfotech.setup.models.AllergyCategoryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class AllergyCategoryViewBoImpl implements AllergyCategoryViewBo {
	
	@Autowired
	private AllergyCategoryViewDao allergyCatViewDao;

	@Override
	public List<AllergyCategoryView> fetchAll() {
		return allergyCatViewDao.fetchAll();
	}

	@Override
	public AllergyCategoryView getAllergyCatViewById(int id) {
		return allergyCatViewDao.getAllergyCatViewById(id);
	}

	@Override
	public List<AllergyCategoryView> fetchAllByOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return this.allergyCatViewDao.fetchAllByOrganisation(organisationid);
	}

}
