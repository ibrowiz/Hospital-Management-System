package org.calminfotech.disease.bo.impl;

import java.util.List;

import org.calminfotech.disease.boInterface.DiseaseCategoryListBo;
import org.calminfotech.disease.daoInterface.DiseaseCategoryListDao;
import org.calminfotech.disease.models.DiseaseCategoryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DiseaseCategoryListBoImpl implements DiseaseCategoryListBo {
	
	@Autowired
	private DiseaseCategoryListDao diseaseCategoryListDao;

	@Override
	public List<DiseaseCategoryList> fetchAllByOrganisation(
			Integer organisationId) {
		return this.diseaseCategoryListDao.fetchAllByOrganisation(organisationId);
	}

}
