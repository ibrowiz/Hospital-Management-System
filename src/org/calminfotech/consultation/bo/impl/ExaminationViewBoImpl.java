package org.calminfotech.consultation.bo.impl;

import java.util.List;

import org.calminfotech.consultation.bo.ExaminationViewBo;
import org.calminfotech.consultation.dao.ExaminationCategoryViewDao;
import org.calminfotech.consultation.dao.ExaminationViewDao;
import org.calminfotech.consultation.models.ExaminationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ExaminationViewBoImpl implements ExaminationViewBo {
	
	@Autowired
	private ExaminationViewDao examViewDao;

	@Override
	public ExaminationView getExamViewById(int id) {
		return this.examViewDao.getExamViewById(id);
	}

	@Override
	public List<ExaminationView> fetchAllByOrganisation(int organisationid) {
		return this.examViewDao.fetchAllByOrganisation(organisationid);
	}

}
