package org.calminfotech.consultation.bo.impl;

import java.util.Date;
import java.util.List;

import org.calminfotech.consultation.bo.VisitBo;
import org.calminfotech.consultation.dao.ConsultationDao;
import org.calminfotech.consultation.dao.VisitDao;
import org.calminfotech.consultation.forms.VisitDoctorForm;
import org.calminfotech.consultation.forms.VisitLaboratoryForm;
import org.calminfotech.consultation.forms.VisitPharmacyForm;
import org.calminfotech.consultation.forms.VisitVitalsForm;
import org.calminfotech.consultation.forms.VisitWorkflowUserConfigurationForm;
import org.calminfotech.consultation.forms.VisitationForm;
import org.calminfotech.consultation.models.Consultation;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.consultation.models.VisitLaboratory;
import org.calminfotech.consultation.models.VisitPharmacy;
import org.calminfotech.consultation.models.VisitVital;
import org.calminfotech.hr.forms.AssignForm;
import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.daoInterface.PatientDao;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.setup.boInterface.UnitCategoryBo;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.setup.models.HrUnitCategory;
import org.calminfotech.system.boInterface.BillingSchemeBo;
import org.calminfotech.system.boInterface.LoginSectionBo;
import org.calminfotech.system.boInterface.VisitStatusBo;
import org.calminfotech.system.boInterface.VisitWorkflowPointBo;
import org.calminfotech.system.models.VisitStatus;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitBoImpl implements VisitBo {
	
	@Autowired
	private UnitCategoryBo unitCatBo;

	@Autowired
	private VisitDao visitDao;

	@Autowired
	private VisitStatusBo visitStatusBo;
	
	@Autowired
	private PatientBo patientBo;

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private LoginSectionBo sectionBo;
	
	@Autowired
	private BillingSchemeBo paymentSchemeBo;

	@Autowired
	private ConsultationDao consultationDao;

	@Autowired
	private PatientDao patientDao;

	@Autowired
	private VisitWorkflowPointBo wfPointBo;

	@Autowired
	private UserBo userBo;

	@Override
	public List<Visit> fetchAll() {
		// TODO Auto-generated method stub
		return this.visitDao.fetchAll();
	}

	/*@Override
	public List<Visit> fetchAll(Consultation consultation) {
		// TODO Auto-generated method stub
		return this.visitDao.fetchAll(consultation);
	}*/

	@Override
	public List<Visit> fetchAllByWorkflowPoint(String point) {
		// TODO Auto-generated method stub
		return this.visitDao.fetchAllByWorkflowPoint(point);
	}

	@Override
	public List<Visit> fetchAllByWorkflowPoint(String point, VisitStatus status) {
		// TODO Auto-generated method stub
		return this.visitDao.fetchAllByWorkflowPoint(point, status);
	}

	@Override
	public List<Visit> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return this.visitDao.fetchAll(this.userIdentity.getOrganisation());
	}

	@Override
	public List<Visit> fetchAll(Patient patient) {
		// TODO Auto-generated method stub
		return this.visitDao.fetchAll(patient);
	}

	@Override
	public Visit getVisitationById(int id) {
		// TODO Auto-generated method stub
		return this.visitDao.getVisitationById(id);
	}

	/*@Override
	public void save(VisitationForm form) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(VisitDoctorForm form) {
		// TODO Auto-generated method stub

	}*/

	@Override
	public void delete(Visit visit) {
		// TODO Auto-generated method stub
		this.visitDao.delete(visit);
	}

	@Override
	public void update(VisitationForm form) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Visit visit) {
		// TODO Auto-generated method stub
		this.visitDao.update(visit);
	}

	@Override
	public Visit getLastVisit1(Visit visit) {
		// TODO Auto-generated method stub
		return this.visitDao.getLastVisit(visit);
	}
	
	

	/*@Override
	public Visit getLastVisit(Consultation consultation) {
		// TODO Auto-generated method stub
		return this.visitDao.getLastVisit(consultation);
	}*/

	/*@Override
	public Visit createVisit(Consultation consultation) {
		// TODO Auto-generated method stub
		Visit visit = new Visit();

		//visit.setConsultation(consultation);
		visit.setCode(this.generateCode(consultation));
		visit.setPatient(consultation.getPatient());
		visit.setOrganisation(this.userIdentity.getOrganisation());
		visit.setCreatedBy(this.userIdentity.getUsername());
		visit.setDeleted(false);
		// Ensure that an organisation has a start point
		visit.setStatus(this.visitStatusBo.getStartPointStatus());
		VisitWorkflowPoint point = this.wfPointBo.getWorkflowStartPoint();
		visit.setPoint(point);
		// Was giving issues until CascadeType.ALL was put on Visit timeline
		// property

		// Attach the timeline object and update the visit
		VisitTimeline timeline = new VisitTimeline();
		timeline.setPointName(point.getName());

		UserProfile userProfile = this.userIdentity.getUserProfile();
		// Construct the username
		String pointUsername = userProfile.getTitle().getAcronym() + " "
				+ userProfile.getLastName() + " " + userProfile.getOtherNames();

		timeline.setPointUsername(pointUsername);
		timeline.setCreatedBy(userIdentity.getUsername());

		Set<VisitTimeline> timelines = visit.getTimeline();
		timelines.add(timeline);
		visit.setTimeline(timelines);
		timeline.setVisit(visit);

		this.visitDao.save(visit);

		return visit;
	}*/

	private String generateCode(Consultation consultation) {
		String code = "";
		Visit lastVisit = this.visitDao.getLastVisit(consultation);

		if (null == lastVisit) {
			code = consultation.getCode() + "-" + 1;
		} else {
			String[] formerCodeParts = lastVisit.getCode().split("-");
			// Take the last part convert to int and add +1
			int lastPart = Integer.parseInt(formerCodeParts[2]);
			lastPart++;
			code = consultation.getCode() + "-" + lastPart;
		}
		return code;
	}

	
	/**
	 * Create the visit points here
	 */
	private Visit createPharmacyVisitPoint(Visit visit, User user) {
		VisitPharmacy pharmacy = visit.getPharmacyVisit();
		if (null == pharmacy) {
			pharmacy = new VisitPharmacy();
		}

		pharmacy.setOrganisation(visit.getOrganisation());
		pharmacy.setPatient(visit.getPatient());
		pharmacy.setCreatedBy(this.userIdentity.getUsername());
		pharmacy.setUser(user);

		pharmacy.setVisit(visit);
		visit.setPharmacyVisit(pharmacy);

		return visit;
	}

	private Visit createVitalsVisitPoint(Visit visit, User user) {
		VisitVital visitObj = visit.getVitalVisit();
		if (null == visitObj) {
			visitObj = new VisitVital();
		}

		System.out.println("Vital Point");
		visitObj.setOrganisation(visit.getOrganisation());
		visitObj.setPatient(visit.getPatient());
		visitObj.setCreatedBy(this.userIdentity.getUsername());
		visitObj.setUser(user);

		visitObj.setVisit(visit);
		visit.setVitalVisit(visitObj);

		return visit;
	}

	

	private Visit createLaboratoryVisitPoint(Visit visit, User user) {
		VisitLaboratory visitObj = visit.getLaboratoryVisit();
		if (null == visitObj) {
			visitObj = new VisitLaboratory();
		}

		visitObj.setOrganisation(visit.getOrganisation());
		visitObj.setPatient(visit.getPatient());
		visitObj.setCreatedBy(this.userIdentity.getUsername());
		visitObj.setUser(user);

		visitObj.setVisit(visit);
		visit.setLaboratoryVisit(visitObj);

		return visit;
	}

	/**
	 * Updates the vitals model and do other stuff like updates audit for vital
	 * 
	 * @param form
	 */

	@Override
	public void updateVitalsVisit(VisitVitalsForm form) {
		// TODO Auto-generated method stub
		Visit visit = this.visitDao.getVisitationById(form.getVisitId());

		VisitVital vitals = visit.getVitalVisit();

		// Set the vitals parameters
		vitals.setBloodPressure(form.getBloodPressure());
		vitals.setHeartRate(form.getHeartRate());
		vitals.setOthers(form.getOthers());
vitals.setRespiration(form.getRespiration());
		vitals.setTemperature(form.getTemperature());

		visit.setVitalVisit(vitals);

		this.visitDao.update(visit);

	}

	

	@Override
	public void updatePharmacyVisit(VisitPharmacyForm form) {
		// TODO Auto-generated method stub
		Visit visit = this.visitDao.getVisitationById(form.getVisitId());

		VisitPharmacy pharmacy = visit.getPharmacyVisit();

		// Set the vitals parameters
		pharmacy.setSampleFieldOne(form.getSampleField1());
		pharmacy.setSampleFieldTwo(form.getSampleField2());
		pharmacy.setComments(form.getComments());

		visit.setPharmacyVisit(pharmacy);

		this.visitDao.update(visit);
	}

	@Override
	public void updateLaboratoryVisit(VisitLaboratoryForm form) {
		// TODO Auto-generated method stub
		Visit visit = this.visitDao.getVisitationById(form.getVisitId());

		VisitPharmacy pharmacy = visit.getPharmacyVisit();

		// Set the vitals parameters
		pharmacy.setSampleFieldOne(form.getSampleField1());
		pharmacy.setSampleFieldTwo(form.getSampleField2());
		pharmacy.setComments(form.getComments());

		visit.setPharmacyVisit(pharmacy);

		this.visitDao.update(visit);
	}

	@Override
	public void clearLabTests(Visit visit) {
		// TODO Auto-generated method stub
		this.visitDao.clearLabTests(visit);
	}

	@Override
	public void clearPrescribedDrugs(Visit visit) {
		// TODO Auto-generated method stub
		this.visitDao.clearPrescribedDrugs(visit);
	}

	@Override
	public List<Visit> fetchByPatientId(int patientId) {
		// TODO Auto-generated method stub
		return this.visitDao.fetchByPatientId(patientId);
	}

	@Override
	public List<Visit> fetchAllByUId(int userId) {
		// TODO Auto-generated method stub
		return this.visitDao.fetchAllByUId(userId);
	}

	@Override
	public List<Visit> fetchAllByThese(int userId,Date from, Date to, int mstatus, String chkothers) {
		// TODO Auto-generated method stub
		return this.visitDao.fetchAllByThese(userId, from, to,mstatus,chkothers);
	}

	@Override
	public Visit getOnGoingConsultationByPatient(Patient patient) {
		return this.visitDao.getOnGoingConsultationByPatient(patient);
	}

	@Override
	public Visit getLastVisit(Visit visit) {
		return this.visitDao.getLastVisit(visit);
	}

	@Override
	public boolean hasConsultation(Patient patient) {
		// TODO Auto-generated method stub

		Visit onGoingConsultation = this.visitDao
				.getOnGoingConsultationByPatient(patient);

		if (null != onGoingConsultation)
			return true;

		return false;
	}

	/*@Override
	public void save(Visit visit) {
		this.visitDao.save(visit);
	}*/
	
	@Override
	public Visit save(Visit visit) {
		this.visitDao.save(visit);
		return visit;
	}

	@Override
	public Visit getVisitByPatient(Integer patientId) {
		Patient patient = this.patientBo.getPatientById(patientId);
		return this.visitDao.getVisitByPatient(patient);
	}

	@Override
	public Visit getVisitByCode(int visitcode) {
		return this.visitDao.getVisitByCode(visitcode);
	}

	@Override
	public Visit getLastVisit2(Patient patient) {
		return this.visitDao.getLastVisit2(patient);
	}

	@Override
	public void assignUserToPoint(VisitWorkflowUserConfigurationForm form) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDoctorVisit(VisitDoctorForm form) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AssignForm assignForm) {
		Visit visit = visitDao.getVisitationById(assignForm.getVisitId());

		//AllergyCategory1 category = this.allergyCategoryDao1.getCategoryById(allergyForm.getCategoryId());
		visit.setUserId(assignForm.getUserId());
		//UnitCategory unit = this.unitCatBo.getCategoryById(assignForm.getUnitId());
		visit.setUnitId(assignForm.getUnitId());
		HrUnitCategory unitCategory = this.unitCatBo.getCategoryById(assignForm.getUnitId());
		//visit.setPoint(unitCategory.getPoint());
		visit.setModifiedBy(userIdentity.getUsername());
		visit.setModifyDate(new Date(System.currentTimeMillis()));
		visitDao.update(visit);
	}

	@Override
	public List<Visit> fetchAllLabByThese(int userId, Integer patientId,
			Date from, Date to, int mstatus, String chkothers) {
		Patient patient = this.patientBo.getPatientById(patientId);
		return this.visitDao.fetchAllLabByThese(userId, patient, from, to, mstatus, chkothers);
	}
	
}
