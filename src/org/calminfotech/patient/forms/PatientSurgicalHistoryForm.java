package org.calminfotech.patient.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class PatientSurgicalHistoryForm {
	
	private Integer id;
	
	private Integer patientId;
	
	private Integer VisitId;
	
	private Integer surgicalProceduresid;
	
	private Integer surgeryyear;
	
	private String anasthetic_complication;
	
	private String complication_detail;
	
	@NotBlank(message = "Field cannot be empty!")
	private String surgery_hospital;
	
	private String hospital_address;
	
	@NotBlank(message = "Field cannot be empty!")
	@Email(message = "Invalid email format")
	private String hospital_email;
	
	private String hospital_phone;
	
	private String surgeon_full_name;

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

	public Integer getSurgicalProceduresid() {
		return surgicalProceduresid;
	}

	public void setSurgicalProceduresid(Integer surgicalProceduresid) {
		this.surgicalProceduresid = surgicalProceduresid;
	}
	
	public String getAnasthetic_complication() {
		return anasthetic_complication;
	}

	public void setAnasthetic_complication(String anasthetic_complication) {
		this.anasthetic_complication = anasthetic_complication;
	}

	public String getComplication_detail() {
		return complication_detail;
	}

	public void setComplication_detail(String complication_detail) {
		this.complication_detail = complication_detail;
	}

	public String getSurgery_hospital() {
		return surgery_hospital;
	}

	public void setSurgery_hospital(String surgery_hospital) {
		this.surgery_hospital = surgery_hospital;
	}

	public String getHospital_address() {
		return hospital_address;
	}

	public void setHospital_address(String hospital_address) {
		this.hospital_address = hospital_address;
	}

	public String getHospital_email() {
		return hospital_email;
	}

	public void setHospital_email(String hospital_email) {
		this.hospital_email = hospital_email;
	}

	public String getHospital_phone() {
		return hospital_phone;
	}

	public void setHospital_phone(String hospital_phone) {
		this.hospital_phone = hospital_phone;
	}

	public String getSurgeon_full_name() {
		return surgeon_full_name;
	}

	public void setSurgeon_full_name(String surgeon_full_name) {
		this.surgeon_full_name = surgeon_full_name;
	}

	public String getOther_detail() {
		return other_detail;
	}

	public void setOther_detail(String other_detail) {
		this.other_detail = other_detail;
	}

	public Integer getSurgeryyear() {
		return surgeryyear;
	}

	public void setSurgeryyear(Integer surgeryyear) {
		this.surgeryyear = surgeryyear;
	}

	public Integer getVisitId() {
		return VisitId;
	}

	public void setVisitId(Integer visitId) {
		VisitId = visitId;
	}
}
