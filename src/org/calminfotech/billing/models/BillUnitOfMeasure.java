package org.calminfotech.billing.models;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.system.models.GlobalItem;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "unit_of_measure")
@SQLDelete(sql = "UPDATE unit_of_measure SET is_deleted = 1 WHERE unit_of_measure_id = ?")
@Where(clause = "is_deleted <> 1")
public class BillUnitOfMeasure {
	
	@Id
	@GeneratedValue
	@Column(name = "unit_of_measure_id")
	private Integer unitOfMeasureId;
	
	@Column(name = "organisation_id")
	private Integer organisationId;

	@Column(name = "unit_of_measure")
	private String unitOfMeasure;
	
	@ManyToMany
	@JoinTable(name = "globalitem_unitofmeasure", 
	joinColumns = { @JoinColumn(name = "unit_of_measure_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "globalitem_item_id") })
	private List<GlobalItem> globalItem;

	@Column(name = "unit")
	private String unit;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@Column(name = "status")
	private String status;

	public Integer getUnitOfMeasureId() {
		return unitOfMeasureId;
	}

	public void setUnitOfMeasureId(Integer unitOfMeasureId) {
		this.unitOfMeasureId = unitOfMeasureId;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<GlobalItem> getGlobalItem() {
		return globalItem;
	}

	public void setGlobalItem(List<GlobalItem> globalItem) {
		this.globalItem = globalItem;
	}
	
	
	
	
}
