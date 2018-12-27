package org.calminfotech.billing.models;

//import java.sql.Blob;
//import java.util.List;
//import java.util.Set;

//import javax.persistence.CascadeType;
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


@Entity
@Table(name = "bill_scheme_item")
public class BillSchemeItem {
	
	@Id
	@GeneratedValue
	@Column(name ="bill_scheme_item_id")
	private Integer billSchemeItemId;
	
	@Column(name ="description")
	private String billName;

	@Column(name ="global_item_id")
	private Integer globalItemId;
	
	@Column(name ="organisation_id")
	private Integer organisationId;
	
	@ManyToOne
	@JoinColumn(name = "bill_id")
	private BillScheme billScheme;
	
	@OneToMany
	@JoinColumn(name = "bill_scheme_item_id")
	private Set<BillItemPrice> billItemPrice;
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

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

	public Set<BillItemPrice> getBillItemPrice() {
		return billItemPrice;
	}

	public void setBillItemPrice(Set<BillItemPrice> billItemPrice) {
		this.billItemPrice = billItemPrice;
	}

	public BillScheme getBillScheme() {
		return billScheme;
	}

	public void setBillScheme(BillScheme billScheme) {
		this.billScheme = billScheme;
	}

	public Integer getBillSchemeItemId() {
		return billSchemeItemId;
	}

	public void setBillSchemeItemId(Integer billSchemeItemId) {
		this.billSchemeItemId = billSchemeItemId;
	}

	/*public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}*/

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}


	public Integer getGlobalItemId() {
		return globalItemId;
	}

	public void setGlobalItemId(Integer globalItemId) {
		this.globalItemId = globalItemId;
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


	


	
	

	
}

	
