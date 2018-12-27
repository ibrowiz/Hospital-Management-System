package org.calminfotech.system.models;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class HmoPackageTreatmentId implements java.io.Serializable {

	private EhmoPackages hmoPackage;
	private Ailment ailment;

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
	 * @return the ailment
	 */
	@ManyToOne
	public Ailment getAilment() {
		return ailment;
	}

	/**
	 * @param ailment
	 *            the ailment to set
	 */
	public void setAilment(Ailment ailment) {
		this.ailment = ailment;
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
		result = 31 * result + (ailment != null ? ailment.hashCode() : 0);

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

		HmoPackageTreatmentId that = (HmoPackageTreatmentId) instance;

		if (hmoPackage != null ? !hmoPackage.equals(that.hmoPackage)
				: that.hmoPackage != null)
			return false;

		if (ailment != null ? !ailment.equals(that.ailment)
				: that.ailment != null)
			return false;

		return true;
	}

}