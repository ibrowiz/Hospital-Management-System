package org.calminfotech.setup.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class AllergyCategoryForm {
	
	private Integer allergyCategoryId;
	
	private Integer parentId;

	@NotEmpty(message = "Cannot be empty!")
	private String name;

	public Integer getAllergyCategoryId() {
		return allergyCategoryId;
	}

	public void setAllergyCategoryId(Integer allergyCategoryId) {
		this.allergyCategoryId = allergyCategoryId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	

}
