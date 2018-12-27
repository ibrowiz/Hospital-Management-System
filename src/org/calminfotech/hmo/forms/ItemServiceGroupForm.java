package org.calminfotech.hmo.forms;

public class ItemServiceGroupForm {

	private Integer itemServiceId;

   //@NotBlank(message = "Field cannot be empty!")
	private String name;

	private String description;

	public Integer getItemServiceId() {
		return itemServiceId;
	}

	public void setItemServiceId(Integer itemServiceId) {
		this.itemServiceId = itemServiceId;
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
