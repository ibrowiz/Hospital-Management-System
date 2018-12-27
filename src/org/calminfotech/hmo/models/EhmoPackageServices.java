package org.calminfotech.hmo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.calminfotech.system.models.EhmoPackages;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "ehmo_packageservices")
@SQLDelete(sql = "UPDATE ehmo_packageservices SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_delete <> 1")
public class EhmoPackageServices {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "hmo_package_id")
	private EhmoPackages ehmoPackages;
	
	@Column(name = "global_service_id")
	private Integer globalServiceId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private Double price;

	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "modified_by")
	private Integer modifiedBy;

	@Column(name = "modify_date")
	private Date modifiedDate;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@Column(name = "organisation_id")
	private int organisationId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGlobalServiceId() {
		return globalServiceId;
	}

	public void setGlobalServiceId(Integer globalServiceId) {
		this.globalServiceId = globalServiceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}

	public EhmoPackages getEhmoPackages() {
		return ehmoPackages;
	}

	public void setEhmoPackages(EhmoPackages ehmoPackages) {
		this.ehmoPackages = ehmoPackages;
	}

	
	
}
