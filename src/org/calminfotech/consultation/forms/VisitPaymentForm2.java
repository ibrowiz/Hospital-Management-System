package org.calminfotech.consultation.forms;


import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class VisitPaymentForm2 {
	
	@Range(min = 1, message = "Cannot make payment without a visit")
	private Integer visitId;

	@Range(min = 1, message = "select a package")
	private Integer packageId;

	@Valid
	@NotEmpty(message = "Select a payment")
	private List<Integer> item;

	@NotEmpty(message = "Ensure payment type is set")
	private String paymentType;
	

	

	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public List<Integer> getItem() {
		return item;
	}

	public void setItem(List<Integer> item) {
		this.item = item;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	
	
	

}
