package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.forms.ExaminationCategoryForm;
import org.calminfotech.consultation.models.ExaminationCategory;


public interface ExaminationCategoryBo {
	
	
	public List<ExaminationCategory> fetchAllByOrgainsation(Integer organisationId);

	public ExaminationCategory getExaminationById(Integer examId);
	
	public ExaminationCategory save(ExaminationCategoryForm examinationForm);
	
	public void update(ExaminationCategoryForm examinationForm);
	
	public void delete(ExaminationCategory examination);
	

}
