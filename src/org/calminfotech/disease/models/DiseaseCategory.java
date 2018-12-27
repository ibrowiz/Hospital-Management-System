package org.calminfotech.disease.models;

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
@Table(name = "disease_categories")
@SQLDelete(sql = "UPDATE disease_categories SET is_deleted = 1 WHERE disease_category_id = ?")
@Where(clause = "is_deleted <> 1")
public class DiseaseCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "disease_category_id", unique = true, nullable = false)
	private Integer diseaseCategoryId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "parent_id")
	private Integer parentId;
	
	@Column(name = "create_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "modify_by")
	private int modifiedBy;
	
	@Column(name = "organisation_id")
	private Integer organisationId;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	@Column(name = "status")
	private String status;

	public Integer getDiseaseCategoryId() {
		return diseaseCategoryId;
	}

	public void setDiseaseCategoryId(Integer diseaseCategoryId) {
		this.diseaseCategoryId = diseaseCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	

}
