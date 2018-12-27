package org.calminfotech.system.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(
	name = "spGetassignedunit1",
	query = "{CALL sp_assigned_unit1(:itemId)}",
	callable = true,
	resultClass = UnitItem.class
)
@Table(name = "globalitem_unitofmeasure")
public class UnitItem {
	//Variables
		private Integer id;
		private Integer unitId;
		private Integer itemId;
		private Integer typeId;
		private Integer contdValue;
		private Integer contdMeasurement;
		private String createdby;
		private Date createDate;
		private boolean isDeleted;
		//getter and setter
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "globalitem_unitofmeasure_id", unique = true, nullable = false)
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		
		@Column(name = "unit_of_measure_id")
		public Integer getUnitId() {
			return unitId;
		}
		public void setUnitId(Integer unitId) {
			this.unitId = unitId;
		}
		
		@Column(name = "globalitem_item_id")
		public Integer getItemId() {
			return itemId;
		}
		public void setItemId(Integer itemId) {
			this.itemId = itemId;
		}
		
		@Column(name = "globalitem_type")
		public Integer getTypeId() {
			return typeId;
		}
		public void setTypeId(Integer typeId) {
			this.typeId = typeId;
		}
		
		@Column(name = "contained_value")
		public Integer getContdValue() {
			return contdValue;
		}
		public void setContdValue(Integer contdValue) {
			this.contdValue = contdValue;
		}
		
		@Column(name = "contained_measurement")
		public Integer getContdMeasurement() {
			return contdMeasurement;
		}
		public void setContdMeasurement(Integer contdMeasurement) {
			this.contdMeasurement = contdMeasurement;
		}
		@Column(name = "created_by")
		public String getCreatedby() {
			return createdby;
		}
		public void setCreatedby(String createdby) {
			this.createdby = createdby;
		}
		
		@Column(name = "create_date")
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		
		@Column(name = "is_deleted")
		public boolean isDeleted() {
			return isDeleted;
		}
		public void setDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}
}
