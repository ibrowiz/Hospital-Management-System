package org.calminfotech.system.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;

import org.calminfotech.utils.models.Organisation;



@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "payment_scheme_item")
public class PaymentSchemeItem {
	

	private PaymentScheme paymentscheme;
	
	private GlobalItemType itemType;
	
	
	private GlobalItem globalItem;
	
	private Integer id;


	private String createdBy;

	
	private Date createdDate;
	
	
	private Organisation organisation;
	
	
	private Set<BillingItemPriceDetail> itemPriceDetail = new HashSet<BillingItemPriceDetail>();
	
	@ManyToOne
	@JoinColumn(name = "paymentscheme_id")
	public PaymentScheme getPaymentscheme() {
		return paymentscheme;
	}

	public void setPaymentscheme(PaymentScheme paymentscheme) {
		this.paymentscheme = paymentscheme;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	
	@ManyToOne
	@JoinColumn(name = "type_id")
	public GlobalItemType getItemType() {
		return itemType;
	}

	public void setItemType(GlobalItemType itemType) {
		this.itemType = itemType;
	}

	
	@ManyToOne
	@JoinColumn(name = "item_id")
	public GlobalItem getGlobalItem() {
		return globalItem;
	}

	public void setGlobalItem(GlobalItem itemDistribution) {
		this.globalItem = itemDistribution;
	}

	
	@OneToMany
	@JoinColumn(name = "item_id")
	public Set<BillingItemPriceDetail> getItemPriceDetail() {
		return itemPriceDetail;
	}

	public void setItemPriceDetail(Set<BillingItemPriceDetail> itemPriceDetail) {
		this.itemPriceDetail = itemPriceDetail;
	}
}
