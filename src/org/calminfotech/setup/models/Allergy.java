package org.calminfotech.setup.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.PatientAllergy;
import org.calminfotech.utils.models.State;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "allergies")
@SQLDelete(sql = "UPDATE allergies SET is_deleted = 1 WHERE allergy_id = ?")
@Where(clause = "is_deleted <> 1")
public class Allergy {

	private Integer allergyId;
	private String name;
	private String description;
	private Date createDate;
	private String createdBy;
	private Date modifyDate;
	private String modifiedBy;
	private boolean isDeleted;
	private String status;

	private AllergyCategory category;
	
	private int organisationId;

	//private Set<PatientAllergy> allergyPatients = new HashSet<PatientAllergy>();
	
	
	//private Patient patient;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "allergy_id", unique = true, nullable = false)
	public Integer getAllergyId() {
		return allergyId;
	}

	public void setAllergyId(Integer allergyId) {
		this.allergyId = allergyId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "create_date", nullable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "modify_date", nullable = true)
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "modified_by", nullable = true)
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "is_deleted")
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	

	/**
	 * @return the allergyPatients
	 */
	

	@Column(name = "organisation_id")
	public int getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	@ManyToOne
	@JoinColumn(name = "category")
	public AllergyCategory getCategory() {
		return category;
	}

	public void setCategory(AllergyCategory category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	
	

	
	

	

}
