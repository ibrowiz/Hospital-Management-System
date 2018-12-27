package org.calminfotech.consultation.forms;

import java.util.List;


import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class VisitPaymentForm {

	@Range(min = 1, message = "Cannot make payment without a visit")
	private Integer visitId;

	@Range(min = 1, message = "select a package")
	private Integer packageId;

	@Valid
	@NotEmpty(message = "Select a payment")
	private List<Integer> item;
	
	
/*	@Range(min = 1, message = "select a scheme")
	private Integer SchemeId;

	@Valid
	@NotEmpty(message = "Select a payment")
	private List<Integer> item;
	*/
	

	@NotEmpty(message = "Ensure payment type is set")
	private String paymentType;
	

	private String amount;

	/**
	 * @return the visitId
	 */
	public Integer getVisitId() {
		return visitId;
	}

	/**
	 * @param visitId
	 *            the visitId to set
	 */
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	/**
	 * @return the packageId
	 */
/*	public Integer getPackageId() {
		return packageId;
	}

	/**
	 * @param packageId
	 *            the packageId to set
	 *//*
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	/**
	 * @return the item
	 */
	public List<Integer> getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(List<Integer> item) {
		this.item = item;
	}

	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType
	 *            the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

/*	public Integer getSchemeId() {
		return SchemeId;
	}

	public void setSchemeId(Integer schemeId) {
		SchemeId = schemeId;
	}
*/
}
