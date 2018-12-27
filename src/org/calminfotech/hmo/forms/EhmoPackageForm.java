package org.calminfotech.hmo.forms;

public class EhmoPackageForm {

	private Integer packageId;

	//@NotBlank(message = "Field cannot be empty!")
	private String name;

	private Integer parent;
	
	private Integer hmoId;
	
	private String parentName;
	
	private Integer billingType;
	
	private Integer billId;
	
	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Integer getBillingType() {
		return billingType;
	}

	public void setBillingType(Integer billingType) {
		this.billingType = billingType;
	}

	public Integer getHmoId() {
		return hmoId;
	}

	public void setHmoId(Integer hmoId) {
		this.hmoId = hmoId;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	
	
	
	
}
