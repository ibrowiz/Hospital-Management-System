package org.calminfotech.patient.forms;



public class PatientFamilyHistoryForm {
	
	
	private Integer id;

	private Integer patientId;
	
	private Integer VisitId;
	
	private Integer ailment_id;
	
	private String family_line;
	
	private Integer no_of_occurence;
	
	private Integer no_of_casualties;
	
	private String other_detail;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getNo_of_occurence() {
		return no_of_occurence;
	}

	public void setNo_of_occurence(Integer no_of_occurence) {
		this.no_of_occurence = no_of_occurence;
	}

	public String getOther_detail() {
		return other_detail;
	}

	public void setOther_detail(String other_detail) {
		this.other_detail = other_detail;
	}

	public Integer getAilment_id() {
		return ailment_id;
	}

	public void setAilment_id(Integer ailment_id) {
		this.ailment_id = ailment_id;
	}

	public Integer getNo_of_casualties() {
		return no_of_casualties;
	}

	public void setNo_of_casualties(Integer no_of_casualties) {
		this.no_of_casualties = no_of_casualties;
	}

	public String getFamily_line() {
		return family_line;
	}

	public void setFamily_line(String family_line) {
		this.family_line = family_line;
	}

	public Integer getVisitId() {
		return VisitId;
	}

	public void setVisitId(Integer visitId) {
		VisitId = visitId;
	}
	
	
	
}
