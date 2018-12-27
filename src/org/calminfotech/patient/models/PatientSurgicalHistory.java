package org.calminfotech.patient.models;

import java.sql.Date;

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
import org.calminfotech.utils.models.SurgicalProcedures;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "patient_surgicalhistory")
public class PatientSurgicalHistory {
	
	

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
	@JoinColumn(name = "surgical_id")
	private SurgicalProcedures surgicalProcedures;
	
/*	@Column(name = "surgery_date")
	private Date surgery_date;*/
	
	private Integer surgeryyear;
	
	@Column(name = "anasthetic_complication")
	private String anasthetic_complication;
	
	@Column(name = "complication_detail")
	private String complication_detail;
	
	@Column(name = "surgery_hospital")
	private String surgery_hospital;
	
	@Column(name = "hospital_address")
	private String hospital_address;
	
	@Column(name = "hospital_email")
	private String hospital_email;
	
	@Column(name = "hospital_phone")
	private String hospital_phone;
	
	@Column(name = "surgeon_full_name")
	private String surgeon_full_name;
	
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

	public SurgicalProcedures getSurgicalProcedures() {
		return surgicalProcedures;
	}

	public void setSurgicalProcedures(SurgicalProcedures surgicalProcedures) {
		this.surgicalProcedures = surgicalProcedures;
	}

/*	public Date getSurgery_date() {
		return surgery_date;
	}

	public void setSurgery_date(Date surgery_date) {
		this.surgery_date = surgery_date;
	}*/

	public String getAnasthetic_complication() {
		return anasthetic_complication;
	}

	public void setAnasthetic_complication(String anasthetic_complication) {
		this.anasthetic_complication = anasthetic_complication;
	}

	public String getComplication_detail() {
		return complication_detail;
	}

	public void setComplication_detail(String complication_detail) {
		this.complication_detail = complication_detail;
	}

	public String getSurgery_hospital() {
		return surgery_hospital;
	}

	public void setSurgery_hospital(String surgery_hospital) {
		this.surgery_hospital = surgery_hospital;
	}

	public String getHospital_address() {
		return hospital_address;
	}

	public void setHospital_address(String hospital_address) {
		this.hospital_address = hospital_address;
	}

	public String getHospital_email() {
		return hospital_email;
	}

	public void setHospital_email(String hospital_email) {
		this.hospital_email = hospital_email;
	}

	public String getHospital_phone() {
		return hospital_phone;
	}

	public void setHospital_phone(String hospital_phone) {
		this.hospital_phone = hospital_phone;
	}

	public String getSurgeon_full_name() {
		return surgeon_full_name;
	}

	public void setSurgeon_full_name(String surgeon_full_name) {
		this.surgeon_full_name = surgeon_full_name;
	}

	public String getOther_detail() {
		return other_detail;
	}

	public void setOther_detail(String other_detail) {
		this.other_detail = other_detail;
	}



	public Integer getSurgeryyear() {
		return surgeryyear;
	}

	public void setSurgeryyear(Integer surgeryyear) {
		this.surgeryyear = surgeryyear;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	
	
	
	
	
}
