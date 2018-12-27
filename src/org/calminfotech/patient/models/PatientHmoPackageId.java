package org.calminfotech.patient.models;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.calminfotech.system.models.Hmo;
import org.calminfotech.system.models.EhmoPackages;
import org.calminfotech.patient.models.Patient;

@Embeddable
public class PatientHmoPackageId implements java.io.Serializable {

	private Patient patient;
	private EhmoPackages hmoPackage;
	private Hmo hmo;

	/**
	 * @return the patient
	 */
	@ManyToOne
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient
	 *            the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

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

	// hmo
	@ManyToOne
	public Hmo getHmo() {
		return hmo;
	}

	public void setHmo(Hmo hmo) {
		this.hmo = hmo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hmo == null) ? 0 : hmo.hashCode());
		result = prime * result
				+ ((hmoPackage == null) ? 0 : hmoPackage.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PatientHmoPackageId)) {
			return false;
		}
		PatientHmoPackageId other = (PatientHmoPackageId) obj;
		if (hmo == null) {
			if (other.hmo != null) {
				return false;
			}
		} else if (!hmo.equals(other.hmo)) {
			return false;
		}
		if (hmoPackage == null) {
			if (other.hmoPackage != null) {
				return false;
			}
		} else if (!hmoPackage.equals(other.hmoPackage)) {
			return false;
		}
		if (patient == null) {
			if (other.patient != null) {
				return false;
			}
		} else if (!patient.equals(other.patient)) {
			return false;
		}
		return true;
	}
}
