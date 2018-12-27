package org.calminfotech.consultation.forms;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class ItemsPaymentForm {

	@Range(min = 1, message = "Cannot make payment without a visit")
	private Integer visitId;

	@Valid
	@NotEmpty(message = "Select an item")
	private List<Integer> packageId = new ArrayList<Integer>();

	@Valid
	@NotEmpty(message = "Select an item")
	private List<Integer> item = new ArrayList<Integer>();

	@NotEmpty(message = "Ensure payment type is set")
	private String paymentType;

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
	public List<Integer> getPackageId() {
		return packageId;
	}

	/**
	 * @param packageId
	 *            the packageId to set
	 */
	public void setPackageId(List<Integer> packageId) {
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

}
