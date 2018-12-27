package org.calminfotech.admin.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class OrganisationForm {

	private Integer id;

	@NotBlank(message = "Field cannot be empty!")
	private String name;
	private String description;

	@NotBlank(message = "Field cannot be empty!")
	private String address;
	
	private String establishedYear;	
	
	private String hospitalType;
	
	private Integer stateId;
	
	private Integer lgaId;
	
	private String areaOfSpecialisation;

	@NotBlank(message = "Field cannot be empty!")
	@Email(message = "Invalid email format!")
	private String systemEmail;

	@NotBlank(message = "Field cannot be empty!")
	@Email(message = "Invalid email format!")
	private String systemUserEmail;

	@NotBlank(message = "Password is required!")
	private String systemUserPassword;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
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

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getLgaId() {
		return lgaId;
	}

	public void setLgaId(Integer lgaId) {
		this.lgaId = lgaId;
	}

	public String getSystemEmail() {
		return systemEmail;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}

	public String getSystemUserEmail() {
		return systemUserEmail;
	}

	public void setSystemUserEmail(String systemUserEmail) {
		this.systemUserEmail = systemUserEmail;
	}

	public String getSystemUserPassword() {
		return systemUserPassword;
	}

	public void setSystemUserPassword(String systemUserPassword) {
		this.systemUserPassword = systemUserPassword;
	}

}
