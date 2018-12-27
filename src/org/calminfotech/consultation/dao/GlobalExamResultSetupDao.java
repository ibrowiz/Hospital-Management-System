package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.Examination;
import org.calminfotech.consultation.models.ExaminationResultSetup;
import org.calminfotech.consultation.models.GlobalExaminationResultSetup;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface GlobalExamResultSetupDao {
	
	public List<GlobalExaminationResultSetup> fetchAllByOrganisationId(Integer organisationId);	

	public GlobalExaminationResultSetup getExamResultSetupById(Integer id);
	
	public void save(GlobalExaminationResultSetup examResultSetup);
	
	public void update(GlobalExaminationResultSetup examResultSetup);
	
	public void delete(GlobalExaminationResultSetup examResultSetup);

}
