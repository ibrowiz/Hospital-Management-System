package org.calminfotech.consultation.dao;

import java.util.List;

import org.calminfotech.consultation.models.ExaminationList;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface ExaminationListDao {
	
public List<ExaminationList> fetchAll();	
	
	public List<ExaminationList> fetchAllByOrganisation(Integer organisationId);

}
