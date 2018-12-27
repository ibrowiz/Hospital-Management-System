package org.calminfotech.utils.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "globaitem_unitofmeasure")
public class GlobalUnitofMeasureXXX {
	
	private Integer id;
	/*private Integer item_id;*/
	//private Integer unitofmeasureid;
	private GlobalItem item;
	
	private GlobalUnitofMeasure unitMeasure;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	public GlobalItem getItem() {
		return item;
	}
	public void setItem(GlobalItem item) {
		this.item = item;
	}
	
	@ManyToOne
	@JoinColumn(name = "unitofmeasureid")
	public GlobalUnitofMeasure getUnitMeasure() {
		return unitMeasure;
	}
	public void setUnitMeasure(GlobalUnitofMeasure unitMeasure) {
		this.unitMeasure = unitMeasure;
	}
}
