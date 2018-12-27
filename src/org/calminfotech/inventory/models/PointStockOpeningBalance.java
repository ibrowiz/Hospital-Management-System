package org.calminfotech.inventory.models;

import java.io.Serializable;

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
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.utils.CommonVariables;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "point_inventory_stock_opening_balances")
@SQLDelete(sql = "UPDATE point_inventory_stock_opening_balances SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class PointStockOpeningBalance extends CommonVariables implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "global_item_id", nullable = false)
	private GlobalItem globalItem;

	@Column(name = "opening_balance")
	private int openingBalance;

	@Column(name = "date")
	private String dateAdded;

	@OneToOne
	@JoinColumn(name = "unit_of_measure_id", nullable = false)
	private GlobalUnitofMeasure globalUnitofMeasure;

	@OneToOne
	@JoinColumn(name = "point_id", nullable = false)
	private VisitWorkflowPoint visitWorkflowPoint;

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

	public VisitWorkflowPoint getVisitWorkflowPoint() {
		return visitWorkflowPoint;
	}

	public void setVisitWorkflowPoint(VisitWorkflowPoint visitWorkflowPoint) {
		this.visitWorkflowPoint = visitWorkflowPoint;
	}
}
