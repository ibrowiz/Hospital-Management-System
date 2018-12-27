package org.calminfotech.consultation.bo.impl;

import org.calminfotech.consultation.bo.VisitPresentingCompliantBo;
import org.calminfotech.consultation.dao.VisitPresentingCompliantDao;
import org.calminfotech.consultation.models.VisitPresentingComplaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class VisitPresentingCompliantBoImpl implements VisitPresentingCompliantBo{
	
	@Autowired
	private VisitPresentingCompliantDao visitPresentingCompliantDao;

	@Override
	public void save(VisitPresentingComplaint visitPresentingComplaint) {
		// TODO Auto-generated method stub
		this.visitPresentingCompliantDao.save(visitPresentingComplaint);
	}

	@Override
	public void delete(VisitPresentingComplaint visitPresentingComplaint) {
		// TODO Auto-generated method stub
		this.visitPresentingCompliantDao.delete(visitPresentingComplaint);
	}

	@Override
	public VisitPresentingComplaint getVisitPresentingComplaintyById(int id) {
		// TODO Auto-generated method stub
		return this.visitPresentingCompliantDao.getVisitPresentingComplaintById(id);
	}

}
