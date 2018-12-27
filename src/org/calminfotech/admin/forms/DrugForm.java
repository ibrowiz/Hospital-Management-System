package org.calminfotech.admin.forms;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class DrugForm {

	private Integer id;

	@NotBlank(message = "Field cannot be empty!")
	private String name;
 
	@NotBlank(message = "Field cannot be empty!")
	private String description;
	
	@Range(min = 1, message = "Select a classification for drug!")
	private Integer classificationId;

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

	public Integer getClassificationId() {
		return classificationId;
	}

	public void setClassificationId(Integer classificationId) {
		this.classificationId = classificationId;
	}

}
