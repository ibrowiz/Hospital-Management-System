package org.calminfotech.consultation.bo.impl;

import org.calminfotech.consultation.bo.VisitPrescribedDrugBo;
import org.calminfotech.consultation.dao.VisitLaboratoryTestDao;
import org.calminfotech.consultation.dao.VisitPrescribedDrugDao;
import org.calminfotech.consultation.models.VisitPrescribedDrug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class VisitPrescribedDrugBoImpl implements  VisitPrescribedDrugBo{
	
	
	
	@Autowired
	private VisitPrescribedDrugDao visitPrescribedDrugDao;

	@Override
	public void save(VisitPrescribedDrug visitPrescribedDrug) {
		// TODO Auto-generated method stub
		this.visitPrescribedDrugDao.save(visitPrescribedDrug);
	}

	@Override
	public void delete(VisitPrescribedDrug visitPrescribedDrug) {
		// TODO Auto-generated method stub
		this.visitPrescribedDrugDao.delete(visitPrescribedDrug);
	}

	@Override
	public VisitPrescribedDrug getVisitPrescribedDrugById(int id) {
		// TODO Auto-generated method stub
		return this.visitPrescribedDrugDao.getVisitPrescribedDrugById(id);
	}

}
