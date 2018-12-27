package org.calminfotech.patient.models;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.calminfotech.system.models.Hmo;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.patient.models.Patient;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "patients_hmo_packages")
@AssociationOverrides({
		@AssociationOverride(name = "pk.hmoPackage", joinColumns = @JoinColumn(name = "package_id")),
		@AssociationOverride(name = "pk.patient", joinColumns = @JoinColumn(name = "patient_id")),
		@AssociationOverride(name = "pk.hmo", joinColumns = @JoinColumn(name = "hmo_id")) })
public class PatientHmoPackage {

	private PatientHmoPackageId pk = new PatientHmoPackageId();

	private String comments;
	private String createdBy;
	private Date createDate;

	/**
	 * @return the pk
	 */
	@EmbeddedId
	public PatientHmoPackageId getPk() {
		return pk;
	}
	/**
	 * @param pk the pk to set
	 */
	public void setPk(PatientHmoPackageId pk) {
		this.pk = pk;
	}
	/**
	 * @return the patient
	 */
	@Transient
	public Patient getPatient() {
		return this.getPk().getPatient();
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.getPk().setPatient(patient);
	}
	/**
	 * @return the hmoPackage
	 */
	@Transient
	public EhmoPackages getHmoPackage() {
		return this.getPk().getHmoPackage();
	}
	/**
	 * @param hmoPackage the hmoPackage to set
	 */
	public void setHmoPackage(EhmoPackages hmoPackage) {
		this.getPk().setHmoPackage(hmoPackage);
	}
	
	//hmo
	@Transient
	public Hmo getHmo() {
		return this.getPk().getHmo();
	}

	public void setHmo(Hmo hmo) {
		this.getPk().setHmo(hmo);
	}
	/**
	 * @return the comments
	 */
	@Column(name = "comments")
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the createdBy
	 */
	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
