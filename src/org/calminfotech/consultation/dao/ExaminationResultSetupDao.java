package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.Examination;
import org.calminfotech.consultation.models.ExaminationResultSetup;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface ExaminationResultSetupDao {
	
	public List<ExaminationResultSetup> fetchAllByOrganisationId(Integer organisationId);	

	public ExaminationResultSetup getExamResultSetupById(Integer id);
	
	public ExaminationResultSetup getExamResultSetupByRowId(Integer rowId);
	
	
	
	public List<ExaminationResultSetup> getExamResultSetupByExamId(Examination exam);
	
	public void save(ExaminationResultSetup examResultSetup);
	
	public void update(ExaminationResultSetup examResultSetup);
	
	public void delete(ExaminationResultSetup examResultSetup);

}
