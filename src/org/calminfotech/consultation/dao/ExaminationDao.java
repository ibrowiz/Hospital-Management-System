package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.Examination;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface ExaminationDao {
	public List<Examination> fetchAllByOrgainsation(Integer organisationId);

	public Examination getExaminationById(Integer examId);
	
	public void save(Examination examination);
	
	public void update(Examination examination);
	
	public void delete(Examination examination);
}
