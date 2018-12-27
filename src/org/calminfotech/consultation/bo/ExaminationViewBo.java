package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.models.ExaminationView;

public interface ExaminationViewBo {
	
public ExaminationView getExamViewById(int id);
	
	public List<ExaminationView> fetchAllByOrganisation(int organisationid);

}
