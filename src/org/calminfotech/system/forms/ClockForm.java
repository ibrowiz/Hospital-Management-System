package org.calminfotech.system.forms;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ClockForm {
	
	private Integer id;

	private int sectionId;	
	
	private int departmentId;
	
	private int unitId;
	
	private int pointId;
	
	private int userId;
	
	private Date clockTime;
	
	private String clockStatus;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getClockTime() {
		return clockTime;
	}

	public void setClockTime(Date clockTime) {
		this.clockTime = clockTime;
	}

	public String getClockStatus() {
		return clockStatus;
	}

	public void setClockStatus(String clockStatus) {
		this.clockStatus = clockStatus;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	

}
