package org.calminfotech.consultation.forms;



public class VisiPresentingComplaintForm {
	
	private Integer id;
	private Integer visitId;
	private Integer patientId;;
	private Integer symptomid;

	private String severity;

	private String other_detail;
	private Integer no_of_days;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getSymptomid() {
		return symptomid;
	}

	public void setSymptomid(Integer symptomid) {
		this.symptomid = symptomid;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public Integer getNo_of_days() {
		return no_of_days;
	}

	public void setNo_of_days(Integer no_of_days) {
		this.no_of_days = no_of_days;
	}

	public String getOther_detail() {
		return other_detail;
	}

	public void setOther_detail(String other_detail) {
		this.other_detail = other_detail;
	}

	

	
	
	
	

	
	
}
