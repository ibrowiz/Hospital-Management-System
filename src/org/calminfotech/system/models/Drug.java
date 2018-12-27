package org.calminfotech.system.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "drugs")
@SQLDelete(sql = "UPDATE drugs SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class Drug {

	private Integer id;
	private String name;
	private String description;

	/**
	 * Other Drugs descriptions should be included here
	 */

	private Date createdDate;
	private Date modifiedDate;

	private DrugClassification drugClassification;

	private Set<OrganisationDrug> drugOrganisations = new HashSet<OrganisationDrug>();

	private Set<HmoPackageDrug> drugHmoPackages = new HashSet<HmoPackageDrug>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "created_date")
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

	@ManyToOne
	@JoinColumn(name = "classification_id")
	public DrugClassification getDrugClassification() {
		return drugClassification;
	}

	public void setDrugClassification(DrugClassification drugClassification) {
		this.drugClassification = drugClassification;
	}

	@OneToMany(mappedBy = "pk.drug", fetch = FetchType.LAZY)
	public Set<OrganisationDrug> getDrugOrganisations() {
		return drugOrganisations;
	}

	public void setDrugOrganisations(Set<OrganisationDrug> drugOrganisations) {
		this.drugOrganisations = drugOrganisations;
	}

	/**
	 * @return the drugHmoPackages
	 */
	@OneToMany(mappedBy = "pk.drug", fetch = FetchType.LAZY)
	public Set<HmoPackageDrug> getDrugHmoPackages() {
		return drugHmoPackages;
	}

	/**
	 * @param drugHmoPackages
	 *            the drugHmoPackages to set
	 */
	public void setDrugHmoPackages(Set<HmoPackageDrug> drugHmoPackages) {
		this.drugHmoPackages = drugHmoPackages;
	}

}
