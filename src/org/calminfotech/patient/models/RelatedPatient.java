package org.calminfotech.patient.models;

import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.consultation.models.Visit;
import org.calminfotech.system.models.Gender;
import org.calminfotech.user.models.Language;
import org.calminfotech.user.models.Title;
import org.calminfotech.utils.models.LocalGovernmentArea;
import org.calminfotech.utils.models.MaritalStatus;
import org.calminfotech.utils.models.Organisation;
import org.calminfotech.utils.models.State;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "related_patients")
@SQLDelete(sql = "UPDATE related_patients SET is_deleted = 1 WHERE id = ?")
//@SQLInsert(sql="")
@Where(clause = "is_deleted <> 1")
public class RelatedPatient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer Id;
	
	@Column(name = "rel_patient_id")
	private int relPatientId;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@Column(name = "relationship")
	private String relationship;

	
	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "modify_date", nullable = true)
	private Date modifiedDate;
	
	@Column(name = "modified_by", nullable = true)
	private int modifiedBy;
	
	@Column(name = "status", nullable = true)
	private String status;
	
	@Column(name = "organisation_id", nullable = true)
	private int organisationId;
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	public int getRelPatientId() {
		return relPatientId;
	}

	public void setRelPatientId(int relPatientId) {
		this.relPatientId = relPatientId;
	}


}
