package org.calminfotech.system.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "category_item_unit")
public class CategoryItemUnit {
	
	@Id
	@GeneratedValue
	@Column(name = "itemUnitId")
	private Integer itemUnitId;
	
	@ManyToOne
	@JoinColumn(name = "unitId")
	private GlobalUnitofMeasure unit;
	
	@ManyToOne
	@JoinColumn(name = "categoryItemId")
	private CategoryItem categoryItem;

	//Getters and Setters
	
	public Integer getItemUnitId() {
		return itemUnitId;
	}

	public void setItemUnitId(Integer itemUnitId) {
		this.itemUnitId = itemUnitId;
	}

	public GlobalUnitofMeasure getUnit() {
		return unit;
	}

	public void setUnit(GlobalUnitofMeasure unit) {
		this.unit = unit;
	}

	public CategoryItem getCategoryItem() {
		return categoryItem;
	}

	public void setCategoryItem(CategoryItem categoryItem) {
		this.categoryItem = categoryItem;
	}
}
