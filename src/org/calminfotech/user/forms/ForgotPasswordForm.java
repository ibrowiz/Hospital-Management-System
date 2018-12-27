package org.calminfotech.user.forms;

import org.hibernate.validator.constraints.NotBlank;

public class ForgotPasswordForm {

	@NotBlank(message = "Field cannot be empty!")
	public String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
