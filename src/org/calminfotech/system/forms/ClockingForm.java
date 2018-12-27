package org.calminfotech.system.forms;

import org.hibernate.validator.constraints.NotBlank;



public class ClockingForm {
	
	
	private Integer id;
	
	private String username;
	
	private Integer UserloginId;
	

	/*private String clock_in_time;*/
	
	@NotBlank(message = "Field cannot be empty!")
	private String clock_out_time;
	
	private Integer loginSectionId;
	
	private Integer loginSectionPointId;
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getClock_out_time() {
		return clock_out_time;
	}
	public void setClock_out_time(String clock_out_time) {
		this.clock_out_time = clock_out_time;
	}
	
	
	public Integer getLoginSectionId() {
		return loginSectionId;
	}
	public void setLoginSectionId(Integer loginSectionId) {
		this.loginSectionId = loginSectionId;
	}
	
	
	public Integer getLoginSectionPointId() {
		return loginSectionPointId;
	}
	public void setLoginSectionPointId(Integer loginSectionPointId) {
		this.loginSectionPointId = loginSectionPointId;
	}
	public Integer getUserloginId() {
		return UserloginId;
	}
	public void setUserloginId(Integer userloginId) {
		UserloginId = userloginId;
	}
/*	public String getClock_in_time() {
		return clock_in_time;
	}
	public void setClock_in_time(String clock_in_time) {
		this.clock_in_time = clock_in_time;
	}*/
	
	
	
	
	
	
	
	

}
