package org.calminfotech.hmo.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import org.hibernate.annotations.SQLDelete;
//import org.hibernate.annotations.Where;

import org.calminfotech.billing.models.BillScheme;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientHmo;
import org.calminfotech.system.models.EhmoPackages;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

//import org.hibernate.annotations.SQLDelete;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "ehmo")
@SQLDelete(sql = "UPDATE ehmo SET is_deleted = 1 WHERE hmo_id = ?")
@Where(clause = "is_deleted <> 1")
public class Ehmo {

	@Id
	@GeneratedValue
	@Column(name ="hmo_id")
	private Integer hmoId;
	
	@Column(name ="organisation_id")
	private Integer organisationId;
	
	@Column(name = "status")
	private String status;

	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@OneToMany
	@JoinColumn(name = "hmo_id")
	private Set<EhmoPackages> ehmoPackage;
	
	/*@OneToMany
	@JoinColumn(name = "hmo_id")
	private Set<EhmoPackageItem> ehmoItem;*/
	
	/*@OneToMany
	@JoinColumn(name = "hmo_id")
	private Set<PatientHmo> patientHmo;*/
	/*@ManyToOne
	@JoinColumn(name = "patient_hmo_id")
	private PatientHmo patientHmo;*/
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "bank")
	private String bank;
	
	@Column(name = "bank_account")
	private String bankAccount;
	
	@Column(name = "admin_name")
	private String adminName;
	
	@Column(name = "admin_email")
	private String adminEmail;
	
	@Column(name = "admin_phone")
	private String adminPhone;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@Column(name = "bill_scheme_id")
	private Integer billSchemeId;
	
	
	/*public Set<PatientHmo> getPatientHmo() {
		return patientHmo;
	}

	public void setPatientHmo(Set<PatientHmo> patientHmo) {
		this.patientHmo = patientHmo;
	}*/

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Integer getHmoId() {
		return hmoId;
	}

	public void setHmoId(Integer hmoId) {
		this.hmoId = hmoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	

	
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public Set<EhmoPackages> getEhmoPackage() {
		return ehmoPackage;
	}

	public void setEhmoPackage(Set<EhmoPackages> ehmoPackage) {
		this.ehmoPackage = ehmoPackage;
	}

	public Integer getBillSchemeId() {
		return billSchemeId;
	}

	public void setBillSchemeId(Integer billSchemeId) {
		this.billSchemeId = billSchemeId;
	}

	/*public PatientHmo getPatientHmo() {
		return patientHmo;
	}

	public void setPatientHmo(PatientHmo patientHmo) {
		this.patientHmo = patientHmo;
	}*/


	
}
	