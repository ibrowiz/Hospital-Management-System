package org.calminfotech.admin.bo.impl;

import java.util.List;

import org.calminfotech.admin.boInterface.AilmentClassificationBo;
import org.calminfotech.admin.daoInterface.AilmentClassificationDao;
import org.calminfotech.admin.forms.AilmentClassificationForm;
import org.calminfotech.system.models.AilmentClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AilmentClassificationBoImpl implements AilmentClassificationBo {

	@Autowired
	private AilmentClassificationDao ailmentClassificationDao;

	@Override
	public List<AilmentClassification> fetchAll() {
		// TODO Auto-generated method stub
		return ailmentClassificationDao.fetchAll();
	}

	@Override
	public AilmentClassification getAilmentClassificationById(int id) {
		// TODO Auto-generated method stub
		return ailmentClassificationDao.getAilmentClassificationById(id);
	}

	@Override
	public AilmentClassification save(
			AilmentClassificationForm ailmentClassificationForm) {
		// TODO Auto-generated method stub
		AilmentClassification ailmentClassification = new AilmentClassification();
		ailmentClassification.setName(ailmentClassificationForm.getName());
		ailmentClassification.setDescription(ailmentClassificationForm
				.getDescription());

		ailmentClassificationDao.save(ailmentClassification);

		return ailmentClassification;
	}

	@Override
	public void delete(AilmentClassification ailmentClassification) {
		// TODO Auto-generated method stub
		ailmentClassificationDao.delete(ailmentClassification);
	}

	@Override
	public void update(AilmentClassificationForm ailmentClassificationForm) {
		// TODO Auto-generated method stub
		AilmentClassification ailmentClassification = ailmentClassificationDao
				.getAilmentClassificationById(ailmentClassificationForm.getId());
		
		ailmentClassification.setName(ailmentClassificationForm.getName());
		ailmentClassification.setDescription(ailmentClassificationForm
				.getDescription());
		
		ailmentClassificationDao.update(ailmentClassification);
	}
}
