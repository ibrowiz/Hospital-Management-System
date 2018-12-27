package org.calminfotech.system.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="hmo_service")
public class HmoPckService {
	
	@Id
	@GeneratedValue
	@Column(name="hmo_service_id")
	private Integer serviceId;
	
	@Column(name="service_name")
	private String serviceName;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "hmo_service_id")
	public Set<HmoPckSubService> hmoPckSubService;
	
	//Getters and Setters

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Set<HmoPckSubService> getHmoPckSubService() {
		return hmoPckSubService;
	}

	public void setHmoPckSubService(Set<HmoPckSubService> hmoPckSubService) {
		this.hmoPckSubService = hmoPckSubService;
	}
	
}
