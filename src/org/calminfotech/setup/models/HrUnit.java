package org.calminfotech.setup.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.system.models.VisitWorkflowPoint;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hr_unit")
@SQLDelete(sql = "UPDATE hr_unit SET is_deleted = 1 WHERE unitId = ?")
@Where(clause = "is_deleted <> 1")
public class HrUnit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rowid", unique = true, nullable = false)
	private Integer rowId;
	
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "queue")
	private boolean attendQ;
	
	@Column(name = "billing_scheme_id")
	private Integer billingSchemeId;
	
	@ManyToOne
	@JoinColumn(name = "point_id")
	private VisitWorkflowPoint point;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "modify_by")
	private Integer modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@Column(name = "organisation_id")
	private int organisationId;
	
	@Column(name = "status")
	private String status;

	
	

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
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

	public int getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isAttendQ() {
		return attendQ;
	}

	public void setAttendQ(boolean attendQ) {
		this.attendQ = attendQ;
	}

	

	public VisitWorkflowPoint getPoint() {
		return point;
	}

	public void setPoint(VisitWorkflowPoint point) {
		this.point = point;
	}

	


	public Integer getBillingSchemeId() {
		return billingSchemeId;
	}

	public void setBillingSchemeId(Integer billingSchemeId) {
		this.billingSchemeId = billingSchemeId;
	}

	

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	

	
	

	

	

	

}
