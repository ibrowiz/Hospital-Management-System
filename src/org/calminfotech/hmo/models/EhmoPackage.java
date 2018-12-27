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
import javax.persistence.Table;

import org.calminfotech.billing.models.BillScheme;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientHmo;

@Entity
@Table(name = "ehmo_package")

public class EhmoPackage {
	
	@Id
	@GeneratedValue
	@Column(name ="package_id")
	private Integer packageId;
	
	/*@OneToMany
	@JoinColumn(name = "package_id")
	private Set<EhmoPackageItem> ehmoItem;*/
	
	/*@OneToMany
	@JoinColumn(name = "package_id")
	private Set<PatientHmo> patientHmo;*/
		
	@Column(name ="name")
	private String name;	
	
	@ManyToOne
	@JoinColumn(name = "hmo_id")
	private Ehmo ehmo;
	
	/*@ManyToOne
	@JoinColumn(name = "bill_id")
	private BillScheme billScheme;*/
	
	@Column(name ="organisation_id")
	private Integer organisationId;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	

	/*public Set<PatientHmo> getPatientHmo() {
		return patientHmo;
	}

	public void setPatientHmo(Set<PatientHmo> patientHmo) {
		this.patientHmo = patientHmo;
	}*/

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
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

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	/*public BillScheme getBillScheme() {
		return billScheme;
	}

	public void setBillScheme(BillScheme billScheme) {
		this.billScheme = billScheme;
	}*/

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ehmo getEhmo() {
		return ehmo;
	}

	public void setEhmo(Ehmo ehmo) {
		this.ehmo = ehmo;
	}

	/*public Set<EhmoPackageItem> getEhmoItem() {
		return ehmoItem;
	}

	public void setEhmoItem(Set<EhmoPackageItem> ehmoItem) {
		this.ehmoItem = ehmoItem;
	}*/

	

	
}

	
