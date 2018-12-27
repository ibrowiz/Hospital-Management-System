package org.calminfotech.patient.forms;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class PatientDocumentForm {

	private Integer PatientDocumentId;

	private Integer patientId;

	@NotNull(message = "Pick a file")
	private MultipartFile document;
	
	

	public MultipartFile getDocument() {
		return document;
	}

	public void setDocument(MultipartFile document) {
		this.document = document;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getPatientDocumentId() {
		return PatientDocumentId;
	}

	public void setPatientDocumentId(Integer patientDocumentId) {
		PatientDocumentId = patientDocumentId;
	}

	
	
}
