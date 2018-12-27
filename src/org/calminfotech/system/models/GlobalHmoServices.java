package org.calminfotech.system.models;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "global_hmo_service")
@SQLDelete(sql = "UPDATE global_hmo_service SET is_deleted = 1 WHERE hmo_service_id = ?")
//@SQLInsert(sql="")
@Where(clause = "is_deleted <> 1")

public class GlobalHmoServices {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hmo_service_id")
	private Integer hmoServiceId;

	@Column(name = "service_name")
	private String serviceName;
	
	@Column(name = "description")
	private String description;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "modified_by")
	private Integer modifiedBy;
	
	@Column(name = "organisation_id")
	private Integer organisationId;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;

	public Integer getHmoServiceId() {
		return hmoServiceId;
	}

	public void setHmoServiceId(Integer hmoServiceId) {
		this.hmoServiceId = hmoServiceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
