package org.calminfotech.patient.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "rel_patients_view")
public class RelatedPatientView {
	
	@Id
	@Column(name = "rel_patient_id")
	private Integer relPatientId;
	
	@Column(name = "patient_id")
	private Integer patientId;
	
	
	
	@Column(name = "patient_code")
	private String patientCode;

	@Column(name = "surname")
	private String surname;

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "other_names")
	private String othernames;
	
	@Column(name = "relationship")
	private String relationship;

	@Column(name = "email")
	private String email;

	@Column(name = "genotype")
	private String genotype;

	@Column(name = "bldgrp")
	private String bldgrp;
		
	@Column(name = "organisation_id")
	private Integer organisationId;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatientCode() {
		return patientCode;
	}

	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getOthernames() {
		return othernames;
	}

	public void setOthernames(String othernames) {
		this.othernames = othernames;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenotype() {
		return genotype;
	}

	public void setGenotype(String genotype) {
		this.genotype = genotype;
	}

	public String getBldgrp() {
		return bldgrp;
	}

	public void setBldgrp(String bldgrp) {
		this.bldgrp = bldgrp;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public Integer getRelPatientId() {
		return relPatientId;
	}

	public void setRelPatientId(Integer relPatientId) {
		this.relPatientId = relPatientId;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
}
