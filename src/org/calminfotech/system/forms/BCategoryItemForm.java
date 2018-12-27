package org.calminfotech.system.forms;

import javax.persistence.Entity;

@Entity
public class BCategoryItemForm {

	private Integer itemId;

	private String itemName;

	private String description;

	private int parentCatgoryId;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getParentCatgoryId() {
		return parentCatgoryId;
	}

	public void setParentCatgoryId(int parentCatgoryId) {
		this.parentCatgoryId = parentCatgoryId;
	}

}
