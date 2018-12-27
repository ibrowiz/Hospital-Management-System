package org.calminfotech.admin.bo.impl;

import java.util.List;

import org.calminfotech.admin.boInterface.DrugBo;
import org.calminfotech.admin.daoInterface.DrugClassificationDao;
import org.calminfotech.admin.daoInterface.DrugDao;
import org.calminfotech.admin.forms.DrugForm;
import org.calminfotech.system.models.Drug;
import org.calminfotech.system.models.DrugClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DrugBoImpl implements DrugBo {

	@Autowired
	private DrugDao drugDao;

	@Autowired
	private DrugClassificationDao drugClassificationDao;

	@Override
	public List<Drug> fetchAll() {
		// TODO Auto-generated method stub
		return this.drugDao.fetchAll();
	}

	@Override
	public Drug getDrugById(int id) {
		// TODO Auto-generated method stub
		return this.drugDao.getDrugById(id);
	}

	@Override
	public Drug save(DrugForm drugForm) {
		// TODO Auto-generated method stub
		Drug drug = new Drug();
		drug.setName(drugForm.getName());
		drug.setDescription(drugForm.getDescription());

		DrugClassification drugClassification = this.drugClassificationDao
				.getDrugClassificationById(drugForm.getClassificationId());
		drug.setDrugClassification(drugClassification);
		this.drugDao.save(drug);
		return drug;
	}

	@Override
	public void delete(Drug drug) {
		// TODO Auto-generated method stub
		this.drugDao.delete(drug);
	}

	@Override
	public void update(DrugForm drugForm) {
		// TODO Auto-generated method stub
		Drug drug = this.drugDao.getDrugById(drugForm.getId());
		drug.setName(drugForm.getName());
		drug.setDescription(drugForm.getDescription());

		DrugClassification drugClassification = this.drugClassificationDao
				.getDrugClassificationById(drugForm.getClassificationId());
		drug.setDrugClassification(drugClassification);

		this.drugDao.update(drug);
	}

	@Override
	public List<Drug> fetchLikeName(String q) {
		// TODO Auto-generated method stub
		return this.drugDao.fetchLikeName(q);
	}

}
