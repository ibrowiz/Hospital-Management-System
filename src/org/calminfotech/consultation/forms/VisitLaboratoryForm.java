package org.calminfotech.consultation.forms;

import org.calminfotech.utils.annotations.FormFieldName;
import org.hibernate.validator.constraints.NotEmpty;

public class VisitLaboratoryForm {

	private Integer visitId;

	@FormFieldName(name = "Sample Field 1")
	@NotEmpty
	private String sampleField1;

	@FormFieldName(name = "Sample Field 2")
	@NotEmpty
	private String sampleField2;

	@FormFieldName(name = "Comments")
	private String comments;

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
	 * @return the sampleField1
	 */
	public String getSampleField1() {
		return sampleField1;
	}

	/**
	 * @param sampleField1
	 *            the sampleField1 to set
	 */
	public void setSampleField1(String sampleField1) {
		this.sampleField1 = sampleField1;
	}

	/**
	 * @return the sampleField2
	 */
	public String getSampleField2() {
		return sampleField2;
	}

	/**
	 * @param sampleField2
	 *            the sampleField2 to set
	 */
	public void setSampleField2(String sampleField2) {
		this.sampleField2 = sampleField2;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

}
