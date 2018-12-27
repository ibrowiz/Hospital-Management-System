package org.calminfotech.consultation.forms;

import org.hibernate.validator.constraints.Range;

public class VisitWorkflowUserConfigurationForm {

	@Range(min = 1, message = "Select a visit")
	private Integer visitId;

	@Range(min = 1, message = "Select a point")
	private Integer workflowPointId;

	@Range(min = 1, message = "Select a Staff")
	private Integer userId;
	
	@Range(min = 1, message = "Select a Section")
	private Integer section;
	
	@Range(min = 1, message = "Select a dept")
	private Integer departmentId;
	
	@Range(min = 1, message = "Select a unit")
	private Integer unitId;

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
	 * @return the workflowPointId
	 */
	public Integer getWorkflowPointId() {
		return workflowPointId;
	}

	/**
	 * @param workflowPointId
	 *            the workflowPointId to set
	 */
	public void setWorkflowPointId(Integer workflowPointId) {
		this.workflowPointId = workflowPointId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
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

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

}
