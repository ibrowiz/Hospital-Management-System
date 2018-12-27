package org.calminfotech.lab.bo.impl;

import java.util.List;

import org.calminfotech.lab.bo.LabDeleteResultBo;
import org.calminfotech.lab.dao.LabDeleteResultDao;
import org.calminfotech.lab.models.LabDeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LabDeleteResultBoImpl implements LabDeleteResultBo {
	
	@Autowired
	private LabDeleteResultDao deleteResultDao;

	@Override
	public List<LabDeleteResult> deleteResult(Integer testId) {
		return this.deleteResultDao.deleteResult(testId);
	}

}
