package org.calminfotech.system.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "hmo_packages_ailments")
@AssociationOverrides({
		@AssociationOverride(name = "pk.hmoPackage", joinColumns = @JoinColumn(name = "hmo_package_id")),
		@AssociationOverride(name = "pk.ailment", joinColumns = @JoinColumn(name = "ailment_id")) })
public class HmoPackageTreatment {

	private HmoPackageTreatmentId pk = new HmoPackageTreatmentId();

	private BigDecimal price;
	private String createdBy;
	private Date createdDate;

	/**
	 * @return the pk
	 */
	@EmbeddedId
	public HmoPackageTreatmentId getPk() {
		return pk;
	}

	/**
	 * @param pk
	 *            the pk to set
	 */
	public void setPk(HmoPackageTreatmentId pk) {
		this.pk = pk;
	}

	/**
	 * @return the hmoPackage
	 */
	@Transient
	public EhmoPackages getHmoPackage() {
		return this.getPk().getHmoPackage();
	}

	/**
	 * @param hmoPackage
	 *            the hmoPackage to set
	 */
	public void setHmoPackage(EhmoPackages hmoPackage) {
		this.getPk().setHmoPackage(hmoPackage);
	}

	/**
	 * @return the ailment
	 */
	@Transient
	public Ailment getAilment() {
		return this.getPk().getAilment();
	}

	/**
	 * @param ailment
	 *            the ailment to set
	 */
	public void setAilment(Ailment ailment) {
		this.getPk().setAilment(ailment);
	}

	/**
	 * @return the price
	 */
	@Column(name = "price")
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the createdBy
	 */
	@Column(name = "created_by")
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
	@Column(name = "created_date")
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
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

		HmoPackageTreatment that = (HmoPackageTreatment) instance;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

}
