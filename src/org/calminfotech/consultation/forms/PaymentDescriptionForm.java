package org.calminfotech.consultation.forms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.calminfotech.consultation.models.PaymentItem;

public class PaymentDescriptionForm {

	private String patientName;
	private String patientCode;
	private String visitCode;
	private List<PaymentItem> items = new ArrayList<PaymentItem>();
	private BigDecimal totalAmountToBePaid;

	/**
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName
	 *            the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @return the patientCode
	 */
	public String getPatientCode() {
		return patientCode;
	}

	/**
	 * @param patientCode
	 *            the patientCode to set
	 */
	public void setPatientCode(String patientCode) {
		this.patientCode = patientCode;
	}

	/**
	 * @return the visitCode
	 */
	public String getVisitCode() {
		return visitCode;
	}

	/**
	 * @param visitCode
	 *            the visitCode to set
	 */
	public void setVisitCode(String visitCode) {
		this.visitCode = visitCode;
	}

	/**
	 * @return the items
	 */
	public List<PaymentItem> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<PaymentItem> items) {
		this.items = items;
	}

	/**
	 * @return the totalAmountToBePaid
	 */
	public BigDecimal getTotalAmountToBePaid() {
		return totalAmountToBePaid;
	}

	/**
	 * @param totalAmountToBePaid
	 *            the totalAmountToBePaid to set
	 */
	public void setTotalAmountToBePaid(BigDecimal totalAmountToBePaid) {
		this.totalAmountToBePaid = totalAmountToBePaid;
	}

}
