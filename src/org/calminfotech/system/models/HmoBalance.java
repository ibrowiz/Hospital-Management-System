package org.calminfotech.system.models;

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

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hmo_balance")
public class HmoBalance {
	// variables
	private Integer id;
	private Patient patientId;
	private EhmoPackages packageId;
	private Integer subservice;
	private Double balance;
	private Organisation organisation;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	// getters and setters;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "balance")
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	public Patient getPatientId() {
		return patientId;
	}
	public void setPatientId(Patient patientId) {
		this.patientId = patientId;
	}
	
	@ManyToOne
	@JoinColumn(name = "package_id")
	public EhmoPackages getPackageId() {
		return packageId;
	}
	public void setPackageId(EhmoPackages packageId) {
		this.packageId = packageId;
	}
	
	@Column(name = "subservice_id")
	public Integer getSubservice() {
		return subservice;
	}
	public void setSubservice(Integer subservice) {
		this.subservice = subservice;
	}
	
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return organisation;
	}
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	
	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "create_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "modified_by")
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@Column(name = "modify_date")
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
