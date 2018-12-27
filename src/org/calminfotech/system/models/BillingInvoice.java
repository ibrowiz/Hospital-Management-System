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

import org.calminfotech.consultation.models.Visit;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;
import org.calminfotech.views.models.VwItem;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "billing_invoice")
@SQLDelete(sql = "UPDATE billing_invoice SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class BillingInvoice {
	//variable
	
	private Integer id;
	private LoginSection section;
	private Integer billing;
	private VisitWorkflowPoint point;
	private VwItem item;
	private Visit visit;
	private Patient patient;
	private String quantity;
	private String unitId;
	private Double price;
	private Double amountPaid;
	private Organisation organisation;
	private String status;
	private String createdby;
	private Date createDate;
	private boolean isDeleted;
	//getters and setters
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
	@JoinColumn(name = "section_id")
	public LoginSection getSection() {
		return section;
	}
	public void setSection(LoginSection section) {
		this.section = section;
	}
	
	@Column(name = "billing_id")
	public Integer getBilling() {
		return billing;
	}
	public void setBilling(Integer billing) {
		this.billing = billing;
	}
	
	@ManyToOne
	@JoinColumn(name = "point_id")
	public VisitWorkflowPoint getPoint() {
		return point;
	}
	public void setPoint(VisitWorkflowPoint point) {
		this.point = point;
	}
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	public VwItem getItem() {
		return item;
	}
	public void setItem(VwItem item) {
		this.item = item;
	}
	
	@ManyToOne
	@JoinColumn(name = "visit_id")
	public Visit getVisit() {
		return visit;
	}
	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	@Column(name = "qty")
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	@Column(name = "unit")
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	@Column(name = "price")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Column(name = "payment")
	public Double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return organisation;
	}
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "created_by")
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "is_deleted")
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
