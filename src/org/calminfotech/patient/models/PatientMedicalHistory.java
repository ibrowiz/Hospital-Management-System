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
import org.calminfotech.system.models.Ailment;
import org.calminfotech.system.models.Drug;
import org.calminfotech.patient.models.Patient;

import org.calminfotech.utils.models.Organisation;



@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "patient_medical_history")
public class PatientMedicalHistory {
	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_medical_history_id", unique = true, nullable = false)
	private Integer id;
	
/*	@Column(name = "ailment_id")
	private Integer ailment_id;*/
	
	
	/*@Column(name = "drug_id")
	private Integer drug_id;*/
	
	@ManyToOne
	@JoinColumn(name = "drug_id")
	private Drug drug;
	
	
	
	
	@Column(name = "other_drugs")
	private String other_drugs;
	
	
	@Column(name = "other_detail")
	private String other_detail;
		
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;	
	
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "cons_visit_id")
	private Visit visit;
	
	
	
	
	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOther_drugs() {
		return other_drugs;
	}

	public void setOther_drugs(String other_drugs) {
		this.other_drugs = other_drugs;
	}

	public String getOther_detail() {
		return other_detail;
	}

	public void setOther_detail(String other_detail) {
		this.other_detail = other_detail;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}


	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	
	
/*	public Integer getAilment_id() {
		return ailment_id;
	}

	public void setAilment_id(Integer ailment_id) {
		this.ailment_id = ailment_id;
	}

	public Integer getDrug_id() {
		return drug_id;
	}

	public void setDrug_id(Integer drug_id) {
		this.drug_id = drug_id;
	}*/

	
	
	
}
