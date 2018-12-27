package org.calminfotech.consultation.forms;


public class VisitPrescribedDrugForm {

	
	
	private Integer id;

	private Integer visitId;
	
	private Integer patientId;
	
	private Integer drugId;
	
	private String frequency;
	
	private String cycle;
	
	private String how_long;
	
	private String other_detail;

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

	public Integer getDrugId() {
		return drugId;
	}

	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getOther_detail() {
		return other_detail;
	}

	public void setOther_detail(String other_detail) {
		this.other_detail = other_detail;
	}

	public String getHow_long() {
		return how_long;
	}

	public void setHow_long(String how_long) {
		this.how_long = how_long;
	}
	
	
}
