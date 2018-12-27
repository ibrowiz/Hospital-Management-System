package org.calminfotech.consultation.forms;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class RescheduleVisitForm {

	@Range(min = 1)
	private Integer visitId;

	@NotEmpty(message = "Field cannot be empty!")
	private String rescheduleDate;
	
	@NotEmpty(message = "Field cannot be empty!")
	private String reason;
	
	@NotEmpty(message = "Cannot be empty!")
	private String initiator;

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
	 * @return the rescheduleDate
	 */
	public String getRescheduleDate() {
		return rescheduleDate;
	}

	/**
	 * @param rescheduleDate
	 *            the rescheduleDate to set
	 */
	public void setRescheduleDate(String rescheduleDate) {
		this.rescheduleDate = rescheduleDate;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the initiator
	 */
	public String getInitiator() {
		return initiator;
	}

	/**
	 * @param initiator the initiator to set
	 */
	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

}
