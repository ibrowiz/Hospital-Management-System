package org.calminfotech.system.forms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class LaboratoryTestForm {
	
	private Integer id;
	
	private Integer catId;

	private String name;
	
	private String minimumValue;
	
	private String maximumValue;
	
	private String  labMeasure;
	
	private String description;

	private String createdBy;

	private Date createdDate;
	
	private String modifiedBy;
	
	private Date LastModifiedDate;
	
	private String status;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCatId() {
		return catId;
	}


	public void setCatId(Integer catId) {
		this.catId = catId;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public Date getLastModifiedDate() {
		return LastModifiedDate;
	}


	public void setLastModifiedDate(Date lastModifiedDate) {
		LastModifiedDate = lastModifiedDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMinimumValue() {
		return minimumValue;
	}


	public void setMinimumValue(String minimumValue) {
		this.minimumValue = minimumValue;
	}


	public String getMaximumValue() {
		return maximumValue;
	}


	public void setMaximumValue(String maximumValue) {
		this.maximumValue = maximumValue;
	}


	public String getLabMeasure() {
		return labMeasure;
	}


	public void setLabMeasure(String labMeasure) {
		this.labMeasure = labMeasure;
	}

}
