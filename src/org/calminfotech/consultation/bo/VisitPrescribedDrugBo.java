package org.calminfotech.consultation.bo;


import org.calminfotech.consultation.models.VisitPrescribedDrug;

public interface VisitPrescribedDrugBo {
	
	public void save(VisitPrescribedDrug visitPrescribedDrug);

	public void delete(VisitPrescribedDrug visitPrescribedDrug);

	public VisitPrescribedDrug getVisitPrescribedDrugById(int id);


}
