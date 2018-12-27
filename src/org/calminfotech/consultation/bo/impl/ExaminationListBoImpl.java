package org.calminfotech.consultation.bo.impl;

import java.util.List;

import org.calminfotech.consultation.bo.ExaminationListBo;
import org.calminfotech.consultation.dao.ExaminationListDao;
import org.calminfotech.consultation.models.ExaminationList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ExaminationListBoImpl implements ExaminationListBo {
	
	@Autowired
	private ExaminationListDao examinationListDao;

	@Override
	public List<ExaminationList> fetchAll() {
		return this.examinationListDao.fetchAll();
	}

	@Override
	public List<ExaminationList> fetchAllByOrganisation(Integer organisationId) {
		return this.examinationListDao.fetchAllByOrganisation(organisationId);
	}

}
