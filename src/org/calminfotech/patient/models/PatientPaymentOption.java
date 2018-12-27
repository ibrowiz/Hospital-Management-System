package org.calminfotech.patient.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.BillingScheme;
import org.calminfotech.system.models.BillingSchemeItem;
import org.calminfotech.utils.models.Organisation;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "patient_payment_scheme")
public class PatientPaymentOption {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;
	
	
	@ManyToOne
	@JoinColumn(name = "payment_scheme_id")
	private BillingScheme paymentScheme; 
	
	
	@ManyToOne
	@JoinColumn(name = "payment_scheme_item_id")
	private BillingSchemeItem paymentSchemeitem;

		
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "created_by")
	private String createdBy;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public BillingScheme getPaymentScheme() {
		return paymentScheme;
	}

	public void setPaymentScheme(BillingScheme paymentScheme) {
		this.paymentScheme = paymentScheme;
	}

	public BillingSchemeItem getPaymentSchemeitem() {
		return paymentSchemeitem;
	}

	public void setPaymentSchemeitem(BillingSchemeItem paymentSchemeitem) {
		this.paymentSchemeitem = paymentSchemeitem;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
