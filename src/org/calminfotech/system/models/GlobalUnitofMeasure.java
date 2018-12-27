package org.calminfotech.system.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "unitofmeasure")
public class GlobalUnitofMeasure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unit_of_measure_id", unique = true, nullable = false)
	private Integer id;
	// full
	@Column(name = "unit_of_measure", nullable = false)
	private String unit_of_measure;
	// short cut
	@Column(name = "unit", nullable = false)
	private String unit;

	/*@ManyToMany
	@JoinTable(name = "globalitem_unitofmeasure", 
	joinColumns = { @JoinColumn(name = "unit_of_measure_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "globalitem_item_id") })
	
	private List<GlobalItem> item;
*/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUnit_of_measure() {
		return unit_of_measure;
	}

	public void setUnit_of_measure(String unit_of_measure) {
		this.unit_of_measure = unit_of_measure;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
