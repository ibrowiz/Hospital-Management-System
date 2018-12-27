package org.calminfotech.patient.models;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.user.models.Title;
import org.calminfotech.utils.models.Organisation;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "patient_nok")
public class PatientEmergency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "relationship")
	private String relationship;

	@Column(name = "email_id")
	private String email_id;

	@Column(name = "home_address")
	private String homeAddress;

	@Column(name = "mobile_no1")
	private String mobileNo1;

	@Column(name = "mobile_no2")
	private String mobileNo2;

	@Column(name = "other_detail")
	private String otherDetail;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "title_id")
	private Title title;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getMobileNo1() {
		return mobileNo1;
	}

	public void setMobileNo1(String mobileNo1) {
		this.mobileNo1 = mobileNo1;
	}

	public String getMobileNo2() {
		return mobileNo2;
	}

	public void setMobileNo2(String mobileNo2) {
		this.mobileNo2 = mobileNo2;
	}

	public String getOtherDetail() {
		return otherDetail;
	}

	public void setOtherDetail(String otherDetail) {
		this.otherDetail = otherDetail;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
}
