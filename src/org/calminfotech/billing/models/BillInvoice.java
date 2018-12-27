package org.calminfotech.billing.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "bill_invoice")
@SQLDelete(sql = "UPDATE bill_invoice SET is_deleted = 1 WHERE invoiceId = ?")
@Where(clause = "is_deleted <> 1")


public class BillInvoice {
	//variables
	@Id
	@GeneratedValue
	@Column(name = "invoice_id")
	private Integer invoiceId;
	
	@Column(name= "section_id")
	private Integer sectionId;

	@Column(name= "bill_id")
	private Integer billId;
	
	@Column(name= "point_id")
	private Integer pointId;

	@Column(name= "item_id")
    private Integer billItemId;

	@Column(name= "visit_id")
	private Integer visitId;

	@Column(name= "patient_id")
	private Integer patientId;

	@Column(name= "quantity")
	private String billQuantity;

	@Column(name= "unit_id")
	private String billUnit;

	@Column(name= "price")
	private Double billPrice;

	@Column(name= "amount_paid")
	private Double amountPaid;

	@Column(name= "organisation_id")
	private Integer organisation;

	@Column(name= "status")
	private String status;

	@Column(name= "created_by")
	private String createdBy;

	@Column(name= "created_date")
	private Date createdDate;
	
	@Column(name= "modified_by")
	private String modifiedBy;
	
	@Column(name= "modified_date")
	private Date modifiedDate;

	@Column(name= "is_deleted")
	private boolean isDeleted;
	
	
}
