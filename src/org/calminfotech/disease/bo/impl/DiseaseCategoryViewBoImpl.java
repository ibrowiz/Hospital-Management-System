package org.calminfotech.disease.bo.impl;

import java.util.List;

import org.calminfotech.disease.boInterface.DiseaseCategoryViewBo;
import org.calminfotech.disease.daoInterface.DiseaseCategoryViewDao;
import org.calminfotech.disease.models.DiseaseCategoryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DiseaseCategoryViewBoImpl implements DiseaseCategoryViewBo{
	
	@Autowired
	private DiseaseCategoryViewDao diseaseCategoryViewDao;

	@Override
	public DiseaseCategoryView getDiseaseCatViewById(int id) {
		// TODO Auto-generated method stub
		return this.diseaseCategoryViewDao.getDiseaseCatViewById(id);
	}

	@Override
	public List<DiseaseCategoryView> fetchAllByOrganisation(int organisationid) {
		// TODO Auto-generated method stub
		return this.diseaseCategoryViewDao.fetchAllByOrganisation(organisationid);
	}

}
