package org.calminfotech.system.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "hmo_package_services_tbl")
public class HmoAddPackageServices {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;		
	
	@Column(name = "spending_limit")
	private Double spendingLimit;
	
	@Column(name = "period")
	private int period;
	
	@Column(name = "cycle")
	private int cycle;
	
	@ManyToOne
	@JoinColumn(name = "hmo_package_id")
	//@JoinColumn(name = "id")
	private EhmoPackages organisationHmoPackage;
	
	@ManyToOne
	@JoinColumn(name = "service_id")
	private HmoPckService hmoPckService;
	
	@ManyToOne
	@JoinColumn(name = "subservice_id")
	private HmoPckSubService hmoPckSubservice;

	// Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getSpendingLimit() {
		return spendingLimit;
	}

	public void setSpendingLimit(Double spendingLimit) {
		this.spendingLimit = spendingLimit;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	public EhmoPackages getOrganisationHmoPackage() {
		return organisationHmoPackage;
	}

	public void setOrganisationHmoPackage(
			EhmoPackages organisationHmoPackage) {
		this.organisationHmoPackage = organisationHmoPackage;
	}

	public HmoPckService getHmoPckService() {
		return hmoPckService;
	}

	public void setHmoPckService(HmoPckService hmoPckService) {
		this.hmoPckService = hmoPckService;
	}

	public HmoPckSubService getHmoPckSubservice() {
		return hmoPckSubservice;
	}

	public void setHmoPckSubservice(HmoPckSubService hmoPckSubservice) {
		this.hmoPckSubservice = hmoPckSubservice;
	}
	
	
	
	
	
	

}
