package org.calminfotech.consultation.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.system.models.PaymentSchemeItem;
import org.calminfotech.utils.models.Organisation;



@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "consultation_paymentlog")
public class VisitPayment {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "visit_id")
	private Visit visit;
	
	@Column(name = "point_name")
	private String pointName;
	
	/*//this is the plan id
	@ManyToOne
	@JoinColumn(name = "hmo_package_id")
	private OrganisationHmoPackage hmoPackage;
	
	// this is the service and amount paid
	@JoinColumn(name = "hmo_package_item_id")
	private Integer hmoPackageItemId;*/
	
	
	@ManyToOne
	@JoinColumn(name = "paymentscheme_id")
	private PaymentSchemeItem  hmoPackage;
	
	// this is the service and amount paid
	@JoinColumn(name = "paymentscheme_id")
	private Integer hmoPackageItemId;
	
	@Column(name = "amount")
	private String amount;
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;
	
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}




	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
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

	public PaymentSchemeItem getHmoPackage() {
		return hmoPackage;
	}

	public void setHmoPackage(PaymentSchemeItem hmoPackage) {
		this.hmoPackage = hmoPackage;
	}

	public Integer getHmoPackageItemId() {
		return hmoPackageItemId;
	}

	public void setHmoPackageItemId(Integer hmoPackageItemId) {
		this.hmoPackageItemId = hmoPackageItemId;
	}

	


		 
	
	

}
