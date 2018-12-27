package org.calminfotech.lab.bo.impl;

import java.util.List;

import org.calminfotech.lab.bo.LabTestListBo;
import org.calminfotech.lab.dao.LabTestListDao;
import org.calminfotech.lab.models.LabTestList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class LabTestListBoImpl implements LabTestListBo{
	
	@Autowired
	private LabTestListDao labTestListDao;

	@Override
	public List<LabTestList> fetchAllByOrganisation(Integer organisationId) {
		return this.labTestListDao.fetchAllByOrganisation(organisationId);
	}

}
