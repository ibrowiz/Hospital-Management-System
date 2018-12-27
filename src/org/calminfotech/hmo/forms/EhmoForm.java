package org.calminfotech.hmo.forms;

//import org.hibernate.validator.constraints.NotBlank;
// org.hibernate.validator.constraints.Range;

public class EhmoForm {

	private Integer hmoId;
	private Integer organisationId;
	private String name;
	private String address;
	//private String state;
	//private String lga;
	private String phoneNumber;
	private String postalNumber;
	private String email;
	//private String bank;
//	private String bankAccount;
	private String adminName;
	private String adminEmail;
	private String adminPhone;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/*public String getLga() {
		return lga;
	}

	public void setLga(String lga) {
		this.lga = lga;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}*/

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalNumber() {
		return postalNumber;
	}

	public void setPostalNumber(String postalNumber) {
		this.postalNumber = postalNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}*/

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

}
