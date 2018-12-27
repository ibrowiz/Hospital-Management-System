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
@Table(name = "payment_log")
public class PaymentLog {
	// variables
	private Integer id;
	private BillingInvoice billingId;
	private Double amount;
	private EhmoPackages packageId;
	private String paymentType;
	private Integer subservice;
	private Organisation organisation;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	// getters and setters
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "billing_id")
	public BillingInvoice getBillingId() {
		return billingId;
	}
	public void setBillingId(BillingInvoice billingId) {
		this.billingId = billingId;
	}
	
	@Column(name = "amount")
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Column(name = "payment_type")
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	@ManyToOne
	@JoinColumn(name = "package_id")
	public EhmoPackages getPackageId() {
		return packageId;
	}
	public void setPackageId(EhmoPackages packageId) {
		this.packageId = packageId;
	}
	
	@Column(name="subservice_id")
	public Integer getSubservice() {
		return subservice;
	}
	public void setSubservice(Integer subservice) {
		this.subservice = subservice;
	}
	
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return organisation;
	}
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	
	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "create_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "modified_by")
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@Column(name = "modify_date")
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public static final String HMO_PAYMENT_TYPE = "hmo";
	public static final String ATM_PAYMENT_TYPE = "atm";
	public static final String DIRECT_PAYMENT_TYPE = "cash";
}
