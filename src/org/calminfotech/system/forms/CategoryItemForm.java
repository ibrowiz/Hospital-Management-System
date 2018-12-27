package org.calminfotech.system.forms;


public class CategoryItemForm {
	
	private Integer itemId;	
	
	private String itemName;	
	
	private String itemDesc;	
	
	private Integer categoryId;
	
	private Integer categoryTypeId;

	//Getters and Setters
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

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCategoryTypeId() {
		return categoryTypeId;
	}

	public void setCategoryTypeId(Integer categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}	
	

}
