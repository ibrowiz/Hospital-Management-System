package org.calminfotech.patient.models;

import java.util.Date;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.calminfotech.consultation.models.Visit;
import org.calminfotech.disease.models.Diseases;
import org.calminfotech.system.models.Disease;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "patient_familyhistory")
public class PatientFamilyHistory 
{
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
	
	@ManyToOne
	@JoinColumn(name = "disease_id")
	private Diseases disease;
	
	@Column(name = "family_line")
	private String familyLine;
	
	
	@Column(name = "no_of_occurence")
	private Integer no_of_occurence;
	
	
	@Column(name = "no_of_casualties")
	private Integer no_of_casualties;
	
	
	@Column(name = "other_detail")
	private String other_detail;
	
	@Column(name = "created_date")
	private Date createDate;
	
	
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


	public Integer getNo_of_occurence() {
		return no_of_occurence;
	}

	public void setNo_of_occurence(Integer no_of_occurence) {
		this.no_of_occurence = no_of_occurence;
	}

	public String getOther_detail() {
		return other_detail;
	}

	public void setOther_detail(String other_detail) {
		this.other_detail = other_detail;
	}

	public Integer getNo_of_casualties() {
		return no_of_casualties;
	}

	public void setNo_of_casualties(Integer no_of_casualties) {
		this.no_of_casualties = no_of_casualties;
	}

	
	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public Diseases getDisease() {
		return disease;
	}

	public void setDisease(Diseases disease) {
		this.disease = disease;
	}

	public String getFamilyLine() {
		return familyLine;
	}

	public void setFamilyLine(String familyLine) {
		this.familyLine = familyLine;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	
	
	

}
