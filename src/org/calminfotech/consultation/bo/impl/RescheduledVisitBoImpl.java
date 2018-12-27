package org.calminfotech.consultation.bo.impl;

import java.util.List;

import org.calminfotech.consultation.bo.RescheduledVisitBo;
import org.calminfotech.consultation.dao.ConsultationDao;
import org.calminfotech.consultation.dao.RescheduledVisitDao;
import org.calminfotech.consultation.dao.VisitDao;
import org.calminfotech.consultation.forms.RescheduleVisitForm;
import org.calminfotech.consultation.models.RescheduledVisit;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.AppConfig;
import org.calminfotech.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RescheduledVisitBoImpl implements RescheduledVisitBo {

	@Autowired
	private RescheduledVisitDao rescheduledVisitDaoImpl;

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private PatientDao patientDao;

	@Autowired
	private VisitDao visitDao;

	@Autowired
	private ConsultationDao consultationDao;

	@Override
	public List<RescheduledVisit> fetchAll() {
		// TODO Auto-generated method stub
		return this.rescheduledVisitDaoImpl.fetchAll();
	}

	@Override
	public List<RescheduledVisit> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return this.rescheduledVisitDaoImpl.fetchAllByOrganisation();
	}

	@Override
	public RescheduledVisit getRescheduledVisitById(int id) {
		// TODO Auto-generated method stub
		return this.rescheduledVisitDaoImpl.getRescheduledVisitById(id);
	}

	@Override
	public void save(RescheduledVisit rescheduledVisit) {
		// TODO Auto-generated method stub
		this.rescheduledVisitDaoImpl.save(rescheduledVisit);
	}

	@Override
	public void save(RescheduleVisitForm form) {
		// TODO Auto-generated method stub
		Visit visit = this.visitDao.getVisitationById(form.getVisitId());

		RescheduledVisit rVisit = visit.getRescheduledVisit();
		if (rVisit == null) {
			rVisit = new RescheduledVisit();
			rVisit.setVisit(visit);
			rVisit.setPatient(visit.getPatient());
			//rVisit.setConsultation(visit.getConsultation());
			rVisit.setOrganisation(this.userIdentity.getOrganisation());
			rVisit.setTrials(AppConfig.NUMBER_OF_RESCHEDULE_TRAIL);
			rVisit.setCreatedBy(this.userIdentity.getUsername());
		}

		rVisit.setReason(form.getReason());
		// Transform the date and insert!
		rVisit.setScheduledDate(DateUtils.formatStringToDate(form
				.getRescheduleDate()));
		visit.setRescheduledVisit(rVisit);
		this.visitDao.update(visit);
	}

	@Override
	public void delete(RescheduledVisit rescheduledVisit) {
		// TODO Auto-generated method stub
		this.rescheduledVisitDaoImpl.delete(rescheduledVisit);
	}

	@Override
	public void update(RescheduledVisit rescheduledVisit) {
		// TODO Auto-generated method stub
		this.rescheduledVisitDaoImpl.update(rescheduledVisit);
	}

	@Override
	public void update(RescheduleVisitForm form) {
		// TODO Auto-generated method stub

	}

}
