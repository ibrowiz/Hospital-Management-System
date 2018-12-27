package org.calminfotech.consultation.bo.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.calminfotech.consultation.bo.GlobalExamResultSetupBo;
import org.calminfotech.consultation.dao.GlobalExamResultSetupDao;
import org.calminfotech.consultation.forms.GlobalExamResultSetupForm;
import org.calminfotech.consultation.models.ExaminationCategory;
import org.calminfotech.consultation.models.GlobalExaminationResultSetup;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class GlobalExamResultSetupBoImpl implements GlobalExamResultSetupBo {
	
	@Autowired
	private GlobalExamResultSetupDao gExamSetupDao;
	
	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<GlobalExaminationResultSetup> fetchAllByOrganisationId(
			Integer organisationId) {
		return this.gExamSetupDao.fetchAllByOrganisationId(organisationId);
	}

	@Override
	public GlobalExaminationResultSetup getExamResultSetupById(Integer id) {
		return this.gExamSetupDao.getExamResultSetupById(id);
	}

	@Override
	public GlobalExaminationResultSetup save(GlobalExamResultSetupForm globalExamResultSetupForm) {
		GlobalExaminationResultSetup GlobalExamResultSetup = new GlobalExaminationResultSetup();
		GlobalExamResultSetup.setExamResultName(globalExamResultSetupForm.getExamResultName());
		GlobalExamResultSetup.setDescription(globalExamResultSetupForm.getDescription());
		GlobalExamResultSetup.setCreated_by(userIdentity.getUserId());
		GlobalExamResultSetup.setOrganisationId(userIdentity.getOrganisation().getId());
		GlobalExamResultSetup.setCreateDate(new GregorianCalendar().getTime());
		GlobalExamResultSetup.setDeleted(false);
		gExamSetupDao.save(GlobalExamResultSetup);
		return GlobalExamResultSetup;
		
	}

	@Override
	public void update(GlobalExamResultSetupForm globalExamResultSetupForm) {
		GlobalExaminationResultSetup GlobalExamResultSetup = this.gExamSetupDao.getExamResultSetupById(globalExamResultSetupForm.getExamResultSetupId());
		GlobalExamResultSetup.setExamResultName(globalExamResultSetupForm.getExamResultName());
		GlobalExamResultSetup.setDescription(globalExamResultSetupForm.getDescription());
		GlobalExamResultSetup.setModified_by(userIdentity.getUserId());
		GlobalExamResultSetup.setOrganisationId(userIdentity.getOrganisation().getId());
		GlobalExamResultSetup.setModified_date(new GregorianCalendar().getTime());
		gExamSetupDao.save(GlobalExamResultSetup);
	}

	@Override
	public void delete(GlobalExaminationResultSetup examResultSetup) {
		this.gExamSetupDao.delete(examResultSetup);
		
	}

}
