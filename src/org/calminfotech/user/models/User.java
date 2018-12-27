package org.calminfotech.user.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
//import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


import org.calminfotech.hr.models.Clockin;
import org.calminfotech.system.models.ClockOut;
import org.calminfotech.system.models.Clocking;

import org.calminfotech.system.models.HrUserDepartment;
import org.calminfotech.system.models.HrUserSection;
/*import org.calminfotech.system.models.HrUserSection;*/
import org.calminfotech.system.models.LoginSection;
//import org.calminfotech.system.models.LoginSectionPoint;
//import org.calminfotech.system.models.Unit;
import org.calminfotech.system.models.VisitWorkflowPoint;
import org.calminfotech.utils.models.Organisation;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	
	private String username;
	private String password;
	private boolean activation;
	private String createdBy;
	private Date createdDate;
	private Date modifiedDate;
	private boolean status;
	private boolean lock;
	private Role role;
	private Organisation organisation;
	private UserType userType;
	private Set<UserActivation> userActivation = new HashSet<UserActivation>();
	private Set<HrUserSection> hrUserSection = new HashSet<HrUserSection>();
	private Set<HrUserDepartment> hrUserDepartment = new HashSet<HrUserDepartment>();
	private UserProfile userProfile;
	private Date clock_in_time;
	private String clock_out_time;
	private LoginSection loginSection;
	//private LoginSectionPoint loginSectionPoint;
	private String clocking_status;
	//private Department department;
	//private Unit unit;
	
	
	private Set<Clocking> clocking = new HashSet<Clocking>();
	private Set<ClockOut> clockout = new HashSet<ClockOut>();
	

	private Set<VisitWorkflowPoint> workflowPoints = new HashSet<VisitWorkflowPoint>();

	private Set<Permission> userPermission = new HashSet<Permission>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "email", nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String email) {
		this.username = email;
	}
	
	

	@Column(name = "staff_id", nullable = false)
	private String staffId;
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "password", nullable = true)
	public String getPassword() {
		return password;
	}
	
	
	
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="userid")
	
	
	public Set<HrUserSection> getHrUserSection() {
		return hrUserSection;
	}

	public void setHrUserSection(Set<HrUserSection> hrUserSection) {
		this.hrUserSection = hrUserSection;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "activation", nullable = true)
	public boolean getActivation() {
		return activation;
	}

	public void setActivation(boolean activation) {
		this.activation = activation;
	}

	@Column(name = "created_by", nullable = false)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_date", nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "modified_date", nullable = true)
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "status", nullable = true)
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Column(name = "lock", nullable = true)
	public boolean getLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	@ManyToOne
	@JoinColumn(name = "role_id")
	@JsonIgnore
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Column(name = "clock_in_time")
	public Date getClock_in_time() {
		return clock_in_time;
	}

	public void setClock_in_time(Date clock_in_time) {
		this.clock_in_time = clock_in_time;
	}

	@Column(name = "clock_out_time", nullable = false)
	public String getClock_out_time() {
		return clock_out_time;
	}

	public void setClock_out_time(String clock_out_time) {
		this.clock_out_time = clock_out_time;
	}

	@ManyToOne
	@JoinColumn(name = "clocking_section_id")
	public LoginSection getLoginSection() {
		return loginSection;
	}

	public void setLoginSection(LoginSection loginSection) {
		this.loginSection = loginSection;
	}
	
	/*@ManyToOne
	@JoinColumn(name = "clocking_department_id")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}*/
	
	/*@ManyToOne
	@JoinColumn(name = "clocking_unit_id")
	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}*/

	/*@ManyToOne
	@JoinColumn(name = "clocking_point_id")
	public LoginSectionPoint getLoginSectionPoint() {
		return loginSectionPoint;
	}

	public void setLoginSectionPoint(LoginSectionPoint loginSectionPoint) {
		this.loginSectionPoint = loginSectionPoint;
	}*/

	@OneToMany
	@JoinColumn(name = "user_login_id")
	public Set<Clocking> getClocking() {
		return clocking;
	}

	public void setClocking(Set<Clocking> clocking) {
		this.clocking = clocking;
	}

	@OneToMany
	@JoinColumn(name = "user_login_id")
	public Set<ClockOut> getClockout() {
		return clockout;
	}

	public void setClockout(Set<ClockOut> clockout) {
		this.clockout = clockout;
	}
	
	@Column(name = "clocking_status")
	public String getClocking_status() {
		return clocking_status;
	}

	public void setClocking_status(String clocking_status) {
		this.clocking_status = clocking_status;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	public Set<UserActivation> getUserActivation() {
		return userActivation;
	}

	public void setUserActivation(Set<UserActivation> userActivation) {
		this.userActivation = userActivation;
	}

	@ManyToOne
	@JoinColumn(name = "organisation_id")
	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	@ManyToOne
	@JoinColumn(name = "user_type_id")
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@ManyToMany
	@JoinTable(name = "consultation_workflow_points_users", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "point_id") })
	public Set<VisitWorkflowPoint> getWorkflowPoints() {
		return workflowPoints;
	}

	public void setWorkflowPoints(Set<VisitWorkflowPoint> workflowPoints) {
		this.workflowPoints = workflowPoints;
	}

	@ManyToMany(mappedBy = "user", fetch = FetchType.LAZY)
	public Set<Permission> getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(Set<Permission> userPermission) {
		this.userPermission = userPermission;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="userid")
	public Set<HrUserDepartment> getHrUserDepartment() {
		return hrUserDepartment;
	}

	public void setHrUserDepartment(Set<HrUserDepartment> hrUserDepartment) {
		this.hrUserDepartment = hrUserDepartment;
	}

	
	
	



	

	
}
