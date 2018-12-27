package org.calminfotech.user.forms;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class UserForm {

	private int userId;

	@NotEmpty(message = "email field cannot be empty!")
	private String email;

	@NotEmpty(message = "password field cannot be empty")
	private String password;
	
	private String clock_in_time;
	private String clock_out_time;
	
	
	private Integer loginSectionPointId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getClock_in_time() {
		return clock_in_time;
	}

	public void setClock_in_time(String clock_in_time) {
		this.clock_in_time = clock_in_time;
	}

	public String getClock_out_time() {
		return clock_out_time;
	}

	public void setClock_out_time(String clock_out_time) {
		this.clock_out_time = clock_out_time;
	}

	public Integer getLoginSectionPointId() {
		return loginSectionPointId;
	}

	public void setLoginSectionPointId(Integer loginSectionPointId) {
		this.loginSectionPointId = loginSectionPointId;
	}
}
