package org.calminfotech.hmo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "service_group")

public class ItemServiceGroup {
	
	@Id
	@GeneratedValue
	@Column(name ="item_service_id")
	private Integer itemServiceId;
	
	@Column(name ="name")
	private String name;
	
	@Column(name ="description")
	private String description;

	public Integer getItemServiceId() {
		return itemServiceId;
	}

	public void setItemServiceId(Integer itemServiceId) {
		this.itemServiceId = itemServiceId;
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

	
	
}

