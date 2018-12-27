package org.calminfotech.system.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "audits")
public class Audit {

	private Integer auditId;

	@NotNull
	private String actionUrl;

	@NotNull
	private String formName;

	@NotNull
	private String fieldName;

	@NotNull
	private String oldValue;

	@NotNull
	private String newValue;

	@NotNull
	private Date updatedDate;

	@NotNull
	private String updatedBy;

	@NotNull
	private Date createdDate;
	
	public Audit(){
		
	}

	public Audit(String actionUrl, String formName, String fieldName, String oldValue, String newValue, String updateBy) {
		// TODO Auto-generated constructor stub
		this.actionUrl = actionUrl;
		this.formName = formName;
		this.fieldName = fieldName;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.updatedBy = updateBy;
		this.createdDate = this.updatedDate = new Date(System.currentTimeMillis());
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "audit_id", unique = true, nullable = false)
	public Integer getAuditId() {
		return auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}

	@Column(name = "action_url", nullable = false)
	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	@Column(name = "form_name", nullable = false)
	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	@Column(name = "field_name", nullable = false)
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Column(name = "old_value", nullable = false)
	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	@Column(name = "new_value", nullable = false)
	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	@Column(name = "updated_date", nullable = false)
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "updated_by", nullable = false)
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "created_date", nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Audit [auditId=" + this.auditId + ", actionUrl="
				+ this.actionUrl + ", formName=" + this.formName
				+ ", fieldName=" + this.fieldName + ", oldValue="
				+ this.oldValue + ", newValue=" + this.newValue
				+ ", updatedDate=" + this.updatedDate + ", updatedBy="
				+ this.updatedBy + ", createdDate=" + this.createdDate + "]";
	}

}
