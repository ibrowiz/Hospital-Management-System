package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.forms.ExaminationResultSetupForm;

import org.calminfotech.consultation.models.ExaminationResultSetup;

public interface ExaminationResultSetupBo {
	
	public List<ExaminationResultSetup> fetchAllByOrganisationId(Integer organisationId);	

	public ExaminationResultSetup getExamResultSetupById(Integer id);
	
	public ExaminationResultSetup getExamResultSetupByRowId(Integer rowId);
	
	public List<ExaminationResultSetup> getExamResultSetupByExamId(Integer examId);
	
	public ExaminationResultSetup  save(ExaminationResultSetupForm examResultSetupForm);
	
	public void update(ExaminationResultSetupForm examResultSetupForm);
	
	public void delete(ExaminationResultSetup examResultSetup);

}
