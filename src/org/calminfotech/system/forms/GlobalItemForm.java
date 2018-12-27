package org.calminfotech.system.forms;


import org.hibernate.validator.constraints.NotBlank;

public class GlobalItemForm {
	
	private Integer id;
	private String description;
	@NotBlank(message = "Field cannot be empty!")
	private String item_name;
	private Integer category;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
}
