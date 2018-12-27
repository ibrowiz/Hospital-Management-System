package org.calminfotech.system.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="hmo_subservice")
public class HmoPckSubService {
	
	@Id
	@GeneratedValue
	@Column(name = "hmo_subservice_id")
	private Integer id;
	
	@Column(name = "hmo_subservice_name")
	private String subserviceName;
	
	@ManyToOne
	@JoinColumn(name = "hmo_service_id")
	private HmoPckService hmoPckService;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "hmo_subservice_item",
	joinColumns = { @JoinColumn(name = "hmo_subservice_id")},
	inverseJoinColumns = { @JoinColumn(name = "globalitem_item_id")})
	private Set<GlobalItem> item = new HashSet<GlobalItem>();

	//Getters and Setters
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubserviceName() {
		return subserviceName;
	}

	public void setSubserviceName(String subserviceName) {
		this.subserviceName = subserviceName;
	}

	public HmoPckService getHmoPckService() {
		return hmoPckService;
	}

	public void setHmoPckService(HmoPckService hmoPckService) {
		this.hmoPckService = hmoPckService;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Set<GlobalItem> getGlobalItem() {
		return item;
	}

	public void setGlobalItem(Set<GlobalItem> item) {
		this.item = item;
	}
}
