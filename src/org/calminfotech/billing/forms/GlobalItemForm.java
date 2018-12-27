package org.calminfotech.billing.forms;

import org.hibernate.validator.constraints.NotBlank;

public class GlobalItemForm {

	
	private Integer globalItemId;

	@NotBlank(message = "Field cannot be empty!")
	private String globalName;

	@NotBlank(message = "Field cannot be empty!")
	private String globalDescription;
	
	private Integer organisationId;

	public Integer getGlobalItemId() {
		return globalItemId;
	}

	public void setGlobalItemId(Integer globalItemId) {
		this.globalItemId = globalItemId;
	}

	public String getGlobalName() {
		return globalName;
	}

	public void setGlobalName(String globalName) {
		this.globalName = globalName;
	}

	public String getGlobalDescription() {
		return globalDescription;
	}

	public void setGlobalDescription(String globalDescription) {
		this.globalDescription = globalDescription;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}
	

	
	
	
}
