package org.calminfotech.admin.forms;

import org.hibernate.validator.constraints.NotBlank;

public class DiseaseForm {

	private Integer id;

	@NotBlank(message = "field cannot be empty!")
	private String name;

	@NotBlank(message = "field cannot be empty!")
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
