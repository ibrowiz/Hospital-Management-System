package org.calminfotech.setup.bo.impl;

import java.util.List;

import org.calminfotech.setup.boInterface.AllergyViewBo;
import org.calminfotech.setup.daoInterface.AllergyViewDao;
import org.calminfotech.setup.models.AllergyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class AllergyViewBoImpl implements AllergyViewBo{

	@Autowired
	private AllergyViewDao allergyViewDao;
	
	@Override
	public List<AllergyView> fetchAll() {
		return allergyViewDao.fetchAll();
	}

	@Override
	public AllergyView getAllergyViewById(int id) {
		return allergyViewDao.getAllergyViewById(id);
	}

	@Override
	public List<AllergyView> fetchAllByOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return this.allergyViewDao.fetchAllByOrganisation(organisationid);
	}

}
