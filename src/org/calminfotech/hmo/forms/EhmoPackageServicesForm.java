package org.calminfotech.hmo.forms;

public class EhmoPackageServicesForm {
	
	private Integer id;

	private Integer hmoPackageId;
	
	private Integer globalServiceId;
	
	private String name;
	
	private Double price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHmoPackageId() {
		return hmoPackageId;
	}

	public void setHmoPackageId(Integer hmoPackageId) {
		this.hmoPackageId = hmoPackageId;
	}

	public Integer getGlobalServiceId() {
		return globalServiceId;
	}

	public void setGlobalServiceId(Integer globalServiceId) {
		this.globalServiceId = globalServiceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
