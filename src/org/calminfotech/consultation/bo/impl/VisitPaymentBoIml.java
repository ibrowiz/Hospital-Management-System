package org.calminfotech.consultation.bo.impl;

import java.util.List;

import org.calminfotech.consultation.bo.VisitPaymentBo;
import org.calminfotech.consultation.dao.VisitPaymentDao;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitPayment;
import org.calminfotech.patient.daoInterface.PatientEmergencyDao;
import org.calminfotech.utils.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class VisitPaymentBoIml implements  VisitPaymentBo{

	
	
	
	
	@Autowired
	private VisitPaymentDao visitPaymentDao;
	


	@Override
	public VisitPayment getVisitPaymentById(int id) {
		// TODO Auto-generated method stub
		return this.visitPaymentDao.getVisitPaymentById(id);
	}

	@Override
	public void save(VisitPayment visitPayment) {
		// TODO Auto-generated method stub
		this.visitPaymentDao.save(visitPayment);
	}

	@Override
	public void delete(VisitPayment visitPayment) {
		// TODO Auto-generated method stub
		this.visitPaymentDao.delete(visitPayment);
	}

	@Override
	public void update(VisitPayment visitPayment) {
		// TODO Auto-generated method stub
		this.visitPaymentDao.update(visitPayment);
	}

}
