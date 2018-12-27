package org.calminfotech.consultation.bo.impl;

import java.util.List;

import org.calminfotech.consultation.bo.LaboratoryTestDocumentBo;
import org.calminfotech.consultation.dao.LaboratoryTestDocumentDaoInter;
import org.calminfotech.consultation.models.LaboratoryTestDocument;
import org.calminfotech.lab.models.LabTest;
import org.calminfotech.utils.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class LaboratoryTestDocumentBoImpl implements LaboratoryTestDocumentBo {

	@Autowired
	private LaboratoryTestDocumentDaoInter labTestDocDao;
	
	@Override
	public List<LaboratoryTestDocument> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LaboratoryTestDocument> fetchAllByOrgainsation(
			Organisation organisation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LaboratoryTestDocument> fetchAllByTest(LabTest LabTest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(LaboratoryTestDocument labDocument) {
		this.labTestDocDao.save(labDocument);
	}

	@Override
	public LaboratoryTestDocument getLabDocumentById(int id) {
		return this.labTestDocDao.getLabDocumentById(id);
	}

	@Override
	public void delete(LaboratoryTestDocument labDocument) {
		this.labTestDocDao.delete(labDocument);
	}

}
