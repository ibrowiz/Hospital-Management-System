package org.calminfotech.system.models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.utils.models.Organisation;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "global_item")
public class BGlobalItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id", unique = true, nullable = false)
	private Integer itemId;

	@Column(name = "item_name", nullable = false)
	private String itemName;

	@Column(name = "current_balance")
	private Integer quantity;

	@Column(name = "description", nullable = false)
	private String description;

	@ManyToOne
	@JoinColumn(name = "item_category_id")
	private BGlobalCategory globalCategory;

/*	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;*/
	
	
	@Column(name = "organisation_id")
	private Integer organisationId;

	@ManyToMany
	@JoinTable(name = "globalitem_unitofmeasure", joinColumns = { @JoinColumn(name = "item_id") }, inverseJoinColumns = { @JoinColumn(name = "unit_of_measure_id") })
	private List<GlobalUnitofMeasure> measurement;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "hmo_subservice_item", joinColumns = { @JoinColumn(name = "globalitem_item_id") }, inverseJoinColumns = { @JoinColumn(name = "hmo_subservice_id") })
	private Set<HmoPckSubService> hmoPackSubService = new HashSet<HmoPckSubService>();

	@ManyToMany
	@JoinTable(name = "globalitem_point", joinColumns = { @JoinColumn(name = "item_id") }, inverseJoinColumns = { @JoinColumn(name = "point_id") })
	private List<VisitWorkflowPoint> globalItemPoint;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "modify_date")
	private Date modifyDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<GlobalUnitofMeasure> getMeasurement() {
		return measurement;
	}

	public void setMeasurement(List<GlobalUnitofMeasure> measurement) {
		this.measurement = measurement;
	}

	public Set<HmoPckSubService> getHmoPackSubService() {
		return hmoPackSubService;
	}

	public void setHmoPackSubService(Set<HmoPckSubService> hmoPackSubService) {
		this.hmoPackSubService = hmoPackSubService;
	}

	public List<VisitWorkflowPoint> getGlobalItemPoint() {
		return globalItemPoint;
	}

	public void setGlobalItemPoint(List<VisitWorkflowPoint> globalItemPoint) {
		this.globalItemPoint = globalItemPoint;
	}

	public BGlobalCategory getGlobalCategory() {
		return globalCategory;
	}

	public void setGlobalCategory(BGlobalCategory globalCategory) {
		this.globalCategory = globalCategory;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * public int getParentCatgoryId() { return parentCatgoryId; }
	 * 
	 * public void setParentCatgoryId(int parentCatgoryId) {
	 * this.parentCatgoryId = parentCatgoryId; }
	 */



	
	
	
	
	
	

	public String getCreatedBy() {
		return createdBy;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
