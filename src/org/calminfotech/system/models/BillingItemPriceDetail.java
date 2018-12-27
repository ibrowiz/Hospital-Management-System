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

import org.calminfotech.utils.models.Organisation;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "billing_item_price_detail")
public class BillingItemPriceDetail {
	
	private Integer id;
	private BillingSchemeItem billingSchemeItem;//name changed
	//private ItemDistribution itemDistribution;
	private String price;
	private Integer quantity;
	private GlobalUnitofMeasure unitofMeasure;
	private String comment;
	private Date createdDate;
	private Organisation organisation;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "item_id")
	public BillingSchemeItem getBillingSchemeItem() {
		return billingSchemeItem;
	}

	public void setBillingSchemeItem(BillingSchemeItem billingSchemeItem) {
		this.billingSchemeItem = billingSchemeItem;
	}

	@Column(name = "price") 
	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}

	@ManyToOne
	@JoinColumn(name = "unit_id")
	public GlobalUnitofMeasure getUnitofMeasure() {
		return unitofMeasure;
	}


	public void setUnitofMeasure(GlobalUnitofMeasure unitofMeasure) {
		this.unitofMeasure = unitofMeasure;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}



	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return organisation;
	}


	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	@Column(name = "quantity") 
	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
