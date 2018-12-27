package org.calminfotech.admin.models;

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
@Table(name = "hospital_director")
public class HospitalDirector {
	
	@Id
	@GeneratedValue
	@Column(name = "director_id")
	private Integer directorId;
	
	@Column(name = "director_lastName")
	private String directorLastName;
	
	@Column(name = "director_firstName")
	private String directorFirstName;
	
	@Column(name = "director_email")
	private String directorEmail;
	
	@Column(name = "director_phone")
	private String directorPhone;	
	
	@Column(name = "director_avatar")
	private Blob directorAvatar;
	
	@ManyToOne
	@JoinColumn(name = "id")
	private Organisation organisation;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "image_file", insertable = false, updatable = false)
	private String avatarFile;
	
	@Column(name = "image_contentType")
	private String avatarContentType;

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

	public String getDirectorEmail() {
		return directorEmail;
	}

	public void setDirectorEmail(String directorEmail) {
		this.directorEmail = directorEmail;
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

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getAvatarFile() {
		return avatarFile;
	}

	public void setAvatarFile(String avatarFile) {
		this.avatarFile = avatarFile;
	}

	public String getAvatarContentType() {
		return avatarContentType;
	}

	public void setAvatarContentType(String avatarContentType) {
		this.avatarContentType = avatarContentType;
	}
	

}
