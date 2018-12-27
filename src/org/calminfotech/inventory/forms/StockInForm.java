package org.calminfotech.inventory.forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class StockInForm {
	private int id;

	@Size(min = 0, max = 100, message = "Field must be less than 100 characters!")
	private String batchId;

	@NotBlank(message = "Field cannot be empty!")
	private String vendor;

	@NotBlank(message = "Field cannot be empty!")
	@Size(min = 1, max = 15, message = "Date Of Supply must between 1 and 15 characters!")
	private String dateOfSupply;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getDateOfSupply() {
		return dateOfSupply;
	}

	public void setDateOfSupply(String dateOfSupply) {
		this.dateOfSupply = dateOfSupply;
	}
}
