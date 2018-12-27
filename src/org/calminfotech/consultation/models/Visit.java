package org.calminfotech.consultation.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientFamilyHistory;
import org.calminfotech.patient.models.PatientMedicalHistory;
import org.calminfotech.patient.models.PatientSocialHistory;
import org.calminfotech.patient.models.PatientSurgicalHistory;
import org.calminfotech.system.models.LoginSection;
import org.calminfotech.system.models.LoginSectionPoint;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.VisitStatus;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "visit_consultation_visitations")
@SQLDelete(sql = "UPDATE visit_consultation_visitations SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class Visit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "system_id")
	private String code;
	
	@Column(name = "user_id")
	private int userId;
	
	
	@Column(name = "modify_date")
	private Date modifyDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "visit_status_id")
	private VisitStatus status;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "visit_id")
	@OrderBy("createDate ASC")
	private Set<VisitTimeline> timeline = new HashSet<VisitTimeline>();

	@ManyToOne
	@JoinColumn(name = "point_id")
	private VisitWorkflowPoint point;
	
	
	/*@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;*/
	
	
	@Column(name = "unit_id")
	private int unitId;
	
	
	/*@ManyToOne
	@JoinColumn(name = "section_id")
	private LoginSection loginSection;*/
	
	
	/*private Integer clerking_status_id;*/
	
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "visit")
	private VisitPharmacy pharmacyVisit;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "visit")
	private VisitVital vitalVisit;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "visit")
	private VisitLaboratory laboratoryVisit;
	
	
	/*@OneToOne(fetch = FetchType.LAZY, mappedBy = "visit", cascade = CascadeType.ALL)
	private LoginSectionPoint loginSectionPoint;*/
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "visit")
	private Set<VisitLaboratoryTest> labTests = new HashSet<VisitLaboratoryTest>();

	@OneToMany
	@JoinColumn(name = "visit_id")
	private Set<VisitPrescribedDrug> prescribedDrugs = new HashSet<VisitPrescribedDrug>();


	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "visit")
	private Set<VisitPayment> visitPayment = new HashSet<VisitPayment>();
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "visit")
	private RescheduledVisit rescheduledVisit;
	
		
	@OneToMany
	@JoinColumn(name = "cons_visit_id")
	private Set<PatientMedicalHistory> patientmedicalhistory = new HashSet<PatientMedicalHistory>();

	@OneToMany
	@JoinColumn(name = "cons_visit_id")
	private Set<PatientSurgicalHistory> patientSurgicalHistory = new HashSet<PatientSurgicalHistory>();
	
	@OneToMany
	@JoinColumn(name = "cons_visit_id")
	private Set<PatientFamilyHistory> patientfamilyhistory = new HashSet<PatientFamilyHistory>();
	
	@OneToMany
	@JoinColumn(name = "cons_visit_id")
	private Set<PatientSocialHistory> patientSocialHistory = new HashSet<PatientSocialHistory>();
	
	@OneToMany
	@JoinColumn(name = "visit_id")
	private Set<VisitPresentingComplaint> visitPresentingComplaint = new HashSet<VisitPresentingComplaint>();
	
	
	@OneToMany
	@JoinColumn(name = "visit_id")
	private Set<VisitLaboratoryTest> visitLaboratoryTest = new HashSet<VisitLaboratoryTest>();
	
	
	
	@OneToMany
	@JoinColumn(name = "visit_id")
	private Set<ExaminationCategory> visitExamination = new HashSet<ExaminationCategory>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="visit_id")
	private Set<VisitLaboratoryTest> visitLabTest;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public VisitStatus getStatus() {
		return status;
	}

	public void setStatus(VisitStatus status) {
		this.status = status;
	}

	/**
	 * @return the doctorVisit
	 */
	/**
	 * @return the pharmacyVisit
	 */
	public VisitPharmacy getPharmacyVisit() {
		return pharmacyVisit;
	}

	/**
	 * @param pharmacyVisit
	 *            the pharmacyVisit to set
	 */
	public void setPharmacyVisit(VisitPharmacy pharmacyVisit) {
		this.pharmacyVisit = pharmacyVisit;
	}

	/**
	 * @return the vitalVisit
	 */
	public VisitVital getVitalVisit() {
		return vitalVisit;
	}

	/**
	 * @param vitalVisit
	 *            the vitalVisit to set
	 */
	public void setVitalVisit(VisitVital vitalVisit) {
		this.vitalVisit = vitalVisit;
	}

	public VisitWorkflowPoint getPoint() {
		return point;
	}

	public void setPoint(VisitWorkflowPoint point) {
		this.point = point;
	}

	/**
	 * @return the laboratoryVisit
	 */
	public VisitLaboratory getLaboratoryVisit() {
		return laboratoryVisit;
	}

	/**
	 * @param laboratoryVisit
	 *            the laboratoryVisit to set
	 */
	public void setLaboratoryVisit(VisitLaboratory laboratoryVisit) {
		this.laboratoryVisit = laboratoryVisit;
	}

	/**
	 * @return the timeline
	 */
	public Set<VisitTimeline> getTimeline() {
		return timeline;
	}

	/**
	 * @param timeline
	 *            the timeline to set
	 */
	public void setTimeline(Set<VisitTimeline> timeline) {
		this.timeline = timeline;
	}

	/**
	 * @return the labTest
	 */
	public Set<VisitLaboratoryTest> getLabTests() {
		return labTests;
	}

	/**
	 * @param labTest
	 *            the labTest to set
	 */
	public void setLabTests(Set<VisitLaboratoryTest> labTest) {
		this.labTests = labTest;
	}

	/**
	 * @return the prescribedDrugs
	 */
	public Set<VisitPrescribedDrug> getPrescribedDrugs() {
		return prescribedDrugs;
	}

	/**
	 * @param prescribedDrugs
	 *            the prescribedDrugs to set
	 */
	public void setPrescribedDrugs(Set<VisitPrescribedDrug> prescribedDrugs) {
		this.prescribedDrugs = prescribedDrugs;
	}

	/**
	 * @return the rescheduledVisit
	 */
	public RescheduledVisit getRescheduledVisit() {
		return rescheduledVisit;
	}

	/**
	 * @param rescheduledVisit the rescheduledVisit to set
	 */
	public void setRescheduledVisit(RescheduledVisit rescheduledVisit) {
		this.rescheduledVisit = rescheduledVisit;
	}



	public Set<VisitPayment> getVisitPayment() {
		return visitPayment;
	}

	public void setVisitPayment(Set<VisitPayment> visitPayment) {
		this.visitPayment = visitPayment;
	}

	/*public Integer getClerking_status_id() {
		return clerking_status_id;
	}

	public void setClerking_status_id(Integer clerking_status_id) {
		this.clerking_status_id = clerking_status_id;
	}*/

	public Set<PatientMedicalHistory> getPatientmedicalhistory() {
		return patientmedicalhistory;
	}

	public void setPatientmedicalhistory(Set<PatientMedicalHistory> patientmedicalhistory) {
		this.patientmedicalhistory = patientmedicalhistory;
	}

	public Set<PatientSurgicalHistory> getPatientSurgicalHistory() {
		return patientSurgicalHistory;
	}

	public void setPatientSurgicalHistory(Set<PatientSurgicalHistory> patientSurgicalHistory) {
		this.patientSurgicalHistory = patientSurgicalHistory;
	}

	public Set<PatientFamilyHistory> getPatientfamilyhistory() {
		return patientfamilyhistory;
	}

	public void setPatientfamilyhistory(Set<PatientFamilyHistory> patientfamilyhistory) {
		this.patientfamilyhistory = patientfamilyhistory;
	}

	public Set<PatientSocialHistory> getPatientSocialHistory() {
		return patientSocialHistory;
	}

	public void setPatientSocialHistory(Set<PatientSocialHistory> patientSocialHistory) {
		this.patientSocialHistory = patientSocialHistory;
	}

	public Set<VisitPresentingComplaint> getVisitPresentingComplaint() {
		return visitPresentingComplaint;
	}

	public void setVisitPresentingComplaint(Set<VisitPresentingComplaint> visitPresentingComplaint) {
		this.visitPresentingComplaint = visitPresentingComplaint;
	}

	public Set<VisitLaboratoryTest> getVisitLaboratoryTest() {
		return visitLaboratoryTest;
	}

	public void setVisitLaboratoryTest(Set<VisitLaboratoryTest> visitLaboratoryTest) {
		this.visitLaboratoryTest = visitLaboratoryTest;
	}

	public Set<ExaminationCategory> getVisitExamination() {
		return visitExamination;
	}

	public void setVisitExamination(Set<ExaminationCategory> visitExamination) {
		this.visitExamination = visitExamination;
	}


	public Set<VisitLaboratoryTest> getVisitLabTest() {
		return visitLabTest;
	}

	public void setVisitLabTest(Set<VisitLaboratoryTest> visitLabTest) {
		this.visitLabTest = visitLabTest;
	}


	

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	
	
/*	public VisitClerkingStatus getvClerkingStatus() {
		return vClerkingStatus;
	}

	public void setvClerkingStatus(VisitClerkingStatus vClerkingStatus) {
		this.vClerkingStatus = vClerkingStatus;
	}*/

/*	public LoginSectionPoint getLoginSectionPoint() {
		return loginSectionPoint;
	}

	public void setLoginSectionPoint(LoginSectionPoint loginSectionPoint) {
		this.loginSectionPoint = loginSectionPoint;
	}*/


}
