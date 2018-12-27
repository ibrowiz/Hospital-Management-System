package org.calminfotech.system.models;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class HmoPackageDrugId implements java.io.Serializable {

	private EhmoPackages hmoPackage;
	private Drug drug;

	/**
	 * @return the hmoPackage
	 */
	@ManyToOne
	public EhmoPackages getHmoPackage() {
		return hmoPackage;
	}

	/**
	 * @param hmoPackage
	 *            the hmoPackage to set
	 */
	public void setHmoPackage(EhmoPackages hmoPackage) {
		this.hmoPackage = hmoPackage;
	}

	/**
	 * @return the drug
	 */
	@ManyToOne
	public Drug getDrug() {
		return drug;
	}

	/**
	 * @param drug
	 *            the drug to set
	 */
	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {

		int result;

		// Need more research
		result = (hmoPackage != null ? hmoPackage.hashCode() : 0);
		result = 31 * result + (drug != null ? drug.hashCode() : 0);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	public boolean equals(Object instance) {
		if (this == instance)
			return true;

		if (instance == null || getClass() != instance.getClass())
			return false;

		HmoPackageDrugId that = (HmoPackageDrugId) instance;

		if (hmoPackage != null ? !hmoPackage.equals(that.hmoPackage)
				: that.hmoPackage != null)
			return false;

		if (drug != null ? !drug.equals(that.drug) : that.drug != null)
			return false;

		return true;
	}

}