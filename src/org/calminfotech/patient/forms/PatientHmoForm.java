package org.calminfotech.patient.forms;

import org.hibernate.validator.constraints.Range;

public class PatientHmoForm {

	@Range(min = 1, message = "select a patient")
	private Integer patientId;

	@Range(min = 1, message = "Select an HMO")
	private Integer hmoId;

	@Range(min = 1, message = "Select an HMO package")
	private Integer packageId;

	private String description;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getHmoId() {
		return hmoId;
	}

	public void setHmoId(Integer hmoId) {
		this.hmoId = hmoId;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

}
