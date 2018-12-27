package org.calminfotech.lab.bo.impl;

import java.util.List;


import org.calminfotech.lab.bo.LabTestDocumentBo;
import org.calminfotech.lab.dao.LabTestDocumentDao;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.lab.models.LabTestDocument;
import org.calminfotech.utils.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class LabTestDocumentBoImpl implements LabTestDocumentBo {

	@Autowired
	private LabTestDocumentDao labTestDocDao;
	
	@Override
	public List<LabTestDocument> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabTestDocument> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabTestDocument> fetchAllByTest(LabTest LabTest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(LabTestDocument labDocument) {
		this.labTestDocDao.save(labDocument);
	}

	@Override
	public LabTestDocument getLabDocumentById(int id) {
		return this.labTestDocDao.getLabDocumentById(id);
	}

	@Override
	public void delete(LabTestDocument labDocument) {
		this.labTestDocDao.delete(labDocument);
	}

}
