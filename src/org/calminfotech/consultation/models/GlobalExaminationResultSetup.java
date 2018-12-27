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

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "global_exam_result_setup")
@SQLDelete(sql = "UPDATE global_exam_result_setup SET is_deleted = 1 WHERE setup_id = ?")
@Where(clause = "is_deleted <> 1")
public class GlobalExaminationResultSetup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "setup_id", unique = true, nullable = false)
	private Integer examResultSetupId;
	
	
	@Column(name = "exam_result_name")
	private String examResultName ;
	
	@Column(name = "description")
	private String description ;
	
	@Column(name = "created_by")
	private Integer created_by;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "modified_by")
	private Integer modified_by;

	@Column(name = "modified_date")
	private Date modified_date;
	
	@Column(name = "organisation_id")
	private Integer organisationId;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;

	public Integer getExamResultSetupId() {
		return examResultSetupId;
	}

	public void setExamResultSetupId(Integer examResultSetupId) {
		this.examResultSetupId = examResultSetupId;
	}

	public String getExamResultName() {
		return examResultName;
	}

	public void setExamResultName(String examResultName) {
		this.examResultName = examResultName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getModified_by() {
		return modified_by;
	}

	public void setModified_by(Integer modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
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
