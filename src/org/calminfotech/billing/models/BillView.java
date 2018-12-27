package org.calminfotech.billing.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "bill_view")
public class BillView {
	@Id
	@Column(name = "bill_item_price_id")
	private Integer billItemPriceId;
	
	@Column(name = "global_item_id")
	private Integer globalItemId;
	
	@Column(name = "unit_of_measure_id")
	private Integer unitOfMeasureId;
	

	@Column(name = "bill_id")
	private Integer billId;
	
	@Column(name = "bill_item_price")
	private Integer billItemPrice;

	
	public Integer getBillItemPriceId() {
		return billItemPriceId;
	}

	public void setBillItemPriceId(Integer billItemPriceId) {
		this.billItemPriceId = billItemPriceId;
	}

	

	public Integer getGlobalItemId() {
		return globalItemId;
	}

	public void setGlobalItemId(Integer globalItemId) {
		this.globalItemId = globalItemId;
	}

	public Integer getUnitOfMeasureId() {
		return unitOfMeasureId;
	}

	public void setUnitOfMeasureId(Integer unitOfMeasureId) {
		this.unitOfMeasureId = unitOfMeasureId;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Integer getBillItemPrice() {
		return billItemPrice;
	}

	public void setBillItemPrice(Integer billItemPrice) {
		this.billItemPrice = billItemPrice;
	}
	
}
