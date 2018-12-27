package org.calminfotech.admin.bo.impl;

import java.util.Date;
import java.util.List;

import org.calminfotech.admin.boInterface.AilmentBo;
import org.calminfotech.admin.daoInterface.AilmentClassificationDao;
import org.calminfotech.admin.daoInterface.AilmentDao;
import org.calminfotech.admin.forms.AilmentForm;
import org.calminfotech.system.models.Ailment;
import org.calminfotech.system.models.AilmentClassification;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AilmentBoImpl implements AilmentBo {

	@Autowired
	private AilmentDao ailmentDao;

	@Autowired
	private AilmentClassificationDao ailmentClassificationDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<Ailment> fetchAll() {
		// TODO Auto-generated method stub
		return ailmentDao.fetchAll();
	}

	@Override
	public Ailment getAilmentById(int id) {
		// TODO Auto-generated method stub
		return ailmentDao.getAilmentById(id);
	}

	@Override
	public Ailment save(AilmentForm ailmentForm) {
		// TODO Auto-generated method stub
		Ailment ailment = new Ailment();
		ailment.setName(ailmentForm.getName());
		ailment.setDescription(ailmentForm.getDescription());

		AilmentClassification ailmentClassification = ailmentClassificationDao
				.getAilmentClassificationById(ailmentForm.getClassificationId());
		ailment.setAilmentClassification(ailmentClassification);

		ailmentDao.save(ailment);
		return ailment;
	}

	@Override
	public void delete(Ailment ailment) {
		// TODO Auto-generated method stub
		ailmentDao.delete(ailment);
	}

	@Override
	public void update(AilmentForm ailmentForm) {
		// TODO Auto-generated method stub

		Ailment ailment = ailmentDao.getAilmentById(ailmentForm.getId());

		ailment.setName(ailmentForm.getName());
		ailment.setDescription(ailmentForm.getDescription());

		AilmentClassification ailmentClassification = ailmentClassificationDao
				.getAilmentClassificationById(ailmentForm.getClassificationId());
		ailment.setAilmentClassification(ailmentClassification);
		ailment.setModifiedBy(userIdentity.getUsername());
		ailment.setModifyDate(new Date(System.currentTimeMillis()));

		ailmentDao.update(ailment);
	}

	@Override
	public List<Ailment> fetchLikeName(String q) {
		// TODO Auto-generated method stub
		return this.ailmentDao.fetchLikeName(q);
	}

}
