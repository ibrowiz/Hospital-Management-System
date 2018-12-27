package org.calminfotech.consultation.bo.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.consultation.bo.ExaminationCategoryBo;
import org.calminfotech.consultation.dao.ExaminationCategoryDao;
import org.calminfotech.consultation.forms.ExaminationCategoryForm;

import org.calminfotech.consultation.models.ExaminationCategory;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service

public class ExaminationCategoryBoImpl implements ExaminationCategoryBo {
	
	
	@Autowired
	private ExaminationCategoryDao examinationDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<ExaminationCategory> fetchAllByOrgainsation(Integer organisationId) {
		return this.examinationDao.fetchAllByOrgainsation(organisationId);
	}

	@Override
	public ExaminationCategory getExaminationById(Integer examId) {
		return this.examinationDao.getExaminationById(examId);
	}

	@Override
	public ExaminationCategory save(ExaminationCategoryForm examinationForm) {
		ExaminationCategory examination = new ExaminationCategory();
		examination.setParentId(examinationForm.getParentId());
		examination.setName(examinationForm.getName());
		/*examination.setMinimumValue(examinationForm.getMinimumValue());
		examination.setMaximumValue(examinationForm.getMaximumValue());*/
		examination.setCreatedBy(userIdentity.getUserId());
		examination.setOrganisationId(userIdentity.getOrganisation().getId());
		examination.setCreatedDate(new GregorianCalendar().getTime());
		examination.setDeleted(false);
		examinationDao.save(examination);
		return examination;
	}

	@Override
	public void update(ExaminationCategoryForm examinationForm) {
		ExaminationCategory examination = this.examinationDao.getExaminationById(examinationForm.getExamCategoryId());
		examination.setParentId(examinationForm.getParentId());
		examination.setName(examinationForm.getName());
		/*examination.setMinimumValue(examinationForm.getMinimumValue());
		examination.setMaximumValue(examinationForm.getMaximumValue());*/
		examination.setModifiedBy(userIdentity.getUserId());
		examination.setModifiedDate(new GregorianCalendar().getTime());
		examinationDao.update(examination);
	}

	@Override
	public void delete(ExaminationCategory examination) {
		this.examinationDao.delete(examination);
	}

	

	

}
