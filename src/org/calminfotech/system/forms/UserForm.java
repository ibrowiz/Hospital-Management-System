package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class UserForm {

	private Integer userId;

	@NotBlank(message = "Field cannot be empty!")
	@Email(message = "Invalid email format!")
	private String email;

	@Range(min = 1, message = "Please select a title")
	private Integer titleId;

	@NotBlank(message = "Field cannot be empty!")
	private String lastName;

	@NotBlank(message = "Field cannot be empty!")
	private String otherNames;
	
	@Range(min = 1, message = "Please select a role")
	private Integer userRole;
	
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(String otherNames) {
		this.otherNames = otherNames;
	}

	public Integer getUserRole() {
		return userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

}
