package org.calminfotech.patient.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class PatientForm {

	private int patientId;
	
	
	private String surname;

	@NotBlank(message = "Field cannot be empty!")
	private String firstName;
	
	private String othernames;

	@Email(message = "Invalid email format")
	private String email;
	
	private String occupation;

	private String homeNumber;
	
	private String genotype;
	
	private String bldgrp;
	
	private String height;
	
	
	
	
	private String phoneNumber;

	private String dob;

	private String address;
	
	@Range(message = "Please select a LGA")
	private Integer lgaId;

	@Range(message = "Please select a State")
	private Integer stateId;
	
	@Range(min = 1, message = "Please select a marital status")
	private Integer statusId;
	
	@Range(min = 1, message = "Select a title")
	private Integer titleId;

	@Range(min = 1, message = "Select a language")
	private Integer languageId;

	@Range(min = 1, message = "Select a gender type")
	private Integer genderId;
	
	private String status;
	
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Integer getLgaId() {
		return lgaId;
	}

	public void setLgaId(Integer lgaId) {
		this.lgaId = lgaId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
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

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}


	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
