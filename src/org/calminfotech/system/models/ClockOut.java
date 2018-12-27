package org.calminfotech.system.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.user.models.UserClockOut;
import org.calminfotech.utils.models.Organisation;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "clocking")
public class ClockOut {
	
	private Integer id;
	private String username;

	private Date clock_out_time;
	private LoginSection loginSection;
	private LoginSectionPoint loginSectionPoint;
	
	
	private String clocking_status;
	private UserClockOut user;
	
	private Date created_date;
	private Organisation organisation;
	
	
	public enum pclocking_status {
		Clockin, Clockout
	};
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clocking_id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "clock_out_time")
	public Date getClock_out_time() {
		return clock_out_time;
	}
	public void setClock_out_time(Date date) {
		this.clock_out_time = date;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "login_section_id")
	public LoginSection getLoginSection() {
		return loginSection;
	}
	public void setLoginSection(LoginSection loginSection) {
		this.loginSection = loginSection;
	}
	
	@ManyToOne
	@JoinColumn(name = "login_point_id")
	public LoginSectionPoint getLoginSectionPoint() {
		return loginSectionPoint;
	}
	public void setLoginSectionPoint(LoginSectionPoint loginSectionPoint) {
		this.loginSectionPoint = loginSectionPoint;
	}
	
	
	@Column(name = "clocking_status")
	public String getClocking_status() {
		return clocking_status;
	}
	public void setClocking_status(String clocking_status) {
		this.clocking_status = clocking_status;
	}
	
	@ManyToOne
	@JoinColumn(name = "user_login_id")
	public UserClockOut getUser() {
		return user;
	}
	public void setUser(UserClockOut user2) {
		this.user = user2;
	}
	
	
	@Column(name = "created_date")
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	@ManyToOne
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return organisation;
	}
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	
	
	
	

}
