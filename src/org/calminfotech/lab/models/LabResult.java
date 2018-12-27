package org.calminfotech.lab.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "laboratory_result")
public class LabResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "test_id")
	private LabTest labTest;


	@ManyToOne
	@JoinColumn(name = "setup_id")
	private LabResultSetup labRSetup;
	
	@Column(name = "visit_id")
	private int visit_id;

	@Column(name = "actual_value")
	private String actual_value;
	
	@Column(name = "final_result")
	private String finalResult;

	@Column(name = "created_by")
	private String created_by;
	
	@Column(name = "modified_by")
	private String modified_by;

	@Column(name = "modified_date")
	private Date modified_date;

	@Column(name = "status")
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	

	public int getVisit_id() {
		return visit_id;
	}

	public void setVisit_id(int visit_id) {
		this.visit_id = visit_id;
	}

	public String getActual_value() {
		return actual_value;
	}

	public void setActual_value(String actual_value) {
		this.actual_value = actual_value;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
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

	public LabResultSetup getLabRSetup() {
		return labRSetup;
	}

	public void setLabRSetup(LabResultSetup labRSetup) {
		this.labRSetup = labRSetup;
	}

	public String getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}
	
}
