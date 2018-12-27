package org.calminfotech.admin.bo.impl;

import java.util.List;

import org.calminfotech.admin.boInterface.DiseaseBo;
import org.calminfotech.admin.daoInterface.DiseaseDao;
import org.calminfotech.admin.forms.DiseaseForm;
import org.calminfotech.system.models.Disease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DiseaseBoImpl implements DiseaseBo {

	@Autowired
	private DiseaseDao diseaseDao;

	@Override
	public List<Disease> fetchAll() {
		// TODO Auto-generated method stub
		return diseaseDao.fetchAll();
	}

	@Override
	public Disease getDiseaseById(int id) {
		// TODO Auto-generated method stub
		return diseaseDao.getDiseaseById(id);
	}

	@Override
	public Disease save(DiseaseForm diseaseForm) {
		// TODO Auto-generated method stub
		Disease disease = new Disease();
		disease.setName(diseaseForm.getName());
		disease.setDescription(diseaseForm.getDescription());

		diseaseDao.save(disease);
		return disease;
	}

	@Override
	public void delete(Disease disease) {
		// TODO Auto-generated method stub
		diseaseDao.delete(disease);
	}

	@Override
	public void update(DiseaseForm diseaseForm) {
		// TODO Auto-generated method stub
		Disease disease = diseaseDao.getDiseaseById(diseaseForm.getId());

		disease.setName(diseaseForm.getName());
		disease.setDescription(diseaseForm.getDescription());

		diseaseDao.update(disease);

	}

}
