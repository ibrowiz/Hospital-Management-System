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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.consultation.models.LaboratoryTestDocument;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;




@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "lab_tests")
@SQLDelete(sql = "UPDATE lab_tests SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class LabTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name = "labtestcat_id")
	private LabTestCategory lCategory;
	
	@Column(name = "organisation_id")
	private Integer organisationId;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="setup_id")
	private Set<LabResultSetup> LabResult;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="test_id")
	private Set<LabResult> LabReslt;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="test_id")
	private Set<LabTestDocument> labTestDoc;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "measure")
	private Integer measure;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		

	public LabTestCategory getlCategory() {
		return lCategory;
	}

	
	public void setlCategory(LabTestCategory lCategory) {
		this.lCategory = lCategory;
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

	
	public Set<LabResultSetup> getLabResult() {
		return LabResult;
	}

	public void setLabResult(Set<LabResultSetup> labResult) {
		LabResult = labResult;
	}

	public Set<LabResult> getLabReslt() {
		return LabReslt;
	}

	public void setLabReslt(Set<LabResult> labReslt) {
		LabReslt = labReslt;
	}

	public Set<LabTestDocument> getLabTestDoc() {
		return labTestDoc;
	}

	public void setLabTestDoc(Set<LabTestDocument> labTestDoc) {
		this.labTestDoc = labTestDoc;
	}



	public Integer getMeasure() {
		return measure;
	}

	public void setMeasure(Integer measure) {
		this.measure = measure;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}	

}
