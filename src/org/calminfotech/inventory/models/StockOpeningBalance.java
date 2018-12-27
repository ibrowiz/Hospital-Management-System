package org.calminfotech.inventory.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.system.models.GlobalUnitofMeasure;
import org.calminfotech.utils.CommonVariables;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "inventory_stock_opening_balances")
@SQLDelete(sql = "UPDATE inventory_stock_opening_balances SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class StockOpeningBalance extends CommonVariables {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected Integer id;
	
	@OneToOne
	@JoinColumn(name = "global_item_id", nullable = false)
	protected GlobalItem globalItem;

	@Column(name = "opening_balance")
	protected int openingBalance;
	
	@Column(name = "date")
	protected String dateAdded;
	
	@OneToOne
	@JoinColumn(name = "unit_of_measure_id", nullable = false)
	protected GlobalUnitofMeasure globalUnitofMeasure;


	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public GlobalItem getGlobalItem() {
		return globalItem;
	}

	public void setGlobalItem(GlobalItem globalItem) {
		this.globalItem = globalItem;
	}

	public int getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(int openingBalance) {
		this.openingBalance = openingBalance;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public GlobalUnitofMeasure getGlobalUnitofMeasure() {
		return globalUnitofMeasure;
	}

	public void setGlobalUnitofMeasure(GlobalUnitofMeasure globalUnitofMeasure) {
		this.globalUnitofMeasure = globalUnitofMeasure;
	}
	
}
