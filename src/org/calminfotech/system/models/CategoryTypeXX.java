package org.calminfotech.system.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "category_global_item")
public class CategoryTypeXX {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "catTypeId", unique = true, nullable = false)
	private Integer categoryTypeId;
	
	@Column(name = "category_type_name", nullable = false)
	private String categoryTypeName;
	
	@Column(name = "description", nullable = false)
	private String categoryTypeDescription;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "catTypeId")
	public Set<GlobalItemCategory> category;
	
	//Getters and Setters
	public Integer getCategoryTypeId() {
		return categoryTypeId;
	}

	public void setCategoryTypeId(Integer categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}

	public String getCategoryTypeName() {
		return categoryTypeName;
	}

	public void setCategoryTypeName(String categoryTypeName) {
		this.categoryTypeName = categoryTypeName;
	}

	public String getCategoryTypeDescription() {
		return categoryTypeDescription;
	}

	public void setCategoryTypeDescription(String categoryTypeDescription) {
		this.categoryTypeDescription = categoryTypeDescription;
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

	public Set<GlobalItemCategory> getCategory() {
		return category;
	}

	public void setCategory(Set<GlobalItemCategory> category) {
		this.category = category;
	}

/*	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}*/
	

	
}
