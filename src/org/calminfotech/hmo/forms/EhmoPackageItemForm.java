package org.calminfotech.hmo.forms;

public class EhmoPackageItemForm {

	private Integer itemId;

	private String name;

	private String description;
	private String spendingLimit;
	private String period;
	private Integer timeNo;
	private Integer periodNo;
	
	
	private Integer packageId;
	
	private Integer itemServiceId;
	
	private Integer serviceGroup;
	private Integer hmoId;
	
	

	public Integer getItemServiceId() {
		return itemServiceId;
	}

	

	public Integer getServiceGroup() {
		return serviceGroup;
	}



	public void setServiceGroup(Integer serviceGroup) {
		this.serviceGroup = serviceGroup;
	}



	public void setItemServiceId(Integer itemServiceId) {
		this.itemServiceId = itemServiceId;
	}

	public Integer getHmoId() {
		return hmoId;
	}

	public void setHmoId(Integer hmoId) {
		this.hmoId = hmoId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public String getSpendingLimit() {
		return spendingLimit;
	}

	public void setSpendingLimit(String spendingLimit) {
		this.spendingLimit = spendingLimit;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Integer getTimeNo() {
		return timeNo;
	}

	public void setTimeNo(Integer timeNo) {
		this.timeNo = timeNo;
	}

	public Integer getPeriodNo() {
		return periodNo;
	}

	public void setPeriodNo(Integer periodNo) {
		this.periodNo = periodNo;
	}

	
	
	
	
}
