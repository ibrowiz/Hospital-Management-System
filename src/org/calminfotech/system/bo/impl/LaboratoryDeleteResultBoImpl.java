package org.calminfotech.system.bo.impl;

import java.util.List;

import org.calminfotech.system.boInterface.LaboratoryDeleteResultBo;
import org.calminfotech.system.daoInterface.LaboratoryDeleteResultDao;
import org.calminfotech.system.daoInterface.RoleAssgnmentDao;
import org.calminfotech.system.models.LaboratoryDeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LaboratoryDeleteResultBoImpl implements LaboratoryDeleteResultBo {
	
	@Autowired
	private LaboratoryDeleteResultDao deleteResultDao;

	@Override
	public List<LaboratoryDeleteResult> deleteResult(Integer testId) {
		return this.deleteResultDao.deleteResult(testId);
	}

}
