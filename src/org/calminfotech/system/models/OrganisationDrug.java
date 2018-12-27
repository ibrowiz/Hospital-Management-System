package org.calminfotech.system.models;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.calminfotech.utils.models.Organisation;

@Entity
@Table(name = "organisations_drugs")
@org.hibernate.annotations.Entity(dynamicInsert = true)
@AssociationOverrides({
		@AssociationOverride(name = "pk.organisation", joinColumns = @JoinColumn(name = "organisation_id")),
		@AssociationOverride(name = "pk.drug", joinColumns = @JoinColumn(name = "drug_id")) })
public class OrganisationDrug {

	private OrganisationDrugId pk = new OrganisationDrugId();
	private String price;
	private Date createdDate;
	private String createdBy;

	@EmbeddedId
	public OrganisationDrugId getPk() {
		return pk;
	}

	public void setPk(OrganisationDrugId pk) {
		this.pk = pk;
	}

	@Transient
	public Organisation getOrganisation() {
		return this.getPk().getOrganisation();
	}

	public void setOrganisation(Organisation organisation) {
		this.getPk().setOrganisation(organisation);
	}

	@Transient
	public Drug getDrug() {
		return this.getPk().getDrug();
	}

	public void setDrug(Drug drug) {
		this.getPk().setDrug(drug);
	}

	@Column(name = "price")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean equals(Object instance) {
		if (this == instance)
			return true;

		if (instance == null || getClass() != instance.getClass())
			return false;

		OrganisationDrug that = (OrganisationDrug) instance;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}

}
