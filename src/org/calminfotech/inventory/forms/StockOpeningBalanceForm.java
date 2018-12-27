package org.calminfotech.inventory.forms;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class StockOpeningBalanceForm {
	private int id;

	@NotBlank(message = "Field cannot be empty!")
	private String globalItem;

	@NotBlank(message = "Field cannot be empty!")
	@Size(min = 1, max = 15, message = "Date must between 1 and 15 characters!")
	private String dateAdded;

	@NotBlank(message = "Field cannot be empty!")
	private String unitOfMeasure;

	@Digits(fraction = 0, integer = 15, message = "Opening Balance must be a number and between 1 and 15 characters!")
	private String openingBalance;

	public String getGlobalItem() {
		return globalItem;
	}

	public void setGlobalItem(String globalItem) {
		this.globalItem = globalItem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(String openingBalance) {
		this.openingBalance = openingBalance;
	}
}
