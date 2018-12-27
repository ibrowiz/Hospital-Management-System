package org.calminfotech.system.models;

import java.util.Date;
import java.util.HashSet;

import java.util.Set;


import javax.persistence.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import org.calminfotech.utils.models.Organisation;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;



@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "payment_scheme")
@SQLDelete(sql = "UPDATE payment_scheme SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class PaymentScheme {
	
	
	private Integer id;
	
	private String name;
	
	private String description;
	
	private String created_by;
	
	
	private Date createdDate;
	

	private Organisation organisation;
	

	private Set<PaymentSchemeItem> paymentSchemeItem;
	
	private Set<BillingItemPriceDetail> itemPriceDetail = new HashSet<BillingItemPriceDetail>();

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "paymentscheme_id")
	public Set<PaymentSchemeItem> getPaymentSchemeItem() {
		return paymentSchemeItem;
	}

	public void setPaymentSchemeItem(Set<PaymentSchemeItem> paymentSchemeItem) {
		this.paymentSchemeItem = paymentSchemeItem;
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
