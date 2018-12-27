package org.calminfotech.inventory.utils;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.utils.CommonVariables;

@MappedSuperclass
public class LineItem extends CommonVariables {
	
	@OneToOne
	@JoinColumn(name = "unit_of_measure_id", nullable = false)
	private GlobalUnitofMeasure globalUnitofMeasure;
	
	@OneToOne
	@JoinColumn(name = "global_item_id", nullable = false)
	private GlobalItem globalItem;
	
	@Column(name = "quantity")
	private int quantity;

	public GlobalUnitofMeasure getGlobalUnitofMeasure() {
		return globalUnitofMeasure;
	}

	public void setGlobalUnitofMeasure(GlobalUnitofMeasure globalUnitofMeasure) {
		this.globalUnitofMeasure = globalUnitofMeasure;
	}

	public GlobalItem getGlobalItem() {
		return globalItem;
	}

	public void setGlobalItem(GlobalItem globalItem) {
		this.globalItem = globalItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
