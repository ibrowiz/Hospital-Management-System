package org.calminfotech.system.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "ailments")
@SQLDelete(sql = "UPDATE ailments SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class Ailment {

	private Integer id;
	private String name;
	private String description;
	private Date createDate;
	private String createdBy;
	private Date modifyDate;
	private String modifiedBy;

	private AilmentClassification ailmentClassification;

	private Set<HmoPackageTreatment> ailmentPackages = new HashSet<HmoPackageTreatment>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aliment_id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "create_date", nullable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "created_by", nullable = true)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "modify_date", nullable = true)
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "modified_by", nullable = true)
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@ManyToOne
	@JoinColumn(name = "classification_id")
	public AilmentClassification getAilmentClassification() {
		return ailmentClassification;
	}

	public void setAilmentClassification(
			AilmentClassification ailmentClassification) {
		this.ailmentClassification = ailmentClassification;
	}

	/**
	 * @return the ailmentPackages
	 */
	@OneToMany(mappedBy = "pk.ailment")
	public Set<HmoPackageTreatment> getAilmentPackages() {
		return ailmentPackages;
	}

	/**
	 * @param ailmentPackages
	 *            the ailmentPackages to set
	 */
	public void setAilmentPackages(Set<HmoPackageTreatment> ailmentPackages) {
		this.ailmentPackages = ailmentPackages;
	}

}
