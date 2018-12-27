package org.calminfotech.system.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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

import org.calminfotech.utils.models.Bank;
import org.calminfotech.utils.models.LocalGovernmentArea;
import org.calminfotech.utils.models.State;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hmos")
@SQLDelete(sql = "UPDATE hmos SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class Hmo {

	private Integer id;
	private String systemId;
	private String name;
	private String address;
	private LocalGovernmentArea lga;
	private State state;
	private String phoneNumber;
	private String postalNumber;
	private String email;
	private Bank bank;
	private String bankAccount;
	private String adminName;
	private String adminEmail;
	private String adminPhone;
	private Date createdDate;
	private String createdBy;
	private Date modifiedDate;
	private Set<EhmoPackages> organisationHmoPackages;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "system_id", nullable = false)
	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToOne
	@JoinColumn(name = "lga_id")
	public LocalGovernmentArea getLga() {
		return lga;
	}

	public void setLga(LocalGovernmentArea lga) {
		this.lga = lga;
	}

	@ManyToOne
	@JoinColumn(name = "state_id")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "postal_number")
	public String getPostalNumber() {
		return postalNumber;
	}

	public void setPostalNumber(String postalNumber) {
		this.postalNumber = postalNumber;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne
	@JoinColumn(name = "bank_id")
	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	@Column(name = "bank_account", nullable = false)
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Column(name = "admin_name", nullable = false)
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Column(name = "admin_email")
	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	@Column(name = "admin_phone")
	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "modified_date", nullable = true)
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the organisationHmoPackages
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "hmo_id")
	public Set<EhmoPackages> getOrganisationHmoPackages() {
		return organisationHmoPackages;
	}

	/**
	 * @param organisationHmoPackage
	 *            the organisationHmoPackage to set
	 */
	public void setOrganisationHmoPackages(
			Set<EhmoPackages> organisationHmoPackages) {
		this.organisationHmoPackages = organisationHmoPackages;
	}

}
