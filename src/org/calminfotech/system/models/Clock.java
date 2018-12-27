package org.calminfotech.system.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "clock")
public class Clock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "section_id")
	private int sectionId;	
	
	@Column(name = "department_id")
	private int departmentId;
	
	@Column(name = "unit_id")
	private int unitId;
	
	@Column(name = "point_id")
	private int pointId;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "clock_time")
	private Date clockTime;
	
	@Column(name = "clock_status")
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

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
