package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.forms.ExaminationForm;
import org.calminfotech.consultation.models.Examination;


public interface ExaminationBo {
	
	public List<Examination> fetchAllByOrgainsation(Integer organisationId);

	public Examination getExaminationById(Integer examId);
	
	public Examination save(ExaminationForm examinationForm);
	
	public void update(ExaminationForm examinationForm);
	
	public void delete(Examination examination);

}
