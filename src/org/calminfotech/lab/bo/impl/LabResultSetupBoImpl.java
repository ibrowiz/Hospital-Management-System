package org.calminfotech.lab.bo.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.lab.bo.LabResultSetupBo;
import org.calminfotech.lab.dao.LabResultSetupDao;
import org.calminfotech.lab.dao.LabTestDao;
import org.calminfotech.lab.forms.LabResultSetupForm;
import org.calminfotech.lab.models.LabResultSetup;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class LabResultSetupBoImpl implements LabResultSetupBo {
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private LabResultSetupDao labResultDao;
	
	
	@Autowired
	private LabTestDao labTestDao;

	@Override
	public List<LabResultSetup> fetchAll() {
		return this.labResultDao.fetchAll();
	}

	@Override
	public LabResultSetup getLabResultSetupById(Integer resultId) {
		return labResultDao.getLabResultSetupById(resultId);
	}

	@Override
	public List<LabResultSetup> getLabResultSetupByTestId(Integer TestId) {
		LabTest labTest = labTestDao.getLabtestById(TestId);
		return labResultDao.getLabResultSetupByTestId(labTest);
	}

	@Override
	public LabResultSetup save(LabResultSetupForm labResultSetForm) {
		
LabTest labtest =  this.labTestDao.getLabtestById(labResultSetForm.getTestId());
		
		LabResultSetup labResult = new LabResultSetup();
		labResult.setLabTest(labtest);
		labResult.setResultName(labResultSetForm.getResultName());
		labResult.setType(labResultSetForm.getType());
		labResult.setMinimumValue(labResultSetForm.getMinimumValue());
		labResult.setMaximumValue(labResultSetForm.getMaximumValue());
		labResult.setLabMeasure(labResultSetForm.getLabMeasure());
		labResult.setResultDescription(labResultSetForm.getResultDescription());	
		labResult.setCreatedBy(userIdentity.getUsername());
		labResult.setCreatedDate(new GregorianCalendar().getTime());
		labResultDao.save(labResult);
		return labResult;
	}

	@Override
	public void update(LabResultSetupForm labRSetupForm) {
		
LabTest labtest =  this.labTestDao.getLabtestById(labRSetupForm.getTestId());
		
		LabResultSetup labResultSetup = this.labResultDao.getLabResultSetupById(labRSetupForm.getResultId());
		
		//labResultSetup.setResultId(labRSetupForm.getResultId());
		
		labResultSetup.setLabTest(labtest);
		
		labResultSetup.setResultName(labRSetupForm.getResultName());
		
		labResultSetup.setType(labRSetupForm.getType());	
		
		labResultSetup.setMinimumValue(labRSetupForm.getMinimumValue());
		
		labResultSetup.setMaximumValue(labRSetupForm.getMaximumValue());
		
		labResultSetup.setLabMeasure(labRSetupForm.getLabMeasure());
		
		labResultSetup.setResultDescription(labRSetupForm.getResultDescription());
		
		labResultSetup.setModifiedBy(userIdentity.getUsername());
		
		labResultSetup.setLastModifiedDate(new GregorianCalendar().getTime());
		
		labResultDao.update(labResultSetup);
		
	}

	@Override
	public void delete(LabResultSetup labResult) {
		labResultDao.delete(labResult);
	}

	@Override
	public LabResultSetup getLabRSetupByTestId(Integer testId) {
		LabTest labtest = this.labTestDao.getLabtestById(testId);
		return this.labResultDao.getLabRSetupByTestId(labtest);
	}

	

}
