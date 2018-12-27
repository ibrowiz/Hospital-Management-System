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

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "globalitem_type")
public class GlobalItemType {
	// variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "globalitem_type_id", unique = true, nullable = false)
	private Integer globalitemTypeId;

	@Column(name = "globalitem_name", nullable = true)
	private String name;

	@Column(name = "description", nullable = true)
	private String description;
	
	@Column(name = "create_date", nullable = true)
	private Date createDate;
	
	@Column(name = "created_by", nullable = true)
	private String creastedBy;
	
	@Column(name = "modify_date", nullable = true)
	private Date modifyDate;
	
	@Column(name = "modified_by", nullable = true)
	private String modifiedBy;
	
	@Column(name = "is_deleted", nullable = true)
	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	@OneToMany
	@JoinColumn(name = "globalitem_type_id")
	private Set<GlobalItemCategory> globalCategoryItem;

	// getters and setters
	public Integer getGlobalitemTypeId() {
		return globalitemTypeId;
	}

	public void setGlobalitemTypeId(Integer globalitemTypeId) {
		this.globalitemTypeId = globalitemTypeId;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreastedBy() {
		return creastedBy;
	}

	public void setCreastedBy(String creastedBy) {
		this.creastedBy = creastedBy;
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

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Set<GlobalItemCategory> getGlobalCategoryItem() {
		return globalCategoryItem;
	}

	public void setGlobalItemCategory(Set<GlobalItemCategory> globalCategoryItem) {
		this.globalCategoryItem = globalCategoryItem;
	}
	
	public void setGlobalCategoryItem(Set<GlobalItemCategory> globalCategoryItem) {
		this.globalCategoryItem = globalCategoryItem;
	}
	
	
}
