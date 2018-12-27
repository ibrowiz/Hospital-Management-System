package org.calminfotech.consultation.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "rescheduled_visits")
public class RescheduledVisit {

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "visit"))
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "visit_id", unique = true, nullable = false)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Visit visit;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "consultation_id")
	private Consultation consultation;

	@Column(name = "scheduled_date")
	private Date scheduledDate;

	@Column(name = "reason")
	private String reason;

	@Column(name = "trials", nullable = false)
	private int trials;

	@Column(name = "is_sent", nullable = false)
	private boolean isSent;

	@Column(name = "failed")
	private boolean failed;

	@Column(name = "create_date", nullable = false)
	private Date createDate;

	@Column(name = "created_by", nullable = false)
	private String createdBy;
	
	@Column(name = "modify_date", nullable = false)
	private Date modifyDate;
	
	@Column(name = "modified_by", nullable = false)
	private String modified;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the visit
	 */
	public Visit getVisit() {
		return visit;
	}

	/**
	 * @param visit
	 *            the visit to set
	 */
	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient
	 *            the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * @return the consultation
	 */
	public Consultation getConsultation() {
		return consultation;
	}

	/**
	 * @param consultation
	 *            the consultation to set
	 */
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	/**
	 * @return the scheduledDate
	 */
	public Date getScheduledDate() {
		return scheduledDate;
	}

	/**
	 * @param scheduledDate
	 *            the scheduledDate to set
	 */
	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the trials
	 */
	public int getTrials() {
		return trials;
	}

	/**
	 * @param trials
	 *            the trials to set
	 */
	public void setTrials(int trials) {
		this.trials = trials;
	}

	/**
	 * @return the isSent
	 */
	public boolean isSent() {
		return isSent;
	}

	/**
	 * @param isSent
	 *            the isSent to set
	 */
	public void setSent(boolean isSent) {
		this.isSent = isSent;
	}

	/**
	 * @return the failed
	 */
	public boolean isFailed() {
		return failed;
	}

	/**
	 * @param failed
	 *            the failed to set
	 */
	public void setFailed(boolean failed) {
		this.failed = failed;
	}

	/**
	 * @return the createdDate
	 */
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
	
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	/**
	 * @param organisation
	 *            the organisation to set
	 */
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

}
