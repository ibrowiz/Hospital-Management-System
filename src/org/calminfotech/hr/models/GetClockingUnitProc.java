package org.calminfotech.hr.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(name = "spGetClockingUnitProc",
query = "{CALL sp_user_clocking(:userid)}", 
callable = true, 
resultClass = GetClockingUnitProc.class)
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hr_unit_category")
public class GetClockingUnitProc implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unit_id", unique = true, nullable = false)
	private Integer unitId;

	@Column(name = "user_id", nullable = true)
	private Integer userId;
	
	@Column(name = "name")
	private String unitName;
	
	@Column(name = "clock_in_time", nullable = true)
	private Date clockingTime;
	//getter and setter

	@Column(name = "status", nullable = true)
	private boolean checkStatus;
	
	@Column(name = "clock_out_time", nullable = true)
	private Date clockOutTime;
	
	@Column(name = "organisation_id")
	private Integer organisationId;

	
	public Date getClockingTime() {
		return clockingTime;
	}

	public void setClockingTime(Date clockingTime) {
		this.clockingTime = clockingTime;
	}

	public boolean isCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(boolean checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Date getClockOutTime() {
		return clockOutTime;
	}

	public void setClockOutTime(Date clockOutTime) {
		this.clockOutTime = clockOutTime;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	
	
	

	

}
