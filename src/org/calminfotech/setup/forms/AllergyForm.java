package org.calminfotech.setup.forms;

import org.hibernate.validator.constraints.NotBlank;

public class AllergyForm {

	private Integer id;

	@NotBlank(message = "field cannot be empty!")
	private String name;
	private String description;
	
	//private String status;
	
	//@NotBlank( message ="Select a category")
	private Integer categoryId;

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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/*public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}*/

	

}
