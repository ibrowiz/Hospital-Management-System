package org.calminfotech.system.models;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.utils.models.Organisation;



@Entity
@Table(name = "organisation_director")
public class OrganisationDirector {
	
	@Id
	@GeneratedValue
	@Column(name = "directorId")
	private Integer directorId;
	
	@Column(name = "director_lastName")
	private String directorLastName;
	
	@Column(name = "director_firstName")
	private String directorFirstName;
	
	@Column(name = "director_otherName")
	private String directorOtherName;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "directorEmail")
	private String email;
	
	@Column(name = "directorPhone")
	private String directorPhone;
	
	@Column(name = "directorAvatar")
	private Blob directorAvatar;
	
	@Column(name = "avatar_contentType")
	private String contentType;
	
	@ManyToOne
	@JoinColumn(name = "organisationId")
	private Organisation organisation;
	
	//Getters and Setters		

	public Integer getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Integer directorId) {
		this.directorId = directorId;
	}

	public String getDirectorLastName() {
		return directorLastName;
	}

	public void setDirectorLastName(String directorLastName) {
		this.directorLastName = directorLastName;
	}

	public String getDirectorFirstName() {
		return directorFirstName;
	}

	public void setDirectorFirstName(String directorFirstName) {
		this.directorFirstName = directorFirstName;
	}

	public String getDirectorOtherName() {
		return directorOtherName;
	}

	public void setDirectorOtherName(String directorOtherName) {
		this.directorOtherName = directorOtherName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDirectorPhone() {
		return directorPhone;
	}

	public void setDirectorPhone(String directorPhone) {
		this.directorPhone = directorPhone;
	}

	public Blob getDirectorAvatar() {
		return directorAvatar;
	}

	public void setDirectorAvatar(Blob directorAvatar) {
		this.directorAvatar = directorAvatar;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
