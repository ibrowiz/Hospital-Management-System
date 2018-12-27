package org.calminfotech.patient.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.consultation.models.Visit;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "patient_socialhistory")
public class PatientSocialHistory {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;
	
	@Column(name = "education_level")
	private String education_level;
	
	@Column(name = "employment_status")
	private String employment_status;
	
	@Column(name = "disabled")
	private String disabled;
	
	@Column(name = "sexual_orientation")
	private String sexual_orientation;
	
	@Column(name = "substance")
	private String substance;
	
	@Column(name = "currentorprevious")
	private String currentorprevious;
	
	@Column(name = "frequency")
	private Integer frequency;
	
	
	@Column(name = "cycle")
	private String cycle;
	
	@Column(name = "start_year")
	private Integer start_year;
	
	@Column(name = "stop_year")
	private Integer stop_year;
	
	@Column(name = "other_detail")
	private String other_detail;
	
	
	@ManyToOne
	@JoinColumn(name = "cons_visit_id")
	private Visit visit;
	
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEducation_level() {
		return education_level;
	}

	public void setEducation_level(String education_level) {
		this.education_level = education_level;
	}

	public String getEmployment_status() {
		return employment_status;
	}

	public void setEmployment_status(String employment_status) {
		this.employment_status = employment_status;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getSexual_orientation() {
		return sexual_orientation;
	}

	public void setSexual_orientation(String sexual_orientation) {
		this.sexual_orientation = sexual_orientation;
	}

	public String getSubstance() {
		return substance;
	}

	public void setSubstance(String substance) {
		this.substance = substance;
	}

	public String getCurrentorprevious() {
		return currentorprevious;
	}

	public void setCurrentorprevious(String currentorprevious) {
		this.currentorprevious = currentorprevious;
	}



	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public Integer getStart_year() {
		return start_year;
	}

	public void setStart_year(Integer start_year) {
		this.start_year = start_year;
	}

	public Integer getStop_year() {
		return stop_year;
	}

	public void setStop_year(Integer stop_year) {
		this.stop_year = stop_year;
	}

	public String getOther_detail() {
		return other_detail;
	}

	public void setOther_detail(String other_detail) {
		this.other_detail = other_detail;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	
	

}
