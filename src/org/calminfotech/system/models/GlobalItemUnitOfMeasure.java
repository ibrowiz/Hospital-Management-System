package org.calminfotech.system.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "globalitem_unitofmeasure")
@SQLDelete(sql = "UPDATE globalitem_unitofmeasure SET is_deleted = 1 WHERE globalitem_unitofmeasure_id = ?")
@Where(clause = "is_deleted <> 1")
public class GlobalItemUnitOfMeasure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "globalitem_unitofmeasure_id")
	private Integer globalItemUnitOfMeasureId;
	
	@Column(name = "globalitem_item_id")
	private Integer globalItemId;
	
	@Column(name = "unit_of_measure_id")
	private Integer unitOfMeasureId;
	
	@Column(name = "globalitem_type")
	private Integer itemType;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	@Column(name = "contained_measurement")
	private String ContainedMeasurement;
	
	@Column(name = "contained_value")
	private String ContainedValue;

	

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

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getContainedMeasurement() {
		return ContainedMeasurement;
	}

	public void setContainedMeasurement(String containedMeasurement) {
		ContainedMeasurement = containedMeasurement;
	}

	public String getContainedValue() {
		return ContainedValue;
	}

	public void setContainedValue(String containedValue) {
		ContainedValue = containedValue;
	}

	public Integer getGlobalItemUnitOfMeasureId() {
		return globalItemUnitOfMeasureId;
	}

	public void setGlobalItemUnitOfMeasureId(Integer globalItemUnitOfMeasureId) {
		this.globalItemUnitOfMeasureId = globalItemUnitOfMeasureId;
	}


}
