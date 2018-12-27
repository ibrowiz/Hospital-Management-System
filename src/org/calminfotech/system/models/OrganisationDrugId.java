package org.calminfotech.system.models;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.calminfotech.utils.models.Organisation;

@Embeddable
public class OrganisationDrugId implements java.io.Serializable {

	private Organisation organisation;
	private Drug drug;

	@ManyToOne
	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	@ManyToOne
	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public int hashCode() {

		int result;

		// Need more research
		result = (organisation != null ? organisation.hashCode() : 0);
		result = 31 * result + (drug != null ? drug.hashCode() : 0);

		return result;
	}

	public boolean equals(Object instance) {
		if (this == instance)
			return true;

		if (instance == null || getClass() != instance.getClass())
			return false;

		OrganisationDrugId that = (OrganisationDrugId) instance;

		if (organisation != null ? !organisation.equals(that.organisation)
				: that.organisation != null)
			return false;

		if (drug != null ? !drug.equals(that.drug) : that.drug != null)
			return false;

		return true;
	}
}
