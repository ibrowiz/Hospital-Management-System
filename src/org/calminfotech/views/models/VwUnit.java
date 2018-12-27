package org.calminfotech.views.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_unit")
public class VwUnit {
	//variables
	@Id
	@GeneratedValue
	@Column(name = "unit_of_measure_id")
	private Integer id;
	private String description;
	private String unit;
	private Integer item;
	private Integer globalitem_type;
	//getters and setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getItem() {
		return item;
	}
	public void setItem(Integer item) {
		this.item = item;
	}
	public Integer getGlobalitem_type() {
		return globalitem_type;
	}
	public void setGlobalitem_type(Integer globalitem_type) {
		this.globalitem_type = globalitem_type;
	}
}
