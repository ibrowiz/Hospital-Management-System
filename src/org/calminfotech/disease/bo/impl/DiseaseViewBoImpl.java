package org.calminfotech.disease.bo.impl;

import java.util.List;

import org.calminfotech.disease.boInterface.DiseaseViewBo;
import org.calminfotech.disease.daoInterface.DiseaseViewDao;
import org.calminfotech.disease.models.DiseaseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseViewBoImpl implements DiseaseViewBo {
	
	@Autowired
	private DiseaseViewDao diseaseViewDao;


	@Override
	public DiseaseView getDiseaseViewById(int id) {
		return this.diseaseViewDao.getDiseaseViewById(id);
	}

	@Override
	public List<DiseaseView> fetchAllByOrganisation(int organisationid) {
		return this.diseaseViewDao.fetchAllByOrganisation(organisationid);
	}

}
