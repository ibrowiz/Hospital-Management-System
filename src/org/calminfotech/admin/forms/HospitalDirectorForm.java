package org.calminfotech.admin.forms;

import org.springframework.web.multipart.MultipartFile;


public class HospitalDirectorForm {
	
	
	private Integer directorId;	
	
	private String directorLastName;	
	
	private String directorFirstName;
	
	private String directorEmail;
	
	private String directorPhone;	
	
	private MultipartFile directorAvatar;
	
	private String avatarFile;
	
	private String avatarContentType;
	
	private Integer organisationId;

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

	public MultipartFile getDirectorAvatar() {
		return directorAvatar;
	}

	public void setDirectorAvatar(MultipartFile directorAvatar) {
		this.directorAvatar = directorAvatar;
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

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

}
