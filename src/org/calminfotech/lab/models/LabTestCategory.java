package org.calminfotech.lab.models;

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

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "lab_test_category")
@SQLDelete(sql = "UPDATE lab_test_category SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class LabTestCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "parent_id")
	private Integer parentId;
	
	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "description")
	private String description;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "create_date")
	private Date createdDate;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modofied_date")
	private Date LastModifiedDate;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@Column(name = "organisation_id")
	private Integer organisationId;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="labtestcat_id")
	private Set<LabTest> LabTest;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getLastModifiedDate() {
		return LastModifiedDate;
	}


	public void setLastModifiedDate(Date lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Set<LabTest> getLabTest() {
		return LabTest;
	}

	public void setLabTest(Set<LabTest> labTest) {
		LabTest = labTest;
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

	
}
