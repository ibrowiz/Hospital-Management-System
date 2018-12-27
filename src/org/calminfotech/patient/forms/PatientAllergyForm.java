package org.calminfotech.patient.forms;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class PatientAllergyForm {
	
	private int id;

	@Range(min = 1, message = "No patient associated")
	private Integer patientId;


	@Range(min = 1, message = "Select an allergy")
	private int allergyId;
	
	private String allergyName;

	@NotEmpty(message = "Cannot be empty!")
	private String reactions;

	private String description;
	/**
	 * @return the patientId
	 */
	public Integer getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId
	 *            the patientId to set
	 */
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}



	/**
	 * @return the allergyId
	 */
	public int getAllergyId() {
		return allergyId;
	}

	/**
	 * @param allergyId
	 *            the allergyId to set
	 */
	public void setAllergyId(int allergyId) {
		this.allergyId = allergyId;
	}

	/**
	 * @return the reactions
	 */
	public String getReactions() {
		return reactions;
	}
	
	public void setReactions(String reactions) {
		this.reactions = reactions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAllergyName() {
		return allergyName;
	}

	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}
}
