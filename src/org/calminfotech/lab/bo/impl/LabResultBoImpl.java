package org.calminfotech.lab.bo.impl;

import java.util.List;

import org.calminfotech.lab.bo.LabResultBo;
import org.calminfotech.lab.dao.LabResultDao;
import org.calminfotech.lab.models.LabDeleteResult;
import org.calminfotech.lab.models.LabResult;
import org.calminfotech.lab.models.LabTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LabResultBoImpl implements LabResultBo {
	
	@Autowired
	private LabResultDao laboratoryResultDao;

	@Override
	public LabResult getResultByTestId(LabTest labTest) {
		return this.laboratoryResultDao.getResultByTestId(labTest);
	}

	@Override
	public LabResult getResultById(int id) {
		return this.laboratoryResultDao.getResultById(id);
	}

	@Override
	public void save(LabResult laboratoryResult) {
		this.laboratoryResultDao.save(laboratoryResult);
	}

	@Override
	public List<LabResult> allLabResultById(Integer id) {
		return this.laboratoryResultDao.allLabResultById(id);
	}

	@Override
	public List<LabResult> allLabResultByTestId(LabTest labTest) {
		return this.laboratoryResultDao.allLabResultByTestId(labTest);
	}

	@Override
	public void deleteByTestId(LabDeleteResult labDelResult) {
		this.laboratoryResultDao.deleteByTestId(labDelResult);
	}

	

}
