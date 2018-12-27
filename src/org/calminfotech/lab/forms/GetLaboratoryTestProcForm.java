package org.calminfotech.lab.forms;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class GetLaboratoryTestProcForm {
	/*@NotBlank(message = "Field cannot be empty!")
	private Integer setupId;*/
	
	@NotBlank(message = "Field cannot be empty!")
	private Integer id;

	@NotBlank(message = "Field cannot be empty!")
	private Integer organisationId;

	@NotBlank(message = "Field cannot be empty!")
	private int testId;
	
	@NotBlank(message = "Field cannot be empty!")
	private String resultName;
	
	@NotBlank(message = "Field cannot be empty!")
	private int visit_id;
	
	@NotBlank(message = "Field cannot be empty!")
	private String final_result;

	@NotBlank(message = "Field cannot be empty!")
	private String minimum_value;
	
	@NotBlank(message = "Field cannot be empty!")
	private String maximum_value;
	
	@NotBlank(message = "Field cannot be empty!")
	private String [] actual_value;
	
	@NotBlank(message = "Field cannot be empty!")
	private String [] finalResult;

	private String[] setupId;
	
	private String subOrder;
	
	private String saveButton;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

	
	public int getVisit_id() {
		return visit_id;
	}

	public void setVisit_id(int visit_id) {
		this.visit_id = visit_id;
	}

	public String[] getActual_value() {
		return actual_value;
	}

	public void setActual_value(String[] actual_value) {
		this.actual_value = actual_value;
	}

	public String[] getSetupId() {
		return setupId;
	}

	public void setSetupId(String[] setupId) {
		this.setupId = setupId;
	}

	public String getSubOrder() {
		return subOrder;
	}

	public void setSubOrder(String subOrder) {
		this.subOrder = subOrder;
	}

	public String getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(String saveButton) {
		this.saveButton = saveButton;
	}

	public String getFinal_result() {
		return final_result;
	}

	public void setFinal_result(String final_result) {
		this.final_result = final_result;
	}

	public String getMinimum_value() {
		return minimum_value;
	}

	public void setMinimum_value(String minimum_value) {
		this.minimum_value = minimum_value;
	}

	public String getMaximum_value() {
		return maximum_value;
	}

	public void setMaximum_value(String maximum_value) {
		this.maximum_value = maximum_value;
	}

	public String[] getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(String[] finalResult) {
		this.finalResult = finalResult;
	}

	

	
}
