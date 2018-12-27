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

import org.calminfotech.billing.models.BillUnitOfMeasure;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "globalitem_item")
@SQLDelete(sql = "UPDATE globalitem_item SET is_deleted = 1 WHERE globalitem_item_id = ?")
@Where(clause = "is_deleted <> 1")
public class GlobalItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "globalitem_item_id")
	private Integer id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "current_balance")
	private Integer quantity;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modify_date")
	private Date modifyDate;
	// other entity
	@ManyToOne
	@JoinColumn(name = "globalitem_category_id")
	private GlobalItemCategory category;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;
	// link
	@ManyToMany
	@JoinTable(name = "globalitem_unitofmeasure", 
	joinColumns = { @JoinColumn(name = "globalitem_item_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "unit_of_measure_id") })
	private List<BillUnitOfMeasure> measurement;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "hmo_subservice_item",
	joinColumns = { @JoinColumn(name = "globalitem_item_id")},
	inverseJoinColumns = { @JoinColumn(name = "hmo_subservice_id")})
	private Set<HmoPckSubService> hmoPackSubService = new HashSet<HmoPckSubService>();
	
	@ManyToMany
	@JoinTable(name = "globalitem_point", 
	joinColumns = { @JoinColumn(name = "item_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "point_id") })
	private List<VisitWorkflowPoint> globalItemPoint;
	//variables
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreatedBy() {
		return createdBy;
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
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public GlobalItemCategory getCategory() {
		return category;
	}
	public void setCategory(GlobalItemCategory category) {
		this.category = category;
	}
	public Organisation getOrganisation() {
		return organisation;
	}
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public List<BillUnitOfMeasure> getMeasurement() {
		return measurement;
	}
	public void setMeasurement(List<BillUnitOfMeasure> measurement) {
		this.measurement = measurement;
	}
}
