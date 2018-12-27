package org.calminfotech.user.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.calminfotech.system.models.LoginSection;
import org.calminfotech.user.models.Group;
import org.calminfotech.user.models.Permission;
import org.calminfotech.user.models.Role;
import org.calminfotech.user.models.UserProfile;
import org.calminfotech.utils.models.Organisation;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserIdentity {

	private int userId; 
	
	private String username;
	private Role role;
	private Role roleAssgn;
	private Permission userPermission;
	private Group group;
	private UserProfile userProfile;
	private Organisation organisation;
	//Assign User to Point
	private Integer sectionId;
	
	private LoginSection loginSection;
	
	
	
	
	
	private String sectionName;
	private Integer billId;
	private String billName;
	private Integer currentPointId;
	private String currentPointName;
	private Integer visitId;
	//global error handler
	private String errormessage;
	Exception exception;
	private Date timestamp;
	private StringBuffer url;
	//role assignment permission list
	private List<Integer> pList = new ArrayList<Integer>();
	//user level assignment permission list
	private List<Integer> uList = new ArrayList<Integer>();
	//declare link
	private boolean identity = false;

	public UserIdentity() {

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public Permission getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(Permission userPermission) {
		this.userPermission = userPermission;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
		// Set all the links available to the user
		//this.setLinks(group.getResources());
	}
	//fetch profile
	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public boolean hasIdentity() {
		return identity;
	}

	public void setIdentity(boolean identity) {
		this.identity = identity;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public Integer getCurrentPointId() {
		return currentPointId;
	}

	public void setCurrentPointId(Integer currentPointId) {
		this.currentPointId = currentPointId;
	}

	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	public String getCurrentPointName() {
		return currentPointName;
	}

	public void setCurrentPointName(String currentPointName) {
		this.currentPointName = currentPointName;
	}

	public List<Integer> getpList() {
		return pList;
	}

	public void setpList(Set<Permission> permission) {
		//clear previours content
		this.pList.clear();
		//Iterate thru all the link from 'user.getGroup().getResource()' into "Resource r"
		for (Permission r : permission) {
			//Add them to the 'this.link'. Now available for userIdentity
			this.pList.add(r.getPermissionId());
			
		}
	}
	
	public List<Integer> getuList() {
		return uList;
	}

	public void setuList(Set<Permission> permission) {
		//clear previous content
		this.uList.clear();
		//Iterate thru the user permission
		for(Permission u : permission){
			this.uList.add(u.getPermissionId());
		}
	}
	

	public boolean renderLink(Integer pId){
		//boolean render = this.pList.contains(pId);
		boolean render = (this.pList.contains(pId)|| this.uList.contains(pId));
		return render;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public StringBuffer getUrl() {
		return url;
	}

	public void setUrl(StringBuffer url) {
		this.url = url;
	}

	

	public LoginSection getLoginSection() {
		return loginSection;
	}

	public void setLoginSection(LoginSection loginSection) {
		this.loginSection = loginSection;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	

}
