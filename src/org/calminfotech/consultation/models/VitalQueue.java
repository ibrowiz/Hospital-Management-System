package org.calminfotech.consultation.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.VisitStatus;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.user.models.Title;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "vw_vital_queue")
public class VitalQueue {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "system_id")
	private String code;
	
	@Column(name = "visit_id")
	private Integer visit_id;
	
/*	@Column(name = "patient_id")
	private Integer patient_id;*/
	
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@Column(name = "consultation_id")
	private Integer consultation_id;
	
	@Column(name = "organisation_id")
	private Integer organisation_id;
	
	@Column(name = "create_date")
	private Date create_date;
	
	@Column(name = "created_by")
	private String created_by;
	
	/*@Column(name = "visit_status_id")
	private Integer visit_status_id;*/
	
	
	@ManyToOne
	@JoinColumn(name = "visit_status_id")
	private VisitStatus status;
	
/*	@Column(name = "point_id")
	private Integer point_id;*/
	
	@ManyToOne
	@JoinColumn(name = "point_id")
	private VisitWorkflowPoint point;
	
	@Column(name = "clerking_status_id")
	private Integer clerking_status_id;
	
	@Column(name = "user_id")
	private Integer user_id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "last_name")
	private String last_name;
	
	@Column(name = "other_names")
	private String other_names;
	
	@ManyToOne
	@JoinColumn(name = "title_id")
	private Title title;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVisit_id() {
		return visit_id;
	}
	public void setVisit_id(Integer visit_id) {
		this.visit_id = visit_id;
	}
/*	public Integer getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}*/
	public Integer getConsultation_id() {
		return consultation_id;
	}
	public void setConsultation_id(Integer consultation_id) {
		this.consultation_id = consultation_id;
	}
	public Integer getOrganisation_id() {
		return organisation_id;
	}
	public void setOrganisation_id(Integer organisation_id) {
		this.organisation_id = organisation_id;
	}
	
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
/*	public Integer getVisit_status_id() {
		return visit_status_id;
	}
	public void setVisit_status_id(Integer visit_status_id) {
		this.visit_status_id = visit_status_id;
	}*/
/*	public Integer getPoint_id() {
		return point_id;
	}
	public void setPoint_id(Integer point_id) {
		this.point_id = point_id;
	}*/
	public Integer getClerking_status_id() {
		return clerking_status_id;
	}
	public void setClerking_status_id(Integer clerking_status_id) {
		this.clerking_status_id = clerking_status_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getOther_names() {
		return other_names;
	}
	public void setOther_names(String other_names) {
		this.other_names = other_names;
	}
	public Title getTitle() {
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public VisitWorkflowPoint getPoint() {
		return point;
	}
	public void setPoint(VisitWorkflowPoint point) {
		this.point = point;
	}
	public VisitStatus getStatus() {
		return status;
	}
	public void setStatus(VisitStatus status) {
		this.status = status;
	}
	
	
	

	
	
}
