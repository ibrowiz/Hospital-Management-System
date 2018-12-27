package org.calminfotech.consultation.models;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;
import org.calminfotech.utils.models.SurgicalProcedures;
import org.calminfotech.utils.models.Symptom;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "visit_presenting_complaint")
public class VisitPresentingComplaint {
	
	
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "visit_id")
	private Visit visit;

	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;
	

	@ManyToOne
	@JoinColumn(name = "symptom_id")
	private Symptom symptom;
	private String severity;
	
	private Integer no_of_days;

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	private String other_detail;
	
	

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public Symptom getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public Integer getNo_of_days() {
		return no_of_days;
	}

	public void setNo_of_days(Integer no_of_days) {
		this.no_of_days = no_of_days;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getOther_detail() {
		return other_detail;
	}

	public void setOther_detail(String other_detail) {
		this.other_detail = other_detail;
	}



}
