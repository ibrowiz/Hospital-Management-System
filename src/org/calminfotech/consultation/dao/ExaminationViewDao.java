package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.ExaminationView;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface ExaminationViewDao {
	
public ExaminationView getExamViewById(int id);
	
	public List<ExaminationView> fetchAllByOrganisation(int organisationid);

}
