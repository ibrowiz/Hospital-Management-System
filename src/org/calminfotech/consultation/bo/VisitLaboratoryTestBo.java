package org.calminfotech.consultation.bo;

import org.calminfotech.consultation.models.VisitLaboratoryTest;


public interface VisitLaboratoryTestBo {
	
	public void save(VisitLaboratoryTest visitLaboratoryTest);

	public void delete(VisitLaboratoryTest visitLaboratoryTest);

	public VisitLaboratoryTest getVisitLaboratoryTestById(int id);

}
