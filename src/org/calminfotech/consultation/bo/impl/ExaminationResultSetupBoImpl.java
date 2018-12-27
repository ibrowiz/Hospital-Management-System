package org.calminfotech.consultation.bo.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.consultation.bo.ExaminationResultSetupBo;
import org.calminfotech.consultation.dao.ExaminationCategoryDao;
import org.calminfotech.consultation.dao.ExaminationDao;
import org.calminfotech.consultation.dao.ExaminationResultSetupDao;
import org.calminfotech.consultation.forms.ExaminationResultSetupForm;
import org.calminfotech.consultation.models.Examination;
import org.calminfotech.consultation.models.ExaminationCategory;
import org.calminfotech.consultation.models.ExaminationResultSetup;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ExaminationResultSetupBoImpl implements ExaminationResultSetupBo { 
	
	@Autowired
	private ExaminationDao examDao;
	
	@Autowired
	private ExaminationResultSetupDao examResultSetupDao;
	
	@Autowired	private UserIdentity userIdentity;

	@Override
	public List<ExaminationResultSetup> fetchAllByOrganisationId(
			Integer organisationId) {
		return this.examResultSetupDao.fetchAllByOrganisationId(organisationId);
	}

	@Override
	public ExaminationResultSetup getExamResultSetupById(Integer id) {
		return this.examResultSetupDao.getExamResultSetupById(id);
	}

	@Override
	public List<ExaminationResultSetup> getExamResultSetupByExamId(
			Integer examId) {
		Examination exam = this.examDao.getExaminationById(examId);
		return this.examResultSetupDao.getExamResultSetupByExamId(exam);
	}

	@Override
	public ExaminationResultSetup save(ExaminationResultSetupForm examResultSetupForm) {
		Examination exam = this.examDao.getExaminationById(examResultSetupForm.getExamId());
		ExaminationResultSetup examResultSetup = new ExaminationResultSetup();
		//examination.setParentId(examinationForm.getParentId());
		
		examResultSetup.setExam(exam);
		examResultSetup.setExamResultSetupId(examResultSetupForm.getGlobalExamResultSetupId());
		examResultSetup.setExamResultName(examResultSetupForm.getExamResultName());
		examResultSetup.setCreated_by(userIdentity.getUserId());
		examResultSetup.setCreateDate(new GregorianCalendar().getTime());
		examResultSetup.setOrganisationId(userIdentity.getOrganisation().getId());
		examResultSetupDao.save(examResultSetup);
		return examResultSetup;
		
	}

	@Override
	public void update(ExaminationResultSetupForm examResultSetupForm) {
		Examination exam = this.examDao.getExaminationById(examResultSetupForm.getExamId());
		ExaminationResultSetup examResultSetup = this.examResultSetupDao.getExamResultSetupById(examResultSetupForm.getGlobalExamResultSetupId());
		examResultSetup.setExam(exam);
		examResultSetup.setExamResultName(examResultSetupForm.getExamResultName());
		examResultSetup.setModified_by(userIdentity.getUserId());
		examResultSetup.setModified_date(new GregorianCalendar().getTime());
		examResultSetup.setOrganisationId(userIdentity.getOrganisation().getId());
		
		this.examResultSetupDao.update(examResultSetup);
		
	}

	@Override
	public void delete(ExaminationResultSetup examResultSetup) {
		this.examResultSetupDao.delete(examResultSetup);
	}

	@Override
	public ExaminationResultSetup getExamResultSetupByRowId(Integer rowId) {
		return this.examResultSetupDao.getExamResultSetupByRowId(rowId);
	}

}
