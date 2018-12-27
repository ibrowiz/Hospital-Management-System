package org.calminfotech.consultation.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.system.models.Ailment;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "exam_category")
@SQLDelete(sql = "UPDATE exam_category SET is_deleted = 1 WHERE exam_category_id = ?")
@Where(clause = "is_deleted <> 1")
public class ExaminationCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exam_category_id", unique = true, nullable = false)
	private Integer examCategoryId;
	
	@Column(name = "parent_id")
	private Integer parentId ;
	
	@Column(name = "name")
	private String name;
	
	
	/*@Column(name = "minimum_value")
	private String minimumValue;
	
	@Column(name = "maximum_value")
	private String maximumValue;*/
	
	@Column(name = "create_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "modified_by")
	private int modifiedBy;
	
	@Column(name = "organisation_id")
	private int organisationId;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;

	public Integer getExamCategoryId() {
		return examCategoryId;
	}

	public void setExamCategoryId(Integer examCategoryId) {
		this.examCategoryId = examCategoryId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public String getMinimumValue() {
		return minimumValue;
	}

	public void setMinimumValue(String minimumValue) {
		this.minimumValue = minimumValue;
	}

	public String getMaximumValue() {
		return maximumValue;
	}

	public void setMaximumValue(String maximumValue) {
		this.maximumValue = maximumValue;
	}*/

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

	public int getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	

	

}
