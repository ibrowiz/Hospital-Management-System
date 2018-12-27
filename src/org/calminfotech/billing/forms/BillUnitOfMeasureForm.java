package org.calminfotech.billing.forms;

import org.hibernate.validator.constraints.NotBlank;

public class BillUnitOfMeasureForm {

	
	private Integer unitOfMeasureId;

	@NotBlank(message = "Field cannot be empty!")
	private String unitOfMeasure;

	@NotBlank(message = "Field cannot be empty!")
	private String unit;
	
	private Integer organisationId;

	public Integer getUnitOfMeasureId() {
		return unitOfMeasureId;
	}

	public void setUnitOfMeasureId(Integer unitOfMeasureId) {
		this.unitOfMeasureId = unitOfMeasureId;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}
	
	
	
	
	
}
