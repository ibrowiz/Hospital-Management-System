package org.calminfotech.lab.models;

import java.util.Date;
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

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Laboratory_result_setup")
public class LabResultSetup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "result_setup_id", unique = true, nullable = false)
	private Integer resultId;
	
	@ManyToOne
	@JoinColumn(name = "test_id")
	private LabTest labTest;
	
	/*@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="setup_id")
	private Set<LabResult> LabRslt;*/
	
	@Column(name = "result_name")
	private String resultName;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "minimum_value")
	private String minimumValue;
	
	@Column(name = "maximum_value")
	private String maximumValue;
	
	@Column(name = "lab_measure")
	private String  labMeasure;

	@Column(name = "result_description")
	private String resultDescription;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "last_modofied_date")
	private Date LastModifiedDate;
	
	@Column(name = "status")
	private String status;

	
	public Integer getResultId() {
		return resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	

	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMinimumValue() {
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
	}

	public String getLabMeasure() {
		return labMeasure;
	}

	public void setLabMeasure(String labMeasure) {
		this.labMeasure = labMeasure;
	}

	public String getResultDescription() {
		return resultDescription;
	}

	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
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

	public void setLastModifiedDate(Date LastModifiedDate) {
		this.LastModifiedDate = LastModifiedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LabTest getLabTest() {
		return labTest;
	}

	public void setLabTest(LabTest labTest) {
		this.labTest = labTest;
	}

	/*public Set<LabResult> getLabRslt() {
		return LabRslt;
	}

	public void setLabRslt(Set<LabResult> labRslt) {
		LabRslt = labRslt;
	}*/

	
}
