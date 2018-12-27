package org.calminfotech.consultation.forms;


import java.util.List;

import javax.persistence.Entity;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
public class BillingForm {
	private Integer id;
	private Integer sectionId;
	private String sectionName;
	private Integer billingId;
	private String billingName;
	private Integer currentPiointId;
	private String currentPiointName;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Valid
	@NotEmpty(message = "Select a payment")
	private Integer item;
	
	@NotEmpty(message = "Ensure payment type is set")
	private String itemType;
	
	@NotEmpty(message = "Ensure payment type is set")
	private String unitofmeasure;
	
	@NotEmpty(message = "Ensure payment type is set")
	private Double price;
	
	@Range(min = 1, message = "Cannot make payment without a visit")
	private Integer visitId;

	@Range(min = 1, message = "select a package")
	private Integer patientId;

	@Valid
	@NotEmpty(message = "Select a payment")
	private List<Integer> items;

	@NotEmpty(message = "Ensure payment type is set")
	private String paymentType;

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public Integer getBillingId() {
		return billingId;
	}

	public void setBillingId(Integer billingId) {
		this.billingId = billingId;
	}

	public String getBillingName() {
		return billingName;
	}

	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}

	public Integer getCurrentPiointId() {
		return currentPiointId;
	}

	public void setCurrentPiointId(Integer currentPiointId) {
		this.currentPiointId = currentPiointId;
	}

	public String getCurrentPiointName() {
		return currentPiointName;
	}

	public void setCurrentPiointName(String currentPiointName) {
		this.currentPiointName = currentPiointName;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getUnitofmeasure() {
		return unitofmeasure;
	}

	public void setUnitofmeasure(String unitofmeasure) {
		this.unitofmeasure = unitofmeasure;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
		
	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public List<Integer> getItems() {
		return items;
	}

	public void setItems(List<Integer> items) {
		this.items = items;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

}
