package org.calminfotech.hr.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.patient.models.Patient;
import org.calminfotech.system.models.UserAssgnment;
import org.calminfotech.user.models.User;
import org.hibernate.annotations.NamedNativeQuery;


@Entity
@NamedNativeQuery(
	name = "spGetDeleteClockingCheckedVal",
	query = "{CALL sp_delete_clocking_checked_values(:userid)}",
	callable = true,
	resultClass = Clockin.class
)
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hr_clocking")
public class Clockin implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "unit_id")
	private int unitId;
	
	@Column(name = "user_id")
	private Integer userId;
		
	@Column(name = "email")
	private String userName;
	
	@Column(name = "clock_in_time")
	private Date clockInTime;
	
	@Column(name = "clock_out_time")
	private Date clockOutTime;
	
	@Column(name = "organisation_id")
	private int organisationId ;
	
	@Column(name = "expected_clock_out_time")
	private Date expClockOutTime ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public Date getClockInTime() {
		return clockInTime;
	}

	public void setClockInTime(Date clockInTime) {
		this.clockInTime = clockInTime;
	}

	public Date getClockOutTime() {
		return clockOutTime;
	}

	public void setClockOutTime(Date clockOutTime) {
		this.clockOutTime = clockOutTime;
	}

	public int getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getExpClockOutTime() {
		return expClockOutTime;
	}

	public void setExpClockOutTime(Date expClockOutTime) {
		this.expClockOutTime = expClockOutTime;
	}

}
