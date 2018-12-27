package org.calminfotech.patient.models;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "patient_documents1")
public class PatientDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_documents_id", unique = true, nullable = false)
	private Integer patientDocumentsId;

	@Column(name = "name")
	private String name;

	@Column(name = "file_document")
	private Blob file;

	@Column(name = "content_type")
	private String contentType;

	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modify_date")
	private Date modifyDate;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	

	public Integer getPatientDocumentsId() {
		return patientDocumentsId;
	}

	public void setPatientDocumentsId(Integer patientDocumentsId) {
		this.patientDocumentsId = patientDocumentsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Blob getFile() {
		return file;
	}

	public void setFile(Blob file) {
		this.file = file;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
