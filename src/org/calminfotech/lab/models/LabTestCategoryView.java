package org.calminfotech.lab.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "lab_test_category_view")
public class LabTestCategoryView {
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "parent_id")
	private Integer parentId;
	
	@Column(name = "parent_name")
	private String parentName;
	
	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "description")
	private String description;
	
	@Column(name = "organisation_id")
	private Integer organisationId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

}
