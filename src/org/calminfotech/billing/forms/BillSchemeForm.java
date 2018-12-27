package org.calminfotech.billing.forms;
import org.hibernate.validator.constraints.NotBlank;

public class BillSchemeForm {

	
	private Integer billId;

	@NotBlank(message = "Field cannot be empty!")
	private String name;

	@NotBlank(message = "Field cannot be empty!")
	private String description;
	private Integer billingType;
	private Integer organisationId;
	
	public Integer getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
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
	public Integer getBillingType() {
		return billingType;
	}
	public void setBillingType(Integer billingType) {
		this.billingType = billingType;
	}
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	
}
