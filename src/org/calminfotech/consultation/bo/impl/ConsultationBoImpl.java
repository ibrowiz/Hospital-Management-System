package org.calminfotech.consultation.bo.impl;

import java.util.List;

import org.calminfotech.consultation.bo.ConsultationBo;
import org.calminfotech.consultation.dao.ConsultationDao;
import org.calminfotech.consultation.forms.VisitDoctorForm;
import org.calminfotech.consultation.models.Consultation;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.boInterface.ConsultationStatusBo;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.models.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConsultationBoImpl implements ConsultationBo {

	@Autowired
	private UserIdentity userIdentity;

	@Autowired
	private ConsultationDao consultationDao;
 
	@Autowired
	private ConsultationStatusBo consultationStatusBo;

	@Override
	public List<Consultation> fetchAll() {
		// TODO Auto-generated method stub
		return this.consultationDao.fetchAll();
	}

	@Override
	public List<Consultation> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		Organisation organisation = this.userIdentity.getOrganisation();
		return this.consultationDao.fetchAllByOrganisation(organisation);
	}

	@Override
	public Consultation getConsultationById(int id) {
		// TODO Auto-generated method stub
		return this.consultationDao.getConsultationById(id);
	}

	@Override
	public void save(VisitDoctorForm visitDoctorForm) {
		// TODO Auto-generated method stub

		// Get consultation Status start point

		// Create consultation code

		// this.consultationDao.save(consultation);
	}

	@Override
	public void delete(Consultation consultation) {
		// TODO Auto-generated method stub
		this.consultationDao.delete(consultation);
	}

	@Override
	public void update(VisitDoctorForm visitDoctorForm) {
		// TODO Auto-generated method stub

	}

	@Override
	public Consultation create(Patient patient) {
		// TODO Auto-generated method stub

		Consultation consultation = new Consultation();
		consultation.setCode(this.generateCode());
		consultation.setOrganisation(this.userIdentity.getOrganisation());
		consultation.setPatient(patient);
		consultation.setStatus(this.consultationStatusBo.getStartPointStatus());
		consultation.setCreatedBy(this.userIdentity.getUsername());
		consultation.setDeleted(false);
		
		this.consultationDao.save(consultation);

		return consultation;
	}

	@Override
	public boolean hasConsultation(Patient patient) {
		// TODO Auto-generated method stub

		Consultation onGoingConsultation = this.consultationDao
				.getOnGoingConsultationByPatient(patient);

		if (null != onGoingConsultation)
			return true;

		return false;
	}

	@Override
	public Consultation getOnGoingConsultationByPatient(Patient patient) {
		return this.consultationDao.getOnGoingConsultationByPatient(patient);
	}

	private String generateCode() {

		String code = "";
		// Get the last consultation
		Consultation consult = this.consultationDao.getLastConsultation();
		if (null == consult) {
			code = this.userIdentity.getOrganisation().getConsultationCode();
			code = code + "-" + 1;
		} else {
			String[] formerCodeParts = consult.getCode().split("-");
			int lastPart = Integer.parseInt(formerCodeParts[1]);
			lastPart++;
			code = this.userIdentity.getOrganisation().getConsultationCode()
					+ "-" + lastPart;
		}

		return code;
	}
}
