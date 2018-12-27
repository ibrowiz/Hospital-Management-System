package org.calminfotech.consultation.bo.impl;

import java.util.List;

import org.calminfotech.consultation.bo.ExaminationCategoryViewBo;
import org.calminfotech.consultation.dao.ExaminationCategoryViewDao;
import org.calminfotech.consultation.models.ExaminationCategoryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ExaminationCategoryViewBoImpl implements ExaminationCategoryViewBo {
	
	@Autowired
	private ExaminationCategoryViewDao examViewDao;

	@Override
	public ExaminationCategoryView getExamViewById(int id) {
		return this.examViewDao.getExamViewById(id);
	}

	@Override
	public List<ExaminationCategoryView> fetchAllByOrganisation(int organisationid) {
		return this.examViewDao.fetchAllByOrganisation(organisationid);
	}

}
