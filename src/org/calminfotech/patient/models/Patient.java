package org.calminfotech.patient.models;
import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.consultation.models.Visit;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.patient.models.PatientDocument;
import org.calminfotech.patient.models.PatientEmergency;
import org.calminfotech.patient.models.PatientFamilyHistory;
import org.calminfotech.patient.models.PatientHmoPackage;
import org.calminfotech.patient.models.PatientMedicalHistory;
import org.calminfotech.patient.models.PatientPaymentOption;
import org.calminfotech.patient.models.PatientSocialHistory;
import org.calminfotech.patient.models.PatientSurgicalHistory;
import org.calminfotech.setup.models.Allergy;
import org.calminfotech.system.models.Gender;
import org.calminfotech.user.models.Language;
import org.calminfotech.user.models.Title;
import org.calminfotech.utils.models.LocalGovernmentArea;
import org.calminfotech.utils.models.MaritalStatus;
import org.calminfotech.utils.models.Organisation;
import org.calminfotech.utils.models.State;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "patients1")
@SQLDelete(sql = "UPDATE patients1 SET is_deleted = 1 WHERE patient_id = ?")
//@SQLInsert(sql="")
@Where(clause = "is_deleted <> 1")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id", unique = true, nullable = false)
	private Integer patientId;
	
	@Column(name = "patient_code")
	private String patientCode;

	@Column(name = "surname")
	private String surname;

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "other_names")
	private String othernames;
	
	@Column(name = "occupation")
	private String occupation;

	@Column(name = "dob")
	private String dob;

	@Column(name = "email")
	private String email;

	@Column(name = "home_number")
	private String homeNumber;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "genotype")
	private String genotype;

	@Column(name = "bldgrp")
	private String bldgrp;

	/*@Column(name = "height_feet")
	private String heightFeet;
	
	@Column(name = "height_inch")
	private String heightInch;*/
	
	@Column(name = "height")
	private String height;
	


	@Column(name = "status")
	private String status;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "is_deleted")
	private int isDeleted;

	@Column(name = "modify_date", nullable = true)
	private Date modifiedDate;
	
	@Column(name = "modified_by", nullable = true)
	private int modifiedBy;

	@ManyToOne
	@JoinColumn(name = "gender_id")
	private Gender gender;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	@ManyToOne
	@JoinColumn(name = "title_id")
	private Title title;

	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private MaritalStatus maritalStatus;

	@ManyToOne
	@JoinColumn(name = "lga_id")
	private LocalGovernmentArea lga;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;

	@Column(name = "patient_image", nullable = true)
	private Blob image;

	@Column(name = "image_content_type", nullable = true)
	private String imageContentType;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="patient_id")
	private Set<RelatedPatient> relPatient;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="pat_id")
	private Set<PatientAllergy> patientAllergy ;
	
	@OneToMany
	@JoinColumn(name = "patient_id")
	private Set<PatientDocument> patientDocuments = new HashSet<PatientDocument>();
	
	@OneToMany
	@JoinColumn(name = "patient_id")
	private Set<Visit> visit = new HashSet<Visit>();

	@OneToMany(mappedBy = "pk.patient")
	private Set<PatientHmoPackage> hmoPackages = new HashSet<PatientHmoPackage>();

	@OneToMany
	@JoinColumn(name = "patient_id")
	private Set<PatientEmergency> patientEmergency = new HashSet<PatientEmergency>();

	@OneToMany
	@JoinColumn(name = "patient_id")
	private Set<PatientMedicalHistory> patientmedicalhistory = new HashSet<PatientMedicalHistory>();

	@OneToMany
	@JoinColumn(name = "patient_id")
	private Set<PatientFamilyHistory> patientfamilyhistory = new HashSet<PatientFamilyHistory>();
	
	@OneToMany
	@JoinColumn(name = "patient_id")
	private Set<PatientPaymentOption> patientpaymentoption = new HashSet<PatientPaymentOption>();
	
	@OneToMany
	@JoinColumn(name = "patient_id")
	private Set<PatientSurgicalHistory> patientSurgicalHistory = new HashSet<PatientSurgicalHistory>();
	
	@OneToMany
	@JoinColumn(name = "patient_id")
	private Set<PatientSocialHistory> patientSocialHistory = new HashSet<PatientSocialHistory>();
	
	
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public Set<PatientDocument> getPatientDocuments() {
		return patientDocuments;
	}

	public void setPatientDocument(Set<PatientDocument> patientDocuments) {
		this.patientDocuments = patientDocuments;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public LocalGovernmentArea getLga() {
		return lga;
	}

	public void setLga(LocalGovernmentArea lga) {
		this.lga = lga;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	/**
	 * @return the hmoPackages
	 */
	public Set<PatientHmoPackage> getHmoPackages() {
		return hmoPackages;
	}

	/**
	 * @param hmoPackages
	 *            the hmoPackages to set
	 */
	public void setHmoPackages(Set<PatientHmoPackage> hmoPackages) {
		this.hmoPackages = hmoPackages;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Set<PatientEmergency> getPatientEmergency() {
		return patientEmergency;
	}

	public void setPatientEmergency(Set<PatientEmergency> patientEmergency) {
		this.patientEmergency = patientEmergency;
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

	/*public String getHeightFeet() {
		return heightFeet;
	}

	public void setHeightFeet(String heightFeet) {
		this.heightFeet = heightFeet;
	}

	public String getHeightInch() {
		return heightInch;
	}

	public void setHeightInch(String heightInch) {
		this.heightInch = heightInch;
	}*/

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}


	public Set<PatientMedicalHistory> getPatientmedicalhistory() {
		return patientmedicalhistory;
	}

	public void setPatientmedicalhistory(
			Set<PatientMedicalHistory> patientmedicalhistory) {
		this.patientmedicalhistory = patientmedicalhistory;
	}

	public Set<PatientFamilyHistory> getPatientfamilyhistory() {
		return patientfamilyhistory;
	}

	public void setPatientfamilyhistory(
			Set<PatientFamilyHistory> patientfamilyhistory) {
		this.patientfamilyhistory = patientfamilyhistory;
	}

	/**
	 * @return the allergies
	 */
	
	public void setPatientDocuments(Set<PatientDocument> patientDocuments) {
		this.patientDocuments = patientDocuments;
	}
	
	public Set<PatientPaymentOption> getPatientpaymentoption() {
		return patientpaymentoption;
	}

	public void setPatientpaymentoption(
			Set<PatientPaymentOption> patientpaymentoption) {
		this.patientpaymentoption = patientpaymentoption;
	}

	public Set<PatientSurgicalHistory> getPatientSurgicalHistory() {
		return patientSurgicalHistory;
	}

	public void setPatientSurgicalHistory(
			Set<PatientSurgicalHistory> patientSurgicalHistory) {
		this.patientSurgicalHistory = patientSurgicalHistory;
	}

	public Set<PatientSocialHistory> getPatientSocialHistory() {
		return patientSocialHistory;
	}

	public void setPatientSocialHistory(
			Set<PatientSocialHistory> patientSocialHistory) {
		this.patientSocialHistory = patientSocialHistory;
	}

	public Set<Visit> getVisit() {
		return visit;
	}

	public void setVisit(Set<Visit> visit) {
		this.visit = visit;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Set<RelatedPatient> getRelPatient() {
		return relPatient;
	}

	public void setRelPatient(Set<RelatedPatient> relPatient) {
		this.relPatient = relPatient;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Set<PatientAllergy> getPatientAllergy() {
		return patientAllergy;
	}

	public void setPatientAllergy(Set<PatientAllergy> patientAllergy) {
		this.patientAllergy = patientAllergy;
	}

	/*public Set<Allergy> getAllergy() {
		return allergy;
	}

	public void setAllergy(Set<Allergy> allergy) {
		this.allergy = allergy;
	}*/

	
}
