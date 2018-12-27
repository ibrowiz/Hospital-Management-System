package org.calminfotech.consultation.bo;

import java.util.List;

import org.calminfotech.consultation.forms.GlobalExamResultSetupForm;
import org.calminfotech.consultation.models.GlobalExaminationResultSetup;

public interface GlobalExamResultSetupBo {
	
	public List<GlobalExaminationResultSetup> fetchAllByOrganisationId(Integer organisationId);	

	public GlobalExaminationResultSetup getExamResultSetupById(Integer id);
	
	public GlobalExaminationResultSetup save(GlobalExamResultSetupForm globalExamResultSetupForm);
	
	public void update(GlobalExamResultSetupForm globalExamResultSetupForm);
	
	public void delete(GlobalExaminationResultSetup examResultSetup);

}
