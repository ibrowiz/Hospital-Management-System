package org.calminfotech.lab.bo.impl;

import java.util.List;

import org.calminfotech.lab.bo.GetLaboratoryTestProcBo;
import org.calminfotech.lab.dao.GetLaboratoryTestProcDao;
import org.calminfotech.lab.models.GetLaboratoryTestProc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class GetLaboratoryTestProcBoImpl implements GetLaboratoryTestProcBo {
	@Autowired
	private GetLaboratoryTestProcDao laboratoryTestProcDao;
	
	public List<GetLaboratoryTestProc> fetchResult(Integer testId) {
		// TODO Auto-generated method stub
		return this.laboratoryTestProcDao.fetchResult(testId);
	
}
}
