package org.calminfotech.setup.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "allergy_view")
public class AllergyView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "allergy_id", unique = true, nullable = false)
	private Integer allergyId;

	@Column(name = "category")
	private String allergyCategoryId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modify_date")
	private Date modifiedDate;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@Column(name = "organisation_id")
	private Integer organisationId;
	
	@Column(name = "parent_name")
	private String parentName;
	
	@Column(name = "status")
	private String status;

	public Integer getAllergyId() {
		return allergyId;
	}

	public void setAllergyId(Integer allergyId) {
		this.allergyId = allergyId;
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

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAllergyCategoryId() {
		return allergyCategoryId;
	}

	public void setAllergyCategoryId(String allergyCategoryId) {
		this.allergyCategoryId = allergyCategoryId;
	}
	
}
