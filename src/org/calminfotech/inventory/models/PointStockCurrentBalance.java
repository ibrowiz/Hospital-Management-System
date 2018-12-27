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
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "point_inventory_stock_current_balances")
@SQLDelete(sql = "UPDATE point_inventory_stock_current_balances SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class PointStockCurrentBalance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "global_item_id", nullable = false)
	private GlobalItem globalItem;

	@Column(name = "current_balance")
	private int currentBalance;

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

	public int getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(int currentBalance) {
		this.currentBalance = currentBalance;
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
