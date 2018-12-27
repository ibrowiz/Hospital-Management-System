package org.calminfotech.consultation.forms;

import org.hibernate.validator.constraints.Range;

public class VisitCloseForm {

	@Range(min = 1, message = "No Visit")
	private Integer visitId;

	@Range(min = 1, message = "No consultation")
	private Integer consultationId;

	@Range(min = 1, message = "Select a status")
	private Integer consultationStatus;

	private String comment;

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
	 * @return the consultationId
	 */
	public Integer getConsultationId() {
		return consultationId;
	}

	/**
	 * @param consultationId
	 *            the consultationId to set
	 */
	public void setConsultationId(Integer consultationId) {
		this.consultationId = consultationId;
	}

	/**
	 * @return the consultationStatus
	 */
	public Integer getConsultationStatus() {
		return consultationStatus;
	}

	/**
	 * @param consultationStatus
	 *            the consultationStatus to set
	 */
	public void setConsultationStatus(Integer consultationStatus) {
		this.consultationStatus = consultationStatus;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

}