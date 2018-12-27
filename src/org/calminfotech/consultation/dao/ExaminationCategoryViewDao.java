package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.ExaminationCategoryView;


public interface ExaminationCategoryViewDao {
	
public ExaminationCategoryView getExamViewById(int id);
	
	public List<ExaminationCategoryView> fetchAllByOrganisation(int organisationid);

}
