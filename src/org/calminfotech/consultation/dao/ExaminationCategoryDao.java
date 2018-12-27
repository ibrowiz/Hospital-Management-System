package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.ExaminationCategory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface ExaminationCategoryDao {
	
	public List<ExaminationCategory> fetchAllByOrgainsation(Integer organisation);

	public ExaminationCategory getExaminationById(Integer examId);
	
	public void save(ExaminationCategory examination);
	
	public void update(ExaminationCategory examination);
	
	public void delete(ExaminationCategory examination);
	
	

}
