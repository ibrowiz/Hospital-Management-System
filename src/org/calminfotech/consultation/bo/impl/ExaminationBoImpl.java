package org.calminfotech.consultation.bo.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.consultation.bo.ExaminationBo;
import org.calminfotech.consultation.dao.ExaminationCategoryDao;
import org.calminfotech.consultation.dao.ExaminationDao;
import org.calminfotech.consultation.forms.ExaminationForm;
import org.calminfotech.consultation.models.Examination;
import org.calminfotech.consultation.models.ExaminationCategory;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ExaminationBoImpl implements ExaminationBo {
	
	@Autowired
	private ExaminationCategoryDao examCategoryDao;
	
	@Autowired
	private ExaminationDao examinationDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<Examination> fetchAllByOrgainsation(Integer organisationId) {
		return this.examinationDao.fetchAllByOrgainsation(organisationId);
	}

	@Override
	public Examination getExaminationById(Integer examId) {
		return this.examinationDao.getExaminationById(examId);
	}

	@Override
	public Examination save(ExaminationForm examinationForm) {
		Examination examination = new Examination();
		ExaminationCategory examCategory = this.examCategoryDao.getExaminationById(examinationForm.getExamCategoryId());
		examination.setExamCategory(examCategory);
		examination.setName(examinationForm.getName());
		examination.setMinimumValue(examinationForm.getMinimumValue());
		examination.setMaximumValue(examinationForm.getMaximumValue());
		examination.setDescription(examinationForm.getDescription());
		examination.setCreatedBy(userIdentity.getUserId());
		examination.setOrganisationId(userIdentity.getOrganisation().getId());
		examination.setCreatedDate(new GregorianCalendar().getTime());
		examination.setDeleted(false);
		examinationDao.save(examination);
		return examination;
	}

	@Override
	public void update(ExaminationForm examinationForm) {
		Examination examination = this.examinationDao.getExaminationById(examinationForm.getExamId());
		ExaminationCategory examCategory = this.examCategoryDao.getExaminationById(examinationForm.getExamCategoryId());
		examination.setExamCategory(examCategory);
		examination.setName(examinationForm.getName());
		examination.setMinimumValue(examinationForm.getMinimumValue());
		examination.setMaximumValue(examinationForm.getMaximumValue());
		examination.setDescription(examinationForm.getDescription());
		examination.setModifiedBy(userIdentity.getUserId());
		examination.setOrganisationId(userIdentity.getOrganisation().getId());
		examination.setModifiedDate(new GregorianCalendar().getTime());
		examinationDao.update(examination);
	}

	@Override
	public void delete(Examination examination) {
		this.examinationDao.delete(examination);
		
	}

}
