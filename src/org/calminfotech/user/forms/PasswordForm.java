package org.calminfotech.user.forms;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class PasswordForm {

	@NotBlank(message = "Field cannot be empty!")
	@Size(min = 6, message = "Password cannot be less than 6 characters")
	private String password;
	
	@NotBlank(message = "Field cannot be empty")
	private String formerPassword;
	
	@NotBlank(message = "Field cannot be empty")
	private String confirmPassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFormerPassword() {
		return formerPassword;
	}

	public void setFormerPassword(String formerPassword) {
		this.formerPassword = formerPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
