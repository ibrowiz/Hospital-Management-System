package org.calminfotech.hmo.models;

//import java.sql.Blob;
//import java.util.List;
//import java.util.Set;

//import javax.persistence.CascadeType;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "ehmo_package_item")
public class EhmoPackageItem {
	
	@Id
	@GeneratedValue
	@Column(name ="item_id")
	private Integer itemId;
	
	@Column(name ="name")
	private String name;

	@Column(name="description")
	private String description;

	@Column(name ="item_package_id")
	private Integer packageId;
	
	@Column(name ="service_group_id")
	private Integer serviceGroup;

	@Column(name="spending_limit")
	private String spendingLimit;
	
	@Column(name="period")
	private String period;
	
	@Column(name="period_no")
	private Integer periodNo;
	
	@Column(name="time_no")
	private Integer timeNo;
	
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
	


	public Integer getServiceGroup() {
		return serviceGroup;
	}
	public void setServiceGroup(Integer serviceGroup) {
		this.serviceGroup = serviceGroup;
	}



	@Column(name ="item_service_id")
	private Integer itemServiceId;
	
	@ManyToOne
	@JoinColumn(name = "package_id")
	private EhmoPackage ehmoPackage;
	
	@ManyToOne
	@JoinColumn(name = "hmo_id")
	private Ehmo ehmo;

	
	public Integer getItemServiceId() {
		return itemServiceId;
	}


	public void setItemServiceId(Integer itemServiceId) {
		this.itemServiceId = itemServiceId;
	}


	public Integer getPackageId() {
		return packageId;
	}


	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public EhmoPackage getEhmoPackage() {
		return ehmoPackage;
	}
	public void setEhmoPackage(EhmoPackage ehmoPackage) {
		this.ehmoPackage = ehmoPackage;
	}
	public Integer getItemId() {
		return itemId;
	}


	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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

	public Ehmo getEhmo() {
		return ehmo;
	}


	public void setEhmo(Ehmo ehmo) {
		this.ehmo = ehmo;
	}
	public String getSpendingLimit() {
		return spendingLimit;
	}
	public void setSpendingLimit(String spendingLimit) {
		this.spendingLimit = spendingLimit;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Integer getTimeNo() {
		return timeNo;
	}
	public void setTimeNo(Integer timeNo) {
		this.timeNo = timeNo;
	}
	public Integer getPeriodNo() {
		return periodNo;
	}
	public void setPeriodNo(Integer periodNo) {
		this.periodNo = periodNo;
	}
	
	
}

	
