package org.calminfotech.consultation.models;

import java.util.Date;
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
import org.calminfotech.system.models.ConsultationStatus;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "consultations")
@SQLDelete(sql = "UPDATE consultations SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class Consultation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "consultations_id", unique = true, nullable = false)
	private int id;
       
	@Column(name = "system_id")
	private String code;

	@Column(name = "description")
	private String description;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "is_deleted", nullable = true)
	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private ConsultationStatus status;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	/*@OneToMany(mappedBy = "consultation")
	private Set<Visit> visits;*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ConsultationStatus getStatus() {
		return status;
	}

	public void setStatus(ConsultationStatus status) {
		this.status = status;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	/*public Set<Visit> getVisits() {
		return visits;
	}

	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}*/

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
