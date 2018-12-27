package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.models.ExaminationCategoryView;

public interface ExaminationCategoryViewBo {
	
public ExaminationCategoryView getExamViewById(int id);
	
	public List<ExaminationCategoryView> fetchAllByOrganisation(int organisationid);

}
