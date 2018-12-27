package org.calminfotech.patient.models;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.calminfotech.hmo.models.Ehmo;
import org.calminfotech.hmo.models.EhmoPackage;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name="patient_hmo")
public class PatientHmo {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
private Integer id;

@Column (name="description")
private String description;

@Column (name = "organisation_id")
private Integer organisationId;

@ManyToOne
@JoinColumn(name = "patient_id")
private Patient patient;

/*@OneToMany
@JoinColumn(name = "patienthmo_id")
private Set<Ehmo> ehmo;
*/
@Column(name ="created_by")
private String createdBy;

@Column (name ="created_date")
private Date createdDate;

@Column (name ="modified_date")
private Date modifiedDate;

@Column (name ="modified_by")
private String modifiedBy;

/*@ManyToOne
@JoinColumn(name = "package_id")
private EhmoPackage ehmoPackage;*/



public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Integer getOrganisationId() {
	return organisationId;
}

public void setOrganisationId(Integer organisationId) {
	this.organisationId = organisationId;
}

public Patient getPatient() {
	return patient;
}

public void setPatient(Patient patient) {
	this.patient = patient;
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

public Date getModifiedDate() {
	return modifiedDate;
}

public void setModifiedDate(Date modifiedDate) {
	this.modifiedDate = modifiedDate;
}

public String getModifiedBy() {
	return modifiedBy;
}

public void setModifiedBy(String modifiedBy) {
	this.modifiedBy = modifiedBy;
}

/*public EhmoPackage getEhmoPackage() {
	return ehmoPackage;
}

public void setEhmoPackage(EhmoPackage ehmoPackage) {
	this.ehmoPackage = ehmoPackage;
}*/

/*public Set<Ehmo> getEhmo() {
	return ehmo;
}

public void setEhmo(Set<Ehmo> ehmo) {
	this.ehmo = ehmo;
}*/


}