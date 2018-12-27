package org.calminfotech.system.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.calminfotech.hmo.models.Ehmo;
import org.calminfotech.patient.models.PatientHmoPackage;
import org.calminfotech.utils.models.Organisation;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "ehmopackage")
public class EhmoPackages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "hmo_id")
	private Ehmo ehmo;

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	private Organisation organisation;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@OneToMany(mappedBy = "pk.hmoPackage")
	private Set<PatientHmoPackage> packagePatients = new HashSet<PatientHmoPackage>();

	@OneToMany(mappedBy = "pk.hmoPackage")
	private Set<HmoPackageDrug> packageDrugs = new HashSet<HmoPackageDrug>();

	@OneToMany(mappedBy = "pk.hmoPackage")
	private Set<HmoPackageTreatment> packageAilments = new HashSet<HmoPackageTreatment>();

	@OneToMany
	@JoinColumn(name = "hmo_package_id")
	private Set<HmoPackageService> packageServices = new HashSet<HmoPackageService>();
	
	//(mappedBy = "organisationHmoPackage")
	//@OneToMany(mappedBy = "organisationHmoPackage")
	//@JoinColumn(name = "hmoAddPackageServices_id")
	//@NotFound(action=NotFoundAction.IGNORE)
	@OneToMany
	//@JoinColumn(name = "id")
	@JoinColumn(name = "hmo_package_id")
	//@OneToMany(mappedBy = "organisationHmoPackage")
	private Set<HmoPackageItems> hmoAddPackageServices = new HashSet<HmoPackageItems>();

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the hmo
	 */
	
	/**
	 * @return the organisation
	 */
	public Organisation getOrganisation() {
		return organisation;
	}

	/**
	 * @param organisation
	 *            the organisation to set
	 */
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the packageDrugs
	 */
	public Set<HmoPackageDrug> getPackageDrugs() {
		return packageDrugs;
	}

	/**
	 * @param packageDrugs
	 *            the packageDrugs to set
	 */
	public void setPackageDrugs(Set<HmoPackageDrug> packageDrugs) {
		this.packageDrugs = packageDrugs;
	}

	/**
	 * @return the packageAilments
	 */
	public Set<HmoPackageTreatment> getPackageAilments() {
		return packageAilments;
	}

	/**
	 * @param packageAilments
	 *            the packageAilments to set
	 */
	public void setPackageAilments(Set<HmoPackageTreatment> packageAilments) {
		this.packageAilments = packageAilments;
	}

	/**
	 * @return the packagePatients
	 */
	public Set<PatientHmoPackage> getPackagePatients() {
		return packagePatients;
	}

	/**
	 * @param packagePatients
	 *            the packagePatients to set
	 */
	public void setPackagePatients(Set<PatientHmoPackage> packagePatients) {
		this.packagePatients = packagePatients;
	}

	/**
	 * @return the packageServices
	 */
	public Set<HmoPackageService> getPackageServices() {
		return packageServices;
	}

	/**
	 * @param packageServices
	 *            the packageServices to set
	 */
	public void setPackageServices(Set<HmoPackageService> packageServices) {
		this.packageServices = packageServices;
	}

	public Set<HmoPackageItems> getHmoAddPackageServices() {
		return hmoAddPackageServices;
	}

	public void setHmoAddPackageServices(
			Set<HmoPackageItems> hmoAddPackageServices) {
		this.hmoAddPackageServices = hmoAddPackageServices;
	}

	public Ehmo getEhmo() {
		return ehmo;
	}

	public void setEhmo(Ehmo ehmo) {
		this.ehmo = ehmo;
	}

	

}