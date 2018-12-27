package org.calminfotech.consultation.bo.impl;

import org.calminfotech.consultation.bo.VisitLaboratoryTestBo;
import org.calminfotech.consultation.dao.VisitLaboratoryTestDao;
import org.calminfotech.consultation.dao.VisitPresentingCompliantDao;
import org.calminfotech.consultation.models.VisitLaboratoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class VisitLaboratoryTestBoImpl implements VisitLaboratoryTestBo{
	
	@Autowired
	private VisitLaboratoryTestDao visitLaboratoryTestDao;

	@Override
	public void save(VisitLaboratoryTest visitLaboratoryTest) {
		// TODO Auto-generated method stub
		this.visitLaboratoryTestDao.save(visitLaboratoryTest);
	}

	@Override
	public void delete(VisitLaboratoryTest visitLaboratoryTest) {
		// TODO Auto-generated method stub
		this.visitLaboratoryTestDao.delete(visitLaboratoryTest);
	}

	@Override
	public VisitLaboratoryTest getVisitLaboratoryTestById(int id) {
		// TODO Auto-generated method stub
		return this.visitLaboratoryTestDao.getVisitLaboratoryTestById(id);
	}

}
