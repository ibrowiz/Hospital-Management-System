package org.calminfotech.hmo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "ehmo_packageservices_view")
public class EhmoPackageServicesView {
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "hmo_package_id")
	private Integer eHmoPackageId;
	
	@Column(name = "global_service_id")
	private Integer globalEhmoServiceId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "global_hmo_servicename")
	private String globalEhmoServiceName;
	
	@Column(name = "ehmo_packagename")
	private String EhmoPackageName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer geteHmoPackageId() {
		return eHmoPackageId;
	}

	public void seteHmoPackageId(Integer eHmoPackageId) {
		this.eHmoPackageId = eHmoPackageId;
	}

	public Integer getGlobalEhmoServiceId() {
		return globalEhmoServiceId;
	}

	public void setGlobalEhmoServiceId(Integer globalEhmoServiceId) {
		this.globalEhmoServiceId = globalEhmoServiceId;
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

	public String getGlobalEhmoServiceName() {
		return globalEhmoServiceName;
	}

	public void setGlobalEhmoServiceName(String globalEhmoServiceName) {
		this.globalEhmoServiceName = globalEhmoServiceName;
	}

	public String getEhmoPackageName() {
		return EhmoPackageName;
	}

	public void setEhmoPackageName(String ehmoPackageName) {
		EhmoPackageName = ehmoPackageName;
	}
	
	
	
	
	
	

}
