package org.calminfotech.billing.models;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
//@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "bill_scheme_item_price")
public class BillItemPrice {
	
	@Id
	@GeneratedValue
	@Column(name ="bill_item_price_id")
	private Integer billItemPriceId;
	
	@ManyToOne
	@JoinColumn(name = "bill_scheme_item_id")
	private BillSchemeItem billSchemeItem;

	@Column(name="global_item_id")
	private Integer globalItemId;

	@Column(name ="unit_of_measure_id")
	private Integer unitOfMeasure;
	
	@Column(name ="bill_item_price")
	private Double billItemPrice;
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "organisation_id")
	private Integer organisationId;

	public Integer getBillItemPriceId() {
		return billItemPriceId;
	}

	public void setBillItemPriceId(Integer billItemPriceId) {
		this.billItemPriceId = billItemPriceId;
	}

	public Integer getGlobalItemId() {
		return globalItemId;
	}

	public void setGlobalItemId(Integer globalItemId) {
		this.globalItemId = globalItemId;
	}

	

	public Integer getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(Integer unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Double getBillItemPrice() {
		return billItemPrice;
	}

	public void setBillItemPrice(Double billItemPrice) {
		this.billItemPrice = billItemPrice;
	}

	public BillSchemeItem getBillSchemeItem() {
		return billSchemeItem;
	}

	public void setBillSchemeItem(BillSchemeItem billSchemeItem) {
		this.billSchemeItem = billSchemeItem;
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

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}
	

	
}

	
