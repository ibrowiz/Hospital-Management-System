package org.calminfotech.patient.models;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.calminfotech.setup.models.Allergy;
import org.calminfotech.setup.models.AllergyCategory;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "patients_allergies")
@SQLDelete(sql = "UPDATE patients_allergies SET is_deleted = 1 WHERE id = ?")
//@SQLInsert(sql="")
@Where(clause = "is_deleted <> 1")

public class PatientAllergy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "pat_id")
	private Patient patient;
	

	
	@Column(name = "allergy_id")
	private int allergyId;
	
	@Column(name = "allergy_name")
	private String allergyName;
	
	@Column(name = "reactions")
	private String reactions;

	@Column(name = "description")
	private String description;
	
	@Column(name = "status")
	private String status;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;

	@Column(name = "modify_date")
	private Date modifiedDate;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "organisation_id")
	private int organisationId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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


	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public int getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public int getAllergyId() {
		return allergyId;
	}

	public void setAllergyId(int allergyId) {
		this.allergyId = allergyId;
	}



	public String getReactions() {
		return reactions;
	}

	public void setReactions(String reactions) {
		this.reactions = reactions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getAllergyName() {
		return allergyName;
	}

	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}

}