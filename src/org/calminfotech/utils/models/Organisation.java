package org.calminfotech.utils.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.calminfotech.admin.models.HospitalDirector;
import org.calminfotech.system.models.OrganisationDirector;
import org.calminfotech.system.models.OrganisationDrug;
import org.calminfotech.user.models.User;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "organisations")
@SQLDelete(sql = "UPDATE organisations SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class Organisation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "system_id")
	private String systemId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "address")
	private String address;

	@Column(name = "hospital_type")
	private String hospitalType;
	
	@Column(name = "system_email")
	private String systemEmail;
	
	@Column(name = "domain")
	private String domain;
	
	@Column(name = "established_year")
	private String establishedYear;
	
	@Column(name = "area_of_specialization")
	private String areaOfSpecialisation;
	
	@Column(name = "is_deleted")
	private boolean isDelete = false;
	
	
	@OneToMany 
	@JoinColumn(name = "organisationId")
	private Set<OrganisationDirector> director = new HashSet<OrganisationDirector>();
	
	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;
	
	@ManyToOne
	@JoinColumn(name = "lga_id")
	private LocalGovernmentArea lga;
	/**
	 * 
	 * Admin name and email should be on the user table
	 * 
	 */

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "modify_date", nullable = true)
	private Date modifyDate;

	@Column(name = "active")
	private boolean active;

	@Column(name = "lock")
	private boolean lock;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "organisation_id")
	private Set<User> users = new HashSet<User>(0);

	@OneToMany(mappedBy = "organisation")	
	private Set<HospitalDirector> hospitalirector;

	@OneToMany(mappedBy = "pk.organisation", fetch = FetchType.LAZY)
	private Set<OrganisationDrug> organisationDrugs;

	@Column(name = "consultation_code")
	private String consultationCode;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSystemEmail() {
		return systemEmail;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> user) {
		this.users = user;
	}

	public Set<OrganisationDrug> getOrganisationDrugs() {
		return organisationDrugs;
	}

	public void setOrganisationDrugs(Set<OrganisationDrug> organisationDrugs) {
		this.organisationDrugs = organisationDrugs;
	}

	public String getConsultationCode() {
		return consultationCode;
	}

	public void setConsultationCode(String consultationCode) {
		this.consultationCode = consultationCode;
	}

	public String getEstablishedYear() {
		return establishedYear;
	}

	public void setEstablishedYear(String establishedYear) {
		this.establishedYear = establishedYear;
	}

	public String getAreaOfSpecialisation() {
		return areaOfSpecialisation;
	}

	public void setAreaOfSpecialisation(String areaOfSpecialisation) {
		this.areaOfSpecialisation = areaOfSpecialisation;
	}

	public Set<OrganisationDirector> getDirector() {
		return director;
	}

	public void setDirector(Set<OrganisationDirector> director) {
		this.director = director;
	}

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public LocalGovernmentArea getLga() {
		return lga;
	}

	public void setLga(LocalGovernmentArea lga) {
		this.lga = lga;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}
