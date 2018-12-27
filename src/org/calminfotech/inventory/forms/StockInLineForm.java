package org.calminfotech.inventory.forms;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotBlank;

public class StockInLineForm {
	private int id;

	private int batchId;

	@NotBlank(message = "Field cannot be empty!")
	private String globalItem;

	private String description;

	@NotBlank(message = "Field cannot be empty!")
	private String unitOfMeasure;

	@Digits(fraction = 0, integer = 15, message = "Quantity must be a number and between 1 and 15 characters!")
	private String quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getGlobalItem() {
		return globalItem;
	}

	public void setGlobalItem(String globalItem) {
		this.globalItem = globalItem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}
