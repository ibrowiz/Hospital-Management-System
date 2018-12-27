package org.calminfotech.system.models;

import java.util.Date;

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
@Table(name = "billing_scheme")
@SQLDelete(sql = "UPDATE billing_scheme SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class BillingScheme {
	
	private Integer id;
	
	private String name;
	
	private String description;
	
	private String created_by;
		
	private Date createDate;
	
	private int	type;
	
	private Organisation organisation;
	
	private Set<BillingSchemeItem> paymentSchemeItem;

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

	
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "billingscheme_id")
	public Set<BillingSchemeItem> getPaymentSchemeItem() {
		return paymentSchemeItem;
	}

	public void setPaymentSchemeItem(Set<BillingSchemeItem> paymentSchemeItem) {
		this.paymentSchemeItem = paymentSchemeItem;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
