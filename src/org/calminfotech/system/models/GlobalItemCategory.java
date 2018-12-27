package org.calminfotech.system.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.utils.models.Organisation;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "globalitem_category")
@SQLDelete(sql = "UPDATE globalitem_category SET is_deleted = 1 WHERE globalitem_category_id = ?")
@Where(clause = "is_deleted <> 1")
public class GlobalItemCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "globalitem_category_id", unique = true, nullable = false)
	private Integer categoryId;
	
	@Column(name = "category_name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "globalitem_type_id")
	private GlobalItemType itemTypeId;
	
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;
	
	@OneToMany
	@JoinColumn(name = "globalitem_category_id")
	private Set<GlobalItem> globalItem;
	
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

	//Getters and Setters
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GlobalItemType getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(GlobalItemType itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Set<GlobalItem> getGlobalItem() {
		return globalItem;
	}

	public void setGlobalItem(Set<GlobalItem> globalItem) {
		this.globalItem = globalItem;
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

	public void setCreateDate(Date createdDate) {
		this.createDate = createdDate;
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
