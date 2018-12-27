package org.calminfotech.billing.models;

import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.calminfotech.hmo.models.Ehmo;
import org.calminfotech.hmo.models.EhmoPackage;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "bill_scheme")
@SQLDelete(sql = "UPDATE bill_scheme SET is_deleted = 1 WHERE hmo_id = ?")
@Where(clause = "is_deleted <> 1")
public class BillScheme {
	
	@Id
	@GeneratedValue
	@Column(name = "bill_id")
	private Integer billId;
	
	@Column(name = "organisation_id")
	private Integer organisationId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "billing_type")
	private Integer billingType;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;
	@Column(name = "is_deleted")
	private boolean isDeleted;
	@Column(name = "status")
	private String status;
	
	@OneToMany
	@JoinColumn(name = "bill_id")
	private Set<BillSchemeItem> billSchemeItem;
	
	@OneToMany
	@JoinColumn(name = "bill_id")
	private Set<EhmoPackage> ehmoPackage;
	
	public Set<EhmoPackage> getEhmoPackage() {
		return ehmoPackage;
	}

	public void setEhmoPackage(Set<EhmoPackage> ehmoPackage) {
		this.ehmoPackage = ehmoPackage;
	}

	public Set<BillSchemeItem> getBillSchemeItem() {
		return billSchemeItem;
	}

	public void setBillSchemeItem(Set<BillSchemeItem> billSchemeItem) {
		this.billSchemeItem = billSchemeItem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}*/

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getBillingType() {
		return billingType;
	}

	public void setBillingType(Integer billingType) {
		this.billingType = billingType;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

}
