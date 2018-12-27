package org.calminfotech.patient.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="patient_hmo_billscheme_view")
public class PatientHmoBillSchemeView {
	
	@Id
	@Column(name = "patient_id")
	private Integer patientId;

	@Column (name="hmo_id")
	private Integer hmoId;

	@Column (name = "name")
	private String name;

	@Column(name = "bill_name")
	private String billName;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getHmoId() {
		return hmoId;
	}

	public void setHmoId(Integer hmoId) {
		this.hmoId = hmoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

}
